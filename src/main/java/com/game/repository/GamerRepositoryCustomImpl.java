package com.game.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.game.dto.GamerDto;
import com.game.dto.QGamerDto;
import com.game.dto.QTeamDto;
import com.game.dto.TeamDto;
import com.game.entity.Gamer;
import com.game.entity.QGamer;
import com.game.entity.QGamerImage;

import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class GamerRepositoryCustomImpl implements GamerRepositoryCustom {

	private JPAQueryFactory queryFactory;
	
	public GamerRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public Page<Gamer> getAdminGamerPage(Pageable pageable) {
		List<Gamer> content = queryFactory
				.selectFrom(QGamer.gamer)
				.orderBy(QGamer.gamer.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QGamer.gamer)
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<GamerDto> getGamerPage(Pageable pageable) {
		QGamer gamer = QGamer.gamer;
		QGamerImage gamerImage = QGamerImage.gamerImage;
		
		List<GamerDto> content = queryFactory.select(
				new QGamerDto(
						gamer.id, 
						gamer.gamerName,
						gamer.gamerRoute,
						gamerImage.gamerImageUrl)
				)
				.from(gamerImage)
				.join(gamerImage.gamer, gamer)
				.where(gamerImage.gamerRepimgYn.eq("Y"))
				.orderBy(gamer.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count)
				.from(gamerImage)
				.join(gamerImage.gamer, gamer)
				.where(gamerImage.gamerRepimgYn.eq("Y"))
				.fetchOne();
		return new PageImpl<>(content, pageable, total);
	}

	
	

}

