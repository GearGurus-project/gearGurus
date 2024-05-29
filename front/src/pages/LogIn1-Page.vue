<template>
	<div class="login-page">
		<div class="left-color">
			<!-- Logo centered vertically -->
			<div class="logo-container">
				<img src="../assets/Efrei-Logo.png" alt="Logo">
			</div>
		</div>
		<div class="right-color">
			<!-- Button centered vertically -->
			<div class="button-container">
				<h1>BIENVENUE</h1>
				<h2>à la plateforme GearGurus</h2>
				<button class="login-button" @click="goToLoginPage2">Se connecter</button>
			</div>
		</div>
	</div>
</template>
  
<script>
export default {
	name: 'LogIn1Page',
	methods: {
		goToLoginPage2() {
			this.$router.push('/Login2');
		}
	}
}
</script>
  
<style scoped>
.login-page {
	display: flex;
	height: 100vh;
}

.left-color {
	background-color: #F9E8C9;
	/* Specify your left color */
	flex: 3;
	display: flex;
	justify-content: center;
	/* Center content horizontally */
	align-items: center;
	/* Center content vertically */
}

.logo-container img {
	width: 600px;
}

.right-color {
	background-color: #147BBF;
	/* Specify your right color */
	flex: 1;
	display: flex;
	justify-content: center;
	/* Center content horizontally */
	align-items: center;
	/* Center content vertically */
}

.button-container {
	text-align: center;

	/* Center button text */
	h1 {
		font-size: 75px;
		color: white;

	}

	h2 {
		font-size: 30px;
		color: white;

	}

}

.login-button {
	background-color: #201658;
	color: white;
	border: none;
	border-radius: 20px;
	padding: 15px 30px;
	font-size: 24px;
	cursor: pointer;
	transition: background-color 0.3s;
	margin-top: 20%;
}

.login-button:hover {
	background-color: #1b1453;
	/* Darker shade of blue on hover */
}</style>
  