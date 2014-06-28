package sciuto.corey.lcwdb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SoldierResultRecord implements Serializable {

	private static final long serialVersionUID = -1800024067406629184L;
	
	private String firstName;
	private String middleInitial;
	private String lastName;
	private String suffix;
	private String cemeteryName;
	private Boolean killedInAction;
	private Integer id;
	private String cemeteryLot;
	private Integer cemeteryBookPage;
	private Date dateOfDeath;
	
	private String residenceState;
	private String residenceCity;

	private String monumentType;
	private String notes;

	private String rank;
	
	private List<Unit> units;
	private List<Ship> ships;
	
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

	public String getCemeteryLot() {
		return cemeteryLot;
	}

	public void setCemeteryLot(String cemeteryLot) {
		this.cemeteryLot = cemeteryLot;
	}
	
	public Integer getCemeteryBookPage() {
		return cemeteryBookPage;
	}

	public void setCemeteryBookPage(Integer cemeteryBookPage) {
		this.cemeteryBookPage = cemeteryBookPage;
	}
	
	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	public String getResidenceState() {
		return residenceState;
	}

	public void setResidenceState(String residenceState) {
		this.residenceState = residenceState;
	}

	public String getResidenceCity() {
		return residenceCity;
	}

	public void setResidenceCity(String residenceCity) {
		this.residenceCity = residenceCity;
	}

	public String getMonumentType() {
		return monumentType;
	}

	public void setMonumentType(String monumentType) {
		this.monumentType = monumentType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
}
