package com.buss.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListToJsonArray {

    public static String listToArray(List<Object> list){
        StringBuilder sb = new StringBuilder();
        if(list == null){
            throw new NullPointerException();
        }
        if(list.size() == 0){
            return  null;
        }
        sb.append("[");
        try {
            for (Object ccc : list){
                sb.append("{");
                Field[] fields =ccc.getClass().getDeclaredFields();
                for(Field field : fields){
                    field.setAccessible(true);
                    sb.append("\"");
                    sb.append(field.getName());
                    sb.append("\"");
                    sb.append(":");
                    sb.append("\"");
                    sb.append(field.get(ccc));
                    sb.append("\"");
                    sb.append(",");
                }
                sb.delete(sb.length()-1,sb.length());
                sb.append("}");
                sb.append(",");
            }
            sb.delete(sb.length()-1,sb.length());
            sb.append("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
