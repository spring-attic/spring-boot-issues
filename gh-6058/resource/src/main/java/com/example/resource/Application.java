package com.example.resource;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }    
}

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable();
        http.authorizeRequests().anyRequest().authenticated();
    }
    
}

interface MovieRepository extends CrudRepository<Movie, Integer>{}

@Entity
class Movie {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String movie;
    private String director;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    
}

@Component
class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;
    
    @Override
    public void run(String... arg0) throws Exception {
        Movie movie1 = new Movie();
        movie1.setMovie("Magnolia");
        movie1.setDirector("Paul Thomas Anderson");
        
        Movie movie2 = new Movie();
        movie2.setMovie("The Tree of Life");
        movie2.setDirector("Terrence Malick");
        
        Movie movie3 = new Movie();
        movie3.setMovie("The Curious Case of Benjamin Button");
        movie3.setDirector("David Fincher");
        
        movieRepository.save(Arrays.asList(movie1, movie2, movie3));
    }
    
}

@RestController
class MovieRestController {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public Iterable<Movie> getMovies(){
        return movieRepository.findAll();
    }
}
