import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://your-backend-api-url.com/api/hardware', // Base URL pour le matériel
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getHardwareByBarCode(hardwareNumber) {
    return apiClient.get(`/${hardwareNumber}`);
  },
  // Ajoutez d'autres méthodes pour le matériel si nécessaire
};
