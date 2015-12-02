package persistances;

import models.User;

public interface Persistance {

    public User getUser(String pseudo,String mdp) throws Exception;
}
