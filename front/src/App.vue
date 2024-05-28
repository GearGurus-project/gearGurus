<template>
  <div id="app">
    <template v-if="showNavbar">
      <Navbar />
    </template>
    <router-view></router-view>
    <template v-if="showNavbar">
      <Footer />
    </template>
  </div>
</template>

<script>
import Navbar from './components/Nav-Bar.vue';
import Footer from './components/Footer-Component.vue';

export default {
  name: 'App',
  components: {
    Navbar,
    Footer
  },
  data() {
    return {
      showNavbar: true
    };
  },
  watch: {
    // Watch for route changes
    '$route'(to) {
      this.updateNavbarVisibility(to);
    }
  },
  created() {
    // Initially, update navbar visibility based on current route
    this.updateNavbarVisibility(this.$route);
  },
  methods: {
    updateNavbarVisibility(route) {
      const pathsToHide = ['/', '/Login2'];
      const isNotFound = route.matched.length === 0;
      this.showNavbar = !pathsToHide.includes(route.path) && !isNotFound;
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
