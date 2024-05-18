<template>
  <div id="app">
    <Navbar />
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>
<script>
import Navbar from './components/Nav-Bar.vue'
import HelloWorld from './components/HelloWorld-Page.vue'

export default {
  name: 'App',
  components: {
    Navbar,
    HelloWorld
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
}
</style>
