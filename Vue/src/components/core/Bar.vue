<template>
  <div class="app">
    <v-bottom-navigation
        :value="activeIndex"
        grow>
      <v-btn width="20px">
        <router-link :to="{name:'home'}" tag="a">首页</router-link>
      </v-btn>
      <v-btn width="20px">
        <router-link :to="{name:'edit'}" tag="a">文章</router-link>
      </v-btn>
      <v-btn width="20px"><a href="https://gitee.com/bmt4578/blog-project" target="blank">源码</a></v-btn>
      <div class="search">
        <v-text-field
            v-model="search"
            label="想搜点什么呢"
            prepend-inner-icon="mdi-magnify"
        ></v-text-field>
      </div>
      <div v-if='this.condition' style="margin-left:80px; margin-top: 5px">
        <v-menu
            bottom
            min-width="20px"
            rounded
            offset-y
        >
          <template v-slot:activator="{ on }">
            <v-btn
                icon
                x-large
                v-on="on"
            >
              <v-avatar
                  size="48"
                  color="brown"
              >
                <img :src="user.isImg" alt="">
              </v-avatar>
            </v-btn>
          </template>
          <v-card>
            <v-list-item-content class="justify-center">
              <div class="mx-auto text-center">
                <v-avatar
                    color="brown"
                >
                  <img :src="this.user.isImg" alt="">
                </v-avatar>
                <h3>{{ user.username }}</h3>
                <v-divider class="my-3"></v-divider>
                <v-btn
                    depressed
                    rounded
                    text
                    :to="{name:'user'}"
                >
                  个人中心
                </v-btn>
                <v-divider class="my-3"></v-divider>
                <v-btn
                    depressed
                    rounded
                    text
                    @click="signOut"
                >
                  退出登录
                </v-btn>
              </div>
            </v-list-item-content>
          </v-card>
        </v-menu>
      </div>
      <span v-else style="display: flex">
         <v-btn> <router-link :to="{name:'login'}" tag="a">登录</router-link></v-btn>
         <v-btn><router-link :to="{name:'register'}" tag="a"> 注册</router-link></v-btn>

      </span>

    </v-bottom-navigation>
  </div>
</template>

<script>
import router from "@/router";
import axios from "axios";

export default {
  name: "Bar",
  data() {
    return {
      value: 0,
      items: [
        {tab: '首页', url: 'home'},
        {tab: '文章', url: 'edit'},
      ],
      login: [
        {tab: '登录', url: 'login'},
        {tab: '注册', url: 'register'},
      ],
      search: '',
      valid: true,
      condition: false,
      //user
      user: {},
    }
  },
  computed: {
    activeIndex() {
      return this.$route.path.replace('/', '')
    }
  },
  mounted() {
    this.condition = window.localStorage.getItem("condition")
    const params = new URLSearchParams();
    const s = window.localStorage.getItem("username")
    const i = s.substring(1, s.length - 1)

    params.append("username", i)
    //获取用户信息
    axios.post("/api/user/selectName", params).then(resp => {
      this.user = resp.data.data
      window.localStorage.setItem("user", JSON.stringify(this.user))
    })
    //加载菜单
  },
  methods: {
    signOut() {
      const params = new URLSearchParams();
      params.append("token", window.localStorage.getItem("token"))
      axios.post('/api/user/loginOut', params).then(resp => {
        const _this = resp.data
        if (resp.data.code === 200) {
          this.$notify({
            title: '成功',
            message: '恭喜您' + _this.msg,
            type: 'success'
          });
          router.replace({name: 'home'})
          setTimeout(function () {
            location.reload();
          }, 500)
        }
      })
    }
  }
}
</script>

<style scoped>

.app {
  width: 100%;
}

/* 鼠标移动到链接的颜色 */
a {
  text-decoration: none;
  font-size: 20px;
  align-content: center;
  line-height: 38px;
  color: #71777c;
}

a:hover {
  color: #1976d2;
}

.router-link-active {
  color: #1976d2;
}

.search {
  margin-left: 180px;
}

@media screen and (max-width: 600px) {
  .search {
    display: none;
  }
}
</style>
