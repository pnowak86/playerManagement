package playerMng.dao;

import playerMng.entity.PlayerDetails;

import java.util.List;

public interface PlayerDetailsDAO {

    PlayerDetails getPlayerDetails(int theId);
    void savePlayerDetails(int theId, PlayerDetails playerDetails);
    void deletePlayerDetails(int playerId, int detailsId);
    List<PlayerDetails> getPlayersDetailsList();

    void clearAllPlayersDetails();
    void resetPlayerDetailsPK();
}
