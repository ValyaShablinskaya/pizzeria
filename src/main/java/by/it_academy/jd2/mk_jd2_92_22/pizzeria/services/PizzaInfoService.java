package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
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
    public void update(PizzaInfo pizzaInfo) {
        pizzaInfo.setUpdateDate(LocalDateTime.now());
        if (!pizzaInfoDao.findById(pizzaInfo.getId()).isPresent()) {
            throw new EntityNotFoundException("PizzaInfo is not found");
        }
        pizzaInfoDao.update(pizzaInfo);
    }

    @Override
    public void deleteById(Long id) {
        if (!pizzaInfoDao.findById(id).isPresent()) {
            throw new EntityNotFoundException("PizzaInfo is not found");
        }
        pizzaInfoDao.deleteById(id);
    }
}
