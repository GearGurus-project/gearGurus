<script>
import GridComponent from "../components/Grid-Component.vue";

export default {
	name: 'Materiels-Page',
	components: {
		GridComponent,
	},
	data() {
		return {
			searchQuery: '',
			gridColumns: ["Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Prix", "Code barre"],
			gridHardwareList: [
				{ Catégorie: "Ordinateur", Modèle: "Airpods Pro", Fournisseur: "Apple", "Date d'achat": "2024-03-21", Prix: "180€", "Code barre": "1234567890123" },
				{ Catégorie: "Ordinateur", Modèle: "Airpods Pro", Fournisseur: "Apple", "Date d'achat": "2024-04-11", Prix: "180€", "Code barre": "1234567890124" },
				{ Catégorie: "Ordinateur", Modèle: "Airpods Max", Fournisseur: "Apple", "Date d'achat": "2024-05-21", Prix: "480€", "Code barre": "1234567890125" },
				{ Catégorie: "Ordinateur", Modèle: "Airpods 2", Fournisseur: "Apple", "Date d'achat": "2024-06-21", Prix: "180€", "Code barre": "1234567890126" },
				{ Catégorie: "Ordinateur", Modèle: "Mac", Fournisseur: "Apple", "Date d'achat": "2024-07-21", Prix: "1180€", "Code barre": "1234567890127" },
				{ Catégorie: "Ordinateur", Modèle: "iPhone", Fournisseur: "Apple", "Date d'achat": "2024-08-21", Prix: "780€", "Code barre": "1234567890128" },
				{ Catégorie: "Ordinateur", Modèle: "iPad", Fournisseur: "Apple", "Date d'achat": "2024-09-21", Prix: "580€", "Code barre": "1234567890129" },
				{ Catégorie: "Ordinateur", Modèle: "Apple Watch", Fournisseur: "Apple", "Date d'achat": "2024-10-21", Prix: "380€", "Code barre": "1234567890130" },
				{ Catégorie: "Ordinateur", Modèle: "Apple TV", Fournisseur: "Apple", "Date d'achat": "2024-11-21", Prix: "1080€", "Code barre": "1234567890131" },
				{ Catégorie: "Ordinateur", Modèle: "HomePod", Fournisseur: "Apple", "Date d'achat": "2024-12-21", Prix: "80€", "Code barre": "1234567890132" },
				{ Catégorie: "Ordinateur", Modèle: "iPod", Fournisseur: "Apple", "Date d'achat": "2025-01-21", Prix: "80€", "Code barre": "1234567890133" },
				{ Catégorie: "Ordinateur", Modèle: "Accessoires", Fournisseur: "Apple", "Date d'achat": "2025-02-21", Prix: "30€", "Code barre": "1234567890134" },
			]
		};
	},
	methods: {
		updateResultsCount(count) {
			this.resultsCount = count;
		},
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
			<form>
				<label for="category">Catégorie</label>
				<select name="category" id="category">
					<option value="computer">Ordinateur</option>
					<option value="printer">Imprimante</option>
					<option value="scanner">Scanner</option>
					<option value="screen">Écran</option>
					<option value="keyboard">Clavier</option>
					<option value="mouse">Souris</option>
					<option value="other">Autre</option>
				</select>
				<label for="model">Modèle : </label>
				<input type="text" name="model" id="model" />
				<label for="supplier">Fournisseur : </label>
				<input type="text" name="supplier" id="supplier" />
				<label for="purchase-date">Date d'achat : </label>
				<input type="date" name="purchase-date" id="purchase-date" />
				<label for="price">Prix</label>
				<input type="number" name="price" id="price" />
				<label for="barcode">Code barre : </label>
				<input type="text" name="barcode" id="barcode" />
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