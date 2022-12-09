package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


public class PizzaInfoService implements IPizzaInfoService {
   private final PizzaInfoDao pizzaInfoDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "PizzaInfo is not found";

    public PizzaInfoService(PizzaInfoDao pizzaInfoDao) {
        this.pizzaInfoDao = pizzaInfoDao;
    }

    @Override
    public PizzaInfoDTO add(PizzaInfoDTO pizzaInfoDTO) {
        PizzaInfo pizzaInfo = IPizzaInfoMapper.INSTANCE.convertToPizzaInfo(pizzaInfoDTO);
        pizzaInfo.setCreationDate(LocalDateTime.now());
        pizzaInfo.setUpdateDate(pizzaInfo.getCreationDate());
        pizzaInfo = pizzaInfoDao.save(pizzaInfo).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IPizzaInfoMapper.INSTANCE.convertToPizzaInfoDTO(pizzaInfo);
    }

    @Override
    public PizzaInfoDTO findById(Long id) {
        PizzaInfo pizzaInfo = pizzaInfoDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IPizzaInfoMapper.INSTANCE.convertToPizzaInfoDTO(pizzaInfo);
    }

    @Override
    public List<PizzaInfoDTO> findAll() {
        List<PizzaInfo> pizzaInfos = pizzaInfoDao.findAll();
        return IPizzaInfoMapper.INSTANCE.convertToPizzaInfoList(pizzaInfos);
    }

    @Override
    public PizzaInfoDTO update(PizzaInfoDTO pizzaInfoDTO, Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToUpdate = pizzaInfoDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!pizzaInfoToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        pizzaInfoToUpdate.setUpdateDate(LocalDateTime.now());
        pizzaInfoToUpdate.setName(pizzaInfoDTO.getName());
        pizzaInfoToUpdate.setDescription(pizzaInfoDTO.getDescription());
        pizzaInfoToUpdate.setSize(pizzaInfoDTO.getSize());
        pizzaInfoToUpdate = pizzaInfoDao.update(pizzaInfoToUpdate).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));

        return IPizzaInfoMapper.INSTANCE.convertToPizzaInfoDTO(pizzaInfoToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToDelete = pizzaInfoDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!pizzaInfoToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        pizzaInfoDao.deleteById(id);
    }
}
