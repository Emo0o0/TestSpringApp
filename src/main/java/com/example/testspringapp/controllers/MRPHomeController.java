package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.StageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPHomeController {

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPHomeController(StageManager stageManager) {
        this.stageManager = stageManager;
    }
}
