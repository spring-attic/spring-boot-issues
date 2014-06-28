package sciuto.corey.lcwdb.model;

import java.io.Serializable;

public class Cemetery implements Serializable {

	private static final long serialVersionUID = -1500262064787766504L;

	private String name;
	private Integer id;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
