package lee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.AbstractCommandController;

//AbstractCommandController =>입력을 받아서 자동적으로 Setter Method 호출
//스프링 =>쇼핑물
@Controller
public class UpdateActionController {
      //setCommandClass(BoardCommand command)상속받아서 이미 가지고 있는 상태
@Autowired
BoardDAO dao;//BoardDAO dao=new BoardDAO();
	/*
	public void setDao(BoardDAO dao) { //<property name="dao"></property>
		this.dao = dao;
		System.out.println("setDao()호출됨(dao)=>"+dao);
	}
	//1.request(요청객체) 2.response(응답객체)
	//3.입력받은 값을 저장한객체(Object (다입력이 가능 O)
	//4.BindException =>사용자로부터 값을 입력시 에러발생 ->예외처리 클래스
	*/
	@RequestMapping("/update.do")
	protected ModelAndView up(@RequestParam("num") int num,
			                                     @RequestParam("title") String title,
			                                     @RequestParam("author") String author,
			                                     @RequestParam("content") String content) 
                                                      throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UpdateActionController의 handle()호출됨");
		//request.setCharacterEncoding("utf-8");
		//BoardCommand data=(BoardCommand)command;
		//추가 (게시물번호가 전달 ->hidden객체 or 읽기전용 input box 이용)
		/* before
		String num=request.getParameter("num");//where num=4
		//----------------------------------------------
		String author=data.getAuthor();//작성자 
		String title=data.getTitle();
		String content=data.getContent();
		*/
		BoardCommand data=new BoardCommand();
		//int newNum=dao.getNewNum()+1;
		
		data.setNum(num);
		data.setTitle(title);
		data.setAuthor(author);
		data.setContent(content);
	    dao.update(data);
	    
		//dao.update(num,author, title, content);
		return new ModelAndView("redirect:/list.do");
	}
}
