package bharad.projects.todo.service;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.repository.TodoRepository;

import java.util.List;

public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodoById(long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto,  long id);

    void deleteTodo(long id);
}
