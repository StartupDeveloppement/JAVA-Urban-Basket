<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Urban Basket : Inscription</title>
    </head>
    <body>
        <form method="post" action="signin">
            <fieldset>
                <label for="user">Pseudo</label>
                <input type="text" id="user" name="user" value="" size="20" maxlength="60" />                

                <label for="password">Mot de passe</label>
                <input type="password" id="pwd" name="pwd" value="" size="20" maxlength="20" />

                <input type="submit" value="Inscription"/>
                
                <p style="color: ${empty form.erreurs ? 'none' : 'red'}">${form.resultat}</p>
            </fieldset>
        </form>
    </body>
</html>