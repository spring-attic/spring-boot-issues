package sciuto.corey.lcwdb.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

public class QueryByName implements Serializable {

	private static final long serialVersionUID = -2366420315963533581L;
	
	private String firstName;
	private String lastName;
	private Boolean fiveHundredClub;
	private Integer cemeteryId;
	private String sortBy;
	
	@Pattern(regexp="(^$)|(^[0-9]{4}-[0-9]{2}-[0-9]{2}$)", message="Dates must be in YYYY-MMM-DD format")
	private String startDeathDate;
	
	@Pattern(regexp="(^$)|(^[0-9]{4}-[0-9]{2}-[0-9]{2}$)", message="Dates must be in YYYY-MMM-DD format")
	private String endDeathDate;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getFiveHundredClub() {
		return fiveHundredClub;
	}
	public void setFiveHundredClub(Boolean fiveHundredClub) {
		this.fiveHundredClub = fiveHundredClub;
	}
	public Integer getCemeteryId() {
		return cemeteryId;
	}
	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getStartDeathDate() {
		return startDeathDate;
	}
	public void setStartDeathDate(String startDeathDate) {
		this.startDeathDate = startDeathDate;
	}
	public String getEndDeathDate() {
		return endDeathDate;
	}
	public void setEndDeathDate(String endDeathDate) {
		this.endDeathDate = endDeathDate;
	}
	
}
