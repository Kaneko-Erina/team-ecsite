package jp.co.internous.team2411.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2411.model.domain.MstCategory;
import jp.co.internous.team2411.model.domain.MstProduct;
import jp.co.internous.team2411.model.form.SearchForm;
import jp.co.internous.team2411.model.mapper.MstCategoryMapper;
import jp.co.internous.team2411.model.mapper.MstProductMapper;
import jp.co.internous.team2411.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2411")
public class IndexController {

	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private LoginSession loginSession;

	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		List<MstProduct> product = productMapper.find();
		m.addAttribute("products", product);
		
		List<MstCategory> category = categoryMapper.find();
		m.addAttribute("categories", category);
	
		if (loginSession.getUserId() == 0 && loginSession.getTmpUserId() > 0) {
			int id = -(100000000 + (int) (Math.random() * 900000000));
			loginSession.setTmpUserId(id);
			}
		
		m.addAttribute("loginSession",loginSession);
		
		return "index";
		
	}
		
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		
		List<MstCategory> category = categoryMapper.find();
		m.addAttribute("categories", category);
		m.addAttribute("selected",f.getCategory());
		
		if (f.getKeywords() == "" && f.getCategory() == 0) {
			List<MstProduct> product = productMapper.find();
			m.addAttribute("products", product);
			
			return "index";
			
		} else if (f.getCategory() == 0) {
			
			String normalizedkeywords = f.getKeywords().replaceAll("　", " ");
			String halfWidthSpacekeywords = normalizedkeywords.replaceAll("\\s{2,}", " ");
			String trimmedkeywords = halfWidthSpacekeywords.trim();
			List<String> changedkeywords = Arrays.asList(trimmedkeywords.split(" "));
			List<MstProduct> products = productMapper.findByProductName(changedkeywords);
			m.addAttribute("products", products);
			m.addAttribute("keywords",trimmedkeywords);
			
			return "index";
			
		}

		String normalizedkeywords = f.getKeywords().replaceAll("　", " ");
		String halfWidthSpacekeywords = normalizedkeywords.replaceAll("\\s{2,}", " ");
		String trimmedkeywords = halfWidthSpacekeywords.trim();
		List<String> changedkeywords = Arrays.asList(trimmedkeywords.split(" "));
		List<MstProduct> products = productMapper.findByCategoryAndProductName(f.getCategory(), changedkeywords);
		m.addAttribute("products", products);
		m.addAttribute("", f.getCategory());
		m.addAttribute("keywords", trimmedkeywords);
		
		return "index";
		
	}
	
}