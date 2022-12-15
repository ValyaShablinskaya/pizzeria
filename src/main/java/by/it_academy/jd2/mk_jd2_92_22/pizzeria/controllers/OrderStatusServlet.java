package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IOrderStatusService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.OrderStatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/orderStatus")
public class OrderStatusServlet {
    private final IOrderStatusService service;

    public OrderStatusServlet(IOrderStatusService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<OrderStatusDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping
    protected ResponseEntity<List<OrderStatusDTO>> getList() {
        return ResponseEntity.ok(service.get());
    }

    @PostMapping
    protected ResponseEntity<OrderStatusDTO> doPost(@RequestBody OrderStatusDTO data) {
        OrderStatusDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<OrderStatusDTO> doPut(@PathVariable long id,
                                            @PathVariable("update_date") long updateDateRaw,
                                            @RequestBody OrderStatusDTO data) {
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
        service.delete(id, updateDate);
    }
}
