<template>
  <div id="app">
    <template v-if="showNavbar">
      <Navbar />
    </template>
    <router-view></router-view>
    <template v-if="showFooter">
      <Footer />
    </template>
  </div>
</template>

<script>
import Navbar from './components/Nav-Bar.vue'
import Footer from './components/Footer-Component.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      showNavbar: true,
      showFooter: true
    };
  },
  watch: {
    // Watch for route changes
    '$route'(to) {
      this.updateNavbarAndFooterVisibility(to);
    }
  },
  created() {
    // Initially, update navbar and footer visibility based on current route
    this.updateNavbarAndFooterVisibility(this.$route);
  },
  methods: {
    updateNavbarAndFooterVisibility(route) {
      // Determine if navbar should be shown based on route
      this.showNavbar = route.path !== '/' && route.path !== '/Login2';
      // Determine if footer should be shown based on route
      this.showFooter = route.path !== '/' && route.path !== '/Login2';
    }
  }
}
</script>

<style>
#app {
  background-color: #F9E8C9;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  min-height: 100vh; /* Ensure the app fills the viewport height */
  display: flex;
  flex-direction: column; /* Make the content stack vertically */
}
</style>
