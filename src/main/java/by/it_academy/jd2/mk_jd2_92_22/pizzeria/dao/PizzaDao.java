package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class PizzaDao extends AbstractCrudDao<Pizza> implements IPizzaDao {
    private static final String SAVE_QUERY =
            "INSERT INTO pizza(name, size, creation_date, update_date, done_order_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM pizza " +
            "LEFT JOIN done_order ON pizza.done_order_id = done_order.id " +
            "LEFT JOIN ticket ON done_order.ticket_id = ticket.id " +
            "LEFT JOIN orders ON ticket.orders_id = orders.id " +
            "WHERE pizza.id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM pizza " +
            "LEFT JOIN done_order ON pizza.done_order_id = done_order.id " +
            "LEFT JOIN ticket ON done_order.ticket_id = ticket.id " +
            "LEFT JOIN orders ON ticket.orders_id = orders.id " +
            "ORDER BY pizza.id";
    private static final String UPDATE_QUERY =
            "UPDATE pizza SET name = ?, size = ?, creation_date = ?, update_date = ?, done_order_id = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM pizza WHERE id = ?";

    public static final String FIND_ALL_PIZZA_BY_ID_DONE_ORDER = "SELECT * FROM pizza " +
            "LEFT JOIN done_order ON pizza.done_order_id = done_order.id " +
            "LEFT JOIN ticket ON done_order.ticket_id = ticket.id " +
            "LEFT JOIN orders ON ticket.orders_id = orders.id " +
            "WHERE done_order.id = ?";

    public PizzaDao(DataSource connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Pizza pizza) throws SQLException {
        preparedStatement.setString(1, pizza.getName());
        preparedStatement.setLong(2, pizza.getSize());
        preparedStatement.setObject(3, pizza.getCreationDate());
        preparedStatement.setObject(4, pizza.getUpdateDate());
        preparedStatement.setLong(5, pizza.getDoneOrder().getId());
    }

    @Override
    protected Pizza mapResultSetToEntity(ResultSet resultSet) throws SQLException {
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

        DoneOrder doneOrder = DoneOrder.builder()
                .id(resultSet.getLong("done_order_id"))
                .ticket(ticket)
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();

        return Pizza.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .size(resultSet.getLong("size"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .doneOrder(doneOrder)
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Pizza pizza) throws SQLException {
        preparedStatement.setString(1, pizza.getName());
        preparedStatement.setLong(2, pizza.getSize());
        preparedStatement.setObject(3, pizza.getCreationDate());
        preparedStatement.setObject(4, pizza.getUpdateDate());
        preparedStatement.setLong(5, pizza.getDoneOrder().getId());
        preparedStatement.setLong(6, pizza.getId());
    }

    @Override
    public List<Pizza> findAllPizzasByIdDoneOrder(Long id) {
        return findAllByLongParameter(id, FIND_ALL_PIZZA_BY_ID_DONE_ORDER);
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
