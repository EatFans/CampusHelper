import { createRouter,createWebHistory } from "vue-router";
import HomePage from "@/Pages/HomePage.vue";
import LoginPage from "@/Pages/LoginPage.vue";
import DocPage from "@/Pages/DocPage.vue";
import UserManagerPage from "@/Pages/UserManagerPage.vue";
import MemberManagerPage from "@/Pages/MemberManagerPage.vue";
import UniversityManagerPage from "@/Pages/UniversityManagerPage.vue";
import TaskManagerPage from "@/Pages/TaskManagerPage.vue";
import adminAPI from "@/api/admin";


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
            path:'/doc',
            name: 'DocPage',
            component: DocPage,
            meta: {
                title: '使用文档'
            }
        },
        {
            path:'/user-manager',
            name: 'UserManagerPage',
            component: UserManagerPage,
            meta: {
                title: '用户管理'
            }
        },
        {
            path: '/task-manager',
            name: 'TaskManagerPage',
            component: TaskManagerPage,
            meta: {
                title: '订单管理'
            }
        },
        {
            path:'/university-manager',
            name: 'UniversityPage',
            component: UniversityManagerPage,
            meta: {
                title: '学校管理'
            }
        },
        {
            path:'/member-manager',
            name: 'MemberManagerPage',
            component: MemberManagerPage,
            meta: {
                title: '成员管理'
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


// 检查 token 是否有效的异步函数
const checkToken = async (token) => {
    try {
        const response = await adminAPI.checkToken(token); // 假设您有一个 checkToken 的 API
        const status = response.data.status;
        return status === 'success';
    } catch (error) {
        return false;
    }
};

router.beforeEach( (to, from, next) => {
    // 检查用户是否登录
    const token = localStorage.getItem("token");

    if (token){
        checkToken({token}).then(isValid => {
            if (!isValid) {
                console.log('token无效，直接跳转到/login');
                router.push('/login');
            }

        });

    } else {
        router.push('/login');
    }

    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title
    }
    next();
})

// 暴露路由
export default router;

