package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class PizzaService implements IPizzaService {
    private final PizzaDao pizzaDao;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Pizza is not found";

    public PizzaService(PizzaDao pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    @Override
    public PizzaDTO add(PizzaDTO pizzaDTO) {
        Pizza pizza = IPizzaMapper.INSTANCE.convertToPizza(pizzaDTO);
        pizza.setCreationDate(LocalDateTime.now());
        pizza.setUpdateDate(pizza.getCreationDate());
        pizza = pizzaDao.save(pizza).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IPizzaMapper.INSTANCE.convertToPizzaDTO(pizza);
    }

    @Override
    public PizzaDTO findById(Long id) {
        Pizza pizza = pizzaDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IPizzaMapper.INSTANCE.convertToPizzaDTO(pizza);
    }

    @Override
    public List<PizzaDTO> findAll() {
        List<Pizza> pizzas = pizzaDao.findAll();
        return IPizzaMapper.INSTANCE.convertToPizzaList(pizzas);
    }

    @Override
    public PizzaDTO update(PizzaDTO pizzaDTO, Long id, LocalDateTime updateDate) {
        Pizza updatePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updatePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        updatePizza.setUpdateDate(LocalDateTime.now());
        updatePizza.setName(pizzaDTO.getName());
        updatePizza.setSize(pizzaDTO.getSize());
        DoneOrder doneOrder = IDoneOrderMapper.INSTANCE.convertToDoneOrder(pizzaDTO.getDoneOrder());
        updatePizza.setDoneOrder(doneOrder);
        updatePizza = pizzaDao.update(updatePizza).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return IPizzaMapper.INSTANCE.convertToPizzaDTO(updatePizza);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        Pizza deletePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deletePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        pizzaDao.deleteById(id);
    }

    @Override
    public List<PizzaDTO> findAllByIdDoneOrder(Long id) {
        List<Pizza> pizzas = pizzaDao.findAllPizzasByIdDoneOrder(id);
        return IPizzaMapper.INSTANCE.convertToPizzaList(pizzas);
    }
}
