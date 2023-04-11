<template>
  <div class="app">
    <v-container class="grey lighten-5">
      <v-row class="img">
        <!--        轮播图-->
        <v-col
            cols="12"
            md="8"
        >
          <div>
            <el-carousel height="370px">
              <el-carousel-item v-for="(item,index) in urls" :key="index">
                <el-image :src="item.sysUrl" style="width: 100%;height: 100%">
                  {{ item }}
                </el-image>
              </el-carousel-item>
            </el-carousel>
          </div>
        </v-col>
        <v-col
            cols="3"
            md="4"
        >
          <v-card
              class="mx-auto"
              max-width="400"
              max-height="370"
          >
            <v-img
                class="white--text align-end"
                height="200px"
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
            >
              <v-card-title>
                <v-btn
                    color="cyan accent-1"
                    text
                    :to="{name:'message'}"
                >
                  关于我
                </v-btn>
              </v-card-title>
            </v-img>

            <v-card-subtitle class="pb-0">
              公告栏
            </v-card-subtitle>
            <v-card-text class="text--primary">
              <v-tooltip bottom>
                <template v-slot:activator="{ on, attrs }">

                      <span style="  display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow:hidden"
                            v-bind="attrs"
                            v-on="on">
                              {{text}}
                            </span>
                </template>
                <span style="display:-webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow:hidden;width: 400px"
                >
                  {{this.text}}
                </span>
              </v-tooltip>

            </v-card-text>
            <br>
            <br>
            <br>
          </v-card>
        </v-col>
      </v-row>
      <!-- Columns are always 50% wide, on mobile and desktop -->
      <br>
      <br>
      <!--      工具栏-->
      <hr>
      <br>
      <v-row>
        <LeftBody />
        <!-- 日历-->
        <RightBody class="right" />
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import RightBody from "@/components/MainBody/RightBody";
import LeftBody from "@/components/MainBody/LeftBody";

export default {
  name: "Home",
  components:{
    RightBody,
    LeftBody
  },
  data() {
    return {
      show: false,
      urls: [],
      article: [],
      text: '',
      arrayEvents: null,
    }
  },
  mounted() {
    axios.get("/api/article/list/getCarousel").then(resp => {
      this.urls = resp.data.data
    })
    axios.get("/api/article/list/getCarousel/" + 1).then(resp => {
      this.text = resp.data.data.sysText

    })
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 60px;
  margin: 0 ;
}
a{
  text-decoration: none;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
@media screen and (max-width: 960px) {
  .right {
    display: none;
  }
  .tool{
    display: none;
  }
  .img{
    display: none;
  }
}

</style>
