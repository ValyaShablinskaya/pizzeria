package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.IPizzaService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaServlet {
    private final IPizzaService service;

    public PizzaServlet(IPizzaService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<PizzaDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping
    protected ResponseEntity<List<PizzaDTO>> getList() {
        return ResponseEntity.ok(service.get());
    }

    @PostMapping
    protected ResponseEntity<PizzaDTO> doPost(@RequestBody PizzaDTO data) {
        PizzaDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<PizzaDTO> doPut(@PathVariable long id,
                                            @PathVariable("update_date") long updateDateRaw,
                                            @RequestBody PizzaDTO data) {
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
