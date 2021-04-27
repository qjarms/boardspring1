
package lee;

import java.util.*; //List,ArrayList ->글목록보기
//DataAccessException(스프링 전용 예외처리클래스 ->try~catch를 사용x
//예외가 발생ㅇ이 될때 처리를 해준다.

import  org.springframework.dao.DataAccessException;

public interface BoardDAO{
	
	//1.글목록보기
	public List list() throws DataAccessException;
	
	
	//2-1.글쓰기 -게시물의 최댁값 구하기 ->int
	public int getNewNum() throws DataAccessException;
	
	//2-2.글쓰기
	public void write(BoardCommand data) throws DataAccessException;
	
	//3-1.글사셍보기-조회수 증가시키기-> update
	public void updateReadcnt(String num) throws DataAccessException;
	
	//3-2.글 상세보기 -조회수가 증가된 레코드 데이터 담기
	public BoardCommand retrieve(String num) throws DataAccessException;

	//4.글수정하기 
	public void update(BoardCommand data) throws DataAccessException;
	
	//5.글삭제하기
	public void delete(String num) throws DataAccessException;
	
	//6.검색하기->(String searchName,String searchValue) =>HashMap처리방법
	//                 따로클래스로 만들어서 불러오는 경우
	public List search(BoardCommand data) throws DataAccessException;
	
	public List getBoardList() throws DataAccessException;
	
	public List getBoardList2(Pagination pagination) throws DataAccessException;
	
	public int getBoardTotalCnt() throws DataAccessException;
}









