package playerMng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import playerMng.dao.PlayerDetailsDAO;
import playerMng.entity.PlayerDetails;

import java.util.List;

@Service
public class PlayerDetailsServiceImpl implements PlayerDetailsService {

    @Autowired
    PlayerDetailsDAO playerDetailsDAO;

    @Override
    @Transactional
    public PlayerDetails getDetails(int theId) {
        return playerDetailsDAO.getPlayerDetails(theId);
    }

    @Override
    @Transactional
    public void savePlayerDetails(int theId ,PlayerDetails playerDetails) {
       playerDetailsDAO.savePlayerDetails(theId, playerDetails);
    }

    @Override
    @Transactional
    public void deletePlayerDetails(int playerId, int detailsId) {
        playerDetailsDAO.deletePlayerDetails(playerId, detailsId);}

    @Override
    @Transactional
    public List<PlayerDetails> getPlayersDetailsList() {
        return playerDetailsDAO.getPlayersDetailsList();
    }

    @Override
    @Transactional
    public void clearAllPlayersDetails() {
        playerDetailsDAO.clearAllPlayersDetails();
    }

    @Override
    @Transactional
    public void resetPlayerDetailsPK() {
        playerDetailsDAO.resetPlayerDetailsPK();
    }

}
