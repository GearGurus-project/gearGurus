import axios from 'axios';

const apiClient = axios.create({
	baseURL: 'http://localhost:8080/api/students', // Base URL pour les étudiants
	timeout: 1000,
	headers: { 'Content-Type': 'application/json' },
});

export default {
	getStudents() {
		return apiClient.get('/');
	},
	getStudentByStudentNumber(studentNumber) {
		return apiClient.get(`/number/${studentNumber}`);
	},
};
