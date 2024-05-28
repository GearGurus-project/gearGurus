import { createRouter, createWebHistory } from 'vue-router';
import Home from '../pages/Home-Page.vue';
import Emprunts from '../pages/Emprunts-Page.vue';
import Login1 from '../pages/LogIn1-Page.vue';
import Login2 from '../pages/LogIn2-Page.vue';
import Materiels from '../pages/Materiels-Page.vue';
import Statistiques from '../pages/Statistiques-Page.vue';
import NotFoundPage from '../pages/Not-Found-Page.vue';




const routes = [
  {
    path: '/',
    name: 'Login1',
    component: Login1
  },
  {
    path: '/Emprunts',
    name: 'Emprunts',
    component: Emprunts
  },

  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/Login2',
    name: 'Login2',
    component: Login2
  },
  {
    path: '/Materiels',
    name: 'Materiels',
    component: Materiels
  },
  {
    path: '/Statistiques',
    name: 'Statistiques',
    component: Statistiques
  },
  {
	path : '/:pathMatch(.*)*',
	name : 'NotFoundPage',
	component : NotFoundPage
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
