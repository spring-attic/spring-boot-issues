package sciuto.corey.lcwdb.model;

import java.io.Serializable;

public class Unit implements Serializable {
	
	private static final long serialVersionUID = -5530355732881322952L;
	
	private String regiment;
	private String company;
	
	public String getRegiment() {
		return regiment;
	}
	public void setRegiment(String regiment) {
		this.regiment = regiment;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
