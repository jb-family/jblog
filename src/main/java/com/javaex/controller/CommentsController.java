package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.javaex.service.CommentsService;

@Controller
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;
}
