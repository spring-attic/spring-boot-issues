package org.springframework.boot.issues.gh1530.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.boot.issues.gh1530.model.MyModel;

@Repository
public class MyModelDao {

    static final public String SELECTED_FIELDS = "my_value";

    protected DataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(MyModel instance) {
        String sql = "insert into my_data (" + SELECTED_FIELDS + ") values (?)";
        Object[] params = new Object[]{
            instance.getMyValue()
        };
        this.jdbcTemplate.update(sql, params);
    }

    public List<MyModel> readAll() {
        String query = "select " + SELECTED_FIELDS + " from my_data";
        List<MyModel> retVal = this.jdbcTemplate.query(query, new MyModelRowMapper());
        return retVal;
    }

    public void deleteAll() {
        String sql = "delete from my_data";
        int updRows = this.jdbcTemplate.update(sql);
    }

    protected class MyModelRowMapper implements RowMapper<MyModel> {

        public MyModelRowMapper() {
        }

        @Override
        public MyModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            String my_value = rs.getString("my_value");
            return new MyModel(my_value);
        }

    }

}
