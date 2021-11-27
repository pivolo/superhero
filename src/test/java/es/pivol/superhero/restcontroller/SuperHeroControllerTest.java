package es.pivol.superhero.restcontroller;

import es.pivol.superhero.configuration.TestConfControllers;
import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.persistence.SuperHeroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SuperHeroControllerTest {
    @Mock
    SuperHeroRepository superHeroRepository;

    @InjectMocks
    private SuperHeroController sut;


    @Test
    void getAll() {
        List<SuperHero> heros = asList(
                SuperHero.builder().id(1l).name("Superman").build(),
                SuperHero.builder().id(2l).name("Spiderman").build());
        when(superHeroRepository.findAll()).thenReturn(heros);
        List<SuperHero> actual = sut.getAll();
        assertEquals(heros, actual);

    }

    @Test
    void findById() throws Exception {
        when(superHeroRepository.findById(1l)).thenReturn(Optional.of(SuperHero.builder().id(1l).name("Superman").build()));
        ResponseEntity<SuperHero> actual = sut.findById(1l);
        assertNotNull(actual);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void findByName() {
        List<SuperHero> heros = asList(
                SuperHero.builder().id(1l).name("Superman").build(),
                SuperHero.builder().id(2l).name("Spiderman").build());
        when(superHeroRepository.findByNameContainingIgnoreCase("man")).thenReturn(heros);
        List<SuperHero> actual = sut.findByName("man");
        assertEquals(heros, actual);
    }

    @Test
    void create() {
        SuperHero superman = SuperHero.builder().id(1l).name("Superman").build();
        when(superHeroRepository.save(superman)).thenReturn(superman);
        SuperHero actual = sut.create(superman);
        assertNotNull(actual);
    }

    @Test
    void update() {
        SuperHero superman = SuperHero.builder().id(1l).name("Superman, clark").build();
        SuperHero supermanDB = SuperHero.builder().id(1l).name("Superman").build();
        when(superHeroRepository.findById(1l)).thenReturn(Optional.of(supermanDB));
        when(superHeroRepository.save(supermanDB)).thenReturn(supermanDB);
        ResponseEntity<SuperHero> actual = sut.update(1l, superman);

        assertNotNull(actual);
        assertEquals(superman.getName(), actual.getBody().getName());
    }

    @Test
    void delete() {
        when(superHeroRepository.findById(1l)).thenReturn(Optional.of(SuperHero.builder().id(1l).name("Superman").build()));
        ResponseEntity<HttpStatus> actual = sut.delete(1l);
        assertEquals(HttpStatus.NO_CONTENT,  actual.getStatusCode());
    }
}