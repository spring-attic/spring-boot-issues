package sciuto.corey.lcwdb.model;

import java.io.Serializable;

public class Ship implements Serializable {

	private static final long serialVersionUID = -8893890634680740030L;
	
	private String ship;

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}
	
}
