package com.github.akutschera.data.jpatest;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SamePackageEntity.class})
@DataJpaTest
public class SamePackageEntityTest {

    @Autowired
    private SamePackageRepository samePackageRepository;

    @Test
    public void repositoryShouldHaveBeenInitialized() throws Exception {
        assertThat( samePackageRepository.count() ).isEqualTo( 0L );
    }

}
