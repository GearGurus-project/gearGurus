<template>
    <div class="login-page">
      <!-- Background image covering the entire screen -->
      <div class="background-image"></div>
      <!-- Login form inside a white rounded box -->
      <div class="login-container">
        <div class="logo-container">
          <!-- Logo -->
          <img src="../assets/Efrei-Logo.png" alt="Logo">
        </div>
        <h2>Connexion</h2>
        <p>Utiliser votre compte EFREI</p>
        <form>
          <input type="name" placeholder="Nom de famille">
          <input type="password" placeholder="Mot de passe">
          <button class="login-button" @click="goHome">Se connecter</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'LogIn2Page',
    methods: {
      goHome() {
        this.$router.push('/Home');
      }
    }
  }
  </script>
  
  <style scoped>
  .login-page {
    position: relative; /* Ensure proper positioning of child elements */
    height: 100vh;
  }
  
  .background-image {
    background-image: url('../assets/background-login.png'); /* Specify your background image path */
    background-size: cover;
    background-position: center;
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
  }
  
  .login-container {
    position: absolute;
    right: 0;
    top: 50%;
    transform: translateY(-50%);
    background-color: #F9E8C9;
    border-radius: 20px;
    padding: 30px;
    text-align: center;
    color: #201658;
    margin-right : 40px;
  }
  
  .logo-container {
    margin-bottom: 20px;
  }
  
  .logo-container img {
    width: 150px; /* Adjust the size of the logo as needed */
  }
  
  .login-container h2 {
    font-size: 30px;
  }
  
  .login-container p {
    margin-bottom: 20px;
    font-size: 16px;
  }
  
  .login-container input {
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 100%;
  }
  
  .login-button {
    background-color: #201658;
    color: white;
    border: none;
    border-radius: 20px;
    padding: 15px 30px;
    font-size: 24px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .login-button:hover {
    background-color: #1b1453; /* Darker shade of blue on hover */
  }
  </style>
  