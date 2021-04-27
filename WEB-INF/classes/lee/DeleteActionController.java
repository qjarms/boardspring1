package lee;

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
public class DeleteActionController {


	@Autowired
	BoardDAO dao;//BoardDAO dao=new BoardDAO();
	
	public void setDao(BoardDAO dao) { //<property name="dao"></property>
		this.dao = dao;
		System.out.println("setDao()호출됨(dao)=>"+dao);
	}
    // delete.do?num=4 =>get방식
	@RequestMapping("/delete.do")
	public ModelAndView handleRequest(HttpServletRequest request, 
			                                                  HttpServletResponse response) throws Exception {
		
		System.out.println("DeleteActionController의 handleRequest()호출됨");
		// TODO Auto-generated method stub
		String num=request.getParameter("num");
		dao.delete(num);
		//내부 컨트롤러 클래스에서는 특정 jsp이름을 쓸수 있지만
		//외부에서는 요청명령어를 통한 컨트롤러클래스로 전환할 수 있도록 코딩할것.
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/list.do");//request.setAttribute("list",list);
		//${data(키명)}
		return mav;//return "/list.jsp"; //viewResolver가 알려줌
	}
}
