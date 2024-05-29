<script>
import GridComponent from "../components/Grid-Component.vue";
import hardwareService from "@/services/hardwareService";

export default {
	name: "Materiels-Page",
	components: {
		GridComponent,
	},
	data() {
		return {
			searchQuery: "",
			gridColumns: ["Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Prix", "Code barre"],
			gridHardwareList: [],
			hardwareItem: {
				category: "",
				model: "",
				provider: "",
				datePurchase: "",
				price: "",
				barCode: "",
			},
			showAddForm: false,
			showEditForm: false,
			showDeleteForm: false,
			searchBarCode: "",
			searchResult: null,
		};
	},
	methods: {
		async getHardwareList() {
			try {
				const response = await hardwareService.getHardware();
				this.gridHardwareList = response.data.map(item => ({
					Catégorie: item.category,
					Modèle: item.model,
					Fournisseur: item.provider,
					"Date d'achat": item.datePurchase,
					Prix: item.price,
					"Code barre": item.barCode,
					id: item.id,
				}));
			} catch (error) {
				console.error(error);
			}
		},
		async postHardwareItem() {
			try {
				await hardwareService.postHardware(this.hardwareItem);
				this.getHardwareList();
				this.resetForm();
			} catch (error) {
				console.error(error);
			}
		},
		async updateHardwareItem() {
			try {
				await hardwareService.updateHardwareByBarCode(this.hardwareItem);
				this.getHardwareList();
				this.resetForm();
			} catch (error) {
				console.error(error);
			}
		},
		async deleteHardwareItem() {
			try {
				await hardwareService.deleteHardwareByBarCode(this.hardwareItem.id);
				this.getHardwareList();
				this.resetForm();
			} catch (error) {
				console.error(error);
			}
		},
		resetForm() {
			this.hardwareItem = {
				category: "",
				model: "",
				provider: "",
				datePurchase: "",
				price: "",
				barCode: "",
			};
			this.showAddForm = false;
			this.showEditForm = false;
			this.showDeleteForm = false;
			this.searchId = "";
			this.searchResult = null;
		},
		async searchHardware() {
			try {
				const response = await hardwareService.getHardwareByBarCode(this.searchBarCode);
				this.hardwareItem = {
					category: response.data.category,
					model: response.data.model,
					provider: response.data.provider,
					datePurchase: response.data.datePurchase,
					price: response.data.price,
					barCode: response.data.barCode,
				};
				console.log(this.hardwareItem);
			} catch (error) {
				console.error(error);
				this.searchResult = null;
			}
		},
	},
	mounted() {
		this.getHardwareList();
	},
	computed: {
		filteredResultsCount() {
			const count = this.gridHardwareList.filter(row => {
				return Object.keys(row).some(key => {
					return String(row[key]).toLowerCase().includes(this.searchQuery.toLowerCase());
				});
			}).length;

			return count + ' ' + (count === 1 ? 'résultat trouvé' : 'résultats trouvés');
		},
	},
};
</script>

