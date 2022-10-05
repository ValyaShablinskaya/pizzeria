package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityAlreadyExistException;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.PizzaInfoStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;

import java.util.List;
import java.util.Optional;

public class PizzaInfoService implements IPizzaInfoService {
    private final PizzaInfoStorage storage;

    public PizzaInfoService(PizzaInfoStorage storage) {
        this.storage = storage;
    }

    @Override
    public List<PizzaInfo> findAll() {
        return this.storage.findAll();
    }

    @Override
    public PizzaInfo add(PizzaInfo pizzaInfo) {
        this.validate(pizzaInfo);
        return storage.add(pizzaInfo).orElseThrow(() -> new EntityNotFoundException("Pizza info not found"));
    }

    @Override
    public PizzaInfo findByName(String name) {
        return this.storage.findByName(name).orElseThrow(() -> new EntityNotFoundException("Pizza info not found"));
    }

    @Override
    public void delete(PizzaInfo pizzaInfo) {
        this.storage.delete(pizzaInfo);
    }

    @Override
    public void validate(PizzaInfo pizzaInfo) {
        if (pizzaInfo == null) {
            throw new IllegalStateException("You did not transfer pizza info");
        }
        if (storage.findByName(pizzaInfo.getName()).isPresent()) {
            throw new EntityAlreadyExistException("Name already exist");
        }
        if (pizzaInfo.getName() == null || pizzaInfo.getName().isBlank()) {
            throw new IllegalArgumentException("You did not enter name");
        }
        if (pizzaInfo.getDescription() == null || pizzaInfo.getDescription().isBlank()) {
            throw new IllegalArgumentException("You did not enter description");
        }
        if (pizzaInfo.getSize() <= 0) {
            throw new IllegalArgumentException("Wrong pizza size");
        }
    }
}
