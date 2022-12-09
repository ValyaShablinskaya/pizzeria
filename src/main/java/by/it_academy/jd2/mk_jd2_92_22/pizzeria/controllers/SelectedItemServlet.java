package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.api.ISelectedItemService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.SelectedItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/selectedItem")
public class SelectedItemServlet {
    private final ISelectedItemService service;

    public SelectedItemServlet(ISelectedItemService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    protected ResponseEntity<SelectedItemDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    protected ResponseEntity<List<SelectedItemDTO>> getList() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    protected ResponseEntity<SelectedItemDTO> doPost(@RequestBody SelectedItemDTO data) {
        SelectedItemDTO created = this.service.add(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<SelectedItemDTO> doPut(@PathVariable long id,
                                            @PathVariable("update_date") long updateDateRaw,
                                            @RequestBody SelectedItemDTO data) {
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
