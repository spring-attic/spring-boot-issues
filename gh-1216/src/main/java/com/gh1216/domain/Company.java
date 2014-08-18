package com.gh1216.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Version
    private long version;
    @Size(min=2, max=100)
    @NotBlank
    private String name;
    @Email
    private String busEmail;
    @URL
    private String website;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String busPh;
    @Pattern(regexp="(^$|[0-9]{10})")
    private String busFax;

    private Date createdAt;
    private Date updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusEmail() {
        return busEmail;
    }

    public void setBusEmail(String busEmail) {
        this.busEmail = busEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusPh() {
        return busPh;
    }

    public void setBusPh(String busPh) {
        this.busPh = busPh;
    }

    public String getBusFax() {
        return busFax;
    }

    public void setBusFax(String busFax) {
        this.busFax = busFax;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
