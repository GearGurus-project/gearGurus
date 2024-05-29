package net.backendApplication.Controller;

import net.backendApplication.Entities.Borrowed;
import net.backendApplication.Entities.BorrowedId;
import net.backendApplication.Services.BorrowedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrowed")
public class BorrowedController {
    @Autowired
    private BorrowedServices borrowedService;

    @GetMapping
    public List<Borrowed> getAllBorrowedRecords() {
        return borrowedService.getAllBorroweds();
    }

    @GetMapping("/{idHardware}/{idStudent}")
    public ResponseEntity<Borrowed> getBorrowedRecordById(@PathVariable int idHardware, @PathVariable int idStudent) {
        BorrowedId borrowedId = new BorrowedId();
        borrowedId.setIdHardware(idHardware);
        borrowedId.setIdStudent(idStudent);
        Optional<Borrowed> borrowed = borrowedService.getBorrowedById(borrowedId);
        return borrowed.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Borrowed createBorrowedRecord(@RequestBody Borrowed borrowed) {
        return borrowedService.saveBorrowed(borrowed);
    }

    @PutMapping("/{idHardware}/{idStudent}")
    public ResponseEntity<Borrowed> updateBorrowedRecord(@PathVariable int idHardware, @PathVariable int idStudent, @RequestBody Borrowed borrowedDetails) {
        BorrowedId id = new BorrowedId();
        id.setIdHardware(idHardware);
        id.setIdStudent(idStudent);

        Optional<Borrowed> borrowed = borrowedService.getBorrowedById(id);
        if (borrowed.isPresent()) {
            Borrowed updatedBorrowed = borrowed.get();
            updatedBorrowed.setDateDebut(borrowedDetails.getDateDebut());
            updatedBorrowed.setDateFin(borrowedDetails.getDateFin());
            borrowedService.saveBorrowed(updatedBorrowed);
            return ResponseEntity.ok(updatedBorrowed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idHardware}/{idStudent}")
    public ResponseEntity<Void> deleteBorrowedRecord(@PathVariable int idHardware, @PathVariable int idStudent) {
        BorrowedId id = new BorrowedId();
        id.setIdHardware(idHardware);
        id.setIdStudent(idStudent);
        Optional<Borrowed> borrowed = borrowedService.getBorrowedById(id);
        if (borrowed.isPresent()) {
            borrowedService.deleteBorrowed(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
