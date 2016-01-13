package services;

import java.time.LocalDateTime;

import models.Salle;
import models.User;
import persistances.Persistance;

/**
 * Classe du Service de reservation
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class ReserverService {
	
	private Persistance persistance;

    public ReserverService(){
//        this.persistance = OraclePersistance.getInstance();
    }
    
	public boolean reserver(User user, Salle salle, LocalDateTime dateTime) throws Exception{
		if(persistance.existReservation(salle, dateTime)){
			return false;
		}else{
			//TODO return persistance.reserver();
			return true;
		}
	}
}
