package org.springframework.boot.issues.gh10465.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    List<Advertisement> findByTitle(String title);
}