import {createRouter, createWebHistory} from 'vue-router';
import Login from './view/login'
import Admin from './view/admin'
import Welcome from './view/admin/welcome'
import Chapter from './view/admin/chapter'
import Section from './view/admin/section'

const routes = [
  {
    path: '/:catchAll(.*)',
    redirect: '/login'
  }, {
    path: '/login',
    component: Login
  }, {
    path: '/',
    component: Admin,
    children: [{
      path: 'welcome',
      name: 'welcome',
      component: Welcome,
    },{
      path: 'business/chapter',
      name: 'business/chapter',
      component: Chapter,
    },{
      path: 'business/section',
      name: 'business/section',
      component: Section,
    }]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});


export default router;