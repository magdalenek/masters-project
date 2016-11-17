package com.charitable.charity.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticController {

    @RequestMapping("/add")
    public String addCharity() {
        return "add-charity.html";
    }

    @RequestMapping("/terms-and-conditions")
    public String displayTerms() {
        return "terms.html";
    }

    @RequestMapping("/editNotes")
    public String displayNotes() {
        return "update-notes.html";
    }


}
