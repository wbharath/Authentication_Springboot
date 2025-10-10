package bharad.projects.todo.service.impl;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.entity.Todo;
import bharad.projects.todo.mapper.TodoMapper;
import bharad.projects.todo.repository.TodoRepository;
import bharad.projects.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = TodoMapper.mapToTodo(todoDto);

        // Save
        Todo savedTodo = todoRepository.save(todo);

        // Entity to DTO
        return TodoMapper.mapToTodoDto(savedTodo);
    }
}
