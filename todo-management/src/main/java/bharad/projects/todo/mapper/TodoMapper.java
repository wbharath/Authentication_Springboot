package bharad.projects.todo.mapper;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.entity.Todo;

public class TodoMapper {

//    JPA Entity to DTO
    public static TodoDto mapToTodoDto(Todo todo){
        return new TodoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }
//    DTO to JPA Entity
    public static Todo mapToTodo(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        return todo;
    }
}
