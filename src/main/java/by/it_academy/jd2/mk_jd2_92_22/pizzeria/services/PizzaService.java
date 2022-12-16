package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Pizza;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IDoneOrderMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PizzaService implements IPizzaService {
    private final IPizzaDao pizzaDao;
    private final IPizzaMapper pizzaMapper;
    private final IDoneOrderMapper doneOrderMapper;
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Pizza is not found";

    public PizzaService(IPizzaDao pizzaDao, IPizzaMapper pizzaMapper, IDoneOrderMapper doneOrderMapper) {
        this.pizzaDao = pizzaDao;
        this.pizzaMapper = pizzaMapper;
        this.doneOrderMapper = doneOrderMapper;
    }

    @Override
    @Transactional
    public PizzaDTO create(PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaMapper.convertToPizza(pizzaDTO);
        pizza.setCreationDate(LocalDateTime.now());
        pizza.setUpdateDate(pizza.getCreationDate());
        pizza = pizzaDao.save(pizza);
        return pizzaMapper.convertToPizzaDTO(pizza);
    }

    @Override
    public PizzaDTO read(Long id) {
        Pizza pizza = pizzaDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        return pizzaMapper.convertToPizzaDTO(pizza);
    }

    @Override
    public List<PizzaDTO> get() {
        List<Pizza> pizzas = pizzaDao.findAll();
        return pizzaMapper.convertToPizzaList(pizzas);
    }

    @Override
    @Transactional
    public PizzaDTO update(PizzaDTO pizzaDTO, Long id, LocalDateTime updateDate) {
        Pizza updatePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!updatePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        updatePizza.setUpdateDate(LocalDateTime.now());
        updatePizza.setName(pizzaDTO.getName());
        updatePizza.setSize(pizzaDTO.getSize());
        DoneOrder doneOrder = doneOrderMapper.convertToDoneOrder(pizzaDTO.getDoneOrder());
        updatePizza.setDoneOrder(doneOrder);
        updatePizza = pizzaDao.save(updatePizza);
        return pizzaMapper.convertToPizzaDTO(updatePizza);
    }

    @Override
    @Transactional
    public void delete(Long id, LocalDateTime updateDate) {
        Pizza deletePizza = pizzaDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION));
        if (!deletePizza.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("Pizza has been already edited");
        }
        pizzaDao.deleteById(id);
    }
}
