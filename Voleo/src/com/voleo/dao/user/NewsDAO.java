package com.voleo.dao.user;


import java.util.Collection;

import org.springframework.stereotype.Component;

import com.voleo.dao.AbstractDAO;
import com.voleo.entity.news.News;

@Component
public class NewsDAO extends AbstractDAO<News> implements INewsDAO {

	public NewsDAO() {
		super(News.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<News> getAll() {
		return jpaTemplate.find("from News n order by n.createDate DESC");
	}

}
