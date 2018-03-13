package playerMng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import playerMng.dao.PlayerDAO;
import playerMng.entity.Player;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerDAO playerDAO;

    @Override
    @Transactional
    public List<Player> getPlayersList() {
        return playerDAO.getPlayers();
    }

    @Override
    @Transactional
    public void savePlayer(Player player) {
        playerDAO.savePlayer(player);
    }

    @Override
    @Transactional
    public Player getPlayer(int theId) {
        return playerDAO.getPlayer(theId);
    }

    @Override
    @Transactional
    public void deletePlayer(int theId) {
        playerDAO.deletePlayer(theId);
    }

    @Override
    @Transactional
    public List<Player> searchPlayers(String theSearchName) {
        return playerDAO.searchPlayers(theSearchName);
    }

    @Override
    @Transactional
    public void clearAllPlayers() {
        playerDAO.clearAllPlayers();
    }

    @Override
    @Transactional
    public void resetPlayerPK() {
        playerDAO.resetPlayerPK();
    }


}


