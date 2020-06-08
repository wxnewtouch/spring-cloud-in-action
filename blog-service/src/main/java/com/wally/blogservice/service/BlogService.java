package com.wally.blogservice.service;

import com.wally.blogservice.client.UserServiceClient;
import com.wally.blogservice.dao.BlogDao;
import com.wally.blogservice.dto.BlogDetailDTO;
import com.wally.blogservice.entity.Blog;
import com.wally.blogservice.entity.User;
import com.wally.blogservice.utils.UserUtils;
import com.wally.common.dto.RespDTO;
import com.wally.common.exception.CommonException;
import com.wally.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    @Resource
    private UserServiceClient userServiceClient;

    public Blog postBlog(Blog blog){
        return blogDao.save(blog);
    }

    public List<Blog> findBlogs(String userName){
        return blogDao.findByUsername(userName);
    }

    public BlogDetailDTO findBlogDetail(Long id) {
        Blog blog = blogDao.findOne(id);
        if (null == blog) {
            throw new CommonException(ErrorCode.BLOG_IS_NOT_EXIST);
        }
        RespDTO<User> respDTO = userServiceClient.getUser(UserUtils.getCurrentToken(), blog.getUsername());
        if (respDTO==null) {
            throw new CommonException(ErrorCode.RPC_ERROR);
        }
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO();
        blogDetailDTO.setBlog(blog);
        blogDetailDTO.setUser(respDTO.data);
        return blogDetailDTO;
    }
}
