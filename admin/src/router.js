import {createRouter, createWebHistory} from 'vue-router';
import Login from './view/login'
import Admin from './view/admin'

const routes = [
  {
    path: '/:catchAll(.*)',
    redirect: '/login'
  }, {
    path: '/login',
    component: Login
  }, {
    path: '/admin',
    component: Admin
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


export default router;