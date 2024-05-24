import axios from 'axios';

const apiClient = axios.create({
	baseURL: 'http://localhost:8081/api/borrowed', // Base URL pour les emprunts
	timeout: 1000,
	headers: { 'Content-Type': 'application/json' },
});

export default {
	getBorrowedItemsById(studentNumber) {
		return apiClient.get(`/student/${studentNumber}`);
	},
	getBorrowedItems() {
		return apiClient.get('/');
	},
	getBorrowedItemByBarCode(barCode) {
		return apiClient.get(`/${barCode}`);
	},
	createBorrowedItem(borrowedItem) {
		return apiClient.post('/', borrowedItem);
	},
	updateBorrowedItem(borrowedItem) {
		return apiClient.put(`/${borrowedItem.barCode}`, borrowedItem);
	},
	deleteBorrowedItem(barCode) {
		return apiClient.delete(`/${barCode}`);
	},
};
