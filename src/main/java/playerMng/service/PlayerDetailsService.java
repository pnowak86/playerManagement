package playerMng.service;

import playerMng.entity.PlayerDetails;

import java.util.List;

public interface PlayerDetailsService {

    PlayerDetails getDetails(int theId);
    void savePlayerDetails(int theId, PlayerDetails playerDetails);
    void deletePlayerDetails(int playerId, int detailsId);
    List<PlayerDetails> getPlayersDetailsList();
    void clearAllPlayersDetails();
    void resetPlayerDetailsPK();


}
