package com.externalservice.service;

import com.externalservice.entity.DataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PostalService {

    @Value("${endpoint}")
    private String endpoint;
    public List<DataResponse> getPostalDetails() throws URISyntaxException, IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(endpoint))
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> httpResponse = client.send(request,
                HttpResponse.BodyHandlers.ofString());



        return getPostOffice(httpResponse.body());
    }
    private List<DataResponse> getPostOffice(String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, new TypeReference<List<DataResponse>>() {
        });
    }

}
