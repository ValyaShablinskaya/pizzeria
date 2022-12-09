package by.it_academy.jd2.mk_jd2_92_22.services;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.StageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.StageService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.exception.EntityNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StageServiceTest {
    @Mock
    private StageDao stageDao;
    @InjectMocks
    private StageService stageService;

    @Test
    void addStageShouldReturnCorrectResult() {
        Stage stage = Stage.builder()
                .id(1L)
                .description("testy")
                .build();

        when(stageDao.save(stage)).thenReturn(Optional.of(stage));
        stageService.add(stage);
        verify(stageDao).save(stage);
    }

    @Test
    void findStageByIdShouldReturnCorrectResult() {
        Stage stage = Stage.builder()
                .id(1L)
                .description("testy")
                .build();

        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        stageService.findById(stage.getId());
        verify(stageDao).findById(stage.getId());
    }

    @Test
    void findStageByIdShouldReturnExceptionWhenGetIncorrectParameters() {
        Stage stage = Stage.builder()
                .id(1L)
                .description("testy")
                .build();

        when(stageDao.findById(stage.getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> stageService.findById(stage.getId()));
        verifyNoMoreInteractions(stageDao);
    }

    @Test
    void findAllStageShouldReturnCorrectResult() {
        List<Stage> stages = new ArrayList<>();

        stages.add(Stage.builder()
                .id(1L)
                .description("testy")
                .build());

        stages.add(Stage.builder()
                .id(2L)
                .description("big")
                .build());


        when(stageDao.findAll()).thenReturn(stages);
        stageService.findAll();
        verify(stageDao).findAll();
    }

    @Test
    void updateStageShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Stage stage = Stage.builder()
                .id(1L)
                .description("testy")
                .updateDate(updateData)
                .build();

        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        when(stageDao.update(stage)).thenReturn(Optional.of(stage));
        stageService.update(stage, stage.getId(), updateData);
        verify(stageDao).update(stage);
    }

    @Test
    void removeStageByIdShouldReturnCorrectResult() {
        LocalDateTime updateData =
                LocalDateTime.of(2022, Month.SEPTEMBER, 22, 18, 32, 48, 24);

        Stage stage = Stage.builder()
                .id(1L)
                .description("testy")
                .updateDate(updateData)
                .build();

        when(stageDao.findById(stage.getId())).thenReturn(Optional.of(stage));
        stageService.deleteById(stage.getId(), updateData);
        verify(stageDao).deleteById(stage.getId());
    }
}