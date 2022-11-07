package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class PizzaService implements IPizzaService {
    private final PizzaDao pizzaDao;

    public PizzaService(PizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public void add(Pizza pizza) {
        pizza.setCreationDate(LocalDateTime.now());
        pizzaDao.save(pizza);
    }

    @Override
    public Pizza findById(Long id) {
        return pizzaDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Pizza is not found"));
    }

    @Override
    public List<Pizza> findAll() {
        return pizzaDao.findAll();
    }

    @Override
    public void update(Pizza pizza, Long id, LocalDateTime updateDate) {
        Pizza updatePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pizza is not found"));
        if (!updatePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        updatePizza.setUpdateDate(LocalDateTime.now());
        updatePizza.setName(pizza.getName());
        updatePizza.setSize(pizza.getSize());
        updatePizza.setDoneOrder(pizza.getDoneOrder());
        pizzaDao.update(updatePizza);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Pizza deletePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pizza is not found"));
        if (!deletePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        pizzaDao.deleteById(id);
    }

    @Override
    public List<Pizza> findAllByIdDoneOrder(Long id) {
        return pizzaDao.findAllPizzasByIdDoneOrder(id);
    }
}
