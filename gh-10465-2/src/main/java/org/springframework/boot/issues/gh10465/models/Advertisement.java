package org.springframework.boot.issues.gh10465.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** mandatory fields **/
    @NotBlank
    @Column(name = "title")
    private String title;

       
    /**
     * Any JPA Entity needs a default constructor.
     */
    public Advertisement() {
        
    }

    public Advertisement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

   
    @Override
    public String toString() {
        return "Advertisement [id=" + id + ", title=" + title + "]";
    }

    // use only in tests or when you need to map DTO to Entity
    public void setId(Long id) {
        this.id = id;
    }
}
