package com.example.touristguide2.repository;

import com.example.touristguide2.models.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    private List<TouristAttraction> attractions = new ArrayList<>();

    public TouristAttractionRepository() {
        attractions.add(new TouristAttraction( "Tivoli", "Forlystelsespark", "København", List.of("Børnevenlig")));
        attractions.add(new TouristAttraction( "Den Lille Havfrue", "Berømt statue ved havnen", "København", List.of("Gratis")));
        attractions.add(new TouristAttraction("Rundetårn", "Høj bygning i Københavns centrum", "København", List.of("Historie")));
    }

    public List<TouristAttraction> getAllAttractions() {
        return attractions;
    }


    // Create
    public TouristAttraction addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
        return attraction;
    }

    // READ
    public TouristAttraction findByName(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                return attraction;
            }
        }
        return null;
    }
    // Update
    public TouristAttraction updateAttraction(String name, String newName, String newDescription) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attraction.setName(newName);
                attraction.setDescription(newDescription);
                return attraction;
            }
        }
        return null;
    }

    //Delete
    public TouristAttraction deleteAttraction(String name) {
        for (TouristAttraction attraction : attractions) {
            if (attraction.getName().equalsIgnoreCase(name)) {
                attractions.remove(attraction);
                return attraction;
            }
        }
        return null;
    }

    // Rolldown menu
    public List<String> getCities() {
        return List.of("København", "Odense", "Aarhus", "Aalborg", "Esbjerg");
    }

    public List<String> getTags() {
        return List.of("Børnevenlig", "Gratis", "Kunst", "Museum", "Natur");
    }
}

