<template>
  <div class="app">
    <v-container class="grey lighten-5"style="margin-top: 50px">
      <v-row no-gutters>
        <v-col order="last">
        </v-col>

        <v-col class="module">
          <v-card width="500px" height="430px">
            <br>
            <v-card-title>
              <el-divider> <h1  style="margin: 0 auto;font-size: 2em">欢迎注册</h1></el-divider>
            </v-card-title>
            <br>

            <v-form
                ref="user"
                v-model="valid"
                lazy-validation
            >
              <v-col
                  cols="12"
                  sm="6"
                  md="8"
                  style="margin: 0 auto"
              >
                <v-text-field
                    label="用户名"
                    :rules="[rules.required]"
                    v-model="user.username"
                    required
                    outlined
                    dense
                ></v-text-field>

                <v-text-field
                    v-model="user.password"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="[rules.required]"
                    :type="show1 ? 'text' : 'password'"
                    name="input-10-1"
                    label="密码"
                    outlined
                    dense
                    @click:append="show1 = !show1"
                ></v-text-field>
                <div style="display: flex">
                  <v-text-field
                      style="width: 100px"
                      label="验证码"
                      :rules="[rules.required]"
                      v-model="user.code"
                      required
                      outlined
                      dense
                  ></v-text-field>
                  <img :src="this.imgUrl" alt="" style="height: 40px;width: 90px;margin-left: 2px" @click="ImgCode">
                </div>
              </v-col>

            </v-form>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-btn
                  color="green darken-1"
                  text
                  @click="submit(user)"
              >
                立即注册
              </v-btn>
              <v-btn
                  color="green darken-1"
                  text
                  @click="login"
              >
                立即登录
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>

        <v-col order="first">
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

export default {
  name: "Login",
  data(){
    return{
      valid: true,
      show1: false,
      imgUrl:'/api/user/imgCode',
      rules: {
        required: value => !!value || '不能为空'
      },
      user: {
        username: 'test',
        password:'1234',
        code: '',
      },
    }
  },
  mounted() {
    this.$store.dispatch("actionsUpdate")
  },
  methods:{
    ImgCode(){
      //生成随机数,清除前端浏览器缓存
      this.imgUrl= "/api/user/imgCode?time=" +new Date()
    },
    submit(){
      //使用表单传递参数
      const params =  new URLSearchParams();
      params.append("username",this.user.username)
      params.append("password",this.user.password)
      params.append("code",this.user.code)
      axios.post("/api/user/register",params).then(resp => {
        if (resp.data.code === 602 || resp.data.code === 500){
          this.$notify.error({
            title: '错误',
            message: '您好！'+resp.data.msg
          });
        }else {
          this.$notify({
            title: '成功',
            message: '欢迎您'+this.user.username,
            type: 'success'
          });
          router.replace({name:'login'})
        }
      })
    },
    login(){
      router.replace({name:'login'})
    }
  }
}
</script>

<style scoped>
.app{
  width: 100%;
  margin: 0 auto;
}
@media screen and (min-width: 960px) {
  .module {
    margin: 100px;
  }
}
</style>
