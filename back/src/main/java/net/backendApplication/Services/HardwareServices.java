package net.backendApplication.Services;

import net.backendApplication.Entities.Borrowed;
import net.backendApplication.Entities.Hardware;
import net.backendApplication.Entities.User;
import net.backendApplication.Repository.BorrowedRepository;
import net.backendApplication.Repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HardwareServices {
    @Autowired
    private HardwareRepository hardwareRepository;
    @Autowired
    private BorrowedRepository borrowedRepository;
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
    public List<Hardware>
    getHardwareNotBorrowed() {
        List<Hardware> allHardware = hardwareRepository.findAll();

        return allHardware.stream()
                .filter(hardware -> hardware.getBorrowedRecords().isEmpty())
                .collect(Collectors.toList());
    }

}
