package org.springframework.boot.issues.gh10465.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.issues.gh10465.config.JpaConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//@SpringBootTest(classes = { TestConfig.class, JpaConfig.class }) // not recommended
@ContextConfiguration(classes = { /* TestConfig.class, */ JpaConfig.class })
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AdvertisementRepositoryTest {
    @Inject
    private AdvertisementRepository repo;
    private Advertisement entity;

    @Before
    public void setUp() {
        entity = new Advertisement("SOME title");
    }

    @After
    public void tearDown() throws Exception {
        repo.deleteAll();
        assertThat(repo.count(), is(0L));
    }

    @Test
    public void shouldSetIdOnFirstSave() {
        entity = repo.save(entity);
        assertThat(entity.getId(), is(notNullValue()));
    }

    @Test
    public void shouldFindByTitle() {
        String title = "Find me";

        entity.setTitle(title);
        repo.save(entity);

        Advertisement foundEntity = repo.findByTitle(title).get(0);
        assertThat(foundEntity.getTitle(), is(title));
    }
}