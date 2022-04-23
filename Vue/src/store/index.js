import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    //用于保存所有数据
    state: {
    },
    //监听值得最新状态
    getters: {

    },
    //唯一一个可以修改值得地方，相当于Java得set，同步执行
    mutations: {
        update(state) {
            //改变值
            state.condition = true;
            //存储值
            window.sessionStorage.setItem("condition",JSON.stringify(true))
        },
        setCondition(state,status){
            this.state.condition = status
        }
    },
    //异步执行mutations方法
    actions: {
        actionsUpdate({commit}){
          let SetCondition = JSON.parse()
            commit('setCondition',SetCondition)
        }
    },
    modules: {}
})
