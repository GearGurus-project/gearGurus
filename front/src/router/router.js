import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home-Page.vue';
import Emprunts from '../components/Emprunts-Page.vue';
import HelloWorld from '../components/HelloWorld-Page.vue';
import Login1 from '../components/LogIn1-Page.vue';
import Login2 from '../components/LogIn2-Page.vue';
import Materiels from '../components/Materiels-Page.vue';
import NavBar from '../components/Nav-Bar.vue';
import Statistiques from '../components/Statistiques-Page.vue';




const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/Emprunts',
    name: 'Emrprunts',
    component: Emprunts
  },
  {
    path: '/HelloWorld',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {
    path: '/Login1',
    name: 'Login1',
    component: Login1
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
        path: '/NavBar',
        name: 'NavBar',
        component: NavBar
    }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;
