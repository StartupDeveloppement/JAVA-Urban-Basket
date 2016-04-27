package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import exceptions.UserNotFoundException;
import models.User;
import persistances.DAO;

public final class SignInForm {
    private static final String FIELD_USER = "user";
    private static final String FIELD_PWD = "pwd";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public User checkUserName(HttpServletRequest request) {
        String pseudo = getValeurChamp( request, FIELD_USER );
        String pwd = getValeurChamp( request, FIELD_PWD );
        User user = new User();
        DAO dao = DAO.getInstance();
        
        try{
        	user = dao.validate(pseudo, pwd);
        	resultat = "Ce pseudo existe déjà, veuillez en choisir un autre.";
        	setErreur(FIELD_PWD, resultat);
        }catch(UserNotFoundException e){
        	user = dao.create(new User(pseudo, pwd));
        }catch(Exception e){
        	resultat = "Une erreur est survenue.";
        	setErreur(FIELD_PWD, resultat);
        }
        
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