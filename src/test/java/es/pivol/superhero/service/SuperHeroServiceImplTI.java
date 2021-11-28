package es.pivol.superhero.service;

import es.pivol.superhero.configuration.TestConfiguration;
import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.persistence.SuperHeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfiguration.class})
class SuperHeroServiceImplTI {
    @Autowired
    private SuperHeroRepository superHeroRepository;

    @Autowired
    private SuperHeroServiceImpl sut;

    @Test
    void givenCallToGetAllUsersThenShouldReturnListOfAllProduct() {
        SuperHero superHero = new SuperHero();
        superHero.setName("Marcos");
        sut.create(superHero);
        sut.findAll();
        sut.findAll();
        sut.findAll();
        sut.findAll();

        verify(superHeroRepository, times(1)).findAll();
    }
}