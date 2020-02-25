package com.gethobby.web.community;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gethobby.common.Page;
import com.gethobby.common.Search;
import com.gethobby.service.community.CommunityService;
import com.gethobby.service.domain.Article;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Reply;
import com.gethobby.service.domain.User;
import com.sun.org.apache.xpath.internal.operations.Mod;

@Controller
@RequestMapping("/community/*")
public class CommunityController {

	//field
	@Autowired
	@Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	public CommunityController() {
		// TODO Auto-generated constructor stub
	}

	@Value("#{commonProperties['articlePageSize']}")
	int pageSize;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@RequestMapping(value = "listCommunity", method = RequestMethod.POST)
	//public String listCommunity(@ModelAttribute("search") Search search, 
	//						HttpSession session, Model model)throws Exception{
	public String listCommunity(@ModelAttribute("search") Search search, 
				@RequestParam("hobbyClassNo")int hobbyClassNo, HttpSession session, Model model)throws Exception{
		
		System.out.println("\n\n\n\n\n/listCommunityPOST");
		
		System.out.println("/n/n/n/n/n/n/n/n");
		
		//세션가져오기
		User user = (User)session.getAttribute("user");
		System.out.println("/n/n\n\n session의 user----\n"+user);
				
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		System.out.println("////////////n/n/n/n/n/n/n/nhobbyClassNo--\n\n"+hobbyClassNo);
		
		//////////////////////////////////////
		//HobbyClass hobbyClass = new HobbyClass();
		//int hobbyClassNo = 10000;
		//hobbyClass.setHobbyClassNo(hobbyClassNo);
		//////////////////////////////////////
		
		
		Map<String, Object> serviceMap = new HashMap<String, Object>();
		serviceMap.put("search", search);
		serviceMap.put("hobbyClassNo", hobbyClassNo);
		
		//해당 클래스번호와 일치하는 글, 댓글 리스트 가져오는 service
		Map<String, Object> map = communityService.getCommunityList(serviceMap);
		
		int totalCount = (Integer)map.get("totalCountCommunityArticle");
		System.out.println("클래스의 총 글 갯수:"+totalCount);
		Page resultPage = new Page(search.getCurrentPage(), totalCount, pageUnit, pageSize);
		
		List<Article> articleList = (List<Article>)map.get("articleList");
		List<List<Reply>> array = (List<List<Reply>>)map.get("array");
		
//		for (int i = 0; i < articleList.size(); i++) {
//			System.out.println(articleList.get(i).getArticleNo());
//			System.out.println(articleList.get(i).getRegDate());
//			System.out.println(articleList.get(i).getArticleContent());
//				for (int j = 0; j <array.get(i).size(); j++) {
//							//array.size()가 아니라 array.get(i).size()인 이유? => articleList의 순번과 같은 순번의 arrayList가 돌게하려고.
//							//List타입의 array0번쨰에 여러개의 List.. array1번째에 또 여러개의 List..
//							//articleList(1번째 글)-array(1번째댓글List타입-댓글No,댓글내용등이 List로 담겨있으니까)
//							//					 -array(2번째댓글List타입-댓글No,댓글내용등이 List로)
//					
//					//System.out.println(array); <==댓글 안달린 글의 경우 array가 빈 배열로 나옴.
//					System.out.println("=====>"+articleList.get(i).getArticleNo()+"에 달린 댓글임..");
//					System.out.println("=====>"+array.get(i).get(j).getReplyNo());
//					System.out.println("=====>"+array.get(i).get(j).getRegDate());
//					System.out.println("=====>"+array.get(i).get(j).getReplyContent());                                   
//				}
//		}
		                              
		model.addAttribute("sessionUser", user);
		model.addAttribute("hobbyClassNo", hobbyClassNo);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("articleList", (List<Article>)map.get("articleList"));
		model.addAttribute("array", (List<List<Reply>>)map.get("array"));
		return "forward:/community/listCommunity.jsp";
	}
	
	@RequestMapping(value = "listCommunity", method = RequestMethod.GET)
	public String listCommunityGet(@ModelAttribute("search") Search search, 
					@RequestParam("hobbyClassNo")int hobbyClassNo, HttpSession session, Model model	)throws Exception{
	//	 HttpSession session, Model model	)throws Exception{
		
		System.out.println("\n\n\n\n/listCommunity___GET방식\n\n");
		
		System.out.println("/n/n/n/n/n/n/n/n");
		//System.out.println("/n/nhobbyClassNo---\n"+hobbyClassNo+"\n\nsearch----\n"+search);
		
		//세션가져오기
		User user = (User)session.getAttribute("user");
		System.out.println("/n/n\n\n session의 user----\n"+user);
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		System.out.println("////////////n/n/n/n/n/n/n/nhobbyClassNo--\n\n"+hobbyClassNo);

		//구매한 사람만 들어온 상태.
		//////////////////////////////////////실험용
		//HobbyClass hobbyClass = new HobbyClass();
		//int hobbyClassNo = 10000;
		//hobbyClass.setHobbyClassNo(hobbyClassNo);
		//////////////////////////////////////
		
		
		Map<String, Object> serviceMap = new HashMap<String, Object>();
		serviceMap.put("search", search);
		serviceMap.put("hobbyClassNo", hobbyClassNo);
		
		//해당 클래스번호와 일치하는 글, 댓글 리스트 가져오는 service
		Map<String, Object> map = communityService.getCommunityList(serviceMap);
		
		int totalCount = (Integer)map.get("totalCountCommunityArticle");
		System.out.println("클래스의 총 글 갯수:"+totalCount);
		Page resultPage = new Page(search.getCurrentPage(), totalCount, pageUnit, pageSize);
		
		List<Article> articleList = (List<Article>)map.get("articleList");
		List<List<Reply>> array = (List<List<Reply>>)map.get("array");
		
		model.addAttribute("sessionUser", user);
		model.addAttribute("hobbyClassNo", hobbyClassNo);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("articleList", (List<Article>)map.get("articleList"));
		model.addAttribute("array", (List<List<Reply>>)map.get("array"));
		return "forward:/community/listCommunity.jsp";
	}
	
