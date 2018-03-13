package playerMng.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;
import playerMng.dao.PlayerDAO;
import playerMng.dao.PlayerDetailsDAO;
import playerMng.entity.Player;
import playerMng.entity.PlayerDetails;
import playerMng.service.PlayerDetailsService;
import playerMng.service.PlayerService;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PlayerDetailsControllerTest {

    @InjectMocks
    PlayerDetailsController playerDetailsController;

    @Mock
    PlayerDetailsService mockPlayerDetailsService;

    @Mock
    PlayerService mockPlayerService;

    @Mock
    PlayerDetailsDAO mockPlayerDetailsDAO;

    @Mock
    PlayerDAO mockPlayerDAO;

    @Mock
    BindingResult mockBindingResult;

    @Mock
    View mockView;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerDetailsController)
                .setSingleView(mockView)
                .build();

    }

    @Test
    public void shouldGetPlayerDetails() throws Exception {

        Player testPlayer = new Player();
        testPlayer.setPlayerId(1);
        PlayerDetails testPlayerDetails = new PlayerDetails();
        testPlayer.setPlayerDetails(testPlayerDetails);

        testPlayerDetails.setId(1);
        // testPlayerDetails.setYearOfBirth(12);
        // testPlayerDetails.setShortInfo("short, but quick");
        // testPlayerDetails.setPreferredFoot("left");
        //testPlayerDetails.setEmail("wp@wp.pl");
        testPlayerDetails.setPlayer(testPlayer);

        assertEquals(testPlayer.getPlayerDetails(), testPlayerDetails);

        when(mockPlayerService.getPlayer(1)).thenReturn(testPlayer);
        when(mockPlayerDetailsService.getDetails(testPlayer.getPlayerId())).thenReturn(testPlayer.getPlayerDetails());

        mockMvc.perform(get("/players/details").param("playerId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("player", testPlayer))
                .andExpect(model().attribute("details", testPlayerDetails))
                .andExpect(view().name("player-details"));

    }

    @Test
    public void shouldGetPlayerDetailsForm() throws Exception {
        Player testPlayer = new Player();
        testPlayer.setPlayerId(1);
        testPlayer.setPlayerDetails(null);
        testPlayer.setAge(12);

        PlayerDetails testPlayerDetails = new PlayerDetails();
        testPlayerDetails.setYearOfBirth(testPlayer.getAge());

        assertNull(testPlayer.getPlayerDetails());

        when(mockPlayerService.getPlayer(1)).thenReturn(testPlayer);

        mockMvc.perform(get("/players/details").param("playerId", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("player", testPlayer))
                .andExpect(model().attribute("details", testPlayerDetails))
                .andExpect(view().name("details-form"));
    }

    @Test
    public void shouldSavePlayerDetails() throws Exception {
        Player testPlayer = new Player();
        testPlayer.setPlayerId(1);
        testPlayer.setAge(20);

        PlayerDetails testPlayerDetails = new PlayerDetails();
        testPlayerDetails.setYearOfBirth(testPlayer.getAge());

        when(mockPlayerService.getPlayer(1)).thenReturn(testPlayer);
        when(mockBindingResult.hasErrors()).thenReturn(false);

        mockMvc.perform(post("/players/savePlayerDetails")
                .param("playerId", "1"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("player", testPlayer))
                    .andExpect(model().attribute("details", testPlayerDetails))
                    .andExpect(view().name("player-details"));
    }


    //  @Test
  //  public void shouldShowPlayerReturnFormWithErrors() throws Exception {
//        Player testPlayer = new Player();
//        testPlayer.setPlayerId(1);
//        testPlayer.setAge(20);
//
//
//
//        PlayerDetails testPlayerDetails = new PlayerDetails();
//        testPlayerDetails.setId(1);
////       testPlayerDetails.setEmail("dsjgsdjdb");
////        testPlayerDetails.setYearOfBirth(11);
//
//        when(mockPlayerService.getPlayer(1)).thenReturn(testPlayer);
//       when(mockBindingResult.hasErrors()).thenReturn(true);
//
//        mockMvc.perform(post("/players/savePlayerDetails")
//                .param("playerId", "1")
//                .requestAttr("details",testPlayerDetails))
//                    .andDo((ResultHandler) model().attribute("details",testPlayerDetails))
//                    .andExpect(model().hasErrors())
//                    .andExpect(status().isOk())
//                  //  .andExpect(model().attribute("player", testPlayer))
//                   // .andExpect(model().attribute("details", testPlayerDetails))
//                    .andExpect(view().name("details-form"));
  //  }









    @Test
    public void shouldDeletePlayerDetails() throws Exception {

        mockMvc.perform(get("/players/deletePlayerDetails")
                .param("playerDetailsId","1")
                .param("playerId", "1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("redirect:/players/list"));

    }

    @Test
    public void shouldShowFormForUpdatePlayerDetails() throws Exception {
        Player testPlayer = new Player();

        PlayerDetails testPlayerDetails = new PlayerDetails();

        testPlayerDetails.setDateOfBirth(LocalDate.now());

        testPlayer.setPlayerDetails(testPlayerDetails);

        when(mockPlayerDetailsService.getDetails(1)).thenReturn(testPlayerDetails);
        when(mockPlayerService.getPlayer(1)).thenReturn(testPlayer);

        mockMvc.perform(get("/players/showFormForUpdateDetails")
                .param("playerDetailsId","1")
                .param("playerId", "1"))
                    .andExpect(model().attribute("player", testPlayer))
                    .andExpect(model().attribute("details", testPlayerDetails))
                    .andExpect(status().isOk())
                    .andExpect(view().name("details-form"));

    }

    @Test
    public void shouldDeleteAllPlayers() throws Exception {
        mockMvc.perform(get("/players/admin/deleteAllPlayersDetails"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));

    }

    @Test
    public void shouldResetPlayersDetailSPK() throws Exception {
        mockMvc.perform(get("/players/admin/resetPlayersDetailsPK"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));
    }

}