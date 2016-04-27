package javaplanner;

import com.dhtmlx.planner.DHXEvent;

public class CustomEvent extends DHXEvent {
	public static final String WHITE = "white";
	public static final String BLACK = "black";
	public static final String GREY = "lightgrey";
	public static final String DEFAULT_BACKGROUND = "#1796b0";
	
    public Boolean readonly = false;
    public String textColor,color;

    public Boolean getReadonly() {
        return readonly;
    }
    public String getTextColor() {
    	return textColor;
    }
    public String getColor() {
    	return color;
    }

    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
    public void setColor(String color) {
        this.color = color;
    }
}