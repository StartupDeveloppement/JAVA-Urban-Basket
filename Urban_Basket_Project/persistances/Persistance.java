package persistances;

import java.time.LocalDateTime;
import java.util.List;

import models.Reservation;
import models.Salle;
import models.SalleModel;
import models.User;

public interface Persistance {

    public User getUser(String pseudo,String mdp) throws Exception;

    public boolean existReservation(Salle salle, LocalDateTime dateTime) throws Exception;

    public boolean reservationPossible(Reservation reservation) throws Exception;
    
    public SalleModel getSalle(int idSalle) throws Exception;
    
    public List<Reservation> getAllReservationByUser(User user) throws Exception;
}
