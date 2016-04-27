<%@page import="com.dhtmlx.planner.controls.DHXLocalization"%>
<%@page import="com.dhtmlx.planner.extensions.DHXExtension"%>
<%@page import="java.time.LocalDateTime"%>
<html>
	<body>
		<div class="planner" id="planner">
			<%= getPlanner(request) %>
		</div>
		
		<%@ page import="com.dhtmlx.planner.*,com.dhtmlx.planner.data.*,com.dhtmlx.planner.api.DHXBlockTime" %>
		<%!
			String getPlanner(HttpServletRequest request) throws Exception {
			    LocalDateTime date = LocalDateTime.now();
			    DHXPlanner s = new DHXPlanner("./codebase/", DHXSkin.TERRACE);
			    
			    s.localizations.set(DHXLocalization.French);
			    
			    s.extensions.add(DHXExtension.COLLISION);
			    s.extensions.add(DHXExtension.READONLY);
			    
			    s.config.setMarkNow(false);
			    s.config.setEventDuration(60);
			    
			    s.setWidth(900);
			    s.highlight.enable("highlight_area");
			    s.setInitialDate(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
			    s.load("events.jsp", DHXDataFormat.JSON);
			    s.data.dataprocessor.setURL("events.jsp");
			    return s.render();
			}
		%>
	</body>
	<script type="text/javascript">
	scheduler.attachEvent("onBeforeEventChanged", function (event, e, is_new, original){
		event.readonly = false;
		event.start_date = adjustDate(event.start_date);
		event.end_date = adjustDate(event.end_date);
		return true;
	});
	scheduler.attachEvent("onBeforeDrag", function (id, mode, e){
		return checkReadOnly(id);
	});
	scheduler.attachEvent("onClick", function (id, mode, e){
		return checkReadOnly(id);
	});
	scheduler.attachEvent("onDblClick", function (id, mode, e){
		return checkReadOnly(id);
	});
	function checkReadOnly(id){
		if(scheduler.getEvent(id).readonly){
			return false;
		}
	    return true;
	}
	function adjustDate(date){
		var min = date.getMinutes();
		if (min < 15){
			date.setMinutes(0);
		}else if (min <45){
			date.setMinutes(30);
		}else{
			date.setMinutes(0);
			date.setHours(date.getHours()+1);
		}
		return date;
	}
	</script>
	<style>
        .highlight_area {
            opacity: 0.3;
            z-index:0;
            filter:alpha(opacity=30);
        } 
        .highlight_area:hover {
            background-color: #99F799;
        }
</style>
</html>