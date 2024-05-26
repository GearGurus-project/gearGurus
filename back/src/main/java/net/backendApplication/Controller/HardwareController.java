package net.backendApplication.Controller;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Repository.HardwareRepository;
import net.backendApplication.Services.HardwareServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
public class HardwareController {
    @Autowired
    private HardwareServices hardwareServices;

    @GetMapping()
    public List<Hardware> getHardware() {
        return hardwareServices.getAllHardwares();
    }
    @GetMapping("/{id}")
    public Hardware getHardwareById(@PathVariable Long id) {
        return hardwareServices.getHardwareById(id);
    }
    @PostMapping
    public Hardware createHardware(@RequestBody Hardware Hardware) {
        return hardwareServices.saveHardware(Hardware);
    }
    @PutMapping("/{id}")
    public Hardware updateHardware(@PathVariable Long id, @RequestBody Hardware Hardware) {
        Hardware existingHardware = hardwareServices.getHardwareById(id);
        if (existingHardware != null) {
            existingHardware.setModel(existingHardware.getModel());
            existingHardware.setBarCode(existingHardware.getBarCode());
            existingHardware.setPrice(existingHardware.getPrice());
            existingHardware.setCategory(existingHardware.getCategory());
            existingHardware.setProvider(existingHardware.getProvider());
            existingHardware.setDatePurchase(existingHardware.getDatePurchase());
            return hardwareServices.saveHardware(existingHardware);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteHardware(@PathVariable Long id) {
        hardwareServices.deleteHardware(id);
    }

}
