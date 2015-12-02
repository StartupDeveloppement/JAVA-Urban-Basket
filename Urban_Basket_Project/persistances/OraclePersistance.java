package persistances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import models.Reservation;
import models.Salle;
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
    private PreparedStatement pReservation;
	private PreparedStatement pexistReservation;
    
    public static OraclePersistance getInstance(){
    	if(null == oraclePersistance){
    		oraclePersistance = new OraclePersistance();
    	}
    	return oraclePersistance;
    }

    private OraclePersistance(){
    }
    
    @PostConstruct
    private void init() throws SQLException, ClassNotFoundException{
    	Class.forName(driver);
    	connect = DriverManager.getConnection(url,login,mdp);
    	pLogUser = connect.prepareStatement("SELECT UTILISATEUR.PSEUDO,UTILISATEUR.MDP FROM UTILISATEUR WHERE UTILISATEUR.PSEUDO = ?");
    	pReservation = connect.prepareStatement("SELECT * FROM RESERVATION WHERE RESERVATION.PSEUDO_USER = ? AND RESERVATION.ID_SALLE = ? AND RESERVATION.DATE_RESA = ?");
    	pexistReservation = connect.prepareStatement("CASE WHEN EXISTS"
    			+ " (SELECT RESERVATION.ID FROM RESERVATION WHERE RESERVATION.ID_SALLE = ? AND RESERVATION.DATE_RESA = ?)"
    			+ " THEN TRUE ELSE FALSE END");
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
    
    public synchronized Reservation getReservation(User user, Salle salle, LocalDateTime dateTime) throws Exception{
    	try{
    		pReservation.setString(1, user.getPseudo());
    		pReservation.setInt(2, salle.getId());
    		pReservation.setTimestamp(3, Timestamp.valueOf(dateTime));
    		ResultSet res = pReservation.executeQuery();
			return (null != res) ? new Reservation(dateTime, user, salle) : null;
    	}catch(Exception e){
    		throw new Exception(e.getMessage());
    	}
    }

    public synchronized boolean existReservation(Salle salle, LocalDateTime dateTime) throws Exception{
    	try{
    		pexistReservation.setInt(2, salle.getId());
    		pexistReservation.setTimestamp(3, Timestamp.valueOf(dateTime));
    		ResultSet res = pexistReservation.executeQuery();
    		return res.getBoolean(1);
    	}catch(Exception e){
    		throw new Exception(e.getMessage());
    	}
    }
}
