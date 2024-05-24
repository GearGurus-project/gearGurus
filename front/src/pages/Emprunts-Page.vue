<script>
import GridComponent from "../components/Grid-Component.vue";
//import studentService from "../services/studentService";
//import hardwareService from "../services/hardwareService";
//import borrowedService from "../services/borrowedService";

export default {
	name: 'Emprunts-Page',
	components: {
		GridComponent,
		//studentService,
		//hardwareService,
		//borrowedService
	},
	data() {
		return {
			searchNotBorrowedQuery: '',
			gridColumns: ["Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Code barre"],
			gridNotBorrowedData: [
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-01', "Code barre": '1234567890' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Air', Fournisseur: "Apple", "Date d'achat": '2020-01-03', "Code barre": '1234567891' },
				{ Catégorie: 'Clavier', Modèle: 'Magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-02-01', "Code barre": '1234567892' },
				{ Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-04-10', "Code barre": '1234567893' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse', Fournisseur: "Apple", "Date d'achat": '2020-11-01', "Code barre": '1234567894' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-08', "Code barre": '1234567895' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-21', "Code barre": '1234567896' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Max', Fournisseur: "Apple", "Date d'achat": '2020-12-25', "Code barre": '1234567897' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-01', "Code barre": '1234567890' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Air', Fournisseur: "Apple", "Date d'achat": '2020-01-03', "Code barre": '1234567891' },
				{ Catégorie: 'Clavier', Modèle: 'Magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-02-01', "Code barre": '1234567892' },
				{ Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-04-10', "Code barre": '1234567893' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse', Fournisseur: "Apple", "Date d'achat": '2020-11-01', "Code barre": '1234567894' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-08', "Code barre": '1234567895' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-21', "Code barre": '1234567896' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Max', Fournisseur: "Apple", "Date d'achat": '2020-12-25', "Code barre": '1234567897' },
			],
			searchBorrowedQuery: '',
			gridColumnsBorrowed: ["Numéro étudiant", "Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Code barre", "Date de début", "Date de fin"],
			gridBorrowedData: [
				{ "Numéro étudiant": '123456', Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-01', "Code barre": '234567890', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123457', Catégorie: 'Ordinateur', Modèle: 'MacBook Air', Fournisseur: "Apple", "Date d'achat": '2020-01-03', "Code barre": '234567891', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123457', Catégorie: 'Clavier', Modèle: 'Magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-02-01', "Code barre": '234567892', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123458', Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-04-10', "Code barre": '234567893', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123458', Catégorie: 'Souris', Modèle: 'Apple Mouse', Fournisseur: "Apple", "Date d'achat": '2020-11-01', "Code barre": '234567894', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123459', Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-08', "Code barre": '234567895', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123459', Catégorie: 'Casque', Modèle: 'Airpods Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-21', "Code barre": '234567896', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123451', Catégorie: 'Casque', Modèle: 'Airpods Max', Fournisseur: "Apple", "Date d'achat": '2020-12-25', "Code barre": '234567897', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123451', Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', Fournisseur: "Apple", "Date d'achat": '2020-01-01', "Code barre": '234567890', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123452', Catégorie: 'Ordinateur', Modèle: 'MacBook Air', Fournisseur: "Apple", "Date d'achat": '2020-01-03', "Code barre": '234567891', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123452', Catégorie: 'Clavier', Modèle: 'Magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-02-01', "Code barre": '234567892', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123453', Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', Fournisseur: "Apple", "Date d'achat": '2020-04-10', "Code barre": '234567893', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123456', Catégorie: 'Souris', Modèle: 'Apple Mouse', Fournisseur: "Apple", "Date d'achat": '2020-11-01', "Code barre": '234567894', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
			],
			studentNumber: '',
			studentInfo: {
				lastName: '',
				firstName: '',
				group: ''
			},
			hardwareNumber: '',
			hardwareInfo: {
				category: '',
				model: '',
				supplier: '',
				purchaseDate: ''
			},
			startDate: '',
			endDate: '',
			presetPeriod: '',
		};
	},
	computed: {
		filteredNotBorrowedResultsCount() {
			const count = this.gridNotBorrowedData.filter(row => {
				return Object.keys(row).some(key => {
					return String(row[key]).toLowerCase().includes(this.searchNotBorrowedQuery.toLowerCase());
				});
			}).length;

			return count + ' ' + (count === 1 ? 'résultat trouvé' : 'résultats trouvés');
		},
		filteredBorrowedResultsCount() {
			const count = this.gridBorrowedData.filter(row => {
				return Object.keys(row).some(key => {
					return String(row[key]).toLowerCase().includes(this.searchBorrowedQuery.toLowerCase());
				});
			}).length;

			return count + ' ' + (count === 1 ? 'résultat trouvé' : 'résultats trouvés');
		}
	},
	methods: {
		searchStudent() {

			this.studentInfo = {
				nom: '',
				prenom: '',
				groupe: ''
			}

			if (this.studentNumber === '123456') {
				this.studentInfo = {
					lastName: 'ZHAN',
					firstName: 'Ulrich',
					group: 'LSI1'
				};
			} else {
				this.studentInfo = {
					lastName: 'Inconnu',
					firstName: '',
					group: ''
				};
			}

			// Requete SQL pour trouver l'étudiant
			// const student = studentService.getStudentByStudentNumber(this.studentNumber);
		},
		searchHardware() {
			// Réinitialiser les informations du matériel
			this.hardwareInfo = {
				category: '',
				model: '',
				supplier: '',
				purchaseDate: ''
			};

			// Rechercher le matériel dans gridData
			const hardware = this.gridData.find(item => item["Code barre"] === this.hardwareNumber);

			// Requete SQL pour trouver le matériel
			// const hardware = hardwareService.getHardwareByBarCode(this.hardwareNumber);

			// Si le matériel est trouvé, mettre à jour les informations
			if (hardware) {
				this.hardwareInfo = {
					category: hardware.Catégorie,
					model: hardware.Modèle,
					supplier: hardware.Fournisseur,
					purchaseDate: hardware["Date d'achat"]
				};
			} else {
				console.log("Matériel non trouvé.");
			}
		},
		handleBorrowClick() {
			// Requête SQL pour emprunter le matériel
			if (this.studentNumber !== '' && this.hardwareNumber !== '' && this.startDate !== '' && this.endDate !== '') {
				// Vérifier si le numéro étudiant, le code barre et les dates existent
				// const student = studentService.getStudentByStudentNumber(this.studentNumber);
				// const hardware = hardwareService.getHardwareByBarCode(this.hardwareNumber);

				// Validation de base pour vérifier que les dates sont définies
				if (!this.startDate || !this.endDate) {
					alert('Veuillez sélectionner les deux dates.');
					return;
				} else {
					// Validation pour vérifier que la date de fin est après la date de début
					if (this.startDate > this.endDate) {
						alert('La date de fin doit être après la date de début.');
						return;
					}
				}

				console.log('date de début : ' + this.startDate);
				console.log('date de fin : ' + this.endDate);

				// Si les trois existent, emprunter le matériel
				// if(student && hardware && startDate && endDate) {
				// borrowedService.borrowHardware(student, hardware, startDate, endDate);
				// }

				// et réinitialiser les champs
				console.log("Emprunt effectué.");

				this.studentNumber = '';
				this.hardwareNumber = '';
				this.studentInfo = {
					lastName: '',
					firstName: '',
					group: ''
				};
				this.hardwareInfo = {
					category: '',
					model: '',
					supplier: '',
					purchaseDate: ''
				};


			} else {
				// Affichage d'une erreur si les champs ne sont pas remplis sur l'écran
				console.log("Erreur lors de l'emprunt.");
			}
		},
		handlePeriodSubmit() {

		},
	},
};
</script>

<template>
	<h1 class="title">
		Gestion des emprunts
	</h1>
	<div class="body">
		<div class="to-borrow">
			<div class="no-borrowed-list">
				<div class="search-bar">
					<form id="search">
						Rechercher : <input name="query" v-model="searchNotBorrowedQuery" />
					</form>
					<p>{{ filteredNotBorrowedResultsCount }}</p>
				</div>
				<GridComponent :heroes="gridNotBorrowedData" :columns="gridColumns" :filter-key="searchNotBorrowedQuery" />
			</div>
			<div class="to-borrow-forms">
				<h2>Réservation</h2>
				<div class="to-borrow-student-forms">
					<h3>Etudiant</h3>
					<form id="search-student" @submit.prevent="searchStudent">
						<input v-model="studentNumber" type="text" id="studentNumber"
							placeholder="Entrer le numéro étudiant">
						<button type="submit">Rechercher</button>
					</form>
					<div class="student-infos mt-3">
						<div class="row">
							<div class="col-md-4">
								<p>Nom : {{ studentInfo.lastName }}</p>
							</div>
							<div class="col-md-4">
								<p>Prénom : {{ studentInfo.firstName }}</p>
							</div>
							<div class="col-md-4">
								<p>Groupe : {{ studentInfo.group }}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="to-borrow-hardware-forms">
					<h3>Matériel</h3>
					<form id="search-student" @submit.prevent="searchHardware">
						<input v-model="hardwareNumber" type="text" id="HardwareNumber" placeholder="Entrer le code barre">
						<button type="submit">Rechercher</button>
					</form>
					<div class="student-infos mt-3">
						<div class="row">
							<div class="col-md-4">
								<p>Catégorie : {{ hardwareInfo.category }}</p>
							</div>
							<div class="col-md-4">
								<p>Modèle : {{ hardwareInfo.model }}</p>
							</div>
							<div class="col-md-4">
								<p>Fournisseur : {{ hardwareInfo.supplier }}</p>
							</div>
							<div class="col-md-4">
								<p>Date d'achat : {{ hardwareInfo.purchaseDate }}</p>
							</div>
						</div>
					</div>
				</div>
				<div class="to-borrow-period-forms">
					<h3>Période d'emprunt</h3>
					<form>
						<div class="form-group">
							<label for="startDate">Date de début :</label>
							<input type="date" class="form-control" id="startDate" v-model="startDate" />
						</div>

						<div class="form-group">
							<label for="endDate">Date de fin :</label>
							<input type="date" class="form-control" id="endDate" v-model="endDate" />
						</div>
					</form>
				</div>

				<button @click="handleBorrowClick">Emprunter</button>
			</div>
		</div>
		<div class="borrowed-list">
			<h2>Liste du matériel</h2>
			<div class="search-bar">
				<form id="search">
					Rechercher : <input name="query" v-model="searchBorrowedQuery" />
				</form>
				<p>{{ filteredBorrowedResultsCount }}</p>
			</div>
			<GridComponent :heroes="gridBorrowedData" :columns="gridColumnsBorrowed" :filter-key="searchBorrowedQuery" />
		</div>
	</div>
</template>

<style scoped>
.body {
	margin: 0 50px 20px 50px;
}

.title {
	font-weight: bolder;
	font-style: italic;
}

.to-borrow {
	display: inline-grid;
	grid-template-columns: 1fr 1fr;
	gap: 50px;
	margin-bottom: 20px;

	.no-borrowed-list {
		padding: 30px;
		border: 1px solid black;
		border-radius: 10px;
		background-color: #FFFFFF;

		.search-bar {
			display: flex;
			justify-content: space-between;
		}
	}

	h3 {
		margin-bottom: 20px;
	}

	.to-borrow-forms {
		padding: 20px;
		border: 1px solid black;
		border-radius: 10px;
		background-color: #FFFFFF;

		h2 {
			padding-bottom: 20px;
			border-bottom: 1px solid #D9D9D9;
		}

		form {
			input {
				padding: 5px;
				margin-bottom: 20px;
				border-radius: 5px;
				border: 1px solid #D9D9D9;
				width: 70%;
			}
			button {
				border-radius: 5px;
				padding: 5px 20px;
				background-color: #98ABEE;
				color: white;
				border: none;
				cursor: pointer;
			}
		}

		.row {
			margin-top: 20px;
		}

		.to-borrow-student-forms {
			padding-bottom: 20px;
			border-bottom: 1px solid #D9D9D9;
		}

		.to-borrow-hardware-forms {
			padding-bottom: 20px;
			border-bottom: 1px solid #D9D9D9;
		}

		.to-borrow-period-forms {
			margin: 20px;

			label {
				display: block;
				margin: 10px 0 5px;
			}

			input[type="date"],
			select {
				display: block;
				width: 100%;
				padding: 8px;
				margin-bottom: 20px;
			}
		}

		button {
			margin: 0 10px;
			padding: 10px 20px;
			border-radius: 5px;
			background-color: #201658;
			color: white;
			border: none;
			cursor: pointer;
		}
	}
}

.borrowed-list {
	padding: 30px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;
	width: 100%;

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