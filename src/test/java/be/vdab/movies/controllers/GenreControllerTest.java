package be.vdab.movies.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@SpringBootTest
@AutoConfigureMockMvc
class GenreControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final static String GENRES = "genres";
    private final MockMvc mockMvc;
    private static final Path TEST_RESOURCES = Path.of("src/test/resources");

    public GenreControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/genres"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("length()").value(countRowsInTable(GENRES)
                        )
                );
    }
}