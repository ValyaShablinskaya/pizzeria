package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.ICrudDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.exception.DataBaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDao<E> implements ICrudDao<E, Long> {
    protected final BDConnector connector;
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String updateQuery;
    private final String deleteByIdQuery;

    public AbstractCrudDao(BDConnector connector, String saveQuery, String findByIdQuery, String findAllQuery,
                           String updateQuery, String deleteByIdQuery) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.updateQuery = updateQuery;
        this.deleteByIdQuery = deleteByIdQuery;
    }

    private static final BiConsumer<PreparedStatement, Long> LONG_CONSUMER =
            (PreparedStatement preparedStatement, Long param) -> {
                try {
                    preparedStatement.setLong(1, param);
                } catch (SQLException e) {
                    throw new DataBaseRuntimeException(e);
                }
            };

    private static final BiConsumer<PreparedStatement, String> STRING_CONSUMER =
            (PreparedStatement preparedStatement, String param) -> {
                try {
                    preparedStatement.setString(1, param);
                } catch (SQLException e) {
                    throw new DataBaseRuntimeException(e);
                }
            };
    @Override
    public void save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            insert(preparedStatement, entity);
           preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public Optional<E> findById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findByIdQuery)) {
            LONG_CONSUMER.accept(preparedStatement, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Found not give result!", e);
        }
    }

    @Override
    public List<E> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Found not give result!", e);
        }
    }

    @Override
    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            updateValues(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Update is failed!", e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteByIdQuery)) {
            LONG_CONSUMER.accept(preparedStatement, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseRuntimeException("Delete is failed!", e);
        }
    }

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;
    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;
    protected abstract void updateValues(PreparedStatement preparedStatement, E entity) throws SQLException;
}
