package org.helmo.HolyD.controlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.helmo.HolyD.models.reponses.User;
import org.helmo.HolyD.models.requests.LieuAdd;
import org.helmo.HolyD.models.requests.UserSignUp;
import org.helmo.HolyD.models.requests.VacanceAdd;
import org.helmo.HolyD.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@Transactional
@ActiveProfiles("h2, h2Debug")
public class VacanceControlerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    private String userToken;
    private VacanceAdd vacanceToAdd;

    void reinitUser(){
        UserSignUp userSignUp = new UserSignUp();
        userSignUp.setNom("Test");
        userSignUp.setPrenom("Test");
        userSignUp.setEmail("test@test.test");
        userSignUp.setPasswd("testtest");
        if(userRepository.existsByEmail(userSignUp.getEmail())){
            //userRepository.deleteByEmail(this.userSignUp.getEmail());
            userRepository.deleteAll();
        }
        try{
            mockMvc.perform(post("/user/signup")
                            .contentType("application/json")
                            .content(userSignUp.toJsonObject()))
                    .andExpect(status().isOk())
                    .andDo(result -> {
                        ObjectMapper objectMapper = new ObjectMapper();
                        this.userToken = objectMapper.readValue(result.getResponse().getContentAsString(), User.class).getTokenConnectionAPI();
                    });
        }catch (Exception ex){
            System.out.printf("Impossible de signin l'utilisateur : %s", ex.getMessage());
        }
    }
    @BeforeEach
    void init(){
        reinitUser();
        //Base V
        this.vacanceToAdd = new VacanceAdd();
        this.vacanceToAdd.setNom("Name");
        this.vacanceToAdd.setDescription("Name");
        LieuAdd lieuAdd = new LieuAdd();
        lieuAdd.setLatitude(BigDecimal.valueOf(10));
        lieuAdd.setLongitude(BigDecimal.valueOf(10));
        lieuAdd.setRue("Rue des rues");
        lieuAdd.setRueNumero("22");
        lieuAdd.setPays("Bazerty");
        lieuAdd.setCodePostal("5580");
        lieuAdd.setVille("RRRRR");
        this.vacanceToAdd.setLieu(lieuAdd);
    }

    private ResultActions mockMVCPerform() throws Exception {
        return mockMvc.perform(post("/vacance")
                .contentType("application/json")
                .header("Authorization", "Bearer " + this.userToken)
                .content(this.vacanceToAdd.toJsonObject()));
    }

    @Test
    public void testAddVacance() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(1));
        this.vacanceToAdd.setDateFin(now.plusDays(5));

        mockMVCPerform()
                .andExpect(status().isOk());
    }
    @Test
    public void testAdd2Vacances() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(1));
        this.vacanceToAdd.setDateFin(now.plusDays(5));

        mockMVCPerform()
                .andExpect(status().isOk());
        this.vacanceToAdd.setDateDebut(now.plusDays(6));
        this.vacanceToAdd.setDateFin(now.plusDays(7));
        mockMVCPerform()
                .andExpect(status().isOk());
    }
    @Test
    public void testAddVacance_Range1_after_Range2() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(7));
        this.vacanceToAdd.setDateFin(now.plusDays(10));
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range2_after_Range1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(7));
        this.vacanceToAdd.setDateFin(now.plusDays(10));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_SameDates() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        // Même date pour début et fin (pas un intervalle valide)
        this.vacanceToAdd.setDateDebut(now.plusDays(1));
        this.vacanceToAdd.setDateFin(now.plusDays(1));
        mockMVCPerform()
                .andExpect(status().isBadRequest());

    }
    @Test
    public void testAddVacance_DateInPastNow() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        // Date de début et de fin égales à maintenant (dans le passé)
        this.vacanceToAdd.setDateDebut(now);
        this.vacanceToAdd.setDateFin(now);
        mockMVCPerform()
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testAddVacance_DateInPastEnd() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        // Date de début dans le passé, date de fin maintenant
        this.vacanceToAdd.setDateDebut(now.minusDays(1));
        this.vacanceToAdd.setDateFin(now);
        mockMVCPerform()
                .andExpect(status().isBadRequest());

    }
    @Test
    public void testAddVacance_RangeSameDate() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(1));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        //Add 2 fois
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());

    }
    @Test
    public void testAddVacance_Range_DateDebut1AfterDD2_And_DD1EqualDateFin2() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(2));
        this.vacanceToAdd.setDateFin(now.plusDays(3));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD1AfterDD2_And_DF2BetweenDD1AndDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(2));
        this.vacanceToAdd.setDateFin(now.plusDays(4));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD1EqualDD2_And_DF2BetweenDD1AndDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(4));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD2AndDF2BetweenDD1AndDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(2));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(4));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD1EqualDD2_And_DF2EqualDF1() throws Exception { // (déjà testé plus haut)
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DF1EqualDF2_And_DF2AfterDD1AndDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(5));
        this.vacanceToAdd.setDateFin(now.plusDays(7));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD2BetweenDD1AndDF1_And_DF2AfterDD1AndDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(4));
        this.vacanceToAdd.setDateFin(now.plusDays(7));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testAddVacance_Range_DD2BeforeDD1_And_DF2AfterDF1() throws Exception {
        OffsetDateTime now = OffsetDateTime.now();
        this.vacanceToAdd.setDateDebut(now.plusDays(3));
        this.vacanceToAdd.setDateFin(now.plusDays(5));
        this.vacanceToAdd.setDateDebut(now.plusDays(2));
        this.vacanceToAdd.setDateFin(now.plusDays(7));
        mockMVCPerform()
                .andExpect(status().isOk());
        mockMVCPerform()
                .andExpect(status().isBadRequest());
    }
}

