package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import models.User;
import persistances.DAO;

public final class ConnexionForm {
    private static final String FIELD_USER = "user";
    private static final String FIELD_PWD = "pwd";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public User connectUser( HttpServletRequest request ) {
        String pseudo = getValeurChamp( request, FIELD_USER );
        String pwd = getValeurChamp( request, FIELD_PWD );
        User user = new User();
        
        try{
        	user = DAO.getInstance().validate(pseudo, pwd);
        }catch(Exception e){
        	setErreur(FIELD_PWD, e.getMessage());
        }
        resultat = erreurs.isEmpty() ? "Succès de la connexion." : "Échec de la connexion.";   
        return user;
    }

    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
    
    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }
}