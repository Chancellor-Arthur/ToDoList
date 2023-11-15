package ru.dubna.todolist.entities.tasks;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dubna.todolist.entities.tasks.dtos.TaskInputDto;
import ru.dubna.todolist.entities.tasks.dtos.TaskOutputDto;
import ru.dubna.todolist.entities.user.User;
import ru.dubna.todolist.exceptions.dtos.BadRequestExceptionPayload;
import ru.dubna.todolist.exceptions.dtos.DefaultExceptionPayload;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Tag(name = "Задачи", description = "Операции для взаимодействия с задачами пользователя")
@SecurityRequirement(name = "jsessionid")
@ApiResponse(responseCode = "401", content = {
        @Content(schema = @Schema(implementation = DefaultExceptionPayload.class))})
@ApiResponse(responseCode = "403", content = {
        @Content(schema = @Schema(implementation = DefaultExceptionPayload.class))})
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создание задачи", description = "Позволяет создать задачу")
    @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = TaskOutputDto.class))})
    public TaskOutputDto create(@Valid @RequestBody TaskInputDto taskInputDto, Principal principal) {
        Task task = taskService.create(taskInputDto, (User) principal);
        return new TaskOutputDto(task.getId(), task.getName(), task.getDescription(), task.getStatus());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление задачи", description = "Позволяет обновить задачу")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TaskOutputDto.class))})
    public TaskOutputDto update(@PathVariable int id, @Valid @RequestBody TaskInputDto taskInputDto, Principal principal) {
        Task task = taskService.update(id, taskInputDto, (User) principal);
        return new TaskOutputDto(task.getId(), task.getName(), task.getDescription(), task.getStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Удаление задачи", description = "Позволяет удалить задачу")
    @ApiResponse(responseCode = "204")
    public void delete(@PathVariable int id) {
        taskService.delete(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Получение списка задач пользователя", description = "Позволяет получить список задач пользователя")
    @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = TaskOutputDto.class)))})
    @ApiResponse(responseCode = "400", content = {
            @Content(schema = @Schema(implementation = BadRequestExceptionPayload.class))})
    public List<TaskOutputDto> getAll(@PathVariable int userId) {
        return taskService.getAll(userId).stream()
                .map(task -> new TaskOutputDto(task.getId(), task.getName(), task.getDescription(), task.getStatus()))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение задачи по уникальному идентификатору", description = "Позволяет получить запрашиваемую задачу")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TaskOutputDto.class))})
    @ApiResponse(responseCode = "400", content = {
            @Content(schema = @Schema(implementation = BadRequestExceptionPayload.class))})
    public TaskOutputDto get(@PathVariable int id) {
        Task task = taskService.get(id);
        return new TaskOutputDto(task.getId(), task.getName(), task.getDescription(), task.getStatus());
    }
}
