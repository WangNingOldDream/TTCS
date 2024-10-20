import { createRouter, createWebHistory } from 'vue-router'
import FirstView from '../views/FirstView.vue'
import CompView from '@/views/CompView.vue'
import HomeView from '@/views/HomeView.vue'
import ScoreView from '@/views/ScoreView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'first',
      component: FirstView
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/comp/:compName',
      name: 'comp',
      component: CompView
    },
    {
      path: '/score/:compName/:players',
      name: 'score',
      component: ScoreView
    },
  ]
})

export default router
