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
          <select v-model="newUser.role">
            <option value="" disabled>Choisissez un rôle</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
          </select>
          <button type="submit">Ajouter</button>
          <!-- Afficher un message d'erreur si le mot de passe n'est pas valide -->
          <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </form>

        <h2>Modifier un utilisateur</h2>
        <form id="select-user" @submit.prevent="selectUserToEdit">
          <input
            v-model="editUserId"
            type="text"
            placeholder="ID utilisateur"
          />
          <button type="submit">Sélectionner</button>
        </form>
        <form id="edit-user" v-if="userToEdit" @submit.prevent="updateUser">
          <input
            v-model="userToEdit.firstName"
            type="text"
            placeholder="Prénom"
          />
          <input v-model="userToEdit.lastName" type="text" placeholder="Nom" />
          <input
            v-model="userToEdit.password"
            type="password"
            placeholder="Mot de passe"
          />
          <select v-model="userToEdit.role">
            <option value="" disabled>Choisissez un rôle</option>
            <option value="admin">Admin</option>
            <option value="user">User</option>
          </select>
          <button type="submit">Modifier</button>
          <!-- Afficher un message d'erreur si le mot de passe n'est pas valide -->
          <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
        </form>

        <h2>Supprimer un utilisateur</h2>
        <form id="delete-user" @submit.prevent="deleteUser">
          <input
            v-model="deleteUserId"
            type="text"
            placeholder="ID utilisateur"
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
      gridColumns: ["id", "firstName", "lastName", "role"],
      users: [],
      newUser: {
        firstName: "",
        lastName: "",
        password: "",
        role: "",
      },
      editUserId: "", // ID de l'utilisateur à modifier
      userToEdit: null, // Données de l'utilisateur à modifier
      deleteUserId: "", // ID de l'utilisateur à supprimer
      errorMessage: "", // Message d'erreur pour la création d'utilisateur
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
        const firstName = user.firstName ? user.firstName.toLowerCase() : "";
        return firstName.includes(filterKey);
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
        id: user.id,
        firstName: user.firstName,
        lastName: user.lastName,
        role: user.role,
      }));
    },
    async addUser() {
      const passwordValidation = this.validatePassword(this.newUser.password);
      if (
        this.newUser.firstName &&
        this.newUser.lastName &&
        passwordValidation.valid &&
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
          this.errorMessage = ""; // Réinitialiser le message d'erreur après un ajout réussi
          console.log("Utilisateur ajouté avec succès.");
        } catch (error) {
          console.error("Erreur lors de l'ajout de l'utilisateur :", error);
        }
      } else {
        console.log("Veuillez remplir tous les champs correctement.");
        if (!passwordValidation.valid) {
          this.errorMessage = passwordValidation.message; // Mettre à jour le message d'erreur
        }
      }
    },
    validatePassword(password) {
      const minLength = 12;
      const hasNumber = /\d/;
      const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/;

      if (password.length < minLength) {
        return {
          valid: false,
          message: "Le mot de passe doit contenir au moins 12 caractères.",
        };
      }
      if (!hasNumber.test(password)) {
        return {
          valid: false,
          message: "Le mot de passe doit contenir au moins un chiffre.",
        };
      }
      if (!hasSpecialChar.test(password)) {
        return {
          valid: false,
          message:
            "Le mot de passe doit contenir au moins un caractère spécial.",
        };
      }
      return { valid: true, message: "" };
    },
    async selectUserToEdit() {
      try {
        const user = this.users.find(
          (user) => user.id === parseInt(this.editUserId)
        );
        if (user) {
          this.userToEdit = { ...user, password: "" }; // Préremplir le formulaire et vider le champ mot de passe
          console.log(
            "Utilisateur sélectionné pour modification :",
            this.userToEdit
          );
        } else {
          console.log("Utilisateur non trouvé.");
          this.userToEdit = null;
        }
      } catch (error) {
        console.error("Erreur lors de la sélection de l'utilisateur :", error);
      }
    },
    async updateUser() {
      const passwordValidation = this.userToEdit.password
        ? this.validatePassword(this.userToEdit.password)
        : { valid: true, message: "" };

      if (
        this.userToEdit.firstName &&
        this.userToEdit.lastName &&
        passwordValidation.valid &&
        this.userToEdit.role
      ) {
        try {
          const userToUpdate = this.users.find(
            (user) => user.id === this.userToEdit.id
          );

          if (this.userToEdit.firstName) {
            userToUpdate.firstName = this.userToEdit.firstName;
          }
          if (this.userToEdit.lastName) {
            userToUpdate.lastName = this.userToEdit.lastName;
          }
          if (this.userToEdit.password) {
            userToUpdate.password = this.userToEdit.password;
          }
          if (this.userToEdit.role) {
            userToUpdate.role = this.userToEdit.role;
          }

          const response = await userService.updateUser(
            this.userToEdit.id,
            userToUpdate
          );

          const index = this.users.findIndex(
            (user) => user.id === this.userToEdit.id
          );
          this.users[index] = response.data;
          this.userToEdit = null;
          this.editUserId = "";
          console.log("Utilisateur modifié avec succès.");
        } catch (error) {
          console.error(
            "Erreur lors de la modification de l'utilisateur :",
            error
          );
        }
      } else {
        console.log("Veuillez remplir tous les champs correctement.");
        if (!passwordValidation.valid) {
          this.errorMessage = passwordValidation.message; // Mettre à jour le message d'erreur
        }
      }
    },
    async deleteUser() {
      try {
        const userToDelete = this.users.find(
          (user) => user.id === parseInt(this.deleteUserId)
        );
        if (userToDelete) {
          await userService.deleteUser(userToDelete.id);
          this.users = this.users.filter((user) => user.id !== userToDelete.id);
          this.deleteUserId = ""; // Clear the input field after deletion
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
  padding: 20px;
}

.error-message {
  color: red;
  margin-top: 10px;
}

.title {
  font-weight: bolder;
  font-style: italic;
  text-align: center;
  padding-bottom: 1em;
}

.user-management {
  display: flex;
  justify-content: space-between;
}

.user-list, .user-forms {
  width: 45%;
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

form {
  margin-bottom: 20px;
}

input, select {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

input[type="submit"] {
  background-color: #007BFF;
  color: white;
  cursor: pointer;
}

input[type="submit"]:hover {
  background-color: #0056b3;
}
</style>
