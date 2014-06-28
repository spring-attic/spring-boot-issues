package sciuto.corey.lcwdb.model;

import java.io.Serializable;

public class SortBy implements Serializable {

	private static final long serialVersionUID = -7960629557833296665L;

	public enum SortType{
		NAME_ASC("Name ascending"),
		NAME_DESC("Name decending"),
		DOD_ASC("Date of death ascending"),
		DOD_DESC("Date of death decending");
		
		private final String displayName;
		
		private SortType(String displayName){
			this.displayName = displayName;
		}
		
		@Override
		public String toString(){
			return this.displayName;
		}
	};
	
	private SortType sortType;
	
	public SortType getSortType() {
		return sortType;
	}

	public void setSortType(SortType sortType) {
		this.sortType = sortType;
	}

	public String getSortTypeStr() {
		return sortType.toString();
	}

	public void setSortTypeStr(String sortTypeStr) {
		sortType = SortBy.convertFromInternal(sortTypeStr);
	}
	
	public static SortType convertFromInternal(String internalValue){
		for (SortType t : SortType.values()){
			if (t.displayName.equals(internalValue)){
				return t;
			}
		}
		throw new IllegalArgumentException(internalValue + " is not a valid value for SortType enum");
	}
	
}
