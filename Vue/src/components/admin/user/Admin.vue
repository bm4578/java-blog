<template>
  <div class="app">
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col order="12">
          <v-img
              height="350px"
              src="http://picture.moguit.cn//blog/admin/png/2021/12/4/1638581348162.png"
          >
            <div class="img" style="text-align: center;margin-top: 200px">
              <v-card-title class="white--text mt-8" style="display: block;">
                <v-avatar size="56">
                  <img
                      alt="user"
                      :src="user.isImg"
                  >
                </v-avatar>
                <p>
                  {{user.username}}
                </p>
              </v-card-title>
            </div>
          </v-img>
        </v-col>
      </v-row>
    </v-container>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col
            cols="20"
            md="4"
        >
          <v-card
              class="mx-auto"
              width="300"
          >
            <v-list>
              <v-list-group
                  :value="true"
                  prepend-icon="mdi-shape-outline"
              >
                <template v-slot:activator>
                  <v-list-item-title>文章管理</v-list-item-title>
                </template>
                <v-list-item
                    v-for="(link, i) in list_1"
                    :key="i"
                    v-bind="link"
                    link
                >
                  <router-link :to="link.menuSrc" tag="a" style="">{{ link.menuList }} </router-link>
                </v-list-item>

              </v-list-group>

              <v-list-group
                  :value="false"
                  prepend-icon="mdi-account-details"
              >
                <template v-slot:activator>
                  <v-list-item-title>个人中心</v-list-item-title>
                </template>

                <v-list-item
                    v-for="(link, i) in list_2"
                    :key="i"
                    v-bind="link"
                    link
                >
                  <router-link :to={name:link.menuSrc} tag="a" style="">{{ link.menuList }} </router-link>
                </v-list-item>
              </v-list-group>
            </v-list>
          </v-card>
        </v-col>
        <v-col
            cols="12"
            sm="6"
            md="8"
        >
          <v-card
              class="pa-2"
              outlined
              tile
          >
            <router-view></router-view>
          </v-card>
        </v-col>
      </v-row>
    </v-container>

  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "Admin",
  data(){
    return{
      user:[],
      list_1:[],
      list_2:[],
    }
  },
  mounted() {
    this.user = JSON.parse(window.localStorage.getItem("user"))
    axios.get("/api/list/menu/"+JSON.parse(window.localStorage.getItem("user")).isPermission+"/"+1).then(resp=>{
      this.list_1 = resp.data.data
    })
    axios.get("/api/list/menu/"+JSON.parse(window.localStorage.getItem("user")).isPermission+"/"+2).then(resp=>{
      this.list_2 = resp.data.data
    })
  }
}
</script>

<style scoped>
.app{
  width: 100%;
  margin: 0 auto;
}
/* 鼠标移动到链接的颜色 */
a{
  text-decoration: none;
  font-size: 1rem;
  align-content: center;
  line-height: 56px;
  color: black;
}
a:hover {
  color: #1976d2;
}
.router-link-active {
  color: #1976d2;
}

</style>
