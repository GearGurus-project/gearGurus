<template>
  <div class="login-page">
    <div class="background-image"></div>
    <div class="login-container">
      <div class="logo-container">
        <img src="../assets/Efrei-Logo.png" alt="Logo">
      </div>
      <h2>Connexion</h2>
      <p>Utiliser votre compte EFREI</p>
      <form @submit.prevent="login">
        <input v-model="lastName" type="text" placeholder="Nom de famille" required>
        <input v-model="password" type="password" placeholder="Mot de passe" required>
        <button class="login-button" type="submit">Se connecter</button>
      </form>
      <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>

export default {
  name: 'LogIn2Page',
  data() {
    return {
      lastName: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async login() {
      try {
        const response = await fetch('http://localhost:8080/api/users/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            lastName: this.lastName,
            password: this.password
          })
        });
        if (response.ok) {
          const data = await response.json();
          const token = data.token;
          localStorage.setItem('authToken', token); // Store the authentication token
          
          // Decode the token to get the user ID, last name, and first name
          const base64Url = token.split('.')[1];
          const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
          const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
          }).join(''));
          const decodedToken = JSON.parse(jsonPayload);
          
          const userId = decodedToken.userId; // Extract the userId from the token
          const lastName = decodedToken.lastName; // Extract the last name from the token
          const firstName = decodedToken.firstName; // Extract the first name from the token
          
          // Store the last name and first name in localStorage
          localStorage.setItem('lastName', lastName);
          localStorage.setItem('firstName', firstName);

          console.log("User ID:", userId);
          console.log("Last Name:", lastName);
          console.log("First Name:", firstName);

          this.$router.push('/Home'); // Redirect to Home after successful login
        } else {
          this.errorMessage = 'Invalid credentials';
        }
      } catch (error) {
        this.errorMessage = 'An error occurred. Please try again.';
      }
    }
  }
};


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
  