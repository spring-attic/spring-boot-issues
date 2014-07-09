package com.gh1216.repository;

import com.gh1216.domain.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {
}
