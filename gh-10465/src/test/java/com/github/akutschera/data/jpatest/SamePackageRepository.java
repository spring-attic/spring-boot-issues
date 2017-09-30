package com.github.akutschera.data.jpatest;


import org.springframework.data.jpa.repository.JpaRepository;


public interface SamePackageRepository extends JpaRepository<SamePackageEntity,Long> {

}
