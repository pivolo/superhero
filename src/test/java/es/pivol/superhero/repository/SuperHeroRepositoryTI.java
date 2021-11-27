package es.pivol.superhero.repository;

import es.pivol.superhero.model.SuperHero;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/data-h2.sql")
public class SuperHeroRepositoryTI {
    @Autowired
    private SuperHeroRepository sut;

    @Test
    public void findByIdTest(){
        final long id = 1000l;
        Optional<SuperHero> actual = sut.findById(id);
        assertTrue(actual.isPresent(), "Id " + id + " existente");
    }

}