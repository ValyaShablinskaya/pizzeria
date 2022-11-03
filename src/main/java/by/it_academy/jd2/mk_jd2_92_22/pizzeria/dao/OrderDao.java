package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderDao extends AbstractCrudDao<Order> implements IOrderDao {
    private static final String SAVE_QUERY =
            "INSERT INTO orders(creation_date, update_date) VALUES (?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM orders WHERE orders.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM orders ORDER BY orders.id";
    private static final String UPDATE_QUERY =
            "UPDATE orders SET creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM orders WHERE id = ?";

    public OrderDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY,
                DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setObject(1, order.getCreationDate());
        preparedStatement.setObject(2, order.getUpdateDate());
    }

    @Override
    protected Order mapResultSetToEntity(ResultSet resultSet) throws SQLException {

        return Order.builder()
                .id(resultSet.getLong("id"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Order order) throws SQLException {
        preparedStatement.setObject(1, order.getCreationDate());
        preparedStatement.setObject(2, order.getUpdateDate());
        preparedStatement.setLong(3, order.getId());
    }

    private LocalDateTime convertDateFormat(String date) throws SQLException {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
