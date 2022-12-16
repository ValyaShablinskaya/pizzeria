package by.it_academy.jd2.mk_jd2_92_22.pizzeria.mappers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.dto.StageDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IStageMapper {

    StageDTO convertToStageDTO(Stage stage);

    Stage convertToStage(StageDTO stageDTO);
    List<StageDTO> convertToStageList(List<Stage> stages);
    List<Stage> convertToStageListFromDto(List<StageDTO> stages);
}
