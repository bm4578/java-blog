<template>
  <v-data-table
      :headers="headers"
      :items="desserts"
      sort-by="calories"
      class="elevation-1"
  >
    <template v-slot:top>
      <v-toolbar
          flat
      >
        <v-toolbar-title>所有用户</v-toolbar-title>
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
                      md="6"
                  >
                    <v-text-field
                        v-model="editedItem.username"
                        label="用户名修改"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>
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
    <template v-slot:item.isPermission="{ item }">
      <v-switch
          v-model="item.isPermission"
          label=""
          @click="Switch(item)"
      ></v-switch>
    </template>
<!--是否禁用账户-->
    <template v-slot:item.isEnabled="{ item }">
      <v-switch
          v-model="item.isEnabled"
          label=""
          @click = "disabled(item)"
      ></v-switch>
    </template>
<!--编辑与删除-->
    <template v-slot:item.actions="{ item }">
      <v-icon
          small
          class="mr-2"
          @click="editItem(item)"
          style="margin-left: 25px"
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
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      { text: '用户名', value: 'username',sortable: false},
      { text: '是否为用户', value: 'isPermission', sortable: false},
      { text: '是否禁用', value: 'isEnabled', sortable: false },
      { text: '注册时间', value: 'createTime', sortable: false },
      { text: '编辑用户名', value: 'actions', sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
      username: ''
    },
    defaultItem: {
      username: ''
    },
  }),
  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Item' : '编辑用户'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
  },

  created () {
    this.initialize()
  },

  methods: {
    //获取列表数据
    initialize () {
      axios.get("/api/user").then(resp=>{
        this.desserts = resp.data.data
      })
    },
    editItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },
    //是否为用户
    Switch(item){
      const params = new URLSearchParams();
      params.append("id", item.id)
      params.append("isPermission", item.isPermission)
      axios.put("/api/user",params).then(resp=>{
        if (resp.data.data) {
          this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
          //清除数据
          localStorage.clear();
          this.initialize()
        }else {
          this.$message.error('错了哦，修改失败');
        }
      })
    },
  //是否禁用账户
    disabled(item){
      const params = new URLSearchParams();
      params.append("id", item.id)
      params.append("isEnabled", item.isEnabled)
      axios.put("/api/user",params).then(resp=>{
        if (resp.data.data) {
          this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
          //清除数据
          this.initialize()
        }else {
          this.$message.error('错了哦，修改失败');
        }
      })
    },
    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    save () {
      const params = new URLSearchParams();
      params.append("id", this.editedItem.id)
      params.append("username", this.editedItem.username)
      axios.put("/api/user",params).then(resp=>{
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
  },
}
</script>

