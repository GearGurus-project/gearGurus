<template>
  <nav class="navbar navbar-expand-md navbar-dark navbar-custom">
    <router-link to="/" class="navbar-brand mr-auto">
      <img src="..\assets\Efrei-Logo.png" alt="Logo">
    </router-link>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-center" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <router-link to="/" class="nav-link">Accueil</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/emprunts" class="nav-link">Emprunts</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/materiels" class="nav-link">Materiels</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/statistiques" class="nav-link">Statistiques</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/GestionUtilisateurs" class="nav-link">Gestion Utilisateurs</router-link>
        </li>
      </ul>
    </div>
    <router-link to="/LogIn1" class="navbar-disconnect ml-auto">
      <button class="btn disconnect-btn" @click="disconnect">
        <img src="..\assets\LogOut.png" alt="Disconnect">
      </button>
    </router-link>
  </nav>
</template>

<script>
export default {
  methods: {
    disconnect() {
      // Handle disconnect logic here
    }
  }
}
</script>

<style scoped>
.navbar-custom {
  background-color: #201658;
}

.navbar-brand img {
  max-height: 40px;
}

.navbar-nav .nav-link {
  color: #fff;
  font-weight: bold;
}

.disconnect-btn {
  color: #fff;
}

.navbar-disconnect img {
  max-height: 40px;
}
</style>
