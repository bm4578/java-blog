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
                        md="10"
                    >
                      <v-text-field
                          v-model="editedItem.sysText"
                          label="公告"
                      ></v-text-field>
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
      {text: 'id', align: 'start', sortable: false, value: 'sysId',},
      {text: '公告', value: 'sysText', sortable: false, filterable: false},
      {text: '更新时间', value: 'sysUpdate', sortable: false, filterable: false,},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      sysId: '',
      sysUpdate: '',
      sysText:''
    },
    defaultItem: {
      sysId: '',
      sysUpdate: '',
      sysText:''
    },
    text:'',
    id:''
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
      axios.get('/api/SysAnnouncement').then(resp => {
        this.desserts = resp.data.data
      })
    },
    //编辑页面
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.text = item.sysText
      this.id = item.sysId
      this.dialog = true
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
      params.append("sysId", this.id)
      params.append("sysText",this.editedItem.sysText)
      axios.put('/api/SysAnnouncement',params).then(resp => {
        if (resp.data.data) {
          this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
          this.initialize()
        }else {
          this.$message.error('错了哦，修改失败');
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
