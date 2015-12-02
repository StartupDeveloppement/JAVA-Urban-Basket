package persistances;

import java.time.LocalDateTime;

import models.Reservation;
import models.Salle;
import models.User;

public interface Persistance {

    public User getUser(String pseudo,String mdp) throws Exception;

    public Reservation getReservation(User user, Salle salle, LocalDateTime dateTime) throws Exception;
    
    public boolean existReservation(Salle salle, LocalDateTime dateTime) throws Exception;
}
