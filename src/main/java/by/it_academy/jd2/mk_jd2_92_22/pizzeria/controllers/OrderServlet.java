package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderServlet {
   private final IOrderService service;

    public OrderServlet(IOrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<OrderDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    protected ResponseEntity<List<OrderDTO>> getList() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    protected ResponseEntity<OrderDTO> doPost(@RequestBody OrderDTO data) {
        OrderDTO created = this.service.add(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<OrderDTO> doPut(@PathVariable long id,
                                            @PathVariable("update_date") long updateDateRaw,
                                            @RequestBody OrderDTO data) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateDateRaw),
                ZoneId.of("UTC")
        );
        return ResponseEntity.ok(this.service.update(data, id, updateDate));
    }

    @DeleteMapping("/{id}/update_date/{update_date}")
    protected void doDelete(@PathVariable long id,
                            @PathVariable("update_date") long updateDateRaw) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateDateRaw),
                ZoneId.of("UTC")
        );
        service.deleteById(id, updateDate);
    }
}
