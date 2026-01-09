package cat.itacademy.s04.s02.n01.fruit.controllers;

import cat.itacademy.s04.s02.n01.fruit.model.FruitDTO;
import cat.itacademy.s04.s02.n01.fruit.model.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.services.FruitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(FruitController.class)
class FruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FruitService fruitService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateFruit() throws Exception {

        FruitDTO inputDto = new FruitDTO("Banana", 5);
        FruitResponseDTO ResponseDto = new FruitResponseDTO(78L, "Banana", 5);
        when(fruitService.save(any(FruitDTO.class))).thenReturn(ResponseDto);

        String fruitJson = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/fruits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(fruitJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Banana"))
                .andExpect(jsonPath("$.weightInKilos").value(5));

        verify(fruitService, times(1)).save(any(FruitDTO.class));

    }

    @Test
    void shouldReturnFruitWhenIdExist() throws Exception {
        Long fruitId = 21L;
        FruitResponseDTO mockResponse = new FruitResponseDTO(fruitId, "Orange", 3);
        when(fruitService.getById(fruitId)).thenReturn(mockResponse);

        mockMvc.perform(get("/fruits/{id}", fruitId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(fruitId))
                .andExpect(jsonPath("$.name").value("Orange"));

    }

}