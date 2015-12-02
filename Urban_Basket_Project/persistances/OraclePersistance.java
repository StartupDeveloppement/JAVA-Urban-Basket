package persistances;

import models.User;

import java.sql.*;

/**
 * Persistance d'acc�s � une base Oracle
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class OraclePersistance implements Persistance {


    private static String driver = "oracle.jdbc.OracleDriver";
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String login = "SYSTEM";
    private static String mdp = "root";
    private Connection connect;

    private PreparedStatement pLogUser;

    public OraclePersistance() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connect = DriverManager.getConnection(url,login,mdp);

        pLogUser = connect.prepareStatement("SELECT UTILISATEUR.PSEUDO,UTILISATEUR.MDP FROM UTILISATEUR WHERE UTILISATEUR.PSEUDO = ?");

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
}
