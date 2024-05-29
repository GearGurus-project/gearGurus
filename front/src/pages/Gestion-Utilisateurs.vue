<template>
  <div class="body">
    <h1 class="title">Gestion des Utilisateurs</h1>
    <div class="user-management">
      <div class="user-list">
        <form id="search-user">
          Rechercher
          <input
            v-model="searchQuery"
            placeholder="Entrer le prénom utilisateur"
          />
        </form>
        <GridComponent
          :heroes="filteredUsers"
          :columns="gridColumns"
          :filter-key="searchQuery"
        />
      </div>
      <div class="user-forms">
        <h2>Ajouter un utilisateur</h2>
        <form id="add-user" @submit.prevent="addUser">
          <input v-model="newUser.firstName" type="text" placeholder="Prénom" />
          <input v-model="newUser.lastName" type="text" placeholder="Nom" />
          <input
            v-model="newUser.password"
            type="password"
            placeholder="Mot de passe"
          />
          <input v-model="newUser.role" type="text" placeholder="Rôle" />
          <button type="submit">Ajouter</button>
        </form>
        <h2>Supprimer un utilisateur</h2>
        <form id="delete-user" @submit.prevent="deleteUser">
          <input
            v-model="deleteUserFirstName"
            type="text"
            placeholder="Prénom utilisateur"
          />
          <button type="submit">Supprimer</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import GridComponent from "../components/Grid-Component.vue";
import userService from "@/services/userService";

export default {
  name: "GestionUtilisateurs",
  components: {
    GridComponent,
  },
  data() {
    return {
      searchQuery: "",
      gridColumns: ["firstName", "lastName", "role"], // Utilisez les clés des objets utilisateurs
      users: [],
      newUser: {
        firstName: "",
        lastName: "",
        password: "",
        role: "",
      },
      deleteUserFirstName: "",
    };
  },
  computed: {
    filteredUsers() {
      const transformedUsers = this.transformUsers(this.users);
      if (!this.searchQuery) {
        return transformedUsers;
      }
      const filterKey = this.searchQuery.toLowerCase();
      return transformedUsers.filter((user) => {
        return user.firstName.toLowerCase().includes(filterKey);
      });
    },
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await userService.getUsers();
        console.log("Utilisateurs récupérés :", response.data);
        this.users = response.data;
      } catch (error) {
        console.error(
          "Erreur lors de la récupération des utilisateurs :",
          error
        );
      }
    },
    transformUsers(users) {
      return users.map((user) => ({
        firstName: user.firstName,
        lastName: user.lastName,
        role: user.role,
      }));
    },
    async addUser() {
      if (
        this.newUser.firstName &&
        this.newUser.lastName &&
        this.newUser.password &&
        this.newUser.role
      ) {
        try {
          const response = await userService.createUser(this.newUser);
          this.users.push(response.data);
          this.newUser = {
            firstName: "",
            lastName: "",
            password: "",
            role: "",
          };
          console.log("Utilisateur ajouté avec succès.");
        } catch (error) {
          console.error("Erreur lors de l'ajout de l'utilisateur :", error);
        }
      } else {
        console.log("Veuillez remplir tous les champs.");
      }
    },
    async deleteUser() {
      try {
        const userToDelete = this.users.find(
          (user) => user.firstName === this.deleteUserFirstName
        );
        if (userToDelete) {
          await userService.deleteUser(userToDelete.id);
          this.users = this.users.filter((user) => user.id !== userToDelete.id);
          this.deleteUserFirstName = "";
          console.log("Utilisateur supprimé avec succès.");
        } else {
          console.log("Utilisateur non trouvé.");
        }
      } catch (error) {
        console.error(
          "Erreur lors de la suppression de l'utilisateur :",
          error
        );
      }
    },
  },
  mounted() {
    this.fetchUsers();
  },
};
</script>


<style scoped>
.body {
  margin: 0 40px 20px 40px;
}

h1 {
  font-weight: bolder;
  font-style: italic;
}

.user-management {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 50px;
}

.user-list,
.user-forms {
  padding: 30px;
  border: 1px solid black;
  border-radius: 10px;
  background-color: #ffffff;
}

form {
  margin-bottom: 20px;
  width: 100%;
}

input {
  margin-right: 10px;
}
</style>
