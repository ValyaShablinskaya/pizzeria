package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ISelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class SelectedItemDao extends AbstractCrudDao<SelectedItem> implements ISelectedItemDao {

    private static final String SAVE_QUERY =
            "INSERT INTO selected_item(menu_row_id, orders_id, count, creation_date, update_date) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM selected_item " +
            "LEFT JOIN menu_row ON selected_item.menu_row_id = menu_row.id " +
            "LEFT JOIN orders ON selected_item.orders_id = orders.id " +
            "LEFT JOIN pizza_info ON menu_row.pizza_info_id = pizza_info.id " +
            "WHERE selected_item.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM selected_item " +
            "LEFT JOIN menu_row ON selected_item.menu_row_id = menu_row.id " +
            "LEFT JOIN orders ON selected_item.orders_id = orders.id " +
            "LEFT JOIN pizza_info ON menu_row.pizza_info_id = pizza_info.id " +
            "ORDER BY selected_item.id";
    private static final String UPDATE_QUERY =
            "UPDATE selected_item SET count = ?, creation_date = ?, update_date = ?, orders_id = ?, menu_row_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM selected_item WHERE id = ?";

    public static final String FIND_ALL_SELECTED_ITEMS_BY_ID_ORDER = "SELECT * FROM selected_item " +
            "LEFT JOIN menu_row ON selected_item.menu_row_id = menu_row.id " +
            "LEFT JOIN orders ON selected_item.orders_id = orders.id " +
            "LEFT JOIN pizza_info ON menu_row.pizza_info_id = pizza_info.id " +
            "WHERE orders.id = ?";

    public SelectedItemDao(DataSource connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, SelectedItem selectedItem) throws SQLException {
        preparedStatement.setLong(1, selectedItem.getMenuRow().getId());
        preparedStatement.setDouble(2, selectedItem.getOrder().getId());
        preparedStatement.setObject(3, selectedItem.getCount());
        preparedStatement.setObject(4, selectedItem.getCreationDate());
        preparedStatement.setObject(5, selectedItem.getUpdateDate());
    }

    @Override
    protected SelectedItem mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        PizzaInfo pizzaInfo = PizzaInfo.builder()
                .id(resultSet.getLong("pizza_info_id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .size(resultSet.getLong("size"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        MenuRow menuRow = MenuRow.builder()
                .id(resultSet.getLong("menu_row_id"))
                .pizzaInfo(pizzaInfo)
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .price(resultSet.getDouble("price"))
                .build();

        Order order = Order.builder()
                .id(resultSet.getLong("orders_id"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return SelectedItem.builder()
                .id(resultSet.getLong("id"))
                .count(resultSet.getLong("count"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .menuRow(menuRow)
                .order(order)
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, SelectedItem selectedItem) throws SQLException {
        preparedStatement.setLong(1, selectedItem.getCount());
        preparedStatement.setObject(2, selectedItem.getCreationDate());
        preparedStatement.setObject(3, selectedItem.getUpdateDate());
        preparedStatement.setLong(4, selectedItem.getOrder().getId());
        preparedStatement.setLong(5, selectedItem.getMenuRow().getId());
        preparedStatement.setLong(6, selectedItem.getId());
    }

    @Override
    public List<SelectedItem> findAllSelectedItemByIdOrder(Long id) {
        return findAllByLongParameter(id, FIND_ALL_SELECTED_ITEMS_BY_ID_ORDER);
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
