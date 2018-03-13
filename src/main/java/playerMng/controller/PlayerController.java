package playerMng.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import playerMng.entity.Player;
import playerMng.service.PlayerService;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Autowired
    ComboPooledDataSource comboPooledDataSource;


    @GetMapping("/list")
    public String listPlayers(Model model) {
        List<Player> playersList = playerService.getPlayersList();

        model.addAttribute("players", playersList);

        return "list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Player player = new Player();
        model.addAttribute("player", player);

        return "player-form";
    }

    @PostMapping("/savePlayer")
    public String savePlayer(@ModelAttribute("player") Player player) {
        playerService.savePlayer(player);

        return "redirect:/players/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("playerId") int theId, Model model) {

        Player player = playerService.getPlayer(theId);

        model.addAttribute("player", player);


        return "player-form";
    }

    @GetMapping("/deletePlayer")
    public String deletePlayer(@RequestParam("playerId") int theId) {

        playerService.deletePlayer(theId);
        return "redirect:/players/list";
    }

    @PostMapping("/search")
    public String searchPlayers(@RequestParam("theSearchName") String theSearchName, Model model) {

        List<Player> thePlayers = playerService.searchPlayers(theSearchName);

        model.addAttribute("players", thePlayers);

        return "list";
    }


    @GetMapping("/admin/deleteAllPlayers")
    public String deleteAllPlayers() {

        playerService.clearAllPlayers();
        return "redirect:/players/list";
    }

    @GetMapping("/admin/resetPlayersPK")
    public String resetPlayersPK() {

        playerService.resetPlayerPK();
        return "redirect:/players/list";
    }
}
