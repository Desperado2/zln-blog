package com.buss.service.impl;

import com.base.anno.Autowired;
import com.base.anno.Service;
import com.buss.entity.BlogBaseEntity;
import com.buss.service.BlogListService;
import com.buss.service.SystemService;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service("blogListService")
public class BlogListServiceImpl implements BlogListService {
    @Autowired
    private SystemService systemService;
    @Override
    public List<Object> getBlogList() throws Exception {
        List<Object> list = new ArrayList<>();
        BlogBaseEntity blogBaseEntity = null;
        String sql = "select * from blog_base_info limit 0,5";
        ResultSet rs = systemService.execSql(sql);
        while (rs.next()){
            blogBaseEntity =new BlogBaseEntity();
            blogBaseEntity.setId(rs.getString("id"));
            blogBaseEntity.setTitle(rs.getString("title"));
            blogBaseEntity.setKeyWord(rs.getString("key_word"));
            blogBaseEntity.setCreateDate(rs.getDate("create_date"));
            blogBaseEntity.setType(rs.getString("type"));
            blogBaseEntity.setCommentNumber(rs.getString("comment_number"));
            blogBaseEntity.setLikeNumber(rs.getString("like_number"));
            blogBaseEntity.setLookNumber(rs.getString("look_number"));
            blogBaseEntity.setAuthorId(rs.getString("author_id"));
            blogBaseEntity.setStatus(rs.getString("status"));
            list.add(blogBaseEntity);
        }
        return list;
    }
}
