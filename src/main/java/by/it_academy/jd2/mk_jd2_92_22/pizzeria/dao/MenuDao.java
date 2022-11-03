package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class MenuDao extends AbstractCrudDao<Menu> implements IMenuDao {
    private static final String SAVE_QUERY =
            "INSERT INTO menu(creation_date, update_date, name, enabled) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM menu WHERE menu.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM menu ORDER BY menu.id";
    private static final String UPDATE_QUERY =
            "UPDATE menu SET creation_date = ?, update_date = ?, name = ?, enabled = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM menu WHERE id = ?";

    public MenuDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY,
                DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Menu menu) throws SQLException {
        preparedStatement.setObject(1, menu.getCreationDate());
        preparedStatement.setObject(2, menu.getUpdateDate());
        preparedStatement.setString(3, menu.getName());
        preparedStatement.setBoolean(4, menu.isEnabled());
    }

    @Override
    protected Menu mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        return Menu.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .enabled(resultSet.getBoolean("enabled"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Menu menu) throws SQLException {
        preparedStatement.setObject(1, menu.getCreationDate());
        preparedStatement.setObject(2, menu.getUpdateDate());
        preparedStatement.setString(3, menu.getName());
        preparedStatement.setBoolean(4, menu.isEnabled());
        preparedStatement.setLong(5, menu.getId());
    }

    private LocalDateTime convertDateFormat(String date) throws SQLException {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
