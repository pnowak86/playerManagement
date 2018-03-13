package playerMng.dao;

import playerMng.entity.Player;

import java.util.List;

public interface PlayerDAO {

    List<Player> getPlayers();

    void savePlayer(Player player);

    Player getPlayer(int theId);

    void deletePlayer(int theId);

    List<Player> searchPlayers(String theSearchName);
    void clearAllPlayers();
    void resetPlayerPK();


}
