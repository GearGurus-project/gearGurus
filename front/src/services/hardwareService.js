import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8081/api/hardware', // Base URL pour le matériel
  timeout: 1000,
  headers: { 'Content-Type': 'application/json' },
});

export default {
  getHardwareByBarCode(hardwareNumber) {
    return apiClient.get(`/${hardwareNumber}`);
  },
  createHardware(hardware) {
	return apiClient.post('/', hardware);
  },
  updateHardware(hardware) {
	return apiClient.put(`/${hardware.barCode}`, hardware);
  },
  deleteHardware(hardwareNumber) {
	return apiClient.delete(`/${hardwareNumber}`);
  }
};
