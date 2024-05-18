<template>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">Navbar</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="/">Home</b-nav-item>
          <b-nav-item href="/about">About</b-nav-item>
          <b-nav-item href="/contact">Contact</b-nav-item>
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item href="/login">Login</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </template>
  
  <script>
  export default {
    name: 'Nav-Bar'
  };
  </script>
  
  <style scoped>
  /* Add any custom styles here */
  </style>
  