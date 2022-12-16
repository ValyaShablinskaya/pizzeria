package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PizzaInfoService implements IPizzaInfoService {
    private final IPizzaInfoDao dao;
    private final IPizzaInfoMapper pizzaInfoMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "PizzaInfo is not found";

    public PizzaInfoService(IPizzaInfoDao dao, IPizzaInfoMapper pizzaInfoMapper) {
        this.dao = dao;
        this.pizzaInfoMapper = pizzaInfoMapper;
    }

    @Override
    @Transactional
    public PizzaInfoDTO create(PizzaInfoDTO pizzaInfoDTO) {
        PizzaInfo pizzaInfo = pizzaInfoMapper.convertToPizzaInfo(pizzaInfoDTO);
        pizzaInfo.setCreationDate(LocalDateTime.now());
        pizzaInfo.setUpdateDate(pizzaInfo.getCreationDate());
        pizzaInfo = dao.save(pizzaInfo);
        return pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfo);
    }

    @Override
    public PizzaInfoDTO read(Long id) {
        PizzaInfo pizzaInfo = dao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfo);
    }

    @Override
    public List<PizzaInfoDTO> get() {
        List<PizzaInfo> pizzaInfos = dao.findAll();
        return pizzaInfoMapper.convertToPizzaInfoList(pizzaInfos);
    }

    @Override
    @Transactional
    public PizzaInfoDTO update(PizzaInfoDTO pizzaInfoDTO, Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToUpdate = dao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!pizzaInfoToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        pizzaInfoToUpdate.setUpdateDate(LocalDateTime.now());
        pizzaInfoToUpdate.setName(pizzaInfoDTO.getName());
        pizzaInfoToUpdate.setDescription(pizzaInfoDTO.getDescription());
        pizzaInfoToUpdate.setSize(pizzaInfoDTO.getSize());
        pizzaInfoToUpdate = dao.save(pizzaInfoToUpdate);

        return pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfoToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToDelete = dao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!pizzaInfoToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        dao.deleteById(id);
    }
}
