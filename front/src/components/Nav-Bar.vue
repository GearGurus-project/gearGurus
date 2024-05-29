<template>
  <div>
    <nav class="navbar navbar-expand-md navbar-dark navbar-custom">
      <!-- Logo to the right -->
      <router-link to="/Home" class="navbar-brand ml-auto">
        <img src="../assets/Efrei-Logo.png" alt="Logo" />
      </router-link>

      <!-- Navigation links centered -->
      <div class="navbar-brand mx-auto">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link to="/Home" class="nav-link">Accueil</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/emprunts" class="nav-link">Emprunts</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/materiels" class="nav-link"
              >Materiels</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/statistiques" class="nav-link"
              >Statistiques</router-link
            >
          </li>
          <li class="nav-item">
            <router-link to="/GestionUtilisateurs" class="nav-link"
              >Gestion des utilisateurs</router-link
            >
          </li>
        </ul>
      </div>

      <!-- User Info and Disconnect button to the right -->
      <div class="user-info">
        <span v-if="firstName && lastName" class="user-name">
          <span>{{ firstName }} {{ lastName }}</span>
          <span class="connected-light"></span>
        </span>
        <button
          v-if="firstName && lastName"
          class="btn disconnect-btn"
          @click="disconnect"
        >
          <img src="../assets/LogOut.png" alt="Disconnect" />
        </button>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: "",
      lastName: "",
    };
  },
  methods: {
    disconnect() {
      // Clear the authentication token and user info from localStorage
      localStorage.removeItem("authToken");
      localStorage.removeItem("firstName");
      localStorage.removeItem("lastName");
      // Redirect to the login page
      this.$router.push("/");
    },
  },
  mounted() {
    // Retrieve user info from localStorage
    this.firstName = localStorage.getItem("firstName");
    this.lastName = localStorage.getItem("lastName");
  },
};
</script>

<style scoped>
.navbar-custom {
  background-color: #201658;
  height: 80px;
  /* Adjust height as needed */
}

.navbar-brand img {
  max-height: 40px;
  margin: 5px 15px;
}

.navbar-brand li {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.navbar-nav {
  list-style: none;
  /* Remove bullets */
  padding-left: 0;
  /* Remove default padding */
  display: flex;
  /* Add this line to enable flexbox */
  justify-content: center;
  /* Add this line to center the links horizontally */
  align-items: center;
  /* Asdd this line to center the links vertically */
}

.navbar-nav .nav-link {
  color: white;
  font-weight: bold;
  text-decoration: none;
  margin: 0 15px;
}

.navbar-nav .nav-link:hover {
  color: #ddd;
}

.user-info {
  display: flex;
  align-items: center;
  color: white;
  margin-right: 10px;
  margin-left: auto;
  position: absolute;
  right: 0;
  margin-right: 10px;
  margin-top: 10px;
}

.user-name {
  display: flex;
  align-items: center;
}

.connected-light {
  width: 10px;
  height: 10px;
  background-color: green;
  border-radius: 50%;
  margin-left: 5px;
}

.disconnect-btn {
  background-color: transparent;
  border: none;
  margin-left: 10px;
}

.disconnect-btn img {
  max-height: 40px;
}
</style>
