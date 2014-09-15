package org.springframework.boot.issues.gh1530.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.JdbcUpdateAffectedIncorrectNumberOfRowsException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.boot.issues.gh1530.model.Transaction;

@Repository
public class TransactionDao {

    static final public String SELECTED_FIELDS = "tr_id, tr_type, tr_status";

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Transaction instance) {
        String sql = "insert into log_transaction (" + SELECTED_FIELDS + ") values (?,?,?)";
        Object[] params = new Object[]{
            instance.getTransactionId(),
            instance.getType(),
            instance.getStatus()
        };
        this.jdbcTemplate.update(sql, params);
    }

    public Transaction read(String transactionId) {
        String query = "select " + SELECTED_FIELDS + " from log_transaction where tr_id = ?";
        Transaction retVal = this.jdbcTemplate.queryForObject(query, new TransactionRowMapper(), transactionId);
        return retVal;
    }

    public List<Transaction> readAll() {
        String query = "select " + SELECTED_FIELDS + " from log_transaction";
        List<Transaction> retVal = this.jdbcTemplate.query(query, new TransactionDao.TransactionRowMapper());
        return retVal;
    }

    public void update(Transaction instance) {
        String sql = "update log_transaction set tr_type = ?, tr_status = ? "
                + "where tr_id = ?";

        Object[] params = new Object[]{
            instance.getType(),
            instance.getStatus(),
            instance.getTransactionId()
        };

        int updRows = this.jdbcTemplate.update(sql, params);
        if (updRows != 1) {
            throw new JdbcUpdateAffectedIncorrectNumberOfRowsException(sql, 1, updRows);
        }
    }

    public void deleteAll() {
        String sql = "delete from log_transaction";
        int updRows = this.jdbcTemplate.update(sql);
    }

    protected class TransactionRowMapper implements RowMapper<Transaction> {

        public TransactionRowMapper() {
        }

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            String m_tr_id = rs.getString("tr_id");
            String m_type = rs.getString("tr_type");
            String m_status = rs.getString("tr_status");
            return new Transaction(m_tr_id, m_type, m_status);
        }
    }

}
