import { createRouter,createWebHistory } from "vue-router";
import HomePage from "@/Pages/HomePage.vue";
import LoginPage from "@/Pages/LoginPage.vue";


// 创建路由
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:'/',
            component: HomePage,
            meta: {
                title: '仪表盘'
            }

        },
        {
            path:'/dashboard',
            name:'HomePage',
            component: HomePage,
            meta: {
                title: '仪表盘'
            }
        },
        {
            path: '/login',
            name: 'LoginPage',
            component: LoginPage,
            meta: {
                title: '登录'
            }
        }
    ]
});

router.beforeEach((to, from, next) => {
    // 检查用户是否登录

    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next();
})

// 暴露路由
export default router;

