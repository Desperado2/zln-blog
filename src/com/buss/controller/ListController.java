package com.buss.controller;

import com.base.anno.Autowired;
import com.base.anno.Controller;
import com.base.anno.RequestMapping;
import com.buss.entity.BlogBaseEntity;
import com.buss.service.BlogListService;
import com.buss.util.ListToJsonArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller("listController")
public class ListController {

    @Autowired
    private BlogListService blogListService;
    @RequestMapping("/getBlogList")
    public void getBlogList(HttpServletRequest request, HttpServletResponse response){
        List<Object> list = new ArrayList<>();
        try {
             list = blogListService.getBlogList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(ListToJsonArray.listToArray(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
