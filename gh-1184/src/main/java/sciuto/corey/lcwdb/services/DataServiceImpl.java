package sciuto.corey.lcwdb.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import sciuto.corey.lcwdb.model.Cemetery;
import sciuto.corey.lcwdb.model.QueryByName;
import sciuto.corey.lcwdb.model.QueryByNameResultRecord;
import sciuto.corey.lcwdb.model.SoldierResultRecord;

@Service
public class DataServiceImpl implements DataService {

	private static final Logger LOGGER = Logger.getLogger(DataServiceImpl.class);

	//@Autowired
	//DataDao dataDao;

	@Override
	public QueryResultList<QueryByNameResultRecord> queryByName(QueryByName query) {
		
		List<QueryByNameResultRecord> records = null;//dataDao.queryByName(query);
		
		QueryResultList<QueryByNameResultRecord> results = new QueryResultList<QueryByNameResultRecord>();
		results.setTotalResults(records.size());
		results.setTruncated(false);
		
		if (records.size() > MAX_NAME_ROWS){
			if (LOGGER.isInfoEnabled()){
				LOGGER.info(records.size() + " results returned. Truncating to " + MAX_NAME_ROWS);
			}
			records = records.subList(0, MAX_NAME_ROWS);
			results.setTruncated(true);
		}
		
		results.setResults(records);
		
		return results;
	}
	
	@Override
	public SoldierResultRecord querySoldier(Integer id) throws IllegalArgumentException {
		return null; //dataDao.querySoldier(id);
	}

	@Override
	public List<Cemetery> getCemeteries() {
		return null; //dataDao.getCemeteries();
	}
/*
	public DataDao getDataDao() {
		return dataDao;
	}

	public void setDataDao(DataDao dataDao) {
		this.dataDao = dataDao;
	}
	*/
}
