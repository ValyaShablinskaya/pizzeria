package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PizzaInfoDao extends AbstractCrudDao<PizzaInfo> implements IPizzaInfoDao {
    private static final String SAVE_QUERY =
            "INSERT INTO pizza_info(name, description, size, creation_date, update_date) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM pizza_info WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM pizza_info ORDER BY id";
    private static final String UPDATE_QUERY =
            "UPDATE pizza_info SET name = ?, description = ?, size = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM pizza_info WHERE id = ?";

    public PizzaInfoDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, PizzaInfo pizzaInfo) throws SQLException {
        preparedStatement.setString(1, pizzaInfo.getName());
        preparedStatement.setString(2, pizzaInfo.getDescription());
        preparedStatement.setLong(3, pizzaInfo.getSize());
        preparedStatement.setObject(4, pizzaInfo.getCreationDate());
        preparedStatement.setObject(5, pizzaInfo.getUpdateDate());
    }

    @Override
    protected PizzaInfo mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return PizzaInfo.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .size(resultSet.getLong("size"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, PizzaInfo pizzaInfo) throws SQLException {
        preparedStatement.setString(1, pizzaInfo.getName());
        preparedStatement.setString(2, pizzaInfo.getDescription());
        preparedStatement.setLong(3, pizzaInfo.getSize());
        preparedStatement.setObject(4, pizzaInfo.getUpdateDate());
        preparedStatement.setObject(5, pizzaInfo.getUpdateDate());
        preparedStatement.setLong(6, pizzaInfo.getId());
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
