package com.game.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.game.dto.CommentDto;

import com.game.dto.PostDto;
import com.game.dto.PostFormDto;
import com.game.dto.PostImageDto;

import com.game.entity.Comment;

import com.game.entity.Post;
import com.game.entity.PostImage;

import com.game.entity.Users;
import com.game.repository.CommentRepository;
//import com.game.entity.TeamImage;
import com.game.repository.PostImageRepository;
import com.game.repository.PostRepository;
//import com.game.repository.TeamImageRepository;

import com.game.repository.UsersRepository;


import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
	private final PostRepository postRepository;
	private final PostImageService postImageService;
	private final PostImageRepository postImageRepository;
	private final CommentRepository commentRepository;
	private final UsersRepository usersRepository;
	
	
	
	//add post
	
	public Long SavePost(PostFormDto postFormDto, MultipartFile postImageFile) throws Exception{
		Post post = postFormDto.createPost();
		postRepository.save(post);
		
		//이미지 등록
//				for(int i=0; i<postImageFileList.size(); i++) {
//					PostImage postImage = new PostImage();
//					postImage.setPost(post);
//					
//					//첫번째 이미지 일때 대표 상품 이미지 여부 지정
//					if(i == 0) { 
//						postImage.setPostRepimgYn("Y");
//					} else {
//						postImage.setPostRepimgYn("N");
//					}
//					
//					postImageService.savePostImage(postImage, postImageFileList.get(i));
//				}
		PostImage postImage = new PostImage();
		postImage.setPost(post);
		postImage.setPostRepimgYn("Y");
		postImageService.savePostImage(postImage, postImageFile);		
		
				return post.getId();
		
	} 
	
	
	//post list
	@Transactional(readOnly = true)
	public Page<Post> getAdminPostDtoPage(Pageable pageable){
		return postRepository.getAdminPostPage(pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<PostDto> getPostDtoPage(Pageable pageable){
		return postRepository.getPostPage(pageable);
	}

	//post detail
	@Transactional(readOnly = true)
	public PostFormDto getPostDetail(Long postId) {
		List<PostImage> postImageList = postImageRepository.findByPostIdOrderByIdAsc(postId);
		
		List<PostImageDto> postImageDtoList = new ArrayList<>();
		
		for(PostImage postImage : postImageList) {
			PostImageDto postImageDto = PostImageDto.of(postImage);
			postImageDtoList.add(postImageDto);
		}
		
		
		
		
		//2. post테이블에 있는 데이터를 가져온다.
		Post post = postRepository.findById(postId)
									 .orElseThrow(EntityNotFoundException::new);
		
		//엔티티 객체 -> dto객체로 변환
		PostFormDto postFormDto = PostFormDto.of(post);
		
		//gamer의 이미지 정보를 넣어준다.
		postFormDto.setPostImageDtoList(postImageDtoList);
		
		return postFormDto;
	}

//comment insert
	public void insertComment(Long usersId, Long postId, String content) {
		//Users commentUsers = usersRepository.findById(usersId).orElseThrow(EntityNotFoundException::new);
		Post commentPost = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);
		
		Comment comment = new Comment();
		comment.setPost(commentPost);
		//comment.setUsers(commentUsers);
		comment.setRegTime(LocalDateTime.now());
		comment.setContent(content);
		
		commentRepository.save(comment);
	}

//comment post
	@Transactional
	public List<CommentDto> getCommentList(Long id) {
		System.out.println("확인 : start  "+id);
		List<Comment> commentList = commentRepository.findByPostIdOrderByIdAsc(id);
		System.out.println("확인 : start1" + commentList);
		List<CommentDto> commentDtoList = new ArrayList<>();
		System.out.println("확인 : start2"+ commentDtoList);
		
		
		for (Comment comment : commentList) {
			CommentDto commentDto = CommentDto.of(comment);
//			System.out.println("확인 : start3");
			
			//String donatedWriteCommentMember = donationBoardComment.getMember().getNickName(); 修改前直接用这个方法得到昵称，但是要得到两个的话，在会员这里截止，然后分别得到两个值。
			//Users writeCommentUsers = comment.getUsers();
			
//			System.out.println("확인 : start4"+donationBoardComment.getMember().getNickName());
			
			//commentDto.setCreateBy(writeCommentUsers.getName());
//			System.out.println("확인 : start5"+donationBoardCommentDto.getComment());
			//commentDto.setCommentEmail(writeCommentUsers.getEmail());
			
			commentDtoList.add(commentDto);

		}
		return commentDtoList;
	}
	
	//comment delete
	public void deleteComment(Long commentId) {
		commentRepository.deleteById(commentId);
				
	}
	
}
