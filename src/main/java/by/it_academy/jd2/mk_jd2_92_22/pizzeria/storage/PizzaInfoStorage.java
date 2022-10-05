package by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.api.IPizzaInfoStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PizzaInfoStorage implements IPizzaInfoStorage {
    public List<PizzaInfo> data = new ArrayList<>();

    @Override
    public List<PizzaInfo> findAll() {
        return this.data;
    }

    @Override
    public Optional<PizzaInfo> add(PizzaInfo pizzaInfo) {
        this.data.add(pizzaInfo);
        return findByName(pizzaInfo.getName());
    }

    @Override
    public Optional<PizzaInfo> findByName(String name) {
        return this.data.stream()
                .filter((a -> a.getName() == name))
                .findFirst();
    }

    @Override
    public void delete(PizzaInfo pizzaInfo) {
        this.data.remove(pizzaInfo);
    }
}
