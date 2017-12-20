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
import com.xingguang.activiti.service.IMyService;

@RestController
public class MyController {
	
	@Autowired
	private IMyService myService;
	
	/**
	 * 启动请假流程
	 */
	@RequestMapping(value="/apply", method= RequestMethod.POST)
    public String startProcessInstance(@RequestParam String apply , @RequestParam String audit , @RequestParam int days) {
        return myService.leaveApply(apply , audit , days);
    }
	
	@RequestMapping(value="/audit", method= RequestMethod.POST)
    public void auditProcessInstance(@RequestParam String taskId , @RequestParam String approve) {
        myService.leaveAudit(taskId , approve);
    }
	
	@RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<TaskRepresentation>();
        for (Task task : tasks) {
            dtos.add(new TaskRepresentation(task.getId(), task.getName()));
        }
        return dtos;
    }

}
