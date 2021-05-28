package jp.co.exbbs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.exbbs.domain.Comment;


/**
 * Comments テーブルを操作するリポジトリ.
 * 
 * @author daiki.takayama
 *
 */
@Repository
public class CoomentRepository {
	
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	/**
	 * 投稿IDを受け取り、投稿に対して送られたコメントのリストを返す.
	 * 
	 * @param articleId 検索する投稿ID
	 * @return 該当するコメントリスト
	 */
	public List<Comment> findByArticleId(Integer articleId){
		String sql ="select id,name,content,article_id from comments where article_id=:articleId";
		
		SqlParameterSource param =new MapSqlParameterSource("articleId",articleId);
		List<Comment> commentList = template.query(sql,param, COMMENT_ROW_MAPPER);
		return commentList;
	}
}