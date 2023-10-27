package exercise.mapper;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.model.Task;
import org.mapstruct.*;


@Mapper(
        uses = {MagicMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class TaskMapper {
    // BEGIN
    @Mapping(target = "assignee",source = "assigneeId")
    public abstract Task map(TaskCreateDTO taskData);
    @Mapping(target = "assigneeId",source = "assignee")
    public abstract TaskDTO map(Task task);
    @Mapping(target = "assignee",source = "assigneeId")
    public abstract void update(TaskUpdateDTO taskData, @MappingTarget Task task);
    // END
}
