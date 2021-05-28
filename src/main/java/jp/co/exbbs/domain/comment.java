package jp.co.exbbs.domain;

/**
 * コメント情報を格納するドメイン.
 * 
 * @author daiki.takayama
 *
 */
public class Comment {
	/** コメントID*/
	private Integer id;
	/** コメント者名*/
	private String name;
	/** コンテント*/
	private String content;
	/** 投稿ID の外部キー*/
	private Integer article_id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	@Override
	public String toString() {
		return "comment [id=" + id + ", name=" + name + ", content=" + content + ", article_id=" + article_id + "]";
	}
	
	
}
