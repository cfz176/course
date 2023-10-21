import { createRouter,createWebHistory} from 'vue-router';
import Login from './view/login.vue'

const routes = [
    {   path: '/:catchAll(.*)',
        redirect: '/login'
    }, {
        path: '/login',
        component: Login
}
];

const router = createRouter({
    history: createWebHistory(),
    routes
});


export default router;