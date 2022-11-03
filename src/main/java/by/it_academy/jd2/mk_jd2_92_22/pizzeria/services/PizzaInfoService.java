package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityAlreadyExistException;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


public class PizzaInfoService implements IPizzaInfoService {
   private final PizzaInfoDao pizzaInfoDao;

    public PizzaInfoService(PizzaInfoDao pizzaInfoDao) {
        this.pizzaInfoDao = pizzaInfoDao;
    }

    @Override
    public void add(PizzaInfo pizzaInfo) {
        pizzaInfo.setCreationDate(LocalDateTime.now());
        pizzaInfoDao.save(pizzaInfo);
    }

    @Override
    public PizzaInfo findById(Long id) {
        return pizzaInfoDao.findById(id).orElseThrow(() -> new EntityNotFoundException("PizzaInfo is not found"));
    }

    @Override
    public List<PizzaInfo> findAll() {
        return pizzaInfoDao.findAll();
    }

    @Override
    public void update(PizzaInfo pizzaInfo, Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToUpdate = pizzaInfoDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PizzaInfo is not found"));
        if (!pizzaInfoToUpdate.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        pizzaInfoToUpdate.setUpdateDate(LocalDateTime.now());
        pizzaInfoToUpdate.setName(pizzaInfo.getName());
        pizzaInfoToUpdate.setDescription(pizzaInfo.getDescription());
        pizzaInfoToUpdate.setSize(pizzaInfo.getSize());

        pizzaInfoDao.update(pizzaInfoToUpdate);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        PizzaInfo pizzaInfoToDelete = pizzaInfoDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("PizzaInfo is not found"));
        if (!pizzaInfoToDelete.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("PizzaInfo has been already edited");
        }
        pizzaInfoDao.deleteById(id);
    }
}
