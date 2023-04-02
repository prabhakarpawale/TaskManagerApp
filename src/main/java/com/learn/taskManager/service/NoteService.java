package com.learn.taskManager.service;

import com.learn.taskManager.Entities.NoteEntity;
import com.learn.taskManager.Entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NoteService {

    private TaskService taskService;
    private HashMap<Integer , TaskNotesHolder> taskNotesHolder = new HashMap<>();

    public NoteService(TaskService taskService){
        this.taskService=taskService;
    }
    class TaskNotesHolder{
        protected int noteid=1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();

    }

    public List<NoteEntity> getNotesForTask(int taskId){
        TaskEntity task = taskService.getTaskById(taskId);
        if(task==null){
            return null;
        }
        if(taskNotesHolder.get(taskId)==null){
            taskNotesHolder.put(taskId, new TaskNotesHolder());
        }
        return taskNotesHolder.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskid , String title , String body){
        TaskEntity task = taskService.getTaskById(taskid);
        if(task==null){
            return null;
        }
        if(taskNotesHolder.get(taskid)==null){
            taskNotesHolder.put(taskid ,new TaskNotesHolder());
        }
        TaskNotesHolder tasknoteHolders = taskNotesHolder.get(taskid);
        NoteEntity note = new NoteEntity();
        note.setId(tasknoteHolders.noteid);
        note.setTitle(title);
        note.setBody(body);
        tasknoteHolders.notes.add(note);
        tasknoteHolders.noteid++;
        return note;
    }

    public String deleteNoteForTask(int taskId , int noteid){
        TaskEntity task = taskService.getTaskById(taskId);
        if(taskNotesHolder.get(taskId)==null){
            return "Note does not exists";
        }
        TaskNotesHolder taskNotesHolder1 = taskNotesHolder.get(taskId);
        for(NoteEntity note: taskNotesHolder1.notes){
            if(note.getId()==noteid){
                taskNotesHolder1.notes.remove(note);
                return "Note deleted successfully";
            }
        }
        return "Invalid Note Id";
    }

}
