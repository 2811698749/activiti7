package com.activiti.controller;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ActivityController {
    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;
    @GetMapping("/deployProcess")
    public String deploy()  {
        Deployment deployment = repositoryService.createDeployment()
                .name("请假流程")
                .addClasspathResource("bpmn/leave.bpmn")
                .addClasspathResource("bpmn/leave.svg").deploy();
        return deployment.getName();
    }
    @GetMapping("/startProcess")
    public String startProcess(){
        //1.获取流程引擎，获取runtimeService
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //2.根据流程定义启动流程
//        Map<String,Object> map = new HashMap<>();
//        map.put("commit","zhangsan");
        ProcessInstance instance =processEngine.getRuntimeService().startProcessInstanceByKey("apply_leave");
        System.out.printf(instance.getId());
        //3.输出内容
        return instance.getId();
    }
    @GetMapping("/getTasks")
    public String getTasks(String assign){
      Task t =  taskService.createTaskQuery().processDefinitionKey("apply_leave").taskAssignee("zhangsan").list().get(0);
          System.out.println("流程定义例的Id:"+t.getProcessDefinitionId());
          System.out.println("流程实例的Id:"+t.getProcessInstanceId());
          System.out.println("任务名称:"+t.getName());
          System.out.println("任务负ID:"+t.getId());
          System.out.println("任务负责人:"+t.getAssignee());
     return "taskId = "+t.getId();
    }
    @GetMapping("completeTask")
    public void completeTask(String assignee){
       Task task =  taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        taskService.complete(task.getId());
    }
    @GetMapping("completeTaskById")
    public void completeTaskById(String taskId){
        taskService.complete(taskId);
    }
}
