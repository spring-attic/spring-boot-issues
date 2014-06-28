package sciuto.corey.lcwdb.services;

import java.util.List;

import sciuto.corey.lcwdb.model.Cemetery;
import sciuto.corey.lcwdb.model.QueryByName;
import sciuto.corey.lcwdb.model.QueryByNameResultRecord;
import sciuto.corey.lcwdb.model.SoldierResultRecord;


public interface DataService {

	/**
	 * The maximum number of rows we'll return.
	 */
	public static final int MAX_NAME_ROWS = 100;
	
	/**
	 * Queries for a record by a soldier's name. Returns up to MAX_NAME_ROWS
	 * @param query
	 * @return A list of matching soldiers
	 */
	public QueryResultList<QueryByNameResultRecord> queryByName(QueryByName query);
	
	/**
	 * Queries for a soldier by id
	 * @param id
	 * @return The soldier
	 * @throws IllegalArgumentException If the ID is invalid.
	 */
	public SoldierResultRecord querySoldier(Integer id) throws IllegalArgumentException;

	/**
	 * Returns all the cemeteries
	 * @return
	 */
	public List<Cemetery> getCemeteries();
	
}
