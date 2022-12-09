package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ITicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TicketDao extends AbstractCrudDao<Ticket> implements ITicketDao {
    private static final String SAVE_QUERY =
            "INSERT INTO ticket(orders_id, creation_date, update_date) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM ticket " +
            "LEFT JOIN orders ON ticket.orders_id = orders.id " +
            "WHERE ticket.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM ticket " +
            "LEFT JOIN orders ON ticket.orders_id = orders.id " +
            "ORDER BY ticket.id";
    private static final String UPDATE_QUERY =
            "UPDATE ticket SET orders_id = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM ticket WHERE id = ?";

    public TicketDao(DataSource connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Ticket ticket) throws SQLException {
        preparedStatement.setLong(1, ticket.getOrder().getId());
        preparedStatement.setObject(2, ticket.getCreationDate());
        preparedStatement.setObject(3, ticket.getUpdateDate());
    }

    @Override
    protected Ticket mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Order order = Order.builder()
                .id(resultSet.getLong("orders_id"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return Ticket.builder()
                .id(resultSet.getLong("id"))
                .order(order)
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Ticket ticket) throws SQLException {
        preparedStatement.setLong(1, ticket.getOrder().getId());
        preparedStatement.setObject(2, ticket.getCreationDate());
        preparedStatement.setObject(3, ticket.getUpdateDate());
        preparedStatement.setLong(4, ticket.getId());
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