/*
package lee;

//Connection,PreparedStatement
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList
import java.util.ArrayList;

//Context (인터페이스) or InitaContext객체(자식)
import javax.naming.Context;
import javax.naming.InitialContext;
//////////JNDI 방법//////////
//DataSourc객체 ->getConnection()
import javax.sql.DataSource;
                                     //lookup('찾고자하는 jndi명')

public class BoardDAO{

	DataSource ds;//DBConnectionMgr pool;와 기능이 같다(has a 관계)
   	
   public BoardDAO(){
		//생성자: DataSource 얻기 :  InitialContext  
		try {
			//InitialContext ctx=new InitialContext();도 가능
			Context ctx=new InitialContext();
	//형식) datrasource객체명= (DataSource)ctx객체명.
			// lookup("java:comp/env/찾고자하는 jndi명")
		ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
		System.out.println("ds=>"+ds);
		}catch(Exception e) {
		    e.printStackTrace();	
		}
	}
   //public List<Board> list() {
	public ArrayList  list(){  //글목록보기
		
		ArrayList list = new ArrayList();
		try{
			String sql = "SELECT * FROM springboard ORDER BY num desc";
			//----------------------------------------------
			Connection con = ds.getConnection();
			//Connection con=pool.getConnection();
			//----------------------------------------------
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				Board data = new Board();
				data.setNum( rs.getInt( "num" ) );
				data.setAuthor(rs.getString( "author" ));
				data.setTitle(rs.getString( "title"));
				data.setContent(rs.getString( "content" ));
				data.setDate(rs.getString( "writeday" ));
				data.setReadcnt(rs.getInt( "readcnt" ));
				list.add( data );
			}//end while
			rs.close();	stmt.close(); con.close();
		}catch(Exception e){ e.printStackTrace(); }
		
		return  list;
	}//end list
	
	public int getNewNum(){ //게시물번호 구하기
		int newNum=1;//저장할 게시물번호 디폴트 설정값1
		try {
			String sql="select max(num) from springboard";
			Connection con=ds.getConnection();
			PreparedStatement pstmt=con.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery(sql);
			if(rs.next()) {
				newNum=rs.getInt(1)+1;
			}
		}catch(Exception e) {e.printStackTrace();}
		return newNum;
	}//end getNewNum();
	  
	//public void write(Board board) {~
	public void write(String author, String title , String content){
		try{
			int newNum = getNewNum();
			//?,?,? ->값을 입력을 했는지 안했는지 체크하기 어렵다
			String sql ="insert into springboard(num,author,title,content) values(";
			sql +=  newNum + ",'" + author + "','" + title + "','" + content + "')";
			System.out.println(sql);
			
	  	  	Connection con = ds.getConnection();
	  	  	PreparedStatement stmt = con.prepareStatement(sql);
	  	  	stmt.execute(sql);//stmt.executeUpdate(sql);
	  	  	stmt.close(); con.close();
	  	}catch(Exception e ) {e.printStackTrace();}
	}//end write
	//select * from springboard where num=4;
	//public List<Board>~
	public Board retrieve(String num){ // 상세보기 <-Retrieve~
		Board data=new Board();
		try {
			//1.조회수증가
			String sql="update springboard set readcnt=readcnt+1 where num="+num;
		    Connection con=ds.getConnection();
		    PreparedStatement pstmt=con.prepareStatement(sql);
		    int update=pstmt.executeUpdate(sql);
		    System.out.println("조회수 증가유무(update)="+update);
            pstmt=null;//전에 저장된 정보를 제거 
            //2.데이터찾기
            sql="select * from springboard where num="+num;
            pstmt=con.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()) {
            	data.setNum(rs.getInt("num"));
            	data.setAuthor(rs.getString("author"));
            	data.setTitle(rs.getString("title"));
            	data.setContent(rs.getString("content"));
            }
            rs.close(); pstmt.close(); con.close();
		}catch(Exception e) {e.printStackTrace();}
		return data;
	
	}//end retrieve
    //public void update(Board board){
	public void update( String num , String author, 
			            String title , String content){ // �� �����ϱ�
	     try{
		  String sql ="update springboard set title='" + title + "',";
		  sql += " content='" + content+"',";
		  sql += " author ='" + author+"'";
		  sql += " where num=" + num;
		  System.out.println(sql);//?을 써여된다.(보안때문에)

		  Connection con = ds.getConnection();
		  PreparedStatement stmt = con.prepareStatement(sql);  
		  int update=stmt.executeUpdate(sql);
		  System.out.println("게시물수정유무(update)=>"+update);
		  stmt.close();  con.close();
	     }catch(Exception e){e.printStackTrace();}
	  }//end update
      //delete from springboard where num=?(3)
	  public void delete( String num){ //글삭제하기
			try {
				String sql="delete from springboard where num="+num;
				Connection con=ds.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql);
				int delete=pstmt.executeUpdate(sql);
				System.out.println("delete 삭제유무(delete)=>"+delete);
				pstmt.close(); con.close();
			}catch(Exception e) {e.printStackTrace();}
		}//end delete
      //검색어를 입력받아 처리해주는 메서드(검색분야,검색어)
	  //         List search(~
	  public ArrayList search( String name , String value ){ //
		    ArrayList list = new ArrayList();
		    try{
		  	  String sql = "SELECT * FROM springboard";
			  sql += " WHERE  " + name + " LIKE  '%" + value + "%' "; 
			  System.out.println( sql );  //LIKE '%"홍길동"%'  
		  
			      Connection con = ds.getConnection();
		    	  PreparedStatement stmt = con.prepareStatement(sql);
		    	  ResultSet rs = stmt.executeQuery( sql );
		    	  while( rs.next()){
		    		Board data = new Board();
		    		data.setNum(rs.getInt( "num" ));
		    		data.setAuthor(rs.getString( "author" ));
		    		data.setTitle(rs.getString( "title"));
		    		data.setContent(rs.getString( "content" ));
		    		data.setDate(rs.getString( "writeday" ));
		    		data.setReadcnt(rs.getInt( "readcnt" ));
		    		list.add( data );
		    	  }
		    	  rs.close();	stmt.close(); con.close();
		    	}catch( Exception e){ e.printStackTrace();}
		    	return list;
    } 
}
*/
