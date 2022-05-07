<template>
    <v-col
        cols="12"
        md="8"
    >
      <v-card
          v-for="n in article"
          :key="n.index"
          class="mx-auto"
          style="margin-top: 20px"
      >

        <v-img
            :src='n.articleImg'
            height="200px"
        ></v-img>

        <v-card-title>
          <router-link :to="{name:'article',params:{num:n.articleId}}" tag="a"> {{ n.articleTitle }}</router-link>
        </v-card-title>
        <v-card-subtitle>
         创作时间 : {{n.articleCreate}}
        </v-card-subtitle>
      </v-card>

      <v-row justify="center">
        <v-col cols="8">
          <v-container class="max-width">
            <v-pagination
                v-model="curPage"
                :length="this.page"
                @input="onPageChange(curPage, limit)"
            ></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-col>

</template>

<script>
import axios from "axios";
export default {
  name: "LeftBody",
  data() {
    return {
      article: [],
      page:'',
      curPage: 1,
      limit: '3'
    }
  },
  mounted() {
    axios.get("api/article/list").then(resp => {
    })
    axios.get("api/article/list/page/"+this.curPage+"/"+this.limit).then(resp=>{
      this.article = resp.data.list
      this.page = resp.data.pages
    })
  },methods:{
    onPageChange(curPage, limit) {
      axios.get("api/article/list/page/"+curPage+"/"+limit).then(resp=>{
        this.article = resp.data.list
      })
    }
  }
}
</script>
<style>
a{
  text-decoration: none;
}
</style>
