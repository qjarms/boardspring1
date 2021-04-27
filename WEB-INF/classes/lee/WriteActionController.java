package lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//AbstractCommandController =>입력을 받아서 자동적으로 Setter Method 호출
//public class WriteActionController extends AbstractCommandController {
@Controller
public class WriteActionController {
      //setCommandClass(BoardCommand command)상속받아서 이미 가지고 있는 상태
	
	
	@Autowired
      BoardDAO dao;//BoardDAO dao=new BoardDAO();
	/*
	public void setDao(BoardDAO dao) { //<property name="dao"></property>
		this.dao = dao;
		System.out.println("setDao()호출됨(dao)=>"+dao);
	}
	String title=request.getParameter("title");
	형식)@RequestParam("매개변수명") 반환받는 자료형 저장할 변수명
	      @RequestParam("title") String title
	     request객체 ->get방식으로 요청시 처리(@GetMapping("요청경로"))
	     request객체 ->post방식으로 요청시 처리(@PostMapping("요청경로"))
	     @RequestParam("num") String num
	*/
	
	@RequestMapping("/write.do")
	protected ModelAndView test(@RequestParam("title") String title,
			                                       @RequestParam("author") String author,
			                                       @RequestParam("content") String content)
			                                           throws Exception {
		// TODO Auto-generated method stub
		System.out.println("WriteActionController의 handle()호출됨");
		//request.setCharacterEncoding("utf-8");
		/* 어노테이션 때문에 생략이 가능하다.
		String author=request.getParameter("author");
		String title=data.getTitle();
		String content=data.getContent();
		*/
		//메서드 호출 write(data)~
		//최대값+1 ->새로 저장할 게시물번호 num에 저장
		BoardCommand data=new BoardCommand();
		int newNum=dao.getNewNum()+1;
		data.setNum(newNum);//data.getNum() =>#{num}
		//수동 => <jsp:setProperty name="객체명" property="*" />
		data.setTitle(title);
		data.setAuthor(author);
		data.setContent(content);
		dao.write(data);//data.getWriter() =>#{writer},,,
		
		return new ModelAndView("redirect:/list.do");
	}

}
