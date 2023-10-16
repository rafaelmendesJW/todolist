package br.com.rafaelmendes.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public TaskModel created(@RequestBody TaskModel taskModel) {
        System.out.println("Chegou no controller");
        //var idUser = request.getAttribute("idUser");
       // taskModel.setIdUser((UUID) idUser);
        var task = this.taskRepository.save(taskModel);

        return task;
    }

}
