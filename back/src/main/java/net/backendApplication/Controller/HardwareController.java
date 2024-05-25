package net.backendApplication.Controller;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Repository.HardwareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HardwareController {
    @Autowired
    private HardwareRepository hardwareRepository;

    @GetMapping("/hardware")
    public List<Hardware> getHardware() {
        return hardwareRepository.findAll();
    }
}