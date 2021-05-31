package jp.co.exbbs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import jp.co.exbbs.domain.Article;
import jp.co.exbbs.domain.Comment;
import jp.co.exbbs.form.ArticleForm;
import jp.co.exbbs.form.CommentForm;
import jp.co.exbbs.repository.ArticleRepository;
import jp.co.exbbs.repository.CommentRepository;


/**
 * 掲示板画面を表示するコントローラ.
 * 
 * @author daiki.takayama
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	@ModelAttribute
	private ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	private CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	/**
	 * 掲示板画面を出力する.
	 * 
	 * @param model 書き込み情報とコメント情報を格納するリクエストスコープ
	 * @return　掲示板を出力
	 */
	@RequestMapping("/index")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
		List<Comment> commentList= new ArrayList<>();
		for(Article article:articleList) {
			commentList= commentRepository.findByArticleId(article.getId());
			article.setCommentList(commentList);

		}
		model.addAttribute("articleList",articleList);
		
		return "bbs-input";
	}
	
	
	/**
	 * 演習6 結合を活用した書き込み内容とコメントの出力.
	 * 
	 * @param model 書き込み情報を格納するリクエストスコープ
	 * @return 画面出力
	 */
	@RequestMapping("/joinIndex")
	public String joinIndex(Model model) {
		List<Article> articleList = articleRepository.joinFindAll();
		model.addAttribute("articleList",articleList);
		return "bbs-join-input";
	}
	
	
	/**
	 * 書き込みフォームに書き込みがあった場合、登録処理を実施する.
	 * 
	 * @param articleForm 書き込みフォームのリクエストパラメータを格納したオブジェクト
	 * @return 登録処理実施後、再度検索画面を表示
	 */
	@RequestMapping("/insertArticle")
	public String insertArticle(ArticleForm articleForm) {
		Article article =new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		return "redirect:/bbs/index";
	}
	
	
	/**
	 * コメントを追加登録する処理を実行する.
	 * 
	 * @param commentForm コメントのリクエストパラメータを格納するオブジェクト
	 * @return 掲示板画面を表示
	 */
	@RequestMapping("/insertComment")
	public String insertComment(CommentForm commentForm) {
		Comment comment =new Comment();
		BeanUtils.copyProperties(commentForm,comment);
//		System.out.println(comment.getArticleId());
		
		commentRepository.insert(comment);
		return "redirect:/bbs/index";
	}
	
	/**
	 *書き込みとコメントを削除するメソッド.
	 *※comments テーブルはarticles テーブルの外部キーを持っているので、先に削除を実行
	 * 
	 * @param articleForm リクエストパラメータを格納した書き込みオブジェクト
	 * @param commentForm　リクエストパラメータを格納したコメントオブジェクト
	 * @return 掲示板画面へ遷移
	 */
	@RequestMapping("/deleteArticle")
	public String deleteArticle(ArticleForm articleForm) {
		commentRepository.deleteByArticleId(articleForm.getId());
		articleRepository.deleteById(articleForm.getId());

		return "redirect:/bbs/index";
	}
	
	
	
}
