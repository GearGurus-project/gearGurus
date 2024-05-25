<template>
    <div class="body">
        <h1 class="title">Gestion des Utilisateurs</h1>
        <div class="user-management">
            <div class="user-list">
                <form id="search-user">
                    Rechercher <input v-model="searchQuery" placeholder="Entrer le numéro utilisateur" />
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
                    <input v-model="newUser.number" type="text" placeholder="Numéro" />
                    <input v-model="newUser.lastName" type="text" placeholder="Nom" />
                    <input v-model="newUser.firstName" type="text" placeholder="Prénom" />
                    <input v-model="newUser.group" type="text" placeholder="Groupe" />
                    <button type="submit">Ajouter</button>
                </form>
                <h2>Supprimer un utilisateur</h2>
                <form id="delete-user" @submit.prevent="deleteUser">
                    <input v-model="deleteUserNumber" type="text" placeholder="Numéro utilisateur" />
                    <button type="submit">Supprimer</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import GridComponent from "../components/Grid-Component.vue";

export default {
    name: 'GestionUtilisateurs',
    components: {
        GridComponent
    },
    data() {
        return {
            searchQuery: '',
            gridColumns: ["Numéro", "Nom", "Prénom", "Groupe"],
            users: [
                { Numéro: '1001', Nom: 'Doe', Prénom: 'John', Groupe: 'A' },
                { Numéro: '1002', Nom: 'Smith', Prénom: 'Jane', Groupe: 'B' },
                // Ajoutez d'autres utilisateurs si nécessaire
            ],
            newUser: {
                number: '',
                lastName: '',
                firstName: '',
                group: ''
            },
            deleteUserNumber: ''
        };
    },
    computed: {
        filteredUsers() {
            if (!this.searchQuery) {
                return this.users;
            }
            const filterKey = this.searchQuery.toLowerCase();
            return this.users.filter(user => {
                return Object.keys(user).some(key =>
                    String(user[key]).toLowerCase().includes(filterKey)
                );
            });
        }
    },
    methods: {
        addUser() {
            if (this.newUser.number && this.newUser.lastName && this.newUser.firstName && this.newUser.group) {
                this.users.push({
                    Numéro: this.newUser.number,
                    Nom: this.newUser.lastName,
                    Prénom: this.newUser.firstName,
                    Groupe: this.newUser.group
                });
                this.newUser = { number: '', lastName: '', firstName: '', group: '' };
                console.log("Utilisateur ajouté avec succès.");
            } else {
                console.log("Veuillez remplir tous les champs.");
            }
        },
        deleteUser() {
            this.users = this.users.filter(user => user.Numéro !== this.deleteUserNumber);
            this.deleteUserNumber = '';
            console.log("Utilisateur supprimé avec succès.");
        }
    }
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

.user-list, .user-forms {
    padding: 30px;
    border: 1px solid black;
    border-radius: 10px;
    background-color: #FFFFFF;
}

form {
    margin-bottom: 20px;
    width: 100%;
}

input {
    margin-right: 10px;
}
</style>
