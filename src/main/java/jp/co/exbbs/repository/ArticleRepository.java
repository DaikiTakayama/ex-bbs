package jp.co.exbbs.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.exbbs.domain.Article;
import jp.co.exbbs.domain.Comment;


/**
 * articles テーブルを操作するリポジトリ.
 * 
 * @author daiki.takayama
 *
 */
@Repository
public class ArticleRepository {
	private static final RowMapper<Article> ARTICLE_ROW_MAPPET = new BeanPropertyRowMapper<Article>(Article.class);
	
	
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_RESULT_SET_EXTRACTOR = (rs) -> {

		List<Article> articeleList = new ArrayList<>();
		List<Comment> commentList = null;

		Integer nowArticleId=0;
		while(rs.next()) {
			Integer newArticleId= rs.getInt("a_id");
			
			if(newArticleId != nowArticleId) {
				Article article = new Article();
				article.setId(rs.getInt("a_id"));
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));
				
				commentList = new ArrayList<>();
				article.setCommentList(commentList);
				
				
				articeleList.add(article);
			}

			
			if(rs.getInt("c_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("c_id"));
				comment.setName(rs.getString("c_name"));
				comment.setContent(rs.getString("c_content"));
				comment.setArticleId(rs.getInt("c_article_id"));
				
				commentList.add(comment);

			}
			
			nowArticleId = newArticleId;
		}
		
		return articeleList;
	};
	
	
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
	
	
	public List<Article> joinFindAll(){
		String sql="select a.id as a_id,a.name as a_name,a.content as a_content,c.id as c_id,c.name as c_name,c.content as c_content,c.article_id as c_article_id from articles as a \r\n"
				+ "left outer join \"comments\" as c \r\n"
				+ "on a.id=c.article_id\r\n order by a.id desc,c.id";
		
		return template.query(sql,ARTICLE_RESULT_SET_EXTRACTOR);
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
	
	
	/**
	 * 演習8
	 * 書き込みとコメントを1つのSQLで同時に削除する.
	 * 
	 * @param articleId 削除する書き込みのID
	 */
	public void deleteArticleAndCommentByID(Integer articleId) {
		String sql ="delete from articles where id=:articleId";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	}
	

}
