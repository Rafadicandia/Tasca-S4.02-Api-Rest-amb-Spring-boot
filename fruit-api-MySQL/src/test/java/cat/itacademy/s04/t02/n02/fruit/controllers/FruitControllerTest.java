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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import java.util.List;

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

    @Test
    void shouldReturnListOfFruits() throws Exception {
        List<FruitResponseDTO> fruitList = List.of(
                new FruitResponseDTO(1L, "Poma", 2),
                new FruitResponseDTO(2L, "Plàtan", 3)
        );

        when(fruitService.getAll()).thenReturn(fruitList);
        mockMvc.perform(get("/fruits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2)) // Verificamos que vienen 2 elementos
                .andExpect(jsonPath("$[0].name").value("Poma"))
                .andExpect(jsonPath("$[1].name").value("Plàtan"));
    }

    @Test
    void shouldUpdateFruitWhenIdExists() throws Exception {
        Long idExistente = 10L;
        FruitDTO updateRequest = new FruitDTO("Red Apple", 10);
        FruitResponseDTO mockResponse = new FruitResponseDTO(idExistente, "Red Apple", 10);

        when(fruitService.update(eq(idExistente), any(FruitDTO.class))).thenReturn(mockResponse);

        mockMvc.perform(put("/fruits/{id}", idExistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Red Apple"))
                .andExpect(jsonPath("$.weightInKilos").value(10));
    }

    @Test
    void shouldDeleteFruitWhenIdExists() throws Exception {
        Long id = 1L;

        doNothing().when(fruitService).delete(id);

        mockMvc.perform(delete("/fruits/{id}", id))
                .andExpect(status().isNoContent());

        verify(fruitService, times(1)).delete(id);
    }

}