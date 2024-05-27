import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/user', // Assurez-vous que le backend est configuré pour gérer ces routes
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getUsers() {
    return apiClient.get('');
  },
  createUser(user) {
    return apiClient.post('/', user);
  },
  deleteUser(userId) {
    return apiClient.delete(`/${userId}`);
  }
};
