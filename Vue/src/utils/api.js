import axios from "axios";
import {Message} from "element-ui";
import router from "@/router";
//请求拦截器
axios.interceptors.request.use(config => {
    //如果存在，则携带token
    if (window.localStorage.getItem('token')) {
        config.headers['token'] = window.localStorage.getItem('token');
    }
    return config;
}, error => {
    Message.error(error)
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
        return success;
})
