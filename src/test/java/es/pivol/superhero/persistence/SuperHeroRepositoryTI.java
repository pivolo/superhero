package es.pivol.superhero.persistence;

import es.pivol.superhero.model.SuperHero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql("/data-h2-test.sql")
public class SuperHeroRepositoryTI {
    @Autowired
    private SuperHeroRepository sut;

    @Test
    public void findByIdTest(){
        final long id = 1000l;
        Optional<SuperHero> actual = sut.findById(id);
        assertTrue(actual.isPresent(), "Id " + id + " no existente");
    }

    @Test
    public void findByName(){
        final String name ="man";
        List<SuperHero> actual = sut.findByNameContainingIgnoreCase(name);
        assertFalse(actual.isEmpty(), "No Hay superheroes que contienen man");
    }

    @Test
    public void create(){
        SuperHero batman = SuperHero.builder().name("Batman").build();
        SuperHero actual = sut.save(batman);
        assertNotNull(actual.getId());
    }

    @Test
    public void delete(){
        SuperHero superman = SuperHero.builder().id(1000l).build();
        sut.delete(superman);
    }

}