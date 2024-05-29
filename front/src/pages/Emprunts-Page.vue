<script>
import GridComponent from "../components/Grid-Component.vue";
import studentService from "../services/studentService";
import hardwareService from "../services/hardwareService";
import borrowedService from "../services/borrowedService";

export default {
	name: 'Emprunts-Page',
	components: {
		GridComponent
	},
	data() {
		return {
			searchNotBorrowedQuery: '',
			gridColumns: ["Catégorie", "Modèle", "Fournisseur", "Date d'achat", "Code barre"],
			gridNotBorrowedData: [
			],
			searchBorrowedQuery: '',
			gridColumnsBorrowed: ["Numéro étudiant", "Nom", "Prénom", "Groupe", "Catégorie", "Modèle", "Code barre", "Date de début", "Date de fin"],
			gridBorrowedData: [
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
				provider: '',
				purchaseDate: ''
			},
			startDate: '',
			endDate: '',
			presetPeriod: '',

			showRetakeForm: false,
			showEditForm: false,
			retake: {
				hardwareId: '',
				studentId: ''
			},
			toUpdateBorrowedItem: {
				hardwareId: '',
				studentId: '',
				startDate: '',
				endDate: ''
			},
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
		async getAvailableHardwareItemList() {
			try {
				// Requête SQL pour obtenir la liste des matériels
				const hardwareList = await hardwareService.getAvailableHardware();
				// Mettre à jour la liste des matériels
				this.gridNotBorrowedData = hardwareList.data.map(hardware => {
					return {
						"Catégorie": hardware.category,
						"Modèle": hardware.model,
						"Fournisseur": hardware.provider,
						"Date d'achat": hardware.purchaseDate,
						"Code barre": hardware.barCode
					};
				});
			} catch (error) {
				console.error(error);
			}
		},
		async getBorrowedItemList() {
			try {
				// Requête SQL pour obtenir la liste des matériels empruntés
				const borrowedList = await borrowedService.getBorrowedItems();
				// Mettre à jour la liste des matériels empruntés
				this.gridBorrowedData = borrowedList.data.map(borrowedItem => {
					return {
						"Numéro étudiant": borrowedItem.studentNumber,
						"Nom": borrowedItem.studentLastName,
						"Prénom": borrowedItem.studentFirstName,
						"Groupe": borrowedItem.studentGroup,
						"Catégorie": borrowedItem.hardwareCategory,
						"Modèle": borrowedItem.hardwareModel,
						"Code barre": borrowedItem.hardwareBarCode,
						"Date de début": borrowedItem.startDate,
						"Date de fin": borrowedItem.endDate
					};
				});
			} catch (error) {
				console.error(error);
			}
		},
		async searchStudent() {
			// Vérifier si le numéro étudiant est renseigné
			if (this.studentNumber === '') {
				alert('Veuillez entrer un numéro étudiant.');
				return;
			}

			try {
				// Requête SQL pour rechercher l'étudiant
				const student = await studentService.getStudentByStudentNumber(this.studentNumber);

				// Si l'étudiant est trouvé, mettre à jour les informations
				if (student) {
					this.studentInfo = {
						lastName: student.data.lastName,
						firstName: student.data.firstName,
						group: student.data.group_student
					};
				} else {
					console.log("Étudiant non trouvé.");
				}
			} catch (error) {
				console.error(error);
			}
		},
		async searchHardware() {
			// Vérifier si le code barre est renseigné
			if (this.hardwareNumber === '') {
				alert('Veuillez entrer un code barre.');
				return;
			}

			try {
				// Requête SQL pour rechercher le matériel
				const hardware = await hardwareService.getHardwareByBarCode(this.hardwareNumber);

				// Si le matériel est trouvé, mettre à jour les informations
				if (hardware) {
					this.hardwareInfo = {
						category: hardware.data.category,
						model: hardware.data.model,
						provider: hardware.data.provider,
						purchaseDate: hardware.data.purchaseDate
					};
				} else {
					console.log("Matériel non trouvé.");
				}
			} catch (error) {
				console.error(error);
			}
		},
		async handleBorrowClick() {
			// Vérifier si les champs sont renseignés
			if (this.studentNumber === '' || this.hardwareNumber === '' || this.startDate === '' || this.endDate === '') {
				if(this.studentNumber === '' && this.hardwareNumber === '' && this.startDate === '' && this.endDate === '') {
					alert('Veuillez renseigner tous les champs.');
				} 
				else if (this.studentNumber === '' && this.hardwareNumber === '' && this.startDate === '') {
					alert('Veuillez renseigner le numéro étudiant, le code barre et la date de début.');
				} 
				else if (this.studentNumber === '' && this.hardwareNumber === '' && this.endDate === '') {
					alert('Veuillez renseigner le numéro étudiant, le code barre et la date de fin.');
				} 
				else if (this.studentNumber === '' && this.startDate === '' && this.endDate === '') {
					alert('Veuillez renseigner le numéro étudiant, la date de début et la date de fin.');
				} 
				else if (this.hardwareNumber === '' && this.startDate === '' && this.endDate === '') {
					alert('Veuillez renseigner le code barre, la date de début et la date de fin.');
				} 
				else if (this.studentNumber === '' && this.hardwareNumber === '') {
					alert('Veuillez renseigner le numéro étudiant et le code barre.');
				} 
				else if (this.studentNumber === '' && this.startDate === '') {
					alert('Veuillez renseigner le numéro étudiant et la date de début.');
				} 
				else if (this.studentNumber === '' && this.endDate === '') {
					alert('Veuillez renseigner le numéro étudiant et la date de fin.');
				} 
				else if (this.hardwareNumber === '' && this.startDate === '') {
					alert('Veuillez renseigner le code barre et la date de début.');
				} 
				else if (this.hardwareNumber === '' && this.endDate === '') {
					alert('Veuillez renseigner le code barre et la date de fin.');
				} 
				else if (this.startDate === '' && this.endDate === '') {
					alert('Veuillez renseigner la date de début et la date de fin.');
				} 
				else if (this.studentNumber === '') {
					alert('Veuillez renseigner le numéro étudiant.');
				} 
				else if (this.hardwareNumber === '') {
					alert('Veuillez renseigner le code barre.');
				} 
				else if (this.startDate === '') {
					alert('Veuillez renseigner la date de début.');
				} 
				else if (this.endDate === '') {
					alert('Veuillez renseigner la date de fin.');
				}
				return;
			}

			// Requête SQL pour emprunter le matériel
			try {
				const borrowedItem = {
					studentNumber: this.studentNumber,
					hardwareBarCode: this.hardwareNumber,
					startDate: this.startDate,
					endDate: this.endDate
				};

				const response = await borrowedService.borrowHardware(borrowedItem);

				if (response.status === 200) {
					alert('Matériel emprunté.');
					this.resetForm();
				} else {
					alert('Erreur lors de l\'emprunt.');
				}
			} catch (error) {
				console.error(error);
			}
		},
		handlePeriodSubmit() {

		},
		searchBorrowedItem() {
			// Requête SQL pour rechercher l'emprunt
			console.log('Recherche de l\'emprunt.');

			// Réinitialiser le formulaire
			this.showEditForm = false;

			// Réinitialiser les champs
			this.searchBarCode = '';
			this.toUpdateBorrowedItem = {
				hardwareId: '',
				studentId: '',
				startDate: '',
				endDate: ''
			};
		},
		updateBorrowedItem() {
			// Requête SQL pour mettre à jour l'emprunt
			console.log('Emprunt mis à jour.');
		},
		retakeBorrowedItem() {
			// Requête SQL pour reprendre le matériel
			console.log('Matériel rendu.');
		},
		resetForm() {
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
				provider: '',
				purchaseDate: ''
			};
			this.startDate = '';
			this.endDate = '';
			this.presetPeriod = '';

			this.showRetakeForm = false;
			this.showEditForm = false;
			this.retake = {
				hardwareId: '',
				studentId: ''
			};
			this.toUpdateBorrowedItem = {
				hardwareId: '',
				studentId: '',
				startDate: '',
				endDate: ''
			};
		}
	},
	mounted() {
		this.getAvailableHardwareItemList();
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
				<h2>Liste des matériels libres</h2>
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
					<form id="search-student" @submit.prevent="searchStudent()">
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
					<form id="search-student" @submit.prevent="searchHardware()">
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
								<p>Fournisseur : {{ hardwareInfo.provider }}</p>
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
			<h2>Liste des matériels empruntés</h2>
			<div class="search-bar">
				<form id="search">
					Rechercher : <input name="query" v-model="searchBorrowedQuery" />
				</form>
				<p>{{ filteredBorrowedResultsCount }}</p>
			</div>
			<GridComponent :heroes="gridBorrowedData" :columns="gridColumnsBorrowed" :filter-key="searchBorrowedQuery" />
			<button @click="showRetakeForm = true; showEditForm = false;">Récupérer</button>
			<button @click="showEditForm = true; showRetakeForm = false;">Modifier / prolonger</button>
		</div>

		<div v-if="showRetakeForm" class="retake-borrowed-form">
			<h2>Matériel rendu</h2>
			<form @submit.prevent="retakeBorrowedItem()">
				<label for="borrowed-id">ID du matériel : </label>
				<input type="text" name="borrowed-id" id="borrowed-id" v-model="retake.hardwareId" />
				<label for="borrowed-id">ID de l'étudiant : </label>
				<input type="text" name="borrowed-id" id="borrowed-id" v-model="retake.studentId" />
				<button type="submit">Rendu</button>
				<button @click="resetForm">Annuler</button>
			</form>
		</div>

		<div v-if="showEditForm" class="update-borrowed-form">
			<h2>Modifier / Prolonger un emprunt</h2>
			<form id="search-borrowed-item" @submit.prevent="searchBorrowedItem()" class="search-borrowed-item">
				<input v-model="toUpdateBorrowedItem.hardwareId" type="text" id="searchBarCode"
					placeholder="Entrer le code barre du matériel emprunté">
				<button type="submit">Rechercher</button>
			</form>
			<form @submit.prevent="updateBorrowedItem()">
				<label for="studentId">Id de l'étudiant : </label>
				<input type="text" name="studentId" id="studentId" v-model="toUpdateBorrowedItem.studentId" />
				<label for="startDate">Date de début : </label>
				<input type="date" name="startDate" id="startDate" v-model="toUpdateBorrowedItem.startDate" />
				<label for="endDate">Date d'achat : </label>
				<input type="date" name="endDate" id="endDate" v-model="toUpdateBorrowedItem.endDate" />
				<button type="submit">Modifier</button>
				<button @click="resetForm">Annuler</button>
			</form>
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

		h2 {
			padding-top: 10px;
			margin: 0;
		}

		.search-bar {
			margin-top: 20px;
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

.retake-borrowed-form,
.update-borrowed-form {
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

.search-borrowed-item {
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
</style>