package recipes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import recipes.config.WebConfiguration;
import recipes.contrroller.ExternalRecipeController;
import recipes.security.SecurityConfig;
import recipes.service.RecipeService;
import recipes.utils.TestUtils;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@EnableAutoConfiguration
@Import({SecurityConfig.class, WebConfiguration.class})
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExternalRecipeController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService service;


    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testGetWithMockUser() throws Exception {
        this.mockMvc.perform(get("/external/getallrecipes")).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void testGetWithMockUserUserRole() throws Exception {
        this.mockMvc.perform(get("/external/getallrecipes")).andExpect(status().isForbidden());

    }

    @Test
    public void testGetWithoutUser() throws Exception {
        this.mockMvc.perform(get("/external/getallrecipes")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testPostWithAdminRole() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(TestUtils.createDTO());

        this.mockMvc.perform(post("/external/saverecipe").with(csrf()).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testPostWithUserRole() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(TestUtils.createDTO());

        this.mockMvc.perform(post("/external/saverecipe").content(json)).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testDeleteWithAdminRole() throws Exception {
        this.mockMvc.perform(delete("/external/delete/0").with(csrf())).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testDeleteWithUserRole() throws Exception {
        this.mockMvc.perform(delete("/external/delete/0")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testPutWithAdminRole() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(TestUtils.createDTO());

        this.mockMvc.perform(put("/external/editrecipe/0").with(csrf()).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testPutWithUserRole() throws Exception {

        ObjectMapper jsonMapper = new ObjectMapper();
        String json = jsonMapper.writeValueAsString(TestUtils.createDTO());

        this.mockMvc.perform(put("/external/editrecipe/0").content(json)).andExpect(status().isForbidden());
    }


}
