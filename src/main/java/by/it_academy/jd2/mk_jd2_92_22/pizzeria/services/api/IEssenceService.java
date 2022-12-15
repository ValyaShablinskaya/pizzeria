package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api;

import java.time.LocalDateTime;
import java.util.List;

public interface IEssenceService<DTO> {
    DTO create(DTO type);
    DTO read(Long id);
    List<DTO> get();
    DTO update(DTO item, Long id, LocalDateTime updateData);
    void delete(Long id, LocalDateTime updateData);
}
