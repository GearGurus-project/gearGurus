import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://your-backend-api-url.com/api/borrowed', // Base URL pour les emprunts
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getBorrowedItems(studentNumber) {
    return apiClient.get(`/student/${studentNumber}`);
  },
  // Ajoutez d'autres méthodes pour les emprunts si nécessaire
};
