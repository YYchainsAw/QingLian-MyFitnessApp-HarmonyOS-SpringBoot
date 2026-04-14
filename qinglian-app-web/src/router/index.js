import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import CommunityView from '../views/CommunityView.vue'
import LeaderboardView from '../views/LeaderboardView.vue'
import TrainingHubView from '../views/TrainingHubView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: '/home' },
    { path: '/home', name: 'HomeView', component: HomeView },
    { path: '/community', name: 'CommunityView', component: CommunityView },
    { path: '/leaderboard', name: 'LeaderboardView', component: LeaderboardView },
    { path: '/training_hub', name: 'TrainingHubView', component: TrainingHubView }
  ],
  scrollBehavior() {
    return { top: 0 }
  }
})

export default router
