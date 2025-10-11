package bharad.projects.todo.mapper;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.entity.Todo;
import org.modelmapper.ModelMapper;

public class TodoMapper {

    // JPA Entity to DTO
    public static TodoDto mapToTodoDto(Todo todo, ModelMapper modelMapper) {
        return modelMapper.map(todo, TodoDto.class);
    }

    // DTO to JPA Entity
    public static Todo mapToTodo(TodoDto todoDto, ModelMapper modelMapper) {
        return modelMapper.map(todoDto, Todo.class);
    }
}