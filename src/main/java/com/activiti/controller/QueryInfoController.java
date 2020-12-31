package com.activiti.controller;

import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryInfoController {
    @Autowired
    RepositoryService repositoryService;

    /**
     * 查询每个流流程最新的版本列表
     * **/
    @GetMapping("/getDefineInfo")
    public List<Map> getDefineInfo(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
//                .processDefinitionKey("leave")根据key来查询
                .latestVersion()
                .list();
        List<Map> mapList = new ArrayList<>();
        list.stream().forEach(item->{
            Map<String,Object> map = new HashMap<>();
            map.put("id",item.getId());
            map.put("deployId",item.getDeploymentId());
            map.put("name",item.getName());
            map.put("resourceName",item.getResourceName());
            map.put("version",item.getVersion());
            mapList.add(map);
        });
        return mapList;
    }
    /**
     * 流程删除
     * **/
    @ApiOperation(value="删除部署路程", notes="删除部署路程")
    @DeleteMapping()
    public void deleteDeployment(String deploymentId){
        repositoryService.deleteDeployment(deploymentId,true);
    }
}
