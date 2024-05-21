import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://your-backend-api-url.com/api/students', // Base URL pour les étudiants
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getStudentByStudentNumber(studentNumber) {
    return apiClient.get(`/${studentNumber}`);
  },
};
