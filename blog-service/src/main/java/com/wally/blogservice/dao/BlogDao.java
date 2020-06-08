package com.wally.blogservice.dao;

import com.wally.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog,Long> {
    List<Blog> findByUsername(String userName);
}
