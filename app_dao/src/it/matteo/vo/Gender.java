package it.matteo.vo;

public enum Gender {
M, F;
	public static Gender toGender(String g){
		switch(g) {
			case "M":
				return M;
			case "F":
				return F;	
			default:
				return null;
		}
	}
}
