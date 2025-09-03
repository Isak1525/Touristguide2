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


    // Find alle attraktioner
    @GetMapping
    public String getAllAttractions(Model model) {
        List<TouristAttraction> touristAttractions = service.getAllAttractions();
        model.addAttribute("attractions", touristAttractions);
        return "attractionsList";
    }

    // Find bestemt attraktion
    @GetMapping("{name}")
    public String getAttractionByName(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = service.getAttractionByName(name);
        model.addAttribute("attractions", touristAttraction);
        return "attraction";
    }

    //Attraction tag
    @GetMapping("{name}/tags")
    public String showTags(@PathVariable String name, Model model) {
        TouristAttraction touristattraction = service.getAttractionByName(name);
        if (touristattraction == null) {
            throw new IllegalArgumentException("Attraktion ikke fundet.");
        }
        model.addAttribute("attraction", touristattraction);
    }


    // Create Form
    @GetMapping("add")
    public String showAddForm(Model model) {
        model.addAttribute("attraction", new TouristAttraction());
        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());
        return "addAttraction";
    }

     @PostMapping("/save")
        public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
            service.addAttraction(attraction);
            return "redirect:/attractions";
        }

    @GetMapping("/{name}/edit")
    public String showEditForm(@PathVariable String name, Model model) {
        model.addAttribute("attraction", service.getAttractionByName(name));
        model.addAttribute("cities", service.getCities());
        model.addAttribute("tags", service.getTags());
        return "editAttraction";
    }

    //Update Submit
    @PostMapping("/{name}/update")
    public String updateAttraction(@PathVariable String name, @ModelAttribute TouristAttraction updatedAttraction) {
        service.updateAttraction(name, updatedAttraction.getName(), updatedAttraction.getDescription());
        return "redirect:/attractions";
    }

    // DELETE
    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        service.deleteAttraction(name);
        return "redirect:/attractions";
    }
}
