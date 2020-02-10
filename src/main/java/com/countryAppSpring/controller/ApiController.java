package com.countryAppSpring.controller;

import com.countryAppSpring.model.Country;
import com.countryAppSpring.service.CountryRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

@Component
public class ApiController {

    private RestTemplate restTemplate;
    private CountryRepository countryRepository;


    @Autowired
    public ApiController(RestTemplate restTemplate, CountryRepository countryRepository) {
        this.restTemplate = restTemplate;
        this.countryRepository = countryRepository;
    }


    void downloadData() throws IOException {
        if (countryRepository.count() == 0) {
            String restCountriesAPI = "https://restcountries.eu/rest/v2/all";
            URL url = new URL(restCountriesAPI);
            URLConnection connection = url.openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            String text = "";

            while (scanner.hasNextLine()) {
                text = text.concat(scanner.nextLine());
            }

            List<LinkedTreeMap> temp;
            Gson gson = new Gson();
            temp = gson.fromJson(text, List.class);

            for (LinkedTreeMap linkedTreeMap : temp) {
                Country country = new Country();

                JsonObject jsonObject = gson.toJsonTree(linkedTreeMap).getAsJsonObject();
                country = gson.fromJson(jsonObject, Country.class);
                if (!(country.getCapital().isEmpty() || country.getRegion().isEmpty())) {
                    countryRepository.save(country);
                    System.out.println("Added: " + country.getName() + " " + country.getCapital() + " " + country.getRegion());

                }
            }
        }

    }
}
