<template>

  <div class="app">
    <v-container class="grey lighten-5">
      <!-- Stack the columns on mobile by making one full-width and the other half-width -->
      <v-row>
        <v-col
            cols="12"
            md="12"
        >
          <v-card
              class="pa-2"
              outlined
              tile
          >
            <el-breadcrumb separator="/" style="font-size: 20px">
              <el-breadcrumb-item :to="{ name: 'home' }"> <v-icon>mdi-home</v-icon>首页</el-breadcrumb-item>
              <el-breadcrumb-item :to="{name:'article'}">文章详情</el-breadcrumb-item>
            </el-breadcrumb>
          </v-card>
        </v-col>
      </v-row>

      <v-row>
        <v-col
        cols="12"
        md="9">
          <v-card
              class="pa-2"
              outlined
              tile
          >
            <div class="markdown-body" v-html="articleList"></div>
          </v-card>
        </v-col>
<!--        i.articleList-->
        <!--        用户详情-->
        <v-col
            cols="6"
            md="3"
            class="user"
        >
          <v-card
              class="mx-auto"
              max-width="344"
              outlined
          >
            <v-list-item three-line>
              <v-list-item-content>
                <div class="text-overline mb-4">
                  Personal information
                </div>
                <v-list-item-title class="text-h5 mb-1">
                {{this.articleUser.username}}
                </v-list-item-title>
                <v-list-item-subtitle>
                  注册时间 ：{{this.articleUser.createTime}}
                </v-list-item-subtitle>
              </v-list-item-content>

              <v-avatar style="margin-right: 60px">
                <img
                    :src="this.articleUser.isImg"
                    alt="John"
                >
              </v-avatar>
            </v-list-item>
          </v-card>
        </v-col>
      </v-row>

    </v-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Article",
  data() {
    return {
      articleUser:[],
      articleList:''
    }
  },
  mounted() {
    axios.post("/api/article/list/" + this.$route.params.num).then(resp => {
      this.articleUser = resp.data.data[0].user
      this.articleList= resp.data.data[0].articleList
    })
  }
}
</script>

<style scoped>
.app{
  width: 100%;
  margin: 0 auto;
}
@media screen and (max-width: 960px) {
  .user {
    display: none;
  }
}
</style>
