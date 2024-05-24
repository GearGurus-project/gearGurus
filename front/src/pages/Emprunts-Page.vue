<script>
import GridComponent from "../components/Grid-Component.vue";
//import studentService from "../services/studentService";
//import hardwareService from "../services/hardwareService";
//import borrowedService from "../services/borrowedService";

export default {
	name: 'Emprunts-Page',
	components: {
		GridComponent
	},
	data() {
		return {
			searchQuery: '',
			gridColumns: ["Catégorie", "Modèle", "Date d'achat", "Code barre"],
			gridData: [
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', "Date d'achat": '2020-01-01', "Code barre": '1234567890' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Air', "Date d'achat": '2020-01-03', "Code barre": '1234567891' },
				{ Catégorie: 'Clavier', Modèle: 'Magic Keyboard', "Date d'achat": '2020-02-01', "Code barre": '1234567892' },
				{ Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', "Date d'achat": '2020-04-10', "Code barre": '1234567893' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse', "Date d'achat": '2020-11-01', "Code barre": '1234567894' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', "Date d'achat": '2020-01-08', "Code barre": '1234567895' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Pro', "Date d'achat": '2020-01-21', "Code barre": '1234567896' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Max', "Date d'achat": '2020-12-25', "Code barre": '1234567897' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', "Date d'achat": '2020-01-01', "Code barre": '1234567890' },
				{ Catégorie: 'Ordinateur', Modèle: 'MacBook Air', "Date d'achat": '2020-01-03', "Code barre": '1234567891' },
				{ Catégorie: 'Clavier', Modèle: 'Magic Keyboard', "Date d'achat": '2020-02-01', "Code barre": '1234567892' },
				{ Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', "Date d'achat": '2020-04-10', "Code barre": '1234567893' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse', "Date d'achat": '2020-11-01', "Code barre": '1234567894' },
				{ Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', "Date d'achat": '2020-01-08', "Code barre": '1234567895' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Pro', "Date d'achat": '2020-01-21', "Code barre": '1234567896' },
				{ Catégorie: 'Casque', Modèle: 'Airpods Max', "Date d'achat": '2020-12-25', "Code barre": '1234567897' },
			],
			gridColumnsBorrowed: ["Numéro étudiant", "Catégorie", "Modèle", "Date d'achat", "Code barre", "Date de début", "Date de fin"],
			gridDataBorrowed: [
				{ "Numéro étudiant": '123456', Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', "Date d'achat": '2020-01-01', "Code barre": '234567890', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123457', Catégorie: 'Ordinateur', Modèle: 'MacBook Air', "Date d'achat": '2020-01-03', "Code barre": '234567891', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123457', Catégorie: 'Clavier', Modèle: 'Magic Keyboard', "Date d'achat": '2020-02-01', "Code barre": '234567892', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123458', Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', "Date d'achat": '2020-04-10', "Code barre": '234567893', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123458', Catégorie: 'Souris', Modèle: 'Apple Mouse', "Date d'achat": '2020-11-01', "Code barre": '234567894', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123459', Catégorie: 'Souris', Modèle: 'Apple Mouse Pro', "Date d'achat": '2020-01-08', "Code barre": '234567895', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123459', Catégorie: 'Casque', Modèle: 'Airpods Pro', "Date d'achat": '2020-01-21', "Code barre": '234567896', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123451', Catégorie: 'Casque', Modèle: 'Airpods Max', "Date d'achat": '2020-12-25', "Code barre": '234567897', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123451', Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', "Date d'achat": '2020-01-01', "Code barre": '234567890', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123452', Catégorie: 'Ordinateur', Modèle: 'MacBook Air', "Date d'achat": '2020-01-03', "Code barre": '234567891', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123452', Catégorie: 'Clavier', Modèle: 'Magic Keyboard', "Date d'achat": '2020-02-01', "Code barre": '234567892', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123453', Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', "Date d'achat": '2020-04-10', "Code barre": '234567893', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
				{ "Numéro étudiant": '123456', Catégorie: 'Souris', Modèle: 'Apple Mouse', "Date d'achat": '2020-11-01', "Code barre": '234567894', "Date de début": '2021-01-01', "Date de fin": '2021-01-10' },
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
				purchaseDate: ''
			},
			startDate: '',
			endDate: '',
			presetPeriod: '',
		};
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
	<div class="body">
		<h1 class="title">
			Emprunts
		</h1>
		<div class="to-borrow">
			<div class="no-borrowed-list">
				<form id="search">
					Rechercher <input name="query" v-model="searchQuery" />
				</form>
				<GridComponent :heroes="gridData" :columns="gridColumns" :filter-key="searchQuery" />
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
			<form id="search">
				Rechercher <input name="query" v-model="searchQuery" />
			</form>
			<GridComponent :heroes="gridDataBorrowed" :columns="gridColumnsBorrowed" :filter-key="searchQuery" />
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
	}

	h3 {
		margin-bottom: 20px;
	}

	form {
		input {
			margin-bottom: 20px;
			width: 80%;
		}

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

			button {
				padding: 10px 20px;
				background-color: #007bff;
				color: white;
				border: none;
				cursor: pointer;
			}

			button:hover {
				background-color: #0056b3;
			}
		}
	}
}

.borrowed-list {
	padding: 30px;
	border: 1px solid black;
	border-radius: 10px;
	background-color: #FFFFFF;
	width: 100%;
}
</style>