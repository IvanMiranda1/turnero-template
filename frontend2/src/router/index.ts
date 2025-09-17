import { createRouter, createWebHistory } from 'vue-router'
import PaginaPrincipal from '../views/PaginaPrincipal.vue'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'PaginaPrincipal',
    component: PaginaPrincipal
  }
]

export const router = createRouter({
  history: createWebHistory(),
  routes
})
