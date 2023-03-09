package com.game.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.game.dto.QTeamDto;
import com.game.dto.TeamDto;
import com.game.entity.QTeam;
import com.game.entity.QTeamImage;
import com.game.entity.Team;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {

	private JPAQueryFactory queryFactory;
	
	public TeamRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<Team> getAdminTeamPage(Pageable pageable) {
		List<Team> content = queryFactory
				.selectFrom(QTeam.team)
				.orderBy(QTeam.team.id.desc())
				.offset(pageable.getOffset()) //데이터를 가져올 시작 index
				.limit(pageable.getPageSize()) //한번에 가지고 올 최대 개수
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QTeam.team)
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}

	@Override
	public Page<TeamDto> getTeamPage(Pageable pageable) {
		QTeam team = QTeam.team;
		QTeamImage teamImage = QTeamImage.teamImage;
		
		List<TeamDto> content = queryFactory.select(
				new QTeamDto(
					team.id,
					team.teamName,
					teamImage.teamImageUrl)
				)
				.from(teamImage)
				.join(teamImage.team, team)
				.where(teamImage.teamRepimgYn.eq("Y"))
				.orderBy(team.id.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count)
				.from(teamImage)
				.join(teamImage.team, team)
				.where(teamImage.teamRepimgYn.eq("Y"))
				.fetchOne();
		
		return new PageImpl<>(content, pageable, total);
	}
	

}

