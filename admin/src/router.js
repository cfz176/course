import {createRouter, createWebHistory} from 'vue-router';
import Login from './view/login'
import Admin from './view/admin'
import Welcome from './view/admin/welcome'

const routes = [
  {
    path: '/:catchAll(.*)',
    redirect: '/login'
  }, {
    path: '/login',
    component: Login
  }, {
    path: '/admin',
    component: Admin,
    children: [{
      path: 'welcome',
      component: Welcome,
    }]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


export default router;