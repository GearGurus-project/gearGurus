import axios from 'axios';

const apiClient = axios.create({
	baseURL: 'http://localhost:8081/api/students', // Base URL pour les étudiants
	timeout: 1000,
	headers: { 'Content-Type': 'application/json' },
});

export default {
	getStudents() {
		return apiClient.get('/');
	},
	getStudentByStudentNumber(studentNumber) {
		return apiClient.get(`/${studentNumber}`);
	},
	createStudent(student) {
		return apiClient.post('/', student);
	},
	updateStudent(student) {
		return apiClient.put(`/${student.studentNumber}`, student);
	},
	deleteStudent(studentNumber) {
		return apiClient.delete(`/${studentNumber}`);
	},
};
