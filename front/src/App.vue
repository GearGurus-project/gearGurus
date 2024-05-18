<template>
  <div id="app">
    <Navbar />
    <HelloWorldPage />
  </div>
</template>

<script>
import HelloWorldPage from './components/HelloWorld-Page.vue';
import Navbar from './components/Nav-Bar.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    HelloWorldPage
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
