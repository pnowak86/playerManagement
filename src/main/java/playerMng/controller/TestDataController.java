package playerMng.controller;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import playerMng.entity.Player;
import playerMng.entity.PlayerDetails;
import playerMng.service.PlayerDetailsService;
import playerMng.service.PlayerService;

import java.time.LocalDate;
import java.util.Random;

@Controller
@RequestMapping("/players")
public class TestDataController {

    String[] firstNames = {"Andrzej", "Tomasz", "Kamil", "Grzegorz", "Tadeusz", "Alojzy",
            "Hubert", "Krzysztof", "Piotr", "Mateusz", "Marcin", "Geralt"};
    String[] lastNames = {"Przebieracz", "Krzyszcztofczyk", "Kowalski", "Banasiewicz",
            "Norek", "Adamczak", "Sypniewski"};
    int[] age = {22, 34, 19, 18, 30, 26, 17, 21, 24};
    String[] position = {"goalkeeper", "defeneder", "striker", "winger", "midfielder"};
    String[] emails = {"misio@wp.pl", "gracz@onet.pl", "pilkarz@gmail.com", "szybkijakblyskawica@wp.pl",
            "bramkostrzelny@gmail.com"};
    String[] prefFoot = {"left", "right", "both"};
    String[] shortInfo = {"quick, tall player", "chubby slow", "short, with good technique", "big and strong",
            "bad technique"};

    int fNSize = firstNames.length;
    int lNSize = lastNames.length;
    int ageSize = age.length;
    int posSize =position.length;
    int emSize =emails.length;
    int prefFtSize = prefFoot.length;
    int sISize = shortInfo.length;

    @Autowired
    PlayerDetailsService playerDetailsService;

    @Autowired
    PlayerService playerService;

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    @GetMapping("/admin/putTestData")
    public String putTestData() {

        Session currentSession = sessionFactory.getCurrentSession();
        for (int i = 0; i < firstNames.length; i++) {


            Player player = createPlayer(fNSize,lNSize,ageSize,posSize);
            currentSession.save(player);
            int playerAge = player.getAge();

            LocalDate dateOfBirth = createRandomBirthDate(playerAge);
            PlayerDetails playerDetails = createPlayerDetails(dateOfBirth, emSize, prefFtSize,sISize);
            player.setPlayerDetails(playerDetails);
            currentSession.save(playerDetails);
        }

        return "redirect:/players/list";
    }


    public Player createPlayer(int firstNameSize, int lastNameSize, int ageSize, int positionSize){
        Player player = new Player();
        player.setFirstName(firstNames[(int) (Math.random() * firstNameSize)]);
        player.setLastName(lastNames[(int) (Math.random() * lastNameSize)]);
        player.setAge(age[(int) (Math.random() * ageSize)]);
        player.setPosition(position[(int) (Math.random() * positionSize)]);

        return player;
    }


    public PlayerDetails createPlayerDetails(LocalDate dateOfBirth, int emailSize, int prefFootSize, int shortInfSize ){
        PlayerDetails playerDetails = new PlayerDetails();
        playerDetails.setDateOfBirth(dateOfBirth);
        playerDetails.setEmail(emails[(int) (Math.random() * emails.length)]);
        playerDetails.setPreferredFoot(prefFoot[(int) (Math.random() * prefFoot.length)]);
        playerDetails.setShortInfo(shortInfo[(int) (Math.random() * shortInfo.length)]);

        return playerDetails;
    }

    public LocalDate createRandomBirthDate(int playerAge){
        Random random = new Random();
        LocalDate today = LocalDate.now();
        int presetYear = today.getYear();
        int minDay = (int) LocalDate.of(presetYear, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(presetYear, 12, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
        randomBirthDate = randomBirthDate.minusYears(playerAge);

        return randomBirthDate;
    }
}
