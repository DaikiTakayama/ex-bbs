package jp.co.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.exbbs.domain.Article;


/**
 * articles テーブルを操作するリポジトリ.
 * 
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
	
	
	/**
	 * articles テーブルに列を追加する.
	 * 
	 * @param article 書き込み情報を格納したオブジェクト
	 */
	public void insert(Article article) {
		String sql="insert into articles(name,content) values(:name,:content)";
		SqlParameterSource param =new MapSqlParameterSource().addValue("name", article.getName()).addValue("content", article.getContent());
		template.update(sql,param);
	}
	
	/**
	 * 該当する書き込みをすべて消去する.
	 * 
	 * @param id 消去する書き込みのID
	 */
	public void deleteById(Integer id) {
		String sql ="delete from articles where id =:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	

}
