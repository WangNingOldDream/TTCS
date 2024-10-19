import { createRouter, createWebHistory } from 'vue-router'
import FirstView from '../views/FirstView.vue'
import CompView from '@/views/CompView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'first',
      component: FirstView
    },
    {
      path: '/comp/:compId',
      name: 'comp',
      component: CompView
    },
    {
      path: '/home',
      name: 'home',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/HomeView.vue')
    }
  ]
})

export default router
