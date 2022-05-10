<template>
  <div class="app">
    <v-container class="grey lighten-5">
      <!-- Stack the columns on mobile by making one full-width and the other half-width -->
      <v-row>
        <v-col
            cols="12"
            md="12"
        >
          <br>
          <br>
          <div style="display: flex">
          <p style="margin-right: 20px;margin-top: 10px">标题*</p>
            <v-text-field
                ref="name"
                v-model="name"
                label="请输入标题"
                outlined
            ></v-text-field>
          </div>
          <div style="display: flex">
          <p style="margin-right: 20px;margin-top: 10px">内容*</p>
          <div id="demo1"></div>
          </div>
          <div style="display: flex">
            <p style="margin-right: 20px;margin-top: 10px">缩略图*</p>
             <el-upload
              class="avatar-uploader"
              :action='this.urlImg'
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          </div>
          <br>
          <br>
            <el-button type="success" @click="getEditorData">立即发布</el-button>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// 引入 wangEditor
import wangEditor from 'wangeditor'
import axios from "axios";
import router from "@/router";
export default {
  name: "About",
  data() {
    return {
      editor: null,
      editorData: '',
      name:"",
      imageUrl: '',
      urlImg:'/file/files/oss',
      checked: true,
      user:[],
    }
  },
  mounted() {
    const editor = new wangEditor(`#demo1`)
    // 配置 onchange 回调函数，将数据同步到 vue 中
    editor.config.onchange = (newHtml) => {
      this.editorData = newHtml
    }
    //获取用户数据
    this.user = JSON.parse(window.localStorage.getItem("user"))
    // 创建编辑器
    editor.create()
    this.editor = editor
    if (this.checked){
      this.uid = 0
    }
  },
  methods: {
    getEditorData() {
      // 通过代码获取编辑器内容
      let data = this.editor.txt.html()
      //使用表单传递参数
      const params =  new URLSearchParams();
      params.append("uid",this.user.isArticle)
      params.append("articleTitle",this.name)
      params.append("articleList",data)
      params.append("articleImg",this.imageUrl)
      axios.post('/api/article/list/addArticle',params).then(resp=>{
        if (resp.data.data){
          this.$message({
            message: '恭喜你，文章添加成功',
            type: 'success'
          });
          router.push("/home")
        }
      })
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = res
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  },
  beforeDestroy() {
    // 调用销毁 API 对当前编辑器实例进行销毁
    this.editor.destroy()
    this.editor = null
  }
}
</script>

<style scoped>
.app{
  width: 100%;
  margin: 0 auto;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.router-link-active {
  color: #1976d2;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
p{
  white-space:nowrap;
}
</style>
