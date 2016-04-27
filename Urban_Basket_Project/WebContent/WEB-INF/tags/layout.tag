<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
  <head>
 	<title>Urban Basket</title>
  </head>
  <body>
    <div id="header">
	   <a href="home">Home</a>&#160;
	   <a href="reserver">Réserver</a>&#160;
	   <a href="contact">Contact</a>&#160;
	   <c:choose>
		   <c:when test="${null == user}">
			   <a href="login">Connexion</a>
			   <a href="signin">Inscription</a>
		   </c:when>
		   <c:otherwise>
			   <a href="logout">Déconnexion</a>
		   </c:otherwise>
		</c:choose>	   
	   <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>