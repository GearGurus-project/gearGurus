package net.backendApplication.Repository;

import net.backendApplication.Entities.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}
