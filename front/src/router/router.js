import { createRouter, createWebHistory } from 'vue-router';
import Home from '../pages/Home-Page.vue';
import Emprunts from '../pages/Emprunts-Page.vue';
import Login1 from '../pages/LogIn1-Page.vue';
import Login2 from '../pages/LogIn2-Page.vue';
import Materiels from '../pages/Materiels-Page.vue';
import Statistiques from '../pages/Statistiques-Page.vue';
import NotFoundPage from '../pages/Not-Found-Page.vue';
import { isAuthenticated } from '@/auth';
import GestionUtilisateurs from '../pages/Gestion-Utilisateurs.vue';

const routes = [
	{
		path: '/',
		name: 'Login1',
		component: Login1
	},
	{
		path: '/Emprunts',
		name: 'Emprunts',
		component: Emprunts,
		meta: { requiresAuth: true }
	},

	{
		path: '/Home',
		name: 'Home',
		component: Home,
		meta: { requiresAuth: true }
	},
	{
		path: '/Login2',
		name: 'Login2',
		component: Login2
	},
	{
		path: '/Materiels',
		name: 'Materiels',
		component: Materiels,
		meta: { requiresAuth: true }
	},
	{
		path: '/Statistiques',
		name: 'Statistiques',
		component: Statistiques,
		meta: { requiresAuth: true }
	},
	{
		path: '/:pathMatch(.*)*',
		name: 'NotFoundPage',
		component: NotFoundPage
	},
	{
		path: '/GestionUtilisateurs',
		name: 'GestionUtilisateurs',
		component: GestionUtilisateurs
	}
];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
});

router.beforeEach((to, from, next) => {
	if (to.matched.some(record => record.meta.requiresAuth)) {
		if (!isAuthenticated()) {
			next('/'); // Redirect to the first login page if not authenticated
		} else {
			next();
		}
	} else if (to.name === 'Login1' && isAuthenticated()) {
		next('/Home'); // Redirect to Home if already authenticated and trying to access Login1
	} else {
		next();
	}
});


export default router;
