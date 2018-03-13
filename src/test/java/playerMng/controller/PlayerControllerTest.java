package playerMng.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import playerMng.dao.PlayerDAO;
import playerMng.entity.Player;
import playerMng.entity.PlayerDetails;
import playerMng.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.Assert.*;

public class PlayerControllerTest {

    @InjectMocks
    PlayerController playerController;

    @Mock
    PlayerService mockPlayerService;

    @Mock
    PlayerDAO mockPlayerDAO;

    @Mock
    View mockView;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController)
                .setSingleView(mockView)
                .build();
    }

    @Test
    public void shouldShowListPlayers() throws Exception {

        List<Player> expectedPlayers = asList(new Player());

        when(mockPlayerService.getPlayersList()).thenReturn(expectedPlayers);

        mockMvc.perform(get("/players/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players", expectedPlayers))
                .andExpect(view().name("list"));

    }

    @Test
    public void shouldShowFormForAdd() throws Exception {

        Player expectedPlayer = new Player();

        mockMvc.perform(get("/players/showFormForAdd"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("player",expectedPlayer))
                .andExpect(view().name("player-form"));

    }

    @Test
    public void shouldSavePlayer() throws Exception {

        mockMvc.perform(post("/players/savePlayer"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));

    }

    @Test
    public void shouldShowFormForUpdate() throws Exception {

        Player expectedPlayer = new Player();
        expectedPlayer.setPlayerId(1);

        when(mockPlayerService.getPlayer(1)).thenReturn(expectedPlayer);

        mockMvc.perform(get("/players/showFormForUpdate")
                .param("playerId", String.valueOf(expectedPlayer.getPlayerId())))
                .andExpect(status().isOk())
                .andExpect(model().attribute("player", expectedPlayer))
                .andExpect(view().name("player-form"));

    }

    @Test
    public void shouldDeletePlayer() throws Exception {

        mockMvc.perform(get("/players/deletePlayer").param("playerId","1"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));

    }

    @Test
    public void shouldSearchPlayers() throws Exception {
        List<Player> expectedPlayers = new ArrayList<>();

        Player expectedPlayer1 = new Player();
        expectedPlayer1.setPlayerId(1);
        expectedPlayer1.setLastName("Nowak");
        expectedPlayers.add(expectedPlayer1);

        Player expectedPlayer2 = new Player();
        expectedPlayer2.setPlayerId(2);
        expectedPlayer2.setLastName("Nowakowski");
        expectedPlayers.add(expectedPlayer2);

        when(mockPlayerService.searchPlayers("Now")).thenReturn(expectedPlayers);

        mockMvc.perform(post("/players/search").param("theSearchName","Now"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("players",expectedPlayers))
                .andExpect(view().name("list"));

    }

    @Test
    public void shouldDeleteAllPlayers() throws Exception {
        mockMvc.perform(get("/players/admin/deleteAllPlayers"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));
    }

    @Test
    public void shouldResetPlayersPK() throws Exception {
        mockMvc.perform(get("/players/admin/resetPlayersPK"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/players/list"));
    }

}