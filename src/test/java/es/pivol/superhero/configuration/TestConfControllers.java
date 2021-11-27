package es.pivol.superhero.configuration;

import es.pivol.superhero.persistence.SuperHeroRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfControllers {
    @MockBean
    SuperHeroRepository superHeroRepository;
}
