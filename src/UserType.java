package src;

public  enum UserType {
	BS("buy-standard"),
	SS("sell-standard"),
	FS ("full-standard"),
	AA ("admin");
	private final String value;
	UserType(String value){
        this.value = value;
    }
    String getValue(){
        return this.value;
    }
    public static UserType getFromStr(String v) {
    	if("BS".equalsIgnoreCase(v))
    		return BS;
    	else if("SS".equalsIgnoreCase(v))
    		return SS;
    	else if("FS".equalsIgnoreCase(v))
    		return FS;
    	else if("AA".equalsIgnoreCase(v))
    		return AA;
    	return null;
    }
    public String toString(){
		if("buy-standard".equalsIgnoreCase(this.value))
			return "BS";
		else if("sell-standard".equalsIgnoreCase(this.value))
			return "SS";
		else if("full-standard".equalsIgnoreCase(this.value))
			return "FS";
		else if("admin".equalsIgnoreCase(this.value))
			return "AA";
		return null;
	}
}
