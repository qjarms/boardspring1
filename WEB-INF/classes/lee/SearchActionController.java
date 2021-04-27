package lee;

import java.util.List;

//servlet-api.jar를 없어서 에러 발생 ->jar파일 불러올것
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

//public class ListAction implements CommandAction {
//페이지 이동,클래스는 틀리지만 요청해서 처리해주는 메서드 기능은 동일
@Controller
public class SearchActionController {

	@Autowired
	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"></property>
		this.dao = dao;
		System.out.println("setDao()호출됨(dao)=>"+dao);
	}
    // search.do
	@RequestMapping("/search.do")
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                  HttpServletResponse response) throws Exception {
		
		System.out.println("SearchActionController의 handleRequest()호출됨");
		// TODO Auto-generated method stub
		//추가(검색분야,검색어에 해당하는 레코드만 보여줄 수잇도록)
		String searchName=request.getParameter("searchName");//검색분야
		String searchValue=request.getParameter("searchValue");//검색어
		//----------------------------------------------------------
		//ArrayList list=dao.search(searchName,searchValue); before
		//--------------after------------------------------------------
		BoardCommand data=new BoardCommand();
		data.setSearchName(searchName);//검색분야
		data.setSearchValue(searchValue);//검색어
		List list=dao.search(data);//data.getSearchName()=>#{searchName}
		                                         //data.getSearchValue()=>#{searchName}
		//-----------------------------------------------------------
		//화면에 출력할 list.jsp에 전달할 페이지와 전달할값을 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("list");//이동할 파일명만
		mav.addObject("list",list);//request.setAttribute("list",list);
		//${list(키명)}
		return mav;//return "/list.jsp"; //viewResolver가 알려줌
	}
}