<template>
	<h1 class="title">Gestion du matériel</h1>
	<div class="body">
		<div class="hardware-list">
			<h2>Liste du matériel</h2>
			<div class="search-bar">
				<form id="search">
					Rechercher : <input name="query" v-model="searchQuery" />
				</form>
				<p>{{ filteredResultsCount }}</p>
			</div>
			<GridComponent :columns="gridColumns" :heroes="gridHardwareList" :filter-key="searchQuery" />
			<button @click="showAddForm = true; showEditForm = false; showDeleteForm = false">Ajouter</button>
			<button @click="showDeleteForm = true; showAddForm = false; showEditForm = false">Supprimer</button>
			<button @click="showEditForm = true; showAddForm = false; showDeleteForm = false">Modifier</button>
		</div>

		<div v-if="showAddForm" class="add-hardware-form">
			<h2>Ajouter du matériel</h2>
			<form @submit.prevent="postHardwareItem()">
				<label for="category">Catégorie</label>
				<select name="category" id="category" v-model="hardwareItem.category">
					<option value="Ordinateur">Ordinateur</option>
					<option value="Écran">Écran</option>
					<option value="Clavier">Clavier</option>
					<option value="Souris">Souris</option>
					<option value="Autre">Autre</option>
				</select>
				<label for="model">Modèle : </label>
				<input type="text" name="model" id="model" v-model="hardwareItem.model" />
				<label for="provider">Fournisseur : </label>
				<input type="text" name="provider" id="provider" v-model="hardwareItem.provider" />
				<label for="purchase-date">Date d'achat : </label>
				<input type="date" name="purchase-date" id="purchase-date" v-model="hardwareItem.datePurchase" />
				<label for="price">Prix</label>
				<input type="number" name="price" id="price" v-model="hardwareItem.price" />
				<label for="barcode">Code barre : </label>
				<input type="text" name="barCode" id="barCode" v-model="hardwareItem.barCode"/>
				<button type="submit">Ajouter</button>
				<button @click="resetForm">Annuler</button>
			</form>
		</div>

		<div v-if="showDeleteForm" class="delete-hardware-form">
			<h2>Supprimer du matériel</h2>
			<form @submit.prevent="deleteHardwareItem()">
				<label for="hardware-id">ID du matériel : </label>
				<input type="text" name="hardware-id" id="hardware-id" v-model="hardwareItem.id" />
				<button type="submit">Supprimer</button>
				<button @click="resetForm">Annuler</button>
			</form>
		</div>

		<div v-if="showEditForm" class="update-hardware-form">
			<h2>Modifier du matériel</h2>
			<form id="search-hardware" @submit.prevent="searchHardware" class="search-hardware">
				<input v-model="searchBarCode" type="text" id="searchBarCode"
					placeholder="Entrer le code barre du matériel">
				<button type="submit">Rechercher</button>
			</form>
			<form @submit.prevent="updateHardwareItem()">
				<label for="category">Catégorie</label>
				<select name="category" id="category" v-model="hardwareItem.category">
					<option value="Ordinateur">Ordinateur</option>
					<option value="Imprimante">Imprimante</option>
					<option value="Scanner">Scanner</option>
					<option value="Écran">Écran</option>
					<option value="Clavier">Clavier</option>
					<option value="Souris">Souris</option>
					<option value="Autre">Autre</option>
				</select>
				<label for="model">Modèle : </label>
				<input type="text" name="model" id="model" v-model="hardwareItem.model" />
				<label for="provider">Fournisseur : </label>
				<input type="text" name="provider" id="provider" v-model="hardwareItem.provider" />
				<label for="purchase-date">Date d'achat : </label>
				<input type="date" name="purchase-date" id="purchase-date" v-model="hardwareItem.datePurchase" />
				<label for="price">Prix</label>
				<input type="number" name="price" id="price" v-model="hardwareItem.price" />
				<button type="submit">Modifier</button>
				<button @click="resetForm">Annuler</button>
			</form>
		</div>
	</div>
</template>
  
  

<style scoped>
.body {
	margin: 20px 50px;
	justify-content: center;
	justify-items: center;
	align-items: center;
}

.title {
	font-weight: bolder;
	font-style: italic;
}

.add-hardware-form,
.delete-hardware-form,
.update-hardware-form {
	padding: 10px 50px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;
	margin-top: 20px;

	h2 {
		padding-top: 10px;
		margin: 0;
	}

	form {
		padding: 10px 0;

		label {
			margin: 0 10px;
		}

		.attributes {
			margin: 10px 0;
			display: flex;
			align-items: center;
			justify-content: center;
		}

		button {
			margin: 10px;
			padding: 10px 20px;
			border-radius: 5px;
			background-color: #201658;
			color: white;
			border: none;
			cursor: pointer;
		}
	}
}

.search-hardware {
	input {
		padding: 5px;
		margin-bottom: 20px;
		border-radius: 5px;
		border: 1px solid #D9D9D9;
		width: 50%;
	}

	button {
		border-radius: 5px;
		padding: 5px 20px;
		background-color: #98ABEE !important;
		color: white;
		border: none;
		cursor: pointer;
	}
}

.hardware-list {
	margin: 20px 0;
	padding: 20px 30px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;

	button {
		margin: 10px;
		padding: 10px 20px;
		border-radius: 5px;
		background-color: #201658;
		color: white;
		border: none;
		cursor: pointer;
	}
}
</style>
  
  