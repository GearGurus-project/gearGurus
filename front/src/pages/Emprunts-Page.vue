<script>
import GridComponent from "../components/Grid-Component.vue";
// import studentService from "../services/studentService";
// import hardwareService from "../services/hardwareService";
// import borrowedService from "../services/borrowedService";

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
		{ Catégorie : 'Souris', Modèle : 'Apple Mouse', "Date d'achat" : '2020-11-01', "Code barre": '1234567894' },
		{ Catégorie : 'Souris', Modèle : 'Apple Mouse Pro', "Date d'achat" : '2020-01-08', "Code barre": '1234567895' },
		{ Catégorie : 'Casque', Modèle : 'Airpods Pro', "Date d'achat" : '2020-01-21', "Code barre": '1234567896' },
		{ Catégorie : 'Casque', Modèle : 'Airpods Max', "Date d'achat" : '2020-12-25', "Code barre": '1234567897' },
		{ Catégorie: 'Ordinateur', Modèle: 'MacBook Pro', "Date d'achat": '2020-01-01', "Code barre": '1234567890' },
		{ Catégorie: 'Ordinateur', Modèle: 'MacBook Air', "Date d'achat": '2020-01-03', "Code barre": '1234567891' },
		{ Catégorie: 'Clavier', Modèle: 'Magic Keyboard', "Date d'achat": '2020-02-01', "Code barre": '1234567892' },
		{ Catégorie: 'Clavier', Modèle: 'Not magic Keyboard', "Date d'achat": '2020-04-10', "Code barre": '1234567893' },
		{ Catégorie : 'Souris', Modèle : 'Apple Mouse', "Date d'achat" : '2020-11-01', "Code barre": '1234567894' },
		{ Catégorie : 'Souris', Modèle : 'Apple Mouse Pro', "Date d'achat" : '2020-01-08', "Code barre": '1234567895' },
		{ Catégorie : 'Casque', Modèle : 'Airpods Pro', "Date d'achat" : '2020-01-21', "Code barre": '1234567896' },
		{ Catégorie : 'Casque', Modèle : 'Airpods Max', "Date d'achat" : '2020-12-25', "Code barre": '1234567897' },
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
    };
  },
  methods: {
    searchStudent() {

		this.studentInfo = {
			nom : '',
			prenom : '',
			groupe : ''
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
		if(this.studentNumber !== '' && this.hardwareNumber !== '') {
			// Vérifier si le numéro étudiant et le code barre existent
			// const student = studentService.getStudentByStudentNumber(this.studentNumber);
			// const hardware = hardwareService.getHardwareByBarCode(this.hardwareNumber);

			// Si les deux existent, emprunter le matériel
			// borrowedService.borrowHardware(student, hardware);

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
	}

  }
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
				<GridComponent
				:heroes="gridData"
				:columns="gridColumns"
				:filter-key="searchQuery"
				/>
			</div>
			<div class="to-borrow-forms">
				<h2>Réservation</h2>
				<div class="to-borrow-student-forms">
					<h3>Etudiant</h3>
					<form id="search-student" @submit.prevent="searchStudent">
						<input v-model="studentNumber" type="text" id="studentNumber" placeholder="Entrer le numéro étudiant">
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
					<form id=""></form>
				</div>
				<button @click="handleBorrowClick">Emprunter</button>
			</div>
		</div>
		<div class="borrowed-list">

		</div>
    </div>
</template>

<style scoped>
.body {
	margin: 0 40px 20px 40px;
}

h1 {
	font-weight: bolder;
	font-style: italic;
}
.to-borrow {
	display : inline-grid;
	grid-template-columns: 1fr 1fr;
	gap : 50px;

	.no-borrowed-list {
		padding : 30px;
		border : 1px solid black;
		border-radius : 10px;
		background-color: #FFFFFF;
	}

	h3 {
		margin-bottom:20px;
	}

	form {
		margin-bottom:20px;
		width : 100%;
	}

	.to-borrow-forms {
		padding : 20px;
		border : 1px solid black;
		border-radius : 10px;
		background-color: #FFFFFF;

		h2 {
			padding-bottom : 20px;
			border-bottom : 1px solid #D9D9D9;
		}

		.to-borrow-student-forms {
			padding-bottom : 20px;
			border-bottom : 1px solid #D9D9D9;
		}

		.to-borrow-hardware-forms {
			padding-bottom : 20px;
			border-bottom : 1px solid #D9D9D9;
		}

		.to-borrow-period-forms {

		}
	}
}
</style>