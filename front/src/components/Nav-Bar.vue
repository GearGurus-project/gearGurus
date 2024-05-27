<template>
	<div>
		<nav class="navbar navbar-expand-md navbar-dark navbar-custom">
			<!-- Logo to the right -->
			<router-link to="/" class="navbar-brand ml-auto">
				<img src="../assets/Efrei-Logo.png" alt="Logo">
			</router-link>

			<!-- Navigation links centered -->
			<div class="navbar-brand mx-auto">
				<ul class="navbar-nav">
					<li class="nav-item">
						<router-link to="/" class="nav-link">Accueil</router-link>
					</li>
					<li class="nav-item">
						<router-link to="/emprunts" class="nav-link">Emprunts</router-link>
					</li>
					<li class="nav-item">
						<router-link to="/materiels" class="nav-link">Materiels</router-link>
					</li>
					<li class="nav-item">
						<router-link to="/statistiques" class="nav-link">Statistiques</router-link>
					</li>
				</ul>
			</div>

			<!-- Disconnect button to the right -->
			<div>
				<button class="btn disconnect-btn" @click="disconnect">
					<img src="../assets/LogOut.png" alt="Disconnect">
				</button>
			</div>
		</nav>
	</div>
</template>
  
<script>
export default {
	methods: {
		disconnect() {
			// Handle disconnect logic here
		}
	}
}
</script>
  
<style scoped>
.navbar-custom {
	background-color: #201658;
	height: 80px;
	/* Adjust height as needed */
}

.navbar-brand img {
	max-height: 40px;
	margin: 5px 15px;
}

.navbar-brand li {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
}

.navbar-nav {
	list-style: none;
	/* Remove bullets */
	padding-left: 0;
	/* Remove default padding */

	list-style: none;
	/* Remove bullets */
	padding-left: 0;
	/* Remove default padding */
	display: flex;
	/* Add this line to enable flexbox */
	justify-content: center;
	/* Add this line to center the links horizontally */
	align-items: center;
	/* Asdd this line to center the links vertically */

}

.navbar-nav .nav-link {
	color: white;
	font-weight: bold;
	text-decoration: none;
	margin: 0 15px;



}

.navbar-nav .nav-link:hover {
	color: #ddd;
}

.disconnect-btn {
	background-color: transparent;
	border: none;
	position: absolute;
	right: 0;
	margin-right: 10px;
	margin-top: 10px;
}

.disconnect-btn img {
	max-height: 40px;
}</style>
  