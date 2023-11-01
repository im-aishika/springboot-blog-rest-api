package com.imaishika.blog.service;

import com.imaishika.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long postId);

    PostDto updatePostById(PostDto postDto, long postId);

    String deletePostById(long postId);
}
