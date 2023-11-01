package com.imaishika.blog.service.impl;

import com.imaishika.blog.entity.Post;
import com.imaishika.blog.payload.PostDto;
import com.imaishika.blog.repository.PostRepository;
import com.imaishika.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        System.out.println("Inside create post function");
        //need to convert DTO to entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDto pDto = new PostDto();
        pDto.setId(newPost.getId());
        pDto.setTitle(newPost.getTitle());
        pDto.setDescription(newPost.getDescription());
        pDto.setContent(newPost.getContent());

        return pDto;
    }
}
