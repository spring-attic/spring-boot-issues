package sciuto.corey.lcwdb.model;

import java.io.Serializable;
import java.util.Date;

public class QueryByNameResultRecord implements Serializable {

	private static final long serialVersionUID = -6050048760688697603L;
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String suffix;
	private String cemeteryName;
	private Integer cemeteryId;
	private Boolean killedInAction;
	private Integer id;
	private Date dateOfDeath;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getCemeteryName() {
		return cemeteryName;
	}
	public void setCemeteryName(String cemeteryName) {
		this.cemeteryName = cemeteryName;
	}
	public Integer getCemeteryId() {
		return cemeteryId;
	}
	public void setCemeteryId(Integer cemeteryId) {
		this.cemeteryId = cemeteryId;
	}
	public Boolean getKilledInAction() {
		return killedInAction;
	}
	public void setKilledInAction(Boolean killedInAction) {
		this.killedInAction = killedInAction;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
}
