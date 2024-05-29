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
				barCode: '',
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
				hardwareBarCode: '',
				studentNumber: ''
			},
			toUpdateBorrowedItem: {
				id: {
					idStudent: '',
					idHardware: ''
				},
				dateDebut: '',
				dateFin: ''
			},
			forUpdateHardwareBarCode: '',
			forUpdateStudentNumber: ''
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
				this.gridNotBorrowedData = [];
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
				const length = borrowedList.data.length;
				this.gridBorrowedData = [];

				for (let i = 0; i < length; i++) {
					const idHardware = borrowedList.data[i].id.idHardware;
					const idStudent = borrowedList.data[i].id.idStudent;

					const hardwareDetails = await hardwareService.getHardwareById(idHardware);
					const studentDetails = await studentService.getStudentById(idStudent);

					let startDate = borrowedList.data[i].dateDebut;
					let endDate = borrowedList.data[i].dateFin;
					startDate = startDate.substring(0, 10);
					endDate = endDate.substring(0, 10);


					this.gridBorrowedData.push({
						"Numéro étudiant": studentDetails.data.studentNumber,
						"Nom": studentDetails.data.lastName,
						"Prénom": studentDetails.data.firstName,
						"Groupe": studentDetails.data.group_student,
						"Catégorie": hardwareDetails.data.category,
						"Modèle": hardwareDetails.data.model,
						"Code barre": hardwareDetails.data.barCode,
						"Date de début": startDate,
						"Date de fin": endDate
					});
				}
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
						barCode: hardware.data.barCode,
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
				if (this.studentNumber === '' && this.hardwareNumber === '' && this.startDate === '' && this.endDate === '') {
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
				const Student = await studentService.getStudentByStudentNumber(this.studentNumber);
				const Hardware = await hardwareService.getHardwareByBarCode(this.hardwareNumber);
				const borrowedItem = {
					id: {
						idStudent: Student.data.id,
						idHardware: Hardware.data.id,
					},
					dateDebut: this.startDate,
					dateFin: this.endDate
				};

				const response = await borrowedService.borrowHardware(borrowedItem);

				if (response.status === 200) {
					alert('Matériel emprunté.');
					this.getAvailableHardwareItemList();
					this.getBorrowedItemList();
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
		async searchBorrowedItem() {
			try {
				const hardware = await hardwareService.getHardwareByBarCode(this.forUpdateHardwareBarCode);
				const student = await studentService.getStudentByStudentNumber(this.forUpdateStudentNumber);

				const idHardware = hardware.data.id;
				const idStudent = student.data.id;

				console.log(idHardware, idStudent);

				const borrowedItem = await borrowedService.getBorrowedItemById(idHardware, idStudent);

				let dateDebut = borrowedItem.data.dateDebut;
				let dateFin = borrowedItem.data.dateFin;

				dateDebut = dateDebut.substring(0, 10);
				dateFin = dateFin.substring(0, 10);

				if (borrowedItem) {
					this.toUpdateBorrowedItem = {
						id: {
							idStudent: student.data.id,
							idHardware: hardware.data.id
						},
						dateDebut: dateDebut,
						dateFin: dateFin
					};
				} else {
					alert('Emprunt non trouvé.');
				}
			} catch (error) {
				if (error.response) {
					// The request was made and the server responded with a status code that falls out of the range of 2xx
					const status = error.response.status;

					if (status === 404) {
						alert('Emprunt non trouvé.');
					} else if (status === 500) {
						alert('Erreur lors de la modification.');
					} else {
						alert('Erreur lors de la modification.');
					}
				} else if (error.request) {
					// The request was made but no response was received
					console.error('Erreur de réseau ou aucune réponse reçue');
					alert('Erreur réseau. Veuillez réessayer.');
				} else {
					// Something happened in setting up the request that triggered an Error
					console.error('Erreur', error.message);
					alert('Erreur lors de la modification.');
				}
			}
		},
		async updateBorrowedItem() {
			try {
				const Hardware = await hardwareService.getHardwareByBarCode(this.forUpdateHardwareBarCode);
				const Student = await studentService.getStudentByStudentNumber(this.forUpdateStudentNumber);

				const studentId = Student.data.id;
				const hardwareId = Hardware.data.id;

				const dateDebut = this.toUpdateBorrowedItem.dateDebut;
				const dateFin = this.toUpdateBorrowedItem.dateFin;

				console.log(dateDebut, dateFin);

				const borrowedItem = {
					id: {
						idStudent: studentId,
						idHardware: hardwareId
					},
					dateDebut: dateDebut,
					dateFin: dateFin
				};

				const response = await borrowedService.updateBorrowedItem( hardwareId, studentId, borrowedItem);

				if (response.status === 200) {
					alert('Emprunt modifié.');
					this.getAvailableHardwareItemList();
					this.getBorrowedItemList();
					this.resetForm();
				} else {
					alert('Erreur lors de la modification.');
				}
			} catch (error) {
				if (error.response) {
					// The request was made and the server responded with a status code that falls out of the range of 2xx
					const status = error.response.status;

					if (status === 404) {
						alert('Emprunt non trouvé.');
					} else if (status === 500) {
						alert('Erreur lors de la modification.');
					} else {
						alert('Erreur lors de la modification.');
					}
				} else if (error.request) {
					// The request was made but no response was received
					console.error('Erreur de réseau ou aucune réponse reçue');
					alert('Erreur réseau. Veuillez réessayer.');
				} else {
					// Something happened in setting up the request that triggered an Error
					console.error('Erreur', error.message);
					alert('Erreur lors de la modification.');
				}
			}
		},
		async retakeBorrowedItem() {
			try {
				const hardware = await hardwareService.getHardwareByBarCode(this.retake.hardwareBarCode);
				const student = await studentService.getStudentByStudentNumber(this.retake.studentNumber);

				const response = await borrowedService.retakeHardware(hardware.data.id, student.data.id);

				if (response.status === 204) {
					alert('Matériel récupéré.');
					this.getAvailableHardwareItemList();
					this.getBorrowedItemList();
					this.resetForm();
				} else {
					alert('Erreur lors de la récupération.');
				}
			} catch (error) {
				if (error.response) {
					// The request was made and the server responded with a status code that falls out of the range of 2xx
					const status = error.response.status;

					if (status === 404) {
						alert('Matériel emprunté non trouvé.');
					} else if (status === 400) {
						alert('Étudiant non trouvé.');
					} else if (status === 500) {
						alert('Erreur lors de la récupération.');
					} else {
						alert('Erreur lors de la récupération.');
					}
				} else if (error.request) {
					// The request was made but no response was received
					console.error('Erreur de réseau ou aucune réponse reçue');
					alert('Erreur réseau. Veuillez réessayer.');
				} else {
					// Something happened in setting up the request that triggered an Error
					console.error('Erreur', error.message);
					alert('Erreur lors de la récupération.');
				}
			}
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
				barCode: '',
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
				hardwareBarCode: '',
				studentNumber: ''
			};
			this.toUpdateBorrowedItem = {
				id: {
					idStudent: '',
					idHardware: ''
				},
				dateDebut: '',
				dateFin: ''
			};
			this.forUpdateHardwareBarCode = '';
			this.forUpdateStudentNumber = '';
		}
	},
	mounted() {
		this.getAvailableHardwareItemList();
		this.getBorrowedItemList();
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
			<button @click="showEditForm = true; showRetakeForm = false;">Prolonger</button>
		</div>

		<div v-if="showRetakeForm" class="retake-borrowed-form">
			<h2>Matériel rendu</h2>
			<form @submit.prevent="retakeBorrowedItem()">
				<label for="borrowed-id">Code barre du matériel : </label>
				<input type="text" name="borrowed-id" id="borrowed-id" v-model="retake.hardwareBarCode" />
				<label for="borrowed-id">Numéro de l'étudiant : </label>
				<input type="text" name="borrowed-id" id="borrowed-id" v-model="retake.studentNumber" />
				<button type="submit">Rendu</button>
				<button @click="resetForm">Annuler</button>
			</form>
		</div>

		<div v-if="showEditForm" class="update-borrowed-form">
			<h2>Prolonger un emprunt</h2>
			<form id="search-borrowed-item" @submit.prevent="searchBorrowedItem()" class="search-borrowed-item">
				<input v-model="forUpdateHardwareBarCode" type="text" id="searchBarCode"
					placeholder="Entrer le code barre du matériel emprunté">
				<input v-model="forUpdateStudentNumber" type="text" id="searchStudentNumber"
					placeholder="Entrer le numéro étudiant">
				<button type="submit">Rechercher</button>
			</form>
			<form @submit.prevent="updateBorrowedItem()">
				<label for="startDate">Date de début : </label>
				<input type="date" name="dateDebut" id="dateDebut" v-model="toUpdateBorrowedItem.dateDebut" />
				<label for="endDate">Date de fin : </label>
				<input type="date" name="dateFin" id="dateFin" v-model="toUpdateBorrowedItem.dateFin" />
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