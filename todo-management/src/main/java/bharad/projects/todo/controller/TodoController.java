package bharad.projects.todo.controller;


import bharad.projects.todo.dto.TodoDto;
import bharad.projects.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {


    private TodoService todoService;
//    Build add todo REST API

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedToDo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }


}
