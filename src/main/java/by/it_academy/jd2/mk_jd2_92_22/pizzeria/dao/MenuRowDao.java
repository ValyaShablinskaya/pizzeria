package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IMenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.IllegalFormatCodePointException;

public class MenuRowDao extends AbstractCrudDao<MenuRow> implements IMenuRowDao {
    private static final String SAVE_QUERY =
            "INSERT INTO menu_row(pizza_info_id, price, creation_date, update_date) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM menu_row " +
            "LEFT JOIN pizza_info ON menu_row.pizza_info_id = pizza_info.id " +
            "WHERE menu_row.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM menu_row " +
            "LEFT JOIN pizza_info ON menu_row.pizza_info_id = pizza_info.id " +
            "ORDER BY menu_row.id";
    private static final String UPDATE_QUERY =
            "UPDATE menu_row SET price = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM menu_row WHERE id = ?";

    public MenuRowDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, MenuRow menuRow) throws SQLException {
        preparedStatement.setLong(1, menuRow.getPizzaInfo().getId());
        preparedStatement.setDouble(2, menuRow.getPrice());
        preparedStatement.setObject(3, menuRow.getCreationDate());
        preparedStatement.setObject(4, menuRow.getUpdateDate());
    }

    @Override
    protected MenuRow mapResultSetToEntity(ResultSet resultSet) throws SQLException {
//        Timestamp creationDateTimestamp = Timestamp.valueOf(resultSet.getString("creation_date"));
//        Timestamp updateDateDateTimestamp = Timestamp.valueOf(resultSet.getString("update_date"));
        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(resultSet.getLong("pizza_info_id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .size(resultSet.getLong("size"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return MenuRow.builder()
                .id(resultSet.getLong("id"))
                .price(resultSet.getDouble("price"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .pizzaInfo(pizzaInfo)
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, MenuRow menuRow) throws SQLException {
        preparedStatement.setDouble(1, menuRow.getPrice());
        preparedStatement.setObject(2, menuRow.getCreationDate());
        preparedStatement.setObject(3, menuRow.getUpdateDate());
        preparedStatement.setLong(4, menuRow.getId());
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
