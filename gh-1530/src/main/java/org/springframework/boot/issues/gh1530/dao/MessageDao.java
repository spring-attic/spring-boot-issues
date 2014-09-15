package org.springframework.boot.issues.gh1530.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.boot.issues.gh1530.model.Message;

@Repository
public class MessageDao {

    static final public String SELECTED_FIELDS = "m_tr_id, m_message, m_status";

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Message instance) {
        String sql = "insert into log_message (" + SELECTED_FIELDS + ") values (?,?,?)";
        Object[] params = new Object[]{
            instance.getTransactionId(),
            instance.getMessage(),
            instance.getStatus()
        };
        this.jdbcTemplate.update(sql, params);
    }

    public List<Message> readAll() {
        String query = "select " + SELECTED_FIELDS + " from log_message";
        List<Message> retVal = this.jdbcTemplate.query(query, new MessageRowMapper());
        return retVal;
    }

    public void deleteAll() {
        String sql = "delete from log_message";
        int updRows = this.jdbcTemplate.update(sql);
    }

    protected class MessageRowMapper implements RowMapper<Message> {

        public MessageRowMapper() {
        }

        @Override
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            String m_tr_id = rs.getString("m_tr_id");
            String m_message = rs.getString("m_message");
            String m_status = rs.getString("m_status");
            return new Message(m_tr_id, m_message, m_status);
        }
    }

}
