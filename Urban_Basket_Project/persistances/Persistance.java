package persistances;

import java.time.LocalDateTime;
import java.util.List;

import models.Reservation;
import models.Salle;
import models.User;

public interface Persistance {

    User getUser(String pseudo,String mdp) throws Exception;

    boolean existReservation(Salle salle, LocalDateTime dateTime) throws Exception;

    boolean reservationPossible(Reservation reservation) throws Exception;
    
    Salle getSalle(int idSalle) throws Exception;
    
    List<Reservation> getAllReservationByUser(User user) throws Exception;
}
