package es.pivol.superhero.restcontroller;

import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.service.SuperHeroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SuperHeroControllerTest {
    @Mock
    SuperHeroService superHeroService;

    @InjectMocks
    private SuperHeroController sut;


    @Test
    void getAll() {
        List<SuperHero> heros = asList(
                SuperHero.builder().id(1l).name("Superman").build(),
                SuperHero.builder().id(2l).name("Spiderman").build());
        when(superHeroService.findAll()).thenReturn(heros);
        List<SuperHero> actual = sut.getAll();
        assertEquals(heros, actual);

    }

    @Test
    void findById() throws Exception {
        when(superHeroService.findById(1l)).thenReturn(Optional.of(SuperHero.builder().id(1l).name("Superman").build()));
        ResponseEntity<SuperHero> actual = sut.findById(1l);
        assertNotNull(actual);
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void findByName() {
        List<SuperHero> heros = asList(
                SuperHero.builder().id(1l).name("Superman").build(),
                SuperHero.builder().id(2l).name("Spiderman").build());
        when(superHeroService.findByNameContainingIgnoreCase("man")).thenReturn(heros);
        List<SuperHero> actual = sut.findByName("man");
        assertEquals(heros, actual);
    }

    @Test
    void create() {
        SuperHero superman = SuperHero.builder().id(1l).name("Superman").build();
        when(superHeroService.create(superman)).thenReturn(superman);
        SuperHero actual = sut.create(superman);
        assertNotNull(actual);
    }

    @Test
    void update() {
        SuperHero superman = SuperHero.builder().id(1l).name("Superman, clark").build();
        when(superHeroService.update(1l, superman)).thenReturn(Optional.of(superman));
        ResponseEntity<SuperHero> actual = sut.update(1l, superman);

        assertNotNull(actual);
        assertEquals(superman.getName(), actual.getBody().getName());
    }

    @Test
    void delete() {
        when(superHeroService.findById(1l)).thenReturn(Optional.of(SuperHero.builder().id(1l).name("Superman").build()));
        ResponseEntity<HttpStatus> actual = sut.delete(1l);
        assertEquals(HttpStatus.NO_CONTENT,  actual.getStatusCode());
    }
}