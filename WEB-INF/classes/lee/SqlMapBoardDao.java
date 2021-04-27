package lee;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

//SqlSessionDaoSupport -> getSqlSession() 상속 ->SqlSession
public class SqlMapBoardDao extends SqlSessionDaoSupport implements BoardDAO {

	//SqlSession sqlsession; ->DI(생성자 or Setter Method를 사용) ->소스코드 절약
	@Override
	public List list() throws DataAccessException {
		// TODO Auto-generated method stub
		//select ->레코드 한개 ->queryForObject()->selectOne()(Mybatis)
		//select ->레코드 한개이상->queryForList() ->selectList("실행시킬 sql의 id")
		return getSqlSession().selectList("list");
	}
	
	@Override
	public int getNewNum() throws DataAccessException {
		// TODO Auto-generated method stub
		//레코드 한개(SelectOne),특정필드의 자료형(Wrapper)
		//형식) selectOne("실행시킬 sql의 id값",매게변수 전달할값 #{매개변수명}
		//Object ->Integer ->int
		return (Integer)getSqlSession().selectOne("getNewNum");//실행할 sql구문 
	}
	
	@Override
	public void write(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)sqlSession객체명.insert("실행시킬 sql구문의 id",전달할 매개변수명)
		getSqlSession().insert("write",data);//getter Method를 호출=>각각 저장
	}
	
	@Override
	public void updateReadcnt(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)sqlSession객체명.update("실행시킬 구문의 id",매개변수)
		getSqlSession().update("updateReadcnt",num);
	}
	
	@Override
	public BoardCommand retrieve(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식) sqlSession객체명.selectOne("실행시킬 구문의id",매개변수)
		//Object ->BoardCommand
		return (BoardCommand)getSqlSession().selectOne("retrieve",num);
	}
	
	//수정하기
	@Override
	public void update(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		getSqlSession().update("update",data);//#{title},#{content}...
	}
	
	//삭제하기
	@Override
	public void delete(String num) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)sqlSession객체명.delete("실행구문id",매개변수)
		getSqlSession().delete("delete",num);
	}
	
	//검색하기
	@Override
	public List search(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//selectOne() ->레코드 한개 또는 필드 한개의 자료형을 얻어올때
		return getSqlSession().selectList("search",data);
	}
	
	//매개변수없는 전체리스트 
	@Override
	public List getBoardList() throws DataAccessException {
		return getSqlSession().selectList("getBoardList");
	}
	
	//페이지네이션 
	@Override
	public List getBoardList2(Pagination pagination) throws DataAccessException {
		return getSqlSession().selectList("getBoardList2", pagination);
	}
	
	//토탈
	@Override
	public int getBoardTotalCnt() throws DataAccessException {
		
		return (Integer)getSqlSession().selectOne("getBoardTotalCnt");
	}

}