	//글 등록 화면
	@RequestMapping(value = "addCommunityArticleView", method = RequestMethod.GET)
	public String addCommunityArticleView(@RequestParam("hobbyClassNo")int hobbyClassNo, 
											Model model)throws Exception{
		
		System.out.println("/addCommunityArticleView");

		System.out.println("돌려보낼 클래스No:"+hobbyClassNo);
		
		model.addAttribute("hobbyClassNo", hobbyClassNo);
		return "forward:/community/addCommunityArticle.jsp";
	}
	
	//글 등록 
	@RequestMapping(value = "addCommunityArticle", method = RequestMethod.POST )
	public String addCommunityArticle(@ModelAttribute("article") Article article, HttpSession session, Model model )throws Exception{
	//public String addCommunityArticle(@ModelAttribute("article") Article article, HttpSession session,
		//	@RequestParam("hobbyClassNo")int hobbyClassNo		)throws Exception{
		
		System.out.println("\n\n\n/addCommunityArticle");
		System.out.println("\n\n\narticle---\n"+article);
		System.out.println("\n\nhobbyClassNo---"+article.getHobbyClass().getHobbyClassNo());
		
		User user = (User)session.getAttribute("user");
		
		if(user.getUserId() != null) { //+ 해당 클래스를 구매했는지도 체크해줘야...
			article.setUser(user);
		}
	
		
		System.out.println("\n\n\narticle---\n"+article.getUser());
		communityService.addCommunityArticle(article);
		
		return "forward:/community/listCommunity?hobbyClassNo="+article.getHobbyClass().getHobbyClassNo();
	}
	
	
	@RequestMapping(value = "getCommunity") //==> method = RequestMethod.GET으로 설정하면 안됨
	public String getCommunity(@RequestParam("articleNo") int articleNo, 
									Model model)throws Exception{
		
		System.out.println("\n\n\n\n\n/getCommunity");
		System.out.println("articleNo:"+articleNo);
		
		Map<String, Object> map = communityService.getCommunity(articleNo);
		Article article = (Article) map.get("article");
		
		//글 내용 가독성있게 적당히 띄워쓰기하는 파트
		article.setArticleContent( article.getArticleContent().replaceAll("\n", "<br>") );
		
		List<Reply> list = (List<Reply>)map.get("replyList");

		//댓글 내용 가독성있게 적당히 띄워쓰기하는 파트
		for ( int i = 0; i < list.size(); i++ ) {
			list.get(i).setReplyContent( list.get(i).getReplyContent().replaceAll("\n", "<br>") );
		}
		
		System.out.println(article.getUser());
		
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("replyList의 ----"+list.get(i).getReplyNo());
//			System.out.println(list.get(i).getRegDate());
//			System.out.println(list.get(i).getUser().getName());
//		}
		//System.out.println(map.get("replyList"));
		
		int totalCountCommunityReply = (Integer)map.get("totalCountCommunityReply");
		
		model.addAttribute("article", map.get("article"));
		model.addAttribute("replyList", list);
		model.addAttribute("totalCountCommunityReply", totalCountCommunityReply);
		return "forward:/community/getCommunity.jsp";
	}
	
	@RequestMapping(value = "getCommunityArticleUpdateView", method = RequestMethod.GET)
	public String getCommunityArticleUpdateView(@RequestParam int articleNo, Model model)throws Exception{
		
		System.out.println("/getCommunityArticleUpdateView");
		System.out.println("articleNo--"+articleNo);
		
		
		Article article = communityService.getCommunityArticle(articleNo);
		System.out.println("\n\n\n article---\n"+article);
		
		model.addAttribute("article", article);
		return "forward:/community/updateCommunityArticleView.jsp";
	}
	
	@RequestMapping(value = "updateCommunityArticle", method = RequestMethod.POST)
	public String updateCommunityArticle(HttpSession session, Model model, @ModelAttribute("article") Article article)throws Exception{
		
		System.out.println("\n\n\n\n /updateCommunityArticle");
		System.out.println("\n\n\n\narticle--\n"+article);
		System.out.println("\n\n\n\narticle--\n"+article.getArticleContent());
		System.out.println("\n\narticle User--\n"+article.getUser());
		
		communityService.updateCommunityArticle(article);
		
		
		return "forward:/community/getCommunity?articleNo="+article.getArticleNo();
	}
	
	@RequestMapping(value = "deleteCommunityArticle", method = RequestMethod.GET)
	public String deleteCommunityArticle(HttpSession session,@RequestParam int articleNo, @RequestParam int hobbyClassNo)throws Exception{
	
		System.out.println("\n\n\n\n /deleteCommunityArticle");
		System.out.println("\n\n\n\narticle--\n"+articleNo);
		System.out.println("\n\n\n\nhobbyClassNo--\n"+hobbyClassNo);
		
		communityService.deleteCommunityArticle(articleNo);
		
		return "forward:/community/listCommunity?hobbyClassNo="+hobbyClassNo;
	}
	
}
