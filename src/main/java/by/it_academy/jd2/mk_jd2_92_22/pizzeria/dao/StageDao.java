package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IStageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class StageDao extends AbstractCrudDao<Stage> implements IStageDao {

    private static final String SAVE_QUERY =
            "INSERT INTO stage(description, creation_date, update_date) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM stage WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM stage ORDER BY id";
    private static final String UPDATE_QUERY =
            "UPDATE stage SET description = ?, creation_date = ?, update_date = ? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM stage WHERE id = ?";

    public static final String FIND_ALL_STAGE_BY_ID_ORDERSTATUS = "SELECT * FROM stage " +
            "WHERE stage.id IN "
            + "(SELECT stage_id FROM stage_order_status WHERE order_status_id IN "
            + "(SELECT order_status.id FROM order_status WHERE order_status.id = ?)) ORDER BY stage.id";
    private static final String CREATE_CATALOG_ROWS =
            "INSERT INTO stage_order_status(stage_id, order_status_id) VALUES (?, ?)";

    public StageDao(BDConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY);
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, Stage stage) throws SQLException {
        preparedStatement.setString(1, stage.getDescription());
        preparedStatement.setObject(2, stage.getCreationDate());
        preparedStatement.setObject(3, stage.getUpdateDate());
    }

    @Override
    protected Stage mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return Stage.builder()
                .id(resultSet.getLong("id"))
                .description(resultSet.getString("description"))
                .creationDate(convertDateFormat(resultSet.getString( "creation_date")))
                .updateDate(convertDateFormat(resultSet.getString("update_date")))
                .build();
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, Stage stage) throws SQLException {
        preparedStatement.setString(1, stage.getDescription());
        preparedStatement.setObject(2, stage.getUpdateDate());
        preparedStatement.setObject(3, stage.getUpdateDate());
        preparedStatement.setLong(4, stage.getId());
    }

    @Override
    public void addStageOnOrderStatus(Long stageId, Long orderStatusId) {
        saveRelation(orderStatusId, stageId, CREATE_CATALOG_ROWS);
    }

    @Override
    public List<Stage> findAllStageByIdOrderStatus(Long id) {
        return findAllByLongParameter(id, FIND_ALL_STAGE_BY_ID_ORDERSTATUS);
    }

    private LocalDateTime convertDateFormat(String date) {
        if (date == null) {
            return null;
        } else {
            return Timestamp.valueOf(date).toLocalDateTime();
        }
    }
}
