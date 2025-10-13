package bharad.projects.todo.service.impl;

import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.entity.Todo;
import bharad.projects.todo.exception.ResourceNotFoundException;
import bharad.projects.todo.mapper.TodoMapper;
import bharad.projects.todo.repository.TodoRepository;
import bharad.projects.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // DTO to Entity
        Todo todo = TodoMapper.mapToTodo(todoDto, modelMapper);

        // Save
        Todo savedTodo = todoRepository.save(todo);

        // Entity to DTO
        return TodoMapper.mapToTodoDto(savedTodo, modelMapper);
    }

    @Override
    public TodoDto getTodoById(long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: " + id));
        return TodoMapper.mapToTodoDto(todo, modelMapper);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo)->TodoMapper.mapToTodoDto(todo, modelMapper))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto updatedTodoDto, long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Todo not found with id: " + id));


        todo.setTitle(updatedTodoDto.getTitle());
        todo.setDescription(updatedTodoDto.getDescription());
        todo.setCompleted(updatedTodoDto.isCompleted());

        Todo todoObj = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(todoObj, modelMapper);
    }

    @Override
    public void deleteTodo(long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Todo not found with id: " + id));

        todoRepository.deleteById(id);
    }


}
