package by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api;

import java.util.List;
import java.util.Optional;

public interface ICrudDao<E, ID> {
    void save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

    void deleteById(ID id);
}
