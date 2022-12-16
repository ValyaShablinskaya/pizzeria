package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.api.IPizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers.IPizzaInfoMapper;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaInfoService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.PizzaInfoDTO;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaInfoServiceTest {
    @Mock
    private IPizzaInfoDao pizzaInfoDao;
    @Mock
    private IPizzaInfoMapper pizzaInfoMapper;
    @InjectMocks
    private PizzaInfoService pizzaInfoService;
    private PizzaInfo pizzaInfo;
    private PizzaInfoDTO pizzaInfoDTO;
    private List<PizzaInfo> pizzaInfos = new ArrayList<>();
    private List<PizzaInfoDTO> pizzaInfosDTO = new ArrayList<>();

    @BeforeEach
    void beforeTest() {
        generateTestValues();
    }

    @Test
    void addPizzaInfoShouldReturnCorrectResult() {
        when(pizzaInfoMapper.convertToPizzaInfo(pizzaInfoDTO)).thenReturn(pizzaInfo);
        when(pizzaInfoDao.save(pizzaInfo)).thenReturn(pizzaInfo);
        when(pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfo)).thenReturn(pizzaInfoDTO);

        assertEquals(pizzaInfoDTO, pizzaInfoService.create(pizzaInfoDTO));

        verify(pizzaInfoMapper).convertToPizzaInfo(pizzaInfoDTO);
        verify(pizzaInfoDao).save(pizzaInfo);
        verify(pizzaInfoMapper).convertToPizzaInfoDTO(pizzaInfo);
    }

    @Test
    void findPizzaInfoByIdShouldReturnCorrectResult() {
        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
        when(pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfo)).thenReturn(pizzaInfoDTO);

        assertEquals(pizzaInfoDTO, pizzaInfoService.read(pizzaInfo.getId()));

        verify(pizzaInfoDao).findById(pizzaInfo.getId());
        verify(pizzaInfoMapper).convertToPizzaInfoDTO(pizzaInfo);
    }

    @Test
    void findPizzaInfoByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> pizzaInfoService.read(pizzaInfo.getId()));
        verifyNoMoreInteractions(pizzaInfoDao);
    }

    @Test
    void findAllPizzaInfosShouldReturnCorrectResult() {
        when(pizzaInfoDao.findAll()).thenReturn(pizzaInfos);
        when(pizzaInfoMapper.convertToPizzaInfoList(pizzaInfos)).thenReturn(pizzaInfosDTO);

        assertEquals(pizzaInfosDTO, pizzaInfoService.get());

        verify(pizzaInfoDao).findAll();
        verify(pizzaInfoMapper).convertToPizzaInfoList(pizzaInfos);
    }

    @Test
    void updatePizzaInfoShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
        when(pizzaInfoDao.save(pizzaInfo)).thenReturn(pizzaInfo);
        when(pizzaInfoMapper.convertToPizzaInfoDTO(pizzaInfo)).thenReturn(pizzaInfoDTO);

        assertEquals(pizzaInfoDTO, pizzaInfoService.update(pizzaInfoDTO, pizzaInfo.getId(), updateData));

        verify(pizzaInfoDao).save(pizzaInfo);
        verify(pizzaInfoMapper).convertToPizzaInfoDTO(pizzaInfo);
    }

    @Test
    void removePizzaInfoByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        when(pizzaInfoDao.findById(pizzaInfo.getId())).thenReturn(Optional.of(pizzaInfo));
        pizzaInfoService.delete(pizzaInfo.getId(), updateData);
        verify(pizzaInfoDao).deleteById(pizzaInfo.getId());
    }

    private void generateTestValues() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);
        LocalDateTime createData =
                LocalDateTime.of(2022, Month.DECEMBER, 10, 10, 10, 10, 10);

        pizzaInfo = PizzaInfo.builder()
                .id(1L)
                .name("Margarita")
                .description("testy")
                .size(16L)
                .creationDate(createData)
                .updateDate(updateData)
                .build();

        pizzaInfoDTO = PizzaInfoDTO.builder()
                .name("Margarita")
                .description("testy")
                .size(16L)
                .build();

        pizzaInfos.add(pizzaInfo);
        pizzaInfosDTO.add(pizzaInfoDTO);

        pizzaInfos.add(PizzaInfo.builder()
                .id(2L)
                .name("4season")
                .description("very testy")
                .size(32L)
                .creationDate(createData)
                .updateDate(updateData)
                .build());

        pizzaInfosDTO.add(PizzaInfoDTO.builder()
                .name("4season")
                .description("very testy")
                .size(32L)
                .build());
    }
}