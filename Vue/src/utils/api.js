import axios from "axios";
import {Message,Loading} from "element-ui";
import router from "@/router";
//请求拦截器
axios.interceptors.request.use(config => {
    Loading.service(
        {text:"拼命加载中...", background: 'rgba(86,83,83,0.7)'});
    //如果存在，则携带token
    if (window.localStorage.getItem('token')) {
        config.headers['token'] = window.localStorage.getItem('token');
    }
    return config;
}, err => {
    Message.error({message: '请求超时!'})
})

//响应拦截器
axios.interceptors.response.use(success=>{
        if ( success.data.code === 600){
            Message.error({message: success.data.msg})
            //清除所有数据
            localStorage.clear();
            router.replace({name: 'home'}).then(resp=>{
                location.reload();
            })
            location.reload();
            return;
        }
        Loading.service({text:"拼命加载中..."}).close();
        return success;
},err => {
    if (err.response.status === 500 || err.response.status === 404) {
        Message.error({message: '服务器被吃了⊙﹏⊙∥'});
    } else if (err.response.status === 700) {
        Message.error({message: '权限不足,请联系管理员!'});
    }else if (err.response.status === 600) {
        Message.error({message: '匿名用户无权限访问，请加入token'});
    }else {
        Message.error({message: '未知错误'});
    }
    return Promise.reject(err);
})
