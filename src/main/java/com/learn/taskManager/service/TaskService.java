package com.learn.taskManager.service;

import com.learn.taskManager.Entities.TaskEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
    private int taskId=1;
    private final SimpleDateFormat deadlineFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public TaskEntity addtask(String Title, String description ,String deadline) throws ParseException {
        TaskEntity task = new TaskEntity();
        task.setId(taskId);
        task.setTitle(Title);
        task.setDescription(description);
        task.setDeadline(deadlineFormatter.parse(deadline));   //TODO:- Validate date format YYYY-MM-DD
        task.setCompleted(false);
        tasks.add(task);
        taskId++;
        return task;
    }

    public ArrayList<TaskEntity> getTasks(){
        return tasks;
    }

    public TaskEntity getTaskById(int id) {
       //return tasks.stream().findAny().filter(task -> task.getId() == id).orElse(null);      // New style Java
        //old style Java
        for(TaskEntity task : tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }

    public TaskEntity updateTask(int id , String description , String deadline , Boolean completed) throws ParseException {
        TaskEntity task = getTaskById(id);
        if(task==null){
            return null;
        }
        if(description != null){
            task.setDescription(description);
        }
        if(deadline!=null){
            task.setDeadline(deadlineFormatter.parse(deadline));
        }
        if(completed!=null){
            task.setCompleted(completed);
        }
        return task;
    }


}
