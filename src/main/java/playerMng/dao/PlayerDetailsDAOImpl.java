package playerMng.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import playerMng.entity.Player;
import playerMng.entity.PlayerDetails;
import playerMng.service.PlayerService;

import java.util.List;

@Repository
public class PlayerDetailsDAOImpl implements PlayerDetailsDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public PlayerDetails getPlayerDetails(int theId) {

        Session currentSession = sessionFactory.getCurrentSession();

        PlayerDetails playerDetails = currentSession.get(PlayerDetails.class, theId);

        return playerDetails;
    }

    @Override
    public void savePlayerDetails(int theId, PlayerDetails playerDetails) {
        Session currentSession = sessionFactory.getCurrentSession();

        Player player = currentSession.get(Player.class, theId);

        player.setPlayerDetails(playerDetails);

    }

    @Override
    public void deletePlayerDetails(int playerId, int detailsId) {
        Session currentSession = sessionFactory.getCurrentSession();

        PlayerDetails playerDetails = getPlayerDetails(detailsId);
        Player player = currentSession.get(Player.class, playerId);

        player.clearPlayerDetailsId();
        currentSession.delete(playerDetails);
    }

    @Override
    public List<PlayerDetails> getPlayersDetailsList() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<PlayerDetails> theQuery = currentSession.createQuery("from PlayerDetails", PlayerDetails.class);

        List<PlayerDetails> playerDetailsList = theQuery.getResultList();

        return playerDetailsList;
    }

    @Override
    public void clearAllPlayersDetails() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from PlayerDetails");

        theQuery.executeUpdate();
    }

    @Override
    public void resetPlayerDetailsPK() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =currentSession.createNativeQuery("ALTER SEQUENCE player_player_id_seq RESTART WITH 1");

        theQuery.executeUpdate();
    }
}
