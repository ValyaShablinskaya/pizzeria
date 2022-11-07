package by.it_academy.jd2.mk_jd2_92_22.pizzeria.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.DoneOrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.DoneOrder;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.OrderStatus;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IDoneOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class DoneOrderService implements IDoneOrderService {
    private final DoneOrderDao doneOrderDao;

    public DoneOrderService(DoneOrderDao doneOrderDao) {
        this.doneOrderDao = doneOrderDao;
    }

    @Override
    public void add(DoneOrder doneOrder) {
        doneOrder.setCreationDate(LocalDateTime.now());
        doneOrderDao.save(doneOrder);
    }

    @Override
    public DoneOrder findById(Long id) {
        return doneOrderDao.findById(id).orElseThrow(() -> new EntityNotFoundException("DoneOrder is not found"));
    }

    @Override
    public List<DoneOrder> findAll() {
        return doneOrderDao.findAll();
    }

    @Override
    public void update(DoneOrder doneOrder, Long id, LocalDateTime updateDate) {
        DoneOrder updateOrderDoneOrder = doneOrderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("DoneOrder is not found"));
        if (!updateOrderDoneOrder.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("DoneOrder has been already edited");
        }
        updateOrderDoneOrder.setUpdateDate(LocalDateTime.now());
        updateOrderDoneOrder.setTicket(doneOrder.getTicket());
       doneOrderDao.update(updateOrderDoneOrder);
    }

    @Override
    public void deleteById(Long id, LocalDateTime updateDate) {
        DoneOrder deleteDoneOrder = doneOrderDao.findById(id).orElseThrow(
                () -> new EntityNotFoundException("DoneOrder is not found"));
        if (!deleteDoneOrder.getUpdateDate().isEqual(updateDate)) {
            throw new IllegalArgumentException("DoneOrder has been already edited");
        }
        doneOrderDao.deleteById(id);
    }
}
