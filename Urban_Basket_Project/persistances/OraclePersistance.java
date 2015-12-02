package persistances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import models.Reservation;
import models.Salle;
import models.SalleModel;
import models.User;

/**
 * Persistance d'acc�s � une base Oracle
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class OraclePersistance implements Persistance {

	private static OraclePersistance oraclePersistance = null;

    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String login = "SYSTEM";
    private static String mdp = "root";
    private Connection connect;

    private PreparedStatement pLogUser;
    private PreparedStatement pReservationGetAllByUser;
	private PreparedStatement pReservationExist;
	private PreparedStatement pReservationNbByInterval;

	private PreparedStatement pSalleGet;
    
    public static OraclePersistance getInstance(){
    	if(null == oraclePersistance){
    		oraclePersistance = new OraclePersistance();
    	}
    	return oraclePersistance;
    }

    @PostConstruct
    private void init() throws SQLException, ClassNotFoundException{
    	Class.forName(driver);
    	connect = DriverManager.getConnection(url,login,mdp);
    	pLogUser = connect.prepareStatement("SELECT UTILISATEUR.PSEUDO,UTILISATEUR.MDP FROM UTILISATEUR WHERE UTILISATEUR.PSEUDO = ?");
    	pReservationGetAllByUser = connect.prepareStatement("SELECT * FROM RESERVATION WHERE RESERVATION.PSEUDO_USER = ?");
    	pReservationExist = connect.prepareStatement("CASE WHEN EXISTS"
    			+ " (SELECT RESERVATION.ID FROM RESERVATION WHERE RESERVATION.ID_SALLE = ? AND RESERVATION.DATE_RESA = ?)"
    			+ " THEN TRUE ELSE FALSE END");
    	pReservationNbByInterval = connect.prepareStatement("SELECT COUNT(*) FROM RESERVATION WHERE ? BETWEEN RESERVATION.DATE_RESA_DEBUT AND RESERVATION.DATE_RESA_FIN"
    			+ "AND ? BETWEEN RESERVATION.DATE_RESA_DEBUT AND RESERVATION.DATE_RESA_FIN");
    	pSalleGet = connect.prepareStatement("SELECT * FROM SALLE WHERE SALLE.ID = ?");
    }

    public synchronized User getUser(String pseudo,String mdp) throws Exception {
        try{
            pLogUser.setString(1, pseudo);
            ResultSet res = pLogUser.executeQuery();
            if(mdp.equals(res.getString(2))) return new User(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            else throw new Exception("L'utilisateur ou le mot de passe est erroné.");
        }catch (SQLException e){
            throw new Exception("L'utilisateur ou le mot de passe est erroné.");
        }
    }
    
    public synchronized List<Reservation> getAllReservationByUser(User user) throws Exception{
    	try{
    		pReservationGetAllByUser.setString(1, user.getPseudo());
    		ResultSet res = pReservationGetAllByUser.executeQuery();
    		List<Reservation> list = new ArrayList<>();
    		while(res.next()){
    			list.add(new Reservation(res.getTimestamp(3).toLocalDateTime(), res.getTimestamp(3).toLocalDateTime(), user, getSalle(res.getInt(1))));
    		}
    		return list;
    	}catch(Exception e){
    		throw new Exception(e.getMessage());
    	}
    }

    public synchronized SalleModel getSalle(int idSalle) throws Exception {
    	try{
            pSalleGet.setInt(1, idSalle);
            ResultSet res = pSalleGet.executeQuery();
            if(null != res) return new Salle(res.getString(2),res.getInt(3),res.getLong(5),res.getLong(6));
            else throw new Exception("Salle inconnue");
        }catch (SQLException e){
            throw e;
        }
	}

	public synchronized boolean existReservation(Salle salle, LocalDateTime dateTime) throws Exception{
    	try{
    		pReservationExist.setInt(1, salle.getId());
    		pReservationExist.setTimestamp(2, Timestamp.valueOf(dateTime));
    		ResultSet res = pReservationExist.executeQuery();
    		return res.getBoolean(1);
    	}catch(Exception e){
    		throw new Exception(e.getMessage());
    	}
    }
	
    
    public boolean reservationPossible(Reservation reservation) throws Exception{
    	//ChronoUnit.MINUTES.between(t1.toLocalDateTime(), t2.toLocalDateTime())
    	try {
			pReservationNbByInterval.setTimestamp(1, Timestamp.valueOf(reservation.getDateResa_Deb()));
			pReservationNbByInterval.setTimestamp(2, Timestamp.valueOf(reservation.getDateResa_Fin()));
			ResultSet res = pReservationNbByInterval.executeQuery();
			return reservation.getSalle().getNbTerrains() - res.getInt(1) > 0;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
}
