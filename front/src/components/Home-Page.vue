<template>
    <div class="home">
      <img src="../assets/Efrei-Logo.png" alt="Logo" width="350" >
      <div class = "Gestion">
      <h2>Gestion des emprunts</h2>
    </div>
      <div class="container">
        <div class="box emprunts" @click="goToPage('/emprunts')">
          <h2>Emprunts</h2>
        </div>
        <div class="box materiels" @click="goToPage('/materiels')">
          <h2>Materiels</h2>
        </div>
        <div class="box statistiques" @click="goToPage('/statistiques')">
          <h2>Statistiques</h2>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    methods: {
      goToPage(route) {
        this.$router.push(route);
      }
    }
  }
  </script>
  
  <style scoped>
  .Gestion h2{
    font-size: 40px;
    font-weight: bold;
    color:#147BBF;
  }
  .home img{
    margin-top:2%;
  }
  .home {
    text-align: center;
    padding: 20px;
  }
  
  .container {
    display: flex;
    justify-content: space-around;
    align-items: center;
    margin-top: 50px;
  }
  
  .box {
    width: 275px;
    height: 275px;
    background-color: #f3f4f6; /* Light gray background */
    border: 2px solid transparent; /* Transparent border */
    border-radius: 10px;
    cursor: pointer;
    transition: transform 0.3s ease, border-color 0.3s ease; /* Transition for transform and border-color */
    display: flex;
    align-items: center;
    justify-content: center;
    margin-bottom: 4%;
    margin-top: 4%;
  }
  
  .box:hover {
    transform: scale(1.05);
    border-color: white; /* Dark purple border on hover */
  } 
  
  .box h2 {
    font-size: 40px;
    font-weight: bold;
    color:white;
    
}
  
  /* Styling for Emprunts box */
  .emprunts {
  background-image: linear-gradient(to bottom right, #201658, #4462cd); /* Gradient background */
  color: #fff; /* White text color */
}

/* Styling for Materiels box */
.materiels {
  background-image: linear-gradient(to bottom right, #201658, #4462cd); /* Gradient background */
  color: #fff; /* White text color */
}

/* Styling for Statistiques box */
.statistiques {
  background-image: linear-gradient(to bottom right, #201658, #4462cd); /* Gradient background */
  color: #fff; /* White text color */
}
  </style>
  