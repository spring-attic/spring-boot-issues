package org.springframework.boot.issues.gh10465.controllers;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.boot.issues.gh10465.models.Advertisement;
import org.springframework.boot.issues.gh10465.models.AdvertisementRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequestScope
@RestController
@Validated
/*
 * Use a path which does not end with a slash! Otherwise the controller is not reachable when not using the trailing
 * slash in the URL
 */
@RequestMapping(AdvertisementController.PATH)
public class AdvertisementController {
    static final String PATH = "/api/v1/ads";
    private final AdvertisementRepository ads;

    @Inject
    public AdvertisementController(AdvertisementRepository ads) {
        this.ads = ads;
    }

    @GetMapping("/{id}")
    public Advertisement advertisementById(@PathVariable("id") @Min(0) long id) throws Exception {
        Advertisement advertisement = ads.findOne(id);
        return advertisement;
    }

    @PostMapping
    public ResponseEntity<Advertisement> add(@RequestBody @Valid Advertisement advertisement,
            UriComponentsBuilder uriComponentsBuilder) {
        
        Advertisement savedAdvertisement = ads.save(advertisement);
        UriComponents uriComponents = uriComponentsBuilder.path(PATH + "/{id}")
                .buildAndExpand(savedAdvertisement.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return new ResponseEntity<>(savedAdvertisement, headers, HttpStatus.CREATED);
    }
}
