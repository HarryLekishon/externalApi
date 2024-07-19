package com.externalservice.controller;

import com.externalservice.entity.DataResponse;
import com.externalservice.service.PostalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/postal")
public class PostController {

    @Autowired
    PostalService postalService;

    @GetMapping(path = "/byPin")
    public List<DataResponse> getPostalByPinCode() throws URISyntaxException, IOException, InterruptedException {
        return postalService.getPostalDetails();


    }
}
