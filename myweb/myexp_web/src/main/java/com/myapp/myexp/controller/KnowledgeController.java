package com.myapp.myexp.controller;

import com.myapp.myexp.model.Knowledge;
import com.myapp.myexp.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KnowledgeController {
    @Autowired
    KnowledgeService service;
    @RequestMapping(value="/greeting", method= RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World")String name, Model model) {
        model.addAttribute("name", name);
        Knowledge[] knowledge = service.findAllKnowledge();
        model.addAttribute("knowledge",knowledge);
        return "index";
    }
}
