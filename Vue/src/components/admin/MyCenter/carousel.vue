<template>
  <v-card>
    <v-data-table
        :search="search"
        :headers="headers"
        :items="desserts"
        sort-by="calories"
        class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar
            flat
        >
          <v-toolbar-title>轮播图</v-toolbar-title>
          <v-divider
              class="mx-4"
              inset
              vertical
          ></v-divider>
          <v-spacer></v-spacer>
          <v-dialog
              v-model="dialog"
              max-width="500px"
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                  color="primary"
                  dark
                  class="mb-2"
                  v-bind="attrs"
                  v-on="on"
              >
                添加
              </v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="text-h5">{{ formTitle }}</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col
                        cols="12"
                        sm="6"
                        md="4"
                    >
                      <div style="display: flex">
                        <p style="margin-top: 10px;margin-right: 40px;white-space:nowrap;">轮播图*</p>
                        <br>
                        <br><br>
                        <el-upload
                            class="avatar-uploader"
                            :action='urlImg'
                            :show-file-list="false"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload">
                          <img v-if="imageUrl" :src="imageUrl" width="350px" height="100px">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                      </div>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <!--按钮-->
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="close"
                >
                 取消
                </v-btn>
                <v-btn
                    color="blue darken-1"
                    text
                    @click="save"
                >
                  保存
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <!--删除-->
          <v-dialog v-model="dialogDelete" max-width="500px">
            <v-card>
              <v-card-title class="text-h5">您确定要删除吗?</v-card-title>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="closeDelete">取消</v-btn>
                <v-btn color="blue darken-1" text @click="deleteItemConfirm">确定</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>


      <template v-slot:item.actions="{ item }">
        <v-icon
            small
            class="mr-2"
            @click="editItem(item)"
        >
          mdi-pencil
        </v-icon>
        <v-icon
            small
            @click="deleteItem(item)"
        >
          mdi-delete
        </v-icon>
      </template>
      <template v-slot:no-data>
        <v-btn
            color="primary"
            @click="initialize"
        >
          Reset
        </v-btn>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  name: "carousel",
  data: () => ({
    imageUrl: '',
    checked: true,
    urlImg: '/file/files/oss',
    search: '',
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: '序列号', align: 'start', sortable: false, value: 'sysId',},
      {text: '更新时间', value: 'sysUpdate', sortable: false,filterable: false,},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      sysId: '',
      sysUpdate: '',
    },
    defaultItem: {
      sysId: '',
      sysUpdate: '',
    },
    deleteID:'',
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? '添加' : '编辑'
    }
  },

  watch: {
    dialog(val) {
      val || this.close()
    },
    dialogDelete(val) {
      val || this.closeDelete()
    },
  },

  created() {
    this.initialize()
  },
  methods: {
    //数据获取
    initialize() {
      axios.get('/api/getCarousel').then(resp=>{
        this.desserts = resp.data.data
      })
    },
    //编辑页面
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.$router.push({ name: 'carouselEdit', params: { edit: item} });
    },


    //删除
    deleteItem(item) {
      this.deleteID = item.sysId
      this.dialogDelete = true
    },
    //成功后执行
    deleteItemConfirm() {
      axios.delete('/api/getCarousel',{
        params:{
          id:this.deleteID
        }
      }).then(resp=>{
        if (resp.data.data){
          this.$message({
            message: '恭喜你，删除成功',
            type: 'success'
          });
          this.initialize()
        }else {
          this.$message.error('错了哦，删除失败');
        }
      })
      this.closeDelete()
    },

    close() {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete() {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    //保存
    save() {
      const params = new URLSearchParams();
      params.append("sysUrl", this.imageUrl)
     axios.post('/api/getCarousel',params).then(resp=>{
       if (resp.data.data){
         this.$message({
           message: '恭喜你，添加成功',
           type: 'success'
         });
         this.initialize()
       }
     })
      this.close()
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
