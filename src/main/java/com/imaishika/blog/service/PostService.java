package com.imaishika.blog.service;

import com.imaishika.blog.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
