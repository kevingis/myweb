package com.myapp.controller;

import com.google.gson.Gson;
import com.myapp.com.myapp.model.Knowledge;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/myapp")
public class KnowledgeController {

    @RequestMapping("/info")
    public String showInfo(){
        return "Hello World!";
    }
    @RequestMapping("/")
    public String greeting(){
        return "Hello World!";
    }
    @RequestMapping("/all_knowledge")
    public String getAllKnowledge(){
        List<Knowledge> k = new ArrayList<Knowledge>();
        Knowledge js = new Knowledge();
        Knowledge python = new Knowledge();
        js.setName("JavaScript");
        js.setType("IT");

        python.setName("Python");
        python.setType("IT");

        k.add(js);
        k.add(python);

        Gson gson = new Gson();
        return gson.toJson(k);
    }

}
