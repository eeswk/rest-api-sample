package ee.swan.web;

import ee.swan.web.dto.Todo;
import ee.swan.web.dto.TodoResource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/basic")
public class BasicController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/todo")
    public Todo basic() {
        return new Todo(counter.incrementAndGet(), "분석하기");
    }

    @PostMapping("/todo")
    public Todo postBasic(@RequestParam("title") String title) {
        return new Todo(counter.incrementAndGet(), title);
    }

    @PostMapping("/todo2")
    public Todo postBasicRequestBody(@RequestBody Todo todo) {
        return new Todo(counter.incrementAndGet(), todo.getTitle());
    }

    @PostMapping("/todo3")
    public ResponseEntity<Todo> postBasicReponseEntity(@RequestParam("title") String title) {
        return new ResponseEntity(new Todo(counter.incrementAndGet(), title), HttpStatus.CREATED);
    }

    @GetMapping("/todos/{todoId}")
    public Todo getPath(@PathVariable("todoId") int todoId) {
        Todo todo1 = new Todo(1L, "분석하기");
        Todo todo2 = new Todo(2L, "설계하기");
        Todo todo3 = new Todo(3L, "테스트하기");

        Map<Integer, Todo> todoMap = new HashMap<>();
        todoMap.put(1, todo1);
        todoMap.put(2, todo2);
        todoMap.put(3, todo3);

        return todoMap.get(todoId);
    }

    @GetMapping("/todoh")
    public ResponseEntity<TodoResource> geth(@RequestParam("title") String title) {
        TodoResource todoResource = new TodoResource(title);
        todoResource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BasicController.class).geth(title)).withSelfRel());
        return new ResponseEntity(todoResource, HttpStatus.OK);
    }

    @GetMapping("/todoh2")
    public EntityModel<Todo> todoh2(@RequestParam("title") String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        EntityModel<Todo> todoEntityModel = EntityModel.of(todo);
        todoEntityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BasicController.class).todoh2(title)).withSelfRel());
        return todoEntityModel;
    }

    @GetMapping("/todoh3")
    public ResponseEntity<TodoResource> todoh3(@RequestParam("title") String title) {
        TodoResource todoResource = new TodoResource(title);
        todoResource.add(Link.of("http://localhost:8080/basic/todoh3?title="+title));
        return new ResponseEntity(todoResource, HttpStatus.OK);
    }




}
