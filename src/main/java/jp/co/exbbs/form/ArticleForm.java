package jp.co.exbbs.form;

import javax.validation.constraints.NotBlank;

/**
 * 掲示板への投稿フォームのパラメータを格納するフォーム.
 * 
 * @author daiki.takayama
 *
 */
public class ArticleForm {
	/** 書き込みID*/
	private Integer articleId;
	/** 投稿者名*/
	@NotBlank(message ="*投稿者名が入力されていません")
	private String name;
	/** コンテント*/
	@NotBlank(message ="*コンテントが入力されていません")
	private String content;
	public Integer getId() {
		return articleId;
	}
	public void setId(Integer id) {
		this.articleId = id;
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
		return "ArticleForm [id=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
	
	
}
