import axios from 'axios';

const apiClient = axios.create({
	baseURL: 'http://localhost:8080/api/borrowed', // Base URL pour les emprunts
	timeout: 1000,
	headers: { 'Content-Type': 'application/json' },
});

export default {
	getBorrowedItems() {
		return apiClient.get('');
	},

	borrowHardware(borrowedItem) {
		return apiClient.post('/borrow', borrowedItem);
	},

	getBorrowedItemById(barCode) {
		return apiClient.get(`/${barCode}`);
	},

	getBorrowedItemsByStudentNumber(studentNumber) {
		return apiClient.get(`/student/${studentNumber}`);
	},

	createBorrowedItem(borrowedItem) {
		return apiClient.post('/', borrowedItem);
	},

	updateBorrowedItem(idHardware, idStudent, borrowedItem) {
		return apiClient.put(`/${idHardware}/${idStudent}`, borrowedItem);
	},

	deleteBorrowedItem(idHardware, idStudent) {
		return apiClient.delete(`/${idHardware}/${idStudent}`);
	},
};
