<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:layout>
    <jsp:attribute name="header">
   		<h1>Réserver</h1>
	   		<script src="http://maps.googleapis.com/maps/api/js"></script>
			<script>
				function initialize(lat,lon,id) {
					var mapProp = {
						center : new google.maps.LatLng(lat,lon),
						zoom : 16,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};
					var map = new google.maps.Map(document.getElementById(id), mapProp);
			
					//marker
					var marker = new google.maps.Marker({position : mapProp.center, animation : google.maps.Animation.DROP});
					marker.setMap(map);
			
					//popup
					var infowindow = new google.maps.InfoWindow({content : "Hello World!"});
					google.maps.event.addListener(marker, 'click', function() {infowindow.open(map, marker);});
				}
			
			</script>
    </jsp:attribute>
    <jsp:body>
			<table>
			    <tr>
			        <th>Salle ID</th>
			        <th>Adresse</th>
			        <th>Propriétaire</th>
			        <th>Nombre de terrains</th>
			        <th>API</th>
			        <th/>
			    </tr>
			    <c:forEach items="${listeSalles}" var="s">
			        <tr>
			            <td>${s.id}</td>
			            <td>${s.adresse}</td>
			            <td>${s.proprietaire}</td>
			            <td>${s.nbTerrains}</td>
			            <td>
							<div id="map${s.id}" style="width: 500px; height: 380px;"/>
				  			<script type="text/javascript">
							   initialize('<c:out value="${s.latitude}"/>','<c:out value="${s.longitude}"/>', 'map<c:out value="${s.id}"/>');
							</script>
						</td>
						<td>
							<form method="post" action="planning">
								<input type="hidden" id="idSalle" name="idSalle" value="<c:out value="${s.id}"/>" maxlength="4" />                
				                <input type="submit" value="Voir le planning"/>
     					   </form>
				         </td>
			        </tr>
			    </c:forEach>
			</table>
    </jsp:body>
</t:layout>