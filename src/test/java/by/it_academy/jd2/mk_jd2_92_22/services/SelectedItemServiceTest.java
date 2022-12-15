//package by.it_academy.jd2.mk_jd2_92_22.services;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.SelectedItemDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.SelectedItemService;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SelectedItemServiceTest {
//    @Mock
//    private SelectedItemDao selectedItemDao;
//    @InjectMocks
//    private SelectedItemService selectedItemService;
//
//    @Test
//    void addSelectedItemShouldReturnCorrectResult() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        MenuRow menuRow = MenuRow.builder()
//                .id(1L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo)
//                .build();
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .build();
//
//        when(selectedItemDao.save(selectedItem)).thenReturn(Optional.of(selectedItem));
//        selectedItemService.add(selectedItem);
//        verify(selectedItemDao).save(selectedItem);
//    }
//
//    @Test
//    void findSelectedItemByIdShouldReturnCorrectResult() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        MenuRow menuRow = MenuRow.builder()
//                .id(1L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo)
//                .build();
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .build();
//
//        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
//        selectedItemService.findById(selectedItem.getId());
//        verify(selectedItemDao).findById(selectedItem.getId());
//    }
//
//    @Test
//    void findSelectedItemByIdShouldReturnExceptionWhenGetIncorrectParameters() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        MenuRow menuRow = MenuRow.builder()
//                .id(1L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo)
//                .build();
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .build();
//
//        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, () -> selectedItemService.findById(selectedItem.getId()));
//        verifyNoMoreInteractions(selectedItemDao);
//    }
//
//    @Test
//    void findAllSelectedItemsShouldReturnCorrectResult() {
//        List<SelectedItem> selectedItems = new ArrayList<>();
//
//        selectedItems.add(SelectedItem.builder()
//                .id(1L)
//                .count(6L)
//                .build());
//
//        selectedItems.add(SelectedItem.builder()
//                .id(1L)
//                .count(6L)
//                .build());
//
//        when(selectedItemDao.findAll()).thenReturn(selectedItems);
//        selectedItemService.findAll();
//        verify(selectedItemDao).findAll();
//    }
//
//    @Test
//    void updateSelectedItemShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        MenuRow menuRow = MenuRow.builder()
//                .id(1L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo)
//                .build();
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .updateDate(updateData)
//                .build();
//
//        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
//        when(selectedItemDao.update(selectedItem)).thenReturn(Optional.of(selectedItem));
//        selectedItemService.update(selectedItem, selectedItem.getId(), updateData);
//        verify(selectedItemDao).update(selectedItem);
//    }
//
//    @Test
//    void removeSelectedItemByIdShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        MenuRow menuRow = MenuRow.builder()
//                .id(1L)
//                .price(21.2)
//                .pizzaInfo(pizzaInfo)
//                .build();
//
//        Order order = Order.builder()
//                .id(1L)
//                .build();
//
//        SelectedItem selectedItem = SelectedItem.builder()
//                .id(1L)
//                .menuRow(menuRow)
//                .order(order)
//                .count(6L)
//                .updateDate(updateData)
//                .build();
//
//        when(selectedItemDao.findById(selectedItem.getId())).thenReturn(Optional.of(selectedItem));
//        selectedItemService.deleteById(selectedItem.getId(), updateData);
//        verify(selectedItemDao).deleteById(selectedItem.getId());
//    }
//
//    @Test
//    void findAllSelectedItemByOrderIdShouldReturnListOfMenuRows() {
//        List<SelectedItem> selectedItems = new ArrayList<>();
//        Order order1 = Order.builder()
//                .id(1L)
//                .build();
//
//        selectedItems.add(SelectedItem.builder()
//                .id(1L)
//                .count(6L)
//                .order(order1)
//                .build());
//
//        selectedItems.add(SelectedItem.builder()
//                .id(2L)
//                .count(6L)
//                .order(order1)
//                .build());
//
//        when(selectedItemDao.findAllSelectedItemByIdOrder(1L)).thenReturn(selectedItems);
//        selectedItemService.findAllByIdOrder(1L);
//        verify(selectedItemDao).findAllSelectedItemByIdOrder(1L);
//    }
//}
