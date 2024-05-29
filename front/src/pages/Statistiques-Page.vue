<template>
	<div class="statistics-page">
		<h1 class="page-title">Statistics</h1>

		<div class="statistic-card user-count">
			<h2 class="card-title">Number of Users</h2>
			<div class="card-content">{{ userCount }}</div>
		</div>

		<!-- Total number of borrowed materials -->
		<div class="statistic-card total-materials">
			<h2 class="card-title">Total Borrowed Materials</h2>
			<div class="card-content">{{ totalBorrowedMaterials }}</div>
		</div>

		<div class="statistic-card available-hardware">
			<h2 class="card-title">Available Hardware</h2>
			<div class="card-content">{{ availableHardwareCount }}</div>
		</div>

		<!-- Hardware counts by price range -->
		<div class="statistic-card price-range">
			<h2 class="card-title">Hardware Counts by Price Range</h2>
			<div class="card-content" v-if="hardwareCounts.length >= 3">
				<div>0 - 500 Euros: {{ hardwareCounts[0] }}</div>
				<div>500 - 1000 Euros: {{ hardwareCounts[1] }}</div>
				<div>Above 1000 Euros: {{ hardwareCounts[2] }}</div>
			</div>
			<div class="card-content" v-else>
				Loading...
			</div>
		</div>

		<!-- Best producer -->
		<div class="statistic-card best-producer">
			<h2 class="card-title">Best Producer</h2>
			<div class="card-content">{{ bestProducer }}</div>
		</div>

		<!-- Best category -->
		<div class="statistic-card best-category">
			<h2 class="card-title">Best Category</h2>
			<div class="card-content">{{ bestCategory }}</div>
		</div>
	</div>
</template>
  
  
<script>
export default {
	name: 'Statistiques-Page',
	data() {
		return {
			totalBorrowedMaterials: 0,
			bestProducer: '',
			bestCategory: '',
			hardwareCounts: [], // Initialize hardwareCounts as an empty array
			availableHardwareCount: 0,
			userCount: 0
		};
	},
	methods: {
		// Fetch statistics data from backend API and update data properties
		async fetchStatistics() {
			this.totalBorrowedMaterials = await this.fetchTotalBorrowedMaterials();
			this.availableHardwareCount = await this.fetchAvailableHardwareCount();
			this.userCount = await this.fetchUserCount();
			this.bestProducer = await this.fetchBestProducer();
			this.bestCategory = await this.fetchBestCategory();
			await this.fetchHardwareCountsByPriceRange(); // Update hardwareCounts directly
		},

		async fetchTotalBorrowedMaterials() {
			try {
				const response = await fetch('http://localhost:8080/api/borrowed/count');
				if (!response.ok) {
					throw new Error('Failed to fetch total borrowed materials');
				}
				return await response.json();
			} catch (error) {
				console.error('Error fetching total borrowed materials:', error);
				return 0;
			}
		},

		async fetchAvailableHardwareCount() {
			try {
				const response = await fetch('http://localhost:8080/api/hardware/available/count');
				if (!response.ok) {
					throw new Error('Failed to fetch available hardware count');
				}
				return await response.json();
			} catch (error) {
				console.error('Error fetching available hardware count:', error);
				return 0;
			}
		},

		async fetchUserCount() {
			try {
				const response = await fetch('http://localhost:8080/api/users/count');
				if (!response.ok) {
					throw new Error('Failed to fetch user count');
				}
				return await response.json();
			} catch (error) {
				console.error('Error fetching user count:', error);
				return 0;
			}
		},

		async fetchBestProducer() {
			try {
				const response = await fetch('http://localhost:8080/api/hardware/best-producer');
				if (!response.ok) {
					throw new Error('Failed to fetch best producer');
				}
				return await response.text();
			} catch (error) {
				console.error('Error fetching best producer:', error);
				return '';
			}
		},

		async fetchBestCategory() {
			try {
				const response = await fetch('http://localhost:8080/api/hardware/best-category');
				if (!response.ok) {
					throw new Error('Failed to fetch best category');
				}
				return await response.text();
			} catch (error) {
				console.error('Error fetching best category:', error);
				return '';
			}
		},

		async fetchHardwareCountsByPriceRange() {
			try {
				const response = await fetch('http://localhost:8080/api/hardware/count/price-range');
				if (!response.ok) {
					throw new Error('Failed to fetch hardware counts by price range');
				}
				this.hardwareCounts = await response.json();
			} catch (error) {
				console.error('Error fetching hardware counts by price range:', error);
				this.hardwareCounts = [0, 0, 0]; // Set default values in case of an error
			}
		}
	},

	mounted() {
		// Fetch statistics data when the component is mounted
		this.fetchStatistics();
	}
}
</script>


  
<style scoped>
.statistics-page {
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
	padding: 20px;
}

.page-title {
	font-size: 40px;
	margin-bottom: 20px;
	font-weight: bolder;
	font-style: italic;
}

.statistic-card {
	width: calc(50% - 20px);
	/* Two cards per row with spacing */
	min-width: 200px;
	background-color: white;
	border-radius: 20px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
	transition: transform 0.3s ease;
}

.statistic-card:last-child {
	margin-bottom: 100px;
	/* Adjust margin to push the footer down */
}

.statistic-card:first-child {
	margin-top: 100px;
	/* Adjust margin to push the footer down */
}

.statistic-card:hover {
	transform: translateY(-10px);
}

.card-title {
	font-size: 20px;
	margin-bottom: 10px;
	font-weight: bold;
	color: #201658;
}

.card-content {
	font-size: 18px;
}

.card-list {
	list-style-type: none;
	padding: 0;
}

.list-item {
	margin-bottom: 5px;
}</style>
