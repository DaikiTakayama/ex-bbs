package jp.co.exbbs.form;

/**
 * コメント入力フォームのパラメータを受け取るフォーム.
 * 
 * @author daiki.takayama
 *
 */
public class CommentForm {
	/** コメントが記載された投稿のID*/
	private Integer articleId;
	/** コメント者名*/
	private String name;
	/** コンテント*/
	private String content;
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
