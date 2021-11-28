package es.pivol.superhero.restcontroller;

import es.pivol.superhero.configuration.TestConfiguration;
import es.pivol.superhero.model.SuperHero;
import es.pivol.superhero.service.SuperHeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SuperHeroController.class)
@ContextConfiguration(classes = {TestConfiguration.class})
class SuperHeroControllerTI {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private SuperHeroService superHeroService;
    @Autowired
    private SuperHeroController sut;

    @BeforeEach
    public void beforeEach() {
        mvc = MockMvcBuilders.standaloneSetup(sut).build();

    }

    @Test
    void findById() throws Exception {
        when(superHeroService.findById(1l))
                .thenReturn(Optional.of(SuperHero.builder().id(1l).name("Superman").build()));
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/superhero/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").isNotEmpty());
    }

}