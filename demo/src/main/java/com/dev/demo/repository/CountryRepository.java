package com.dev.demo.repository;

import com.dev.demo.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertCountry(Country country) {
        String sql = "INSERT INTO countries (name, code) VALUES (?, ?) ON CONFLICT (code) DO NOTHING";
        jdbcTemplate.update(sql, country.getName(), country.getCode());
    }

    public List<Country> getAllCountries() {
        String sql = "SELECT name, code FROM countries";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Country(rs.getString("name"), rs.getString("code")));
    }
}
