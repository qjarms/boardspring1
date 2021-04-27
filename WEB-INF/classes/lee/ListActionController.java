package lee;

import java.util.List;

//servlet-api.jar를 없어서 에러 발생 ->jar파일 불러올것
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Autowired
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
//어노테이션과 연관된 클래스.인터페이스 불러오게 되어있다.
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.Controller;

//public class ListActionController implements Controller {
@Controller
public class ListActionController {

	BoardDAO dao;// BoardDAO dao=new BoardDAO();

	@Required
	@Autowired
	public void setDao(BoardDAO dao) { // <property name="dao"></property>
		this.dao = dao;
		System.out.println("setDao()호출됨(dao)=>" + dao);
	}
	// list.do

	// public void test() {}

	@RequestMapping("/list.do")
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                  HttpServletResponse response) throws Exception {
		System.out.println("ListActionController의 handleRequest()호출됨");

		
		
		int currentPage = request.getParameter("currentPage") == null ? 1 : Integer.parseInt(request.getParameter("currentPage"));
		int cntPerPage = request.getParameter("cntPerPage")  == null ? 10 : Integer.parseInt(request.getParameter("cntPerPage"));
		int pageSize = request.getParameter("pageSize") == null ? 10 : Integer.parseInt(request.getParameter("pageSize"));
		
		int listCnt = dao.getBoardTotalCnt();
        Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
        pagination.setTotalRecordCount(listCnt);
        
		//ArrayList list=dao.list();
		List list=dao.list();
		List list2=dao.getBoardList2(pagination);
		System.out.println("ListActionController의 list=>"+list);
		//화면에 출력할 list.jsp에 전달할 페이지와 전달할값을 설정
		ModelAndView mav=new ModelAndView();
		mav.setViewName("list");//이동할 파일명만
		mav.addObject("list",list);//request.setAttribute("list",list);
		mav.addObject("list2", list2);
		mav.addObject("pagination", pagination);
		//${list(키명)}
		return mav;//return "/list.jsp"; //viewResolver가 알려줌
	}
}
