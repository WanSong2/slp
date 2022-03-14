package com.songw.slp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.songw.slp.model.Author;
import com.songw.slp.model.Journey;

@Repository
public class AuthorRepository  {

	private SqlSession sqlSession;
    
	@Autowired
    public AuthorRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Author findAuthorById(int id) {
        return sqlSession.selectOne("database.getAuthorById", id);
    }
    
    public List<Map<String,String>> findAuthorList() {
        return sqlSession.selectList("database.getAuthorList");
    }
    
    public List<Journey> getJourneyList() {
    	return sqlSession.selectList("database.getJourneyList");
    }
    
}
