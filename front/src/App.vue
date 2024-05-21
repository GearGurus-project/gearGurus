<template>
  <div id="app">
    <Navbar />
	<router-view></router-view>
    <Footer />
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
