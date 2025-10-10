package bharad.projects.todo.service;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.repository.TodoRepository;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
}
