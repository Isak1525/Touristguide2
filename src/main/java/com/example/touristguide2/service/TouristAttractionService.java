package com.example.touristguide2.service;

import com.example.touristguide2.models.TouristAttraction;
import com.example.touristguide2.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionService {
    private TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository repository) {
        this.repository = repository;
    }

    // Finder alle attraktioner
    public List<TouristAttraction> getAllAttractions() {
        return repository.getAllAttractions();
    }

    //Finder bestemt attraktion
    public TouristAttraction getAttractionByName(String name) {
        return repository.findByName(name);
    }

    // Add Attraction
    public List<TouristAttraction> addAttraction(TouristAttraction attraction) {
        repository.addAttraction(attraction);
        return repository.getAllAttractions();
    }

    // delete message metode
    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    // Update
    public TouristAttraction updateAttraction(String oldName, String newName, String description) {
        return repository.updateAttraction(oldName, newName, description);
    }

    public List<String> getCities() {
        return repository.getCities();
    }

    public List<String> getTags() {
        return repository.getTags();
    }
}


