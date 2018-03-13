package playerMng.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import playerMng.entity.Player;

import org.hibernate.query.Query;
import java.util.List;

@Repository
public class PlayerDAOImpl implements PlayerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Player> getPlayers() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Player> theQuery = currentSession.createQuery("from Player order by playerId", Player.class);

        return theQuery.getResultList();
    }

    @Override
    public void savePlayer(Player player) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(player);

    }

    @Override
    public Player getPlayer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Player player = currentSession.get(Player.class,theId);

        return player;
    }

    @Override
    public void deletePlayer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();

        Player player = getPlayer(theId);

        currentSession.delete(player);

    }

    @Override
    public List<Player> searchPlayers(String theSearchName) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;
        if(theSearchName!=null && theSearchName.trim().length() != 0) {
            theQuery = currentSession.createQuery("from Player where lower(firstName)"+
            " like :theName or lower(lastName) like :theName", Player.class);
            theQuery.setParameter("theName","%"+theSearchName.toLowerCase()+"%");
        }else{
            theQuery = currentSession.createQuery("from Player", Player.class);
        }

        return theQuery.getResultList();
    }

    @Override
    public void clearAllPlayers() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery =currentSession.createQuery("delete from Player");

        theQuery.executeUpdate();

    }

    @Override
    public void resetPlayerPK() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery =currentSession.createNativeQuery("ALTER SEQUENCE player_player_id_seq RESTART WITH 1");
        theQuery.executeUpdate();
    }

}


