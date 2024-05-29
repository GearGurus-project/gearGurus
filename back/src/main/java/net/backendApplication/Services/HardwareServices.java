package net.backendApplication.Services;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Entities.User;
import net.backendApplication.Repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareServices {
    @Autowired
    private HardwareRepository hardwareRepository;
    public List<Hardware> getAllHardwares() {
        return hardwareRepository.findAll();
    }
    public Hardware getHardwareById(Long id) {
        return hardwareRepository.findById(id).orElse(null);
    }
    public Hardware getHardwareByBarCode(String barCode) {
        return hardwareRepository.findByBarCode(barCode);
    }
    public Hardware saveHardware(Hardware Hardware) {
        return hardwareRepository.save(Hardware);
    }
    public void deleteHardware(Long id) {
        hardwareRepository.deleteById(id);
    }
    public void deleteHardwareByBarCode(String barCode) {
        Hardware hardware = hardwareRepository.findByBarCode(barCode);
        if (hardware != null) {
            hardwareRepository.deleteById(hardware.getId());
        }
    }

}
