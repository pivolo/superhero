package es.pivol.superhero.persistence;

import es.pivol.superhero.model.SuperHero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
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