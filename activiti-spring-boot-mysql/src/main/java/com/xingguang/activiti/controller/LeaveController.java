package com.xingguang.activiti.controller;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xingguang.activiti.model.TaskRepresentation;
import com.xingguang.activiti.service.ILeaveService;

@RestController
public class LeaveController {
	
	@Autowired
	private ILeaveService leaveService;
	
	@RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance() {
		leaveService.startProcess();
    }
	
	@RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = leaveService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

}
