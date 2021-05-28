package jp.co.exbbs.controller;

import java.util.List;

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
	
	@RequestMapping("")
	private String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		model.addAttribute("articleList",articleList);
		
		List<Comment> commentList=commentRepository.findByArticleId(articleList.get(0).getId());
		model.addAttribute("commentList",commentList);

		return "bbs-input";
	}
	
	@RequestMapping("/insertArticle")
	private String insertArticle(ArticleForm articleForm) {
		
	}
}
