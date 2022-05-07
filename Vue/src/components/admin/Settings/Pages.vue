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
          <v-toolbar-title>文章管理</v-toolbar-title>
          <v-divider
              class="mx-4"
              inset
              vertical
          ></v-divider>
          <v-spacer></v-spacer>
        <!-- 搜索-->
          <v-card-title>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="用户名查询"
                single-line
                hide-details
            ></v-text-field>
          </v-card-title>
          <!--删除按钮-->
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
  data: () => ({
    search: '',
    dialog: false,
    dialogDelete: false,
    headers: [
      {text: '用户名', align: 'start', sortable: false, value: 'username',},
      {text: '标题', value: 'articleTitle', sortable: false,filterable: false,},
      {text: '发布时间', value: 'articleCreate', sortable: false,filterable: false,},
      {text: 'Actions', value: 'actions', sortable: false},
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      username: '',
      articleTitle: '',
    },
    defaultItem: {
      username: '',
      articleTitle: '',
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
      axios.get('/api/article/list/articleList').then(resp=>{
        this.desserts = resp.data.data
      })
    },

    //编辑页面
    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.$router.push({ name: 'pagesEdit', params: { uid: item.articleId} });
    },


    //删除
    deleteItem(item) {
      this.deleteID = item.articleId

      this.dialogDelete = true
    },
    //成功后执行
    deleteItemConfirm() {
      axios.get('/api/article/list/deleteArticle',{
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

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    },
  }
}
</script>
