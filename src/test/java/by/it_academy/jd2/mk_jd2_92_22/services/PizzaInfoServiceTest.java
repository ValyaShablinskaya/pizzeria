//package by.it_academy.jd2.mk_jd2_92_22.services;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaInfoService;
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
//class PizzaInfoServiceTest {
//    @Mock
//    private PizzaInfoDao pizzaInfoDao;
//
//    @InjectMocks
//    private PizzaInfoService pizzaInfoService;
//
//    @Test
//    void addPizzaInfoShouldReturnCorrectResult() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        when(pizzaInfoDao.save(pizzaInfo)).thenReturn(Optional.of(pizzaInfo));
//        pizzaInfoService.add(pizzaInfo);
//        verify(pizzaInfoDao).save(pizzaInfo);
//    }
//
//    @Test
//    void findPizzaInfoByIdShouldReturnCorrectResult() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
//        pizzaInfoService.findById(pizzaInfo.getId());
//        verify(pizzaInfoDao).findById(pizzaInfo.getId());
//    }
//
//    @Test
//    void findPizzaInfoByIdShouldReturnExceptionWhenGetIncorrectParameters() {
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build();
//
//        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.empty());
//        assertThrows(EntityNotFoundException.class, () -> pizzaInfoService.findById(pizzaInfo.getId()));
//        verifyNoMoreInteractions(pizzaInfoDao);
//    }
//
//    @Test
//    void findAllPizzaInfosShouldReturnCorrectResult() {
//        List<PizzaInfo> pizzaInfos = new ArrayList<>();
//
//        pizzaInfos.add(PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .build());
//
//        pizzaInfos.add(PizzaInfo.builder()
//                .id(2L)
//                .name("4season")
//                .description("very testy")
//                .size(32L)
//                .build());
//
//        when(pizzaInfoDao.findAll()).thenReturn(pizzaInfos);
//        pizzaInfoService.findAll();
//        verify(pizzaInfoDao).findAll();
//    }
//
//    @Test
//    void updatePizzaInfoShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .updateDate(updateData)
//                .build();
//
//        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
//        when(pizzaInfoDao.update(pizzaInfo)).thenReturn(Optional.of(pizzaInfo));
//        pizzaInfoService.update(pizzaInfo, pizzaInfo.getId(), updateData);
//        verify(pizzaInfoDao).update(pizzaInfo);
//    }
//
//    @Test
//    void removePizzaInfoByIdShouldReturnCorrectResult() {
//        LocalDateTime updateData =
//                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
//
//        PizzaInfo pizzaInfo = PizzaInfo.builder()
//                .id(1L)
//                .name("Margarita")
//                .description("testy")
//                .size(16L)
//                .updateDate(updateData)
//                .build();
//
//        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
//        pizzaInfoService.deleteById(pizzaInfo.getId(), updateData);
//        verify(pizzaInfoDao).deleteById(pizzaInfo.getId());
//    }
//}