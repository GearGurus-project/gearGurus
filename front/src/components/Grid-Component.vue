<template>
	<div class="table-container">
		<table>
			<thead>
				<tr>
					<th v-for="key in columns" @click="sortBy(key)" :class="{ active: sortKey == key }" :key="key">
						{{ capitalize(key) }}
						<span class="arrow" :class="sortOrders[key] > 0 ? 'asc' : 'dsc'">
						</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr v-for="entry in filteredHeroes" :key="entry.name">
					<td v-for="key in columns" :key="key">
						{{ entry[key] }}
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</template>

<script>
export default {
	name: 'GridComponent',
	props: {
		heroes: Array,
		columns: Array,
		filterKey: String
	},
	data() {
		let sortOrders = {};
		this.columns.forEach(key => {
			sortOrders[key] = 1;
		});
		return {
			sortKey: '',
			sortOrders: sortOrders
		};
	},
	computed: {
		filteredHeroes() {
			let sortKey = this.sortKey;
			let filterKey = this.filterKey && this.filterKey.toLowerCase();
			let order = this.sortOrders[sortKey] || 1;
			let heroes = this.heroes;
			if (filterKey) {
				heroes = heroes.filter(row => {
					return Object.keys(row).some(key => {
						return String(row[key]).toLowerCase().includes(filterKey);
					});
				});
			}
			if (sortKey) {
				heroes = heroes.slice().sort((a, b) => {
					a = a[sortKey];
					b = b[sortKey];
					return (a === b ? 0 : a > b ? 1 : -1) * order;
				});
			}
			return heroes;
		}
	},
	methods: {
		capitalize(str) {
			return str.charAt(0).toUpperCase() + str.slice(1);
		},
		sortBy(key) {
			this.sortKey = key;
			this.sortOrders[key] = this.sortOrders[key] * -1;
		}
	}
};
</script>

<style scoped>
.table-container {
	margin-top: 20px;
	height: 600px;
	/* Adjust this value as needed */
	overflow-y: auto;
	border: 2px solid #98ABEE;
	border-radius: 3px;
	background-color: #fff;
	
}

table {
	width: 100%;
	border-collapse: collapse;
}

tr {
	border-bottom: 1px solid #ddd;
}

th {
	text-align: center; /* Centrer le texte de tout le tableau */
	background-color: #98ABEE;
	color: rgba(255, 255, 255, 0.66);
	cursor: pointer;
	user-select: none;
}

td {
	background-color: #f9f9f9;
}

th,
td {
	min-width: 120px;
	padding: 10px 20px;
}

th.active {
	color: #fff;
}

th.active .arrow {
	opacity: 1;
}

.arrow {
	display: inline-block;
	vertical-align: middle;
	width: 0;
	height: 0;
	margin-left: 5px;
	opacity: 0.66;
}

.arrow.asc {
	border-left: 4px solid transparent;
	border-right: 4px solid transparent;
	border-bottom: 4px solid #fff;
}

.arrow.dsc {
	border-left: 4px solid transparent;
	border-right: 4px solid transparent;
	border-top: 4px solid #fff;
}
</style>
