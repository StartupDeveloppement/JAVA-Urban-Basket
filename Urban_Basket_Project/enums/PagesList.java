package enums;

public enum PagesList {
	
    VUE_LOGIN("/login.jsp"),
    VUE_SIGNIN("/signin.jsp"),
    REDIRECT_LOGIN("login.jsp"),
    REDIRECT_HOME("home");
	
	private String value;
	
	private PagesList(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
