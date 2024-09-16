import { createRouter,createWebHistory } from "vue-router";
import HomePage from "@/Pages/HomePage.vue";


// 创建路由
const router = createRouter({
    history:createWebHistory(),
    routes:[
        {
            path:"/",
            name: 'HomePage',
            component: HomePage,
            meta: {
                title: '主页'
            }

        },
    ]
});

router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next()
})

// 暴露路由
export default router;

