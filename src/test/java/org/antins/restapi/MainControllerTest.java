package org.antins.restapi;

import org.antins.restapi.controller.MainController;
import org.antins.restapi.exceptions.StringEmptyException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Test
    public void testCalculatingFrequency() throws Exception {
        MainController controller = new MainController();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("str", "aabb");

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(post("")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"str\":\"aabb\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("a")))
                .andExpect(jsonPath("$[0].amount", is(2)))
                .andExpect(jsonPath("$[1].name", is("b")))
                .andExpect(jsonPath("$[1].amount", is(2)));
    }
    @Test
    public void testStringEmptyException()  {
        MainController controller = new MainController();

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("str", "");

        assertThrows(StringEmptyException.class, () -> {
            controller.calculatingFrequency(requestBody);
        });
    }
}