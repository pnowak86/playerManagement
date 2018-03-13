package playerMng.entity;



import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "player_details")
public class PlayerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")

    private LocalDate dateOfBirth;


    @Column(name="email")
    @Pattern(regexp = ".*@.*", message = "Not a valid email" )
    private String email;

    @Column(name="preferred_foot")
    @Pattern(regexp = "^.{1,50}$", message = "Too long input" )
    private String preferredFoot;


    @Column(name="short_info")
    @NotBlank(message = "This field is required")
    //@Pattern(regexp = "^.{1,160}$", message = "Too long input" )
    private String shortInfo;

    @OneToOne(mappedBy = "playerDetails",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Player player;

    @Transient
    private  String yearOfBirth;


    public PlayerDetails() {


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    public void setPreferredFoot(String preferredFoot) {
        this.preferredFoot = preferredFoot;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public  void setYearOfBirth(Object playerAge) {

       if(playerAge.getClass().equals(Integer.class)) {
           LocalDate tempLocalDate = LocalDate.now().minusYears((Integer)playerAge);
           DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
           this.yearOfBirth = tempLocalDate.format(dateTimeFormatter).toString();
       }else if(playerAge.getClass().equals(LocalDate.class)){
           LocalDate tempDateOfBirth = (LocalDate) playerAge;
           DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
           this.yearOfBirth = tempDateOfBirth.format(dateTimeFormatter).toString();
       }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDetails that = (PlayerDetails) o;

        if (id != that.id) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (preferredFoot != null ? !preferredFoot.equals(that.preferredFoot) : that.preferredFoot != null)
            return false;
        if (shortInfo != null ? !shortInfo.equals(that.shortInfo) : that.shortInfo != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        return yearOfBirth != null ? yearOfBirth.equals(that.yearOfBirth) : that.yearOfBirth == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (preferredFoot != null ? preferredFoot.hashCode() : 0);
        result = 31 * result + (shortInfo != null ? shortInfo.hashCode() : 0);
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (yearOfBirth != null ? yearOfBirth.hashCode() : 0);
        return result;
    }
}
