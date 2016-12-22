package com.example.domain;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface SpeakerRepository extends CrudRepository<Speaker, Long> {

	Speaker findByTwitter(String twitter);

	Collection<Speaker> findByLastName(String lastName);

}
