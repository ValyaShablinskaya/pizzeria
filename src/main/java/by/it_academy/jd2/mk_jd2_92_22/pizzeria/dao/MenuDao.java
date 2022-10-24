package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


public class MenuDao extends AbstractCrudDao<Menu> implements IMenuDao {
    private static final String SAVE_QUERY =
            "INSERT INTO menu(menu_row_id, creation_date, update_date) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM menu " +
            "LEFT OUTER JOIN menu_row ON menu.menu_row_id = menu_row.id " +
            "WHERE pizza_info.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM menu ORDER BY id " +
            "LEFT OUTER JOIN menu_row ON menu.menu_row_id = menu_row.id " +
            "ORDER BY menu.id";
    private static final String UPDATE_QUERY =
            "UPDATE menu SET creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM menu WHERE id = ?";

    public MenuDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Menu menu) throws SQLException {
        preparedStatement.setLong(1, menu.getMenuRow().getId());
        preparedStatement.setObject(2, menu.getCreationDate());
        preparedStatement.setObject(3, menu.getUpdateDate());
    }

    @Override
    protected Menu mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        MenuRow menuRow = MenuRow.builder()
                .id(resultSet.getLong("menu_row_id"))
                .price(resultSet.getDouble("price"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return Menu.builder()
                .id(resultSet.getLong("id"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .menuRow(menuRow)
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Menu menu) throws SQLException {
        preparedStatement.setObject(1, menu.getUpdateDate());
        preparedStatement.setObject(2, menu.getUpdateDate());
        preparedStatement.setLong(3, menu.getId());
    }

    private LocalDateTime convertDateFormat(String date) throws SQLException {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
