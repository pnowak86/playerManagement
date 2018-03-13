package playerMng.service;

import playerMng.entity.Player;

import java.util.List;

public interface PlayerService {


    List<Player> getPlayersList();
    void savePlayer(Player player);
    Player getPlayer(int theId);
    void deletePlayer(int theId);
    List<Player> searchPlayers(String theSearchName);
    void clearAllPlayers();
    void resetPlayerPK();

}
