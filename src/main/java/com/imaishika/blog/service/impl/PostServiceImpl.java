package com.imaishika.blog.service.impl;

import com.imaishika.blog.entity.Post;
import com.imaishika.blog.exceptions.ResourceNotFoundException;
import com.imaishika.blog.payload.PostDto;
import com.imaishika.blog.repository.PostRepository;
import com.imaishika.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post mapToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        //need to convert DTO to entity
        Post post = mapToPost(postDto);
        //save the newly created post in database
        Post newPost = postRepository.save(post);
        //convert entity to DTO
        return mapToDto(newPost);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostDto> postsDtoList = new ArrayList<>();

        for(Post post: posts) {
            postsDtoList.add(mapToDto(post));
        }
        return postsDtoList;
    }

    @Override
    public PostDto getPostById(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);

    }

    @Override
    public String deletePostById(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        postRepository.deleteById(postId);

        return String.format("Successfully deleted Post with id = %s", postId);
    }
}
