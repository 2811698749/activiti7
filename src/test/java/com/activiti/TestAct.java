package com.activiti;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestAct {
    @Test
    public void test(){
        System.out.println(ProcessEngines.getDefaultProcessEngine());
    }
}
