import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/hardware', // Base URL pour le matériel
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getHardwareByBarCode(hardwareNumber) {
    return apiClient.get(`/barcode/${hardwareNumber}`);
  },
  postHardware(hardware) {
	return apiClient.post('', hardware);
  },
  getHardware() {
	return apiClient.get('');
  },
  createHardware(hardware) {
	return apiClient.post('/', hardware);
  },
  updateHardwareByBarCode(hardware) {
	return apiClient.put(`/barcode/${hardware.barCode}`, hardware);
  },
  deleteHardwareByBarCode(hardwareNumber) {
	return apiClient.delete(`/barcode/${hardwareNumber}`);
  },
  getAvailableHardware() {
	return apiClient.get('/available');
  },
};
