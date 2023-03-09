package com.game.repository;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.game.dto.PostDto;
import com.game.dto.QPostDto;
import com.game.entity.Post;
import com.game.entity.QPost;
import com.game.entity.QPostImage;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PostRepositoryCustomImpl implements PostRepositoryCustom {

	private JPAQueryFactory queryFactory;
	
	public PostRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	

	@Override
	public Page<Post> getAdminPostPage(Pageable pageable) {
		List<Post> content = queryFactory
				.selectFrom(QPost.post)
				.orderBy(QPost.post.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long total = queryFactory.select(Wildcard.count).from(QPost.post)
				.fetchOne();
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<PostDto> getPostPage(Pageable pageable) {
		QPost post = QPost.post;
		QPostImage postImage = QPostImage.postImage;
		
		List<PostDto> content = queryFactory.select(
				new QPostDto(
						post.id,
						post.postTitle,
						post.postText,
						postImage.postImageUrl,
						post.createdBy,
						post.regTime)
				)
				.from(postImage)
				.join(postImage.post, post)
				.where(postImage.postRepimgYn.eq("Y"))
				.orderBy(post.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		Long total = queryFactory.select(Wildcard.count)
				.from(postImage)
				.join(postImage.post, post)
				.where(postImage.postRepimgYn.eq("Y"))
				.fetchOne();
		return new PageImpl<>(content, pageable, total);
	}


}

