package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import java.time.LocalDateTime;
import java.util.List;

public interface IEssenceService<TYPE> {
    TYPE add(TYPE type);
    TYPE findById(Long id);
    List<TYPE> findAll();
    TYPE update(TYPE type, Long id, LocalDateTime updateData);
    void deleteById(Long id, LocalDateTime updateData);
}
