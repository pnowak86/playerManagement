package playerMng.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import playerMng.entity.Player;
import playerMng.entity.PlayerDetails;
import playerMng.service.PlayerDetailsService;
import playerMng.service.PlayerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/players")
public class PlayerDetailsController {

    @Autowired
    PlayerDetailsService playerDetailsService;

    @Autowired
    PlayerService playerService;


    @GetMapping("/details")
    public String getPlayerDetails(@RequestParam("playerId") int theInt, Model model) {
        Player player = playerService.getPlayer(theInt);


        if (player.getPlayerDetails() != null) {
            PlayerDetails playerDetails = playerDetailsService.getDetails(player.getPlayerDetails().getId());
            model.addAttribute("player", player);
            model.addAttribute("details", playerDetails);

            return "player-details";
        }
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setYearOfBirth(player.getAge());
        model.addAttribute("player", player);
        model.addAttribute("details", playerDetails);
        return "details-form";
    }


    @PostMapping("/savePlayerDetails")
    public String savePlayerDetails(@RequestParam("playerId") int theId,
                                    @Valid @ModelAttribute("details") PlayerDetails playerDetails,
                                    BindingResult theBindingResult,
                                    Model model,
                                    RedirectAttributes redirectAttributes) {


        Player player = playerService.getPlayer(theId);
        playerDetails.setYearOfBirth(player.getAge());
        boolean bindingResult = theBindingResult.hasErrors();
        if (bindingResult) {
            model.addAttribute("player", player);
            redirectAttributes.addFlashAttribute("details", playerDetails);
            return "details-form";
        } else {
            model.addAttribute("player", player);
            redirectAttributes.addFlashAttribute("details", playerDetails);
            playerDetailsService.savePlayerDetails(theId, playerDetails);
            return "player-details";

        }

    }

    @GetMapping("/deletePlayerDetails")
    public String deletePlayerDetails(@RequestParam("playerDetailsId") int detailsId,
                                      @RequestParam("playerId") int playerId) {

        playerDetailsService.deletePlayerDetails(playerId, detailsId);

        return "redirect:/players/list";
    }


    @GetMapping("/showFormForUpdateDetails")
    public String updatePlayerDetails(@RequestParam("playerDetailsId") int detailsId,
                                      @RequestParam("playerId") int playerId, Model model) {

        PlayerDetails playerDetails = playerDetailsService.getDetails(detailsId);
        playerDetails.setYearOfBirth(playerDetails.getDateOfBirth());
        Player player = playerService.getPlayer(playerId);

        model.addAttribute("player", player);

        model.addAttribute("details", playerDetails);

        return "details-form";
    }


    @GetMapping("/admin/deleteAllPlayersDetails")
    public String deleteAllPlayers() {

        playerDetailsService.clearAllPlayersDetails();
        return "redirect:/players/list";
    }

    @GetMapping("/admin/resetPlayersDetailsPK")
    public String resetPlayersDetailSPK() {

        playerDetailsService.resetPlayerDetailsPK();
        return "redirect:/players/list";
    }

}
