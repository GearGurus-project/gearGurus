<script>
import GridComponent from "../components/Grid-Component.vue";
import hardwareService from "@/services/hardwareService";

export default {
	name: 'Materiels-Page',
	components: {
		GridComponent,
	},
	data() {
		return {
			searchQuery: '',
			gridColumns: ["Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Prix", "Code barre"],
			gridHardwareList: [],
			hardwareItem : {
				category: '',
				model: '',
				provider: '',
				datePurchase: '',
				price: '',
				barCode: ''
			},
		};
	},
	methods: {
		async getHardwareList() {
			try {
				const response = await hardwareService.getHardware();
				console.log(response);

				// Map data to match the gridColumns structure
				this.gridHardwareList = response.data.map(item => ({
					Catégorie: item.category,
					Modèle: item.model,
					Fournisseur: item.provider,
					"Date d'achat": item.datePurchase,
					Prix: item.price,
					"Code barre": item.barCode
				}));

			} catch (error) {
				console.error(error);
			}
		},
		async postHardwareItem() {
			try {
				const response = await hardwareService.postHardware(this.hardwareItem);
				console.log(response);

				// Update the hardware list
				this.getHardwareList();

				// Reset the form
				this.hardwareItem = {
					category: '',
					model: '',
					provider: '',
					datePurchase: '',
					price: '',
					barCode: ''
				};

			} catch (error) {
				console.error(error);
			}
		},
		updateResultsCount(count) {
			this.resultsCount = count;
		},
	},
	// Permet de lancer les fonctions au chargement de la page
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
		}
	}
};

</script>

<template>
	<h1 class="title">
		Gestion du matériel
	</h1>
	<div class="body">
		<div class="add-hardware-form">
			<h2>Ajouter du matériel</h2>
			<form @submit.prevent="postHardwareItem">
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
				<input type="text" name="model" id="model" v-model="hardwareItem.model"/>
				<label for="provider">Fournisseur : </label>
				<input type="text" name="provider" id="provider" v-model="hardwareItem.provider"/>
				<label for="purchase-date">Date d'achat : </label>
				<input type="date" name="purchase-date" id="purchase-date" v-model="hardwareItem.datePurchase"/>
				<label for="price">Prix</label>
				<input type="number" name="price" id="price" v-model="hardwareItem.price"/>
				<label for="barcode">Code barre : </label>
				<input type="text" name="barCode" id="barCode" v-model="hardwareItem.barCode"/>
				<button type="submit">Ajouter</button>
			</form>
		</div>
		<div class="hardware-list">
			<h2>Liste du matériel</h2>
			<div class="search-bar">
				<form id="search">
					Rechercher : <input name="query" v-model="searchQuery" />
				</form>
				<p>{{ filteredResultsCount }}</p>
			</div>

			<GridComponent :columns="gridColumns" :heroes="gridHardwareList" :filter-key="searchQuery" />
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

.add-hardware-form {
	padding: 10px 50px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;

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

.hardware-list {
	margin: 20px 0;
	padding: 20px 30px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;

	h2 {
		padding-top: 10px;
		margin: 0;
	}

	.search-bar {
		margin-top: 20px;
		display: flex;
		justify-content: center;
		align-items: center;

		p {
			margin-left: 20px;
		}
	}
}
</style>