package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IDoneOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DoneOrderDao extends AbstractCrudDao<DoneOrder> implements IDoneOrderDao {
    private static final String SAVE_QUERY =
            "INSERT INTO done_order(ticket_id, creation_date, update_date) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM done_order " +
            "LEFT JOIN ticket ON done_order.ticket_id = ticket.id " +
            "WHERE done_order.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM done_order " +
            "LEFT JOIN ticket ON done_order.ticket_id = ticket.id " +
            "ORDER BY done_order.id";
    private static final String UPDATE_QUERY =
            "UPDATE done_order SET ticket_id = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM done_order WHERE id = ?";

    public DoneOrderDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, DoneOrder doneOrder) throws SQLException {
        preparedStatement.setLong(1, doneOrder.getTicket().getId());
        preparedStatement.setObject(2, doneOrder.getCreationDate());
        preparedStatement.setObject(3, doneOrder.getUpdateDate());
    }

    @Override
    protected DoneOrder mapResultSetToEntity(ResultSet resultSet) throws SQLException {
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

        return DoneOrder.builder()
                .id(resultSet.getLong("id"))
                .ticket(ticket)
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, DoneOrder doneOrder) throws SQLException {
        preparedStatement.setLong(1, doneOrder.getTicket().getId());
        preparedStatement.setObject(2, doneOrder.getCreationDate());
        preparedStatement.setObject(3, doneOrder.getUpdateDate());
        preparedStatement.setLong(4, doneOrder.getId());
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
