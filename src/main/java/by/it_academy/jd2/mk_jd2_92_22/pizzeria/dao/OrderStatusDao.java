package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IOrderStatusDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class OrderStatusDao extends AbstractCrudDao<OrderStatus>implements IOrderStatusDao {

    private static final String SAVE_QUERY =
            "INSERT INTO order_status(ticket_id, done, creation_date, update_date) VALUES (?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM order_status " +
            "LEFT JOIN ticket ON order_status.ticket_id = ticket.id " +
            "WHERE order_status.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM order_status " +
            "LEFT JOIN ticket ON order_status.ticket_id = ticket.id " +
            "ORDER BY order_status.id";
    private static final String UPDATE_QUERY =
            "UPDATE order_status SET ticket_id = ?, done = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM order_status WHERE id = ?";

    public OrderStatusDao(DataSource connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, OrderStatus orderStatus) throws SQLException {
        preparedStatement.setLong(1, orderStatus.getTicket().getId());
        preparedStatement.setBoolean(2, orderStatus.isDone());
        preparedStatement.setObject(3, orderStatus.getCreationDate());
        preparedStatement.setObject(4, orderStatus.getUpdateDate());
    }

    @Override
    protected OrderStatus mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Order order = Order.builder()
                .id(resultSet.getLong("orders_id"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        Ticket ticket = Ticket.builder()
                .id(resultSet.getLong("ticket_id"))
                .order(order)
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return OrderStatus.builder()
                .id(resultSet.getLong("id"))
                .ticket(ticket)
                .isDone(resultSet.getBoolean("done"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, OrderStatus orderStatus) throws SQLException {
        preparedStatement.setLong(1, orderStatus.getTicket().getId());
        preparedStatement.setObject(2, orderStatus.isDone());
        preparedStatement.setObject(3, orderStatus.getCreationDate());
        preparedStatement.setObject(4, orderStatus.getUpdateDate());
        preparedStatement.setLong(5, orderStatus.getId());
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
