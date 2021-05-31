package jp.co.exbbs.form;


/**
 * 掲示板への投稿フォームのパラメータを格納するフォーム.
 * 
 * @author daiki.takayama
 *
 */
public class ArticleForm {
	/** 書き込みID*/
	private Integer id;
	/** 投稿者名*/
	private String name;
	/** コンテント*/
	private String content;
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
	@Override
	public String toString() {
		return "ArticleForm [id=" + id + ", name=" + name + ", content=" + content + "]";
	}
	
	
	
	
}
