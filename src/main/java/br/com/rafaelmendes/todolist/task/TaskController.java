package br.com.rafaelmendes.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity created(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        var currentDate = LocalDateTime.now();
        // 10//11/2023 - current
        // 10/10/2023 - statAt
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt()) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio / data de termino deve ser maior que a data atual");
        }

        var task = this.taskRepository.save(taskModel);

        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

}
