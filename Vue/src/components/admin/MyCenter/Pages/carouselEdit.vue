<template>
  <div class="app">
    <v-container class="grey lighten-5">
      <v-row>
        <v-col
            cols="12"
            md="12"
        >
          <br>
          <br>
          <div style="display: flex">
            <p style="margin-right: 20px;margin-top: 10px">缩略图*</p>

            <el-upload
                class="avatar-uploader"
                :action='this.urlImg'
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" width="100%" height="150px">
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
import axios from "axios";
import router from "@/router";

export default {
  name: "carouselEdit",
  data() {
    return {
      imageUrl: '',
      urlImg: '/file/files/oss',
      checked: true,
      user: []
    }
  },
  mounted() {
    //从后端获取数据
    this.imageUrl = this.$route.params.edit.sysUrl
  },
  methods: {
    //修改
    getEditorData() {
      const params = new URLSearchParams();
      params.append("sysId", this.$route.params.edit.sysId)
      params.append("sysUrl", this.imageUrl)
      axios.put('/api/getCarousel',params).then(resp=>{
        if (resp.data.data){
          this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
        }
      })
    },
    //oss服务
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
  }
}
</script>

<style scoped>
.app {
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

p {
  white-space: nowrap;
}
</style>
