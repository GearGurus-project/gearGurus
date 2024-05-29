package net.backendApplication.Repository;

import net.backendApplication.Entities.Borrowed;
import net.backendApplication.Entities.BorrowedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowedRepository extends JpaRepository<Borrowed, BorrowedId>{
    @Query("SELECT b.id.idHardware FROM Borrowed b")
    List<Integer> findAllBorrowedHardwareIds();

    @Query("SELECT b.id.idHardware, COUNT(b.id.idHardware) AS borrowCount FROM Borrowed b GROUP BY b.id.idHardware ORDER BY borrowCount DESC")
    List<Object[]> findMostBorrowedMaterials();


}

