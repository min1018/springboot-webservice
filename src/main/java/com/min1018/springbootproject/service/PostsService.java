package com.min1018.springbootproject.service;

import com.min1018.springbootproject.domain.posts.Posts;
import com.min1018.springbootproject.domain.posts.PostsRepository;
import com.min1018.springbootproject.web.dto.PostsResponseDto;
import com.min1018.springbootproject.web.dto.PostsSaveRequestDto;
import com.min1018.springbootproject.web.dto.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
         Posts entity = postsRepository.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
         return new PostsResponseDto(entity);
    }
}
