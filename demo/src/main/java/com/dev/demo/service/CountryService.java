package com.dev.demo.service;

import com.dev.demo.model.Country;
import com.dev.demo.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ObjectMapper objectMapper;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Autowired
    public void loadCountriesFromJson() {
        try {
            InputStream inputStream = new ClassPathResource("countries.json").getInputStream();
            List<Country> countries = objectMapper.readValue(inputStream, new TypeReference<>() {});
            countries.forEach(countryRepository::insertCountry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Country> getAllCountries() {
        return countryRepository.getAllCountries();
    }
}
