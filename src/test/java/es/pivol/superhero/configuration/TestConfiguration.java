package es.pivol.superhero.configuration;

import es.pivol.superhero.persistence.SuperHeroRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"es.pivol.superhero"})
public class TestConfiguration {
    @MockBean
    SuperHeroRepository superHeroRepository;
}
