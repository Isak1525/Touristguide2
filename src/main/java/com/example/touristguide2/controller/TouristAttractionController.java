package com.example.touristguide2.controller;

import com.example.touristguide2.models.TouristAttraction;
import com.example.touristguide2.service.TouristAttractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions")
public class TouristAttractionController {
    private TouristAttractionService service;

    public TouristAttractionController(TouristAttractionService service) {
        this.service = service;
    }


    //Find alle attraktioner
    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> touristAttractions = service.getAllAttractions();
        model.addAttribute("attractions", touristAttractions);
        return "attractionsList";
    }

    //Find bestemt attraktion
    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getAttractionByName(@PathVariable String name) {
        TouristAttraction touristAttraction = service.getAttractionByName(name);
        return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
    }

    // Add Attraction
    @PostMapping("add")
    public ResponseEntity<TouristAttraction> addAttraction(@RequestBody TouristAttraction touristAttraction) {
        service.addAttraction(touristAttraction);
        return new ResponseEntity<>(touristAttraction, HttpStatus.OK);

    }

    //update attraction
    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateAttraction(@RequestBody TouristAttraction attraction, @RequestParam String name) {
        TouristAttraction updatedAttraction = service.updateAttraction(name, attraction.getName(), attraction.getDescription());
        if (updatedAttraction != null) {
            return new ResponseEntity<>(updatedAttraction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete bestemt attraktion
    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteAttraction(@PathVariable String name) {
        TouristAttraction deletedAttraction = service.deleteAttraction(name);
        if (deletedAttraction != null) {
            return new ResponseEntity<>(deletedAttraction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
