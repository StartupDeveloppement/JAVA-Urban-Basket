<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Urban Basket : Connexion</title>
    </head>
    <body>
        <form method="post" action="login">
            <fieldset>
                <label for="user">Pseudo</label>
                <input type="text" id="user" name="user" value="<c:out value="${user.pseudo}"/>" size="20" maxlength="60" />                

                <label for="password">Mot de passe</label>
                <input type="password" id="pwd" name="pwd" value="" size="20" maxlength="20" />

                <input type="submit" value="Connexion"/>
            </fieldset>
        </form>
    </body>
</html>