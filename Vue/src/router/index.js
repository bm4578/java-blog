import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from "@/views/home/Home";
import NotFound from "@/views/NotFound";
import Index from "@/views/index"
Vue.use(VueRouter)
const routes = [
  {
    path: '/',
    redirect:'/home',
    component: Index,
    children:[
      {
        path: '/home',
        name: 'home',
        component:Home
      },{
        path: '/edit',
        name: 'edit',
        component: ()=>import('@/components/Edit')
      },{
        path: '/message',
        name: 'message',
        component: ()=>import('@/components/MyMessage')
      },
      {
        path: '/login',
        name: 'login',
        component: ()=>import('@/components/admin/user/Login')
      },
      {
        path: '/register',
        name: 'register',
        component: ()=>import('@/components/admin/user/Register')
      },
      {
        path: '/article/list/:num',
        name: 'article',
        component: ()=>import('@/components/admin/Article')
      },
      {
        path: '/user',
        name: 'user',
        component: ()=>import('@/components/admin/user/Admin'),
        redirect:'/adminEdit',
        children:[
          {
            path: '/pages',
            name: 'pages',
            component: ()=>import('@/components/admin/Settings/Pages'),
          },
          {
            path: '/pagesEdit',
            name: 'pagesEdit',
            component: ()=>import('@/components/admin/Settings/Pages/PagesEdit')
          },
          {
            path: '/adminEdit',
            name: 'adminEdit',
            component: ()=>import('@/components/admin/Settings/AdminEdit')
          },{
            path: '/srcs',
            name: 'srcs',
            component: ()=>import('@/components/admin/MyCenter/carousel')
          }, {
            path: '/carouselEdit',
            name: 'carouselEdit',
            component: ()=>import('@/components/admin/MyCenter/Pages/carouselEdit')
          },
          {
            path: '/announcement',
            name: 'announcement',
            component: ()=>import('@/components/admin/MyCenter/announcement')
          },
          {
            path: '/alluser',
            name: 'alluser',
            component: ()=>import('@/components/admin/MyCenter/alluser')
          }, {
            path: '/myuser',
            name: 'myuser',
            component: ()=>import('@/components/admin/MyCenter/myuser')
          }, {
            path: '/mylist',
            name: 'mylist',
            component: ()=>import('@/components/admin/Settings/MyList')
          }
          ]
      },
    ]
  },
  {
    path: '*',
    component: NotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
