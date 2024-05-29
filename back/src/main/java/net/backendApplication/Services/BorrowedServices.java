package net.backendApplication.Services;

import net.backendApplication.Entities.Borrowed;
import net.backendApplication.Entities.BorrowedId;
import net.backendApplication.Repository.BorrowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowedServices {

    @Autowired
    private BorrowedRepository borrowedRepository;

    public List<Borrowed> getAllBorroweds() {
        return borrowedRepository.findAll();
    }
    public Optional<Borrowed> getBorrowedById(BorrowedId id) {
        return borrowedRepository.findById(id);
    }
    public Borrowed saveBorrowed(Borrowed borrowed) {
        return borrowedRepository.save(borrowed);
    }
    public void deleteBorrowed(BorrowedId id) {
        borrowedRepository.deleteById(id);
    }

    public List<Object[]> getMostBorrowedMaterials() {
        return borrowedRepository.findMostBorrowedMaterials();
    }

}
