package com.xingguang.activiti.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingguang.activiti.service.IMyService;

@Service
public class MyServiceImpl implements IMyService {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	@Override
	public String leaveApply(String applyUser , String auditUser , int days) {
		Map<String,Object> map = new HashMap<String,Object>();  
        map.put("apply" , applyUser); // 申请人
        map.put("audit" , auditUser); // 审批人
        map.put("days" , days); // 请假天数
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave",map);
        String processId = pi.getId();
        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        taskService.complete(task.getId(), map);//完成请假申请  
        return task.getId();
	}
	
	@Override
	public void leaveAudit(String taskId , String approveUser) {
		Map<String,Object> map = new HashMap<String,Object>();  
		map.put("approve" , approveUser); 
		taskService.complete(taskId, map);//完成请假审批
	}

	@Override
	public List<Task> getTasks(String assignee) {
		List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).list();
		return list;
	}

	

}
