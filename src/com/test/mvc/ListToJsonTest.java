package com.test.mvc;

import com.buss.entity.BlogBaseEntity;
import com.buss.entity.BlogEntity;
import com.buss.util.ListToJsonArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListToJsonTest {



    @Test
    public void test(){

        List<Object> list = new ArrayList<>();
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId("454566");
        blogEntity.setContent("4254sfsdag");
        list.add(blogEntity);
        BlogEntity blogEntity1 = new BlogEntity();
        blogEntity1.setId("454566");
        blogEntity1.setContent("4254sfsdag");
        list.add(blogEntity1);
        String ss = ListToJsonArray.listToArray(list);
        System.out.println(ss);
    }
}
