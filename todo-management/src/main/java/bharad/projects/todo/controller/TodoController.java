package bharad.projects.todo.controller;


import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
@CrossOrigin(value = "*")
public class TodoController {


    private TodoService todoService;
//    Build add todo REST API

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
//    providing method level security instead of doing it for individual routes in SpringSecurityConfig
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedToDo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable long id) {
        TodoDto savedToDo = todoService.getTodoById(id);
        return new ResponseEntity<>(savedToDo, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")

    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> savedToDo = todoService.getAllTodos();
        return new ResponseEntity<>(savedToDo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable long id) {
        TodoDto savedToDo = todoService.updateTodo(todoDto, id);
        return new ResponseEntity<>(savedToDo, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Deleted sucessfully", HttpStatus.OK);
    }
}
