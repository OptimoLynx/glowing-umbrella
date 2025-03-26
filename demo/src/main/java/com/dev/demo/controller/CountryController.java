package com.dev.demo.controller;

import com.dev.demo.model.Country;
import com.dev.demo.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "Operations for retrieving and inserting countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(summary = "Load countries from JSON file into PostgreSQL")
    @PostMapping("/load")
    public ResponseEntity<String> loadCountries() {
        countryService.loadCountriesFromJson();
        return ResponseEntity.ok("Countries inserted successfully.");
    }

    @Operation(summary = "Retrieve all countries from PostgreSQL")
    @GetMapping
    public ResponseEntity<List<Country>> getCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }
}
