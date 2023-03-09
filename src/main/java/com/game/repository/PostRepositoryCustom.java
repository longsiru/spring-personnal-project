package com.game.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import com.game.dto.PostDto;
import com.game.entity.Post;


public interface PostRepositoryCustom {
	Page<Post> getAdminPostPage(Pageable pageable);
	Page<PostDto> getPostPage(Pageable pageable);
}
