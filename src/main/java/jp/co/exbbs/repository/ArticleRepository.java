package jp.co.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.exbbs.domain.Article;


/**
 * articles テーブルを操作するリポジトリ.
 * @author daiki.takayama
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPET = new BeanPropertyRowMapper<Article>(Article.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * articles テーブルの情報を全件取得する.
	 * 
	 * @return 取得結果を返す.
	 */
	public List<Article> findAll(){
		String sql ="select id,name,content from articles order by id desc";
		
		return template.query(sql, ARTICLE_ROW_MAPPET);
	} 
	

}