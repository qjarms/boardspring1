<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,lee.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 
              어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>자유게시판</title>
<!-- 부트스트랩 -->

<link href="./css/bootstrap.min.css" rel="stylesheet">
<style>
header {
	height: 100px;
	background-color: rgba(125, 211, 242, 0.5); /*투명도*/
	border-radius: 15px;
	padding: 15px;
	margin: 10px 0;
	font-family: 'Nanum Gothic', sans-serif;
	text-align: center;
}

footer {
	height: 150px;
	font-family: 'Nanum Gothic', sans-serif;
	border-radius: 15px;
	padding: 15px;
	margin: 10px 0;
	background-color: #B2E4E0;
	text-align: center;
}

li {
	list-style: none;
}

table, td, th {
    border: 1px solid black;
}
 
th {
    background: #F3F5F5;
}
 
table {
    margin-top: 5%;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    width: 80%;
}
 
a:link {
    color: red;
    text-decoration: none;
    cursor: pointer;
}
 
a:visited {
    color: black;
    text-decoration: none;
}
 
/* paginate */
.paginate {
    padding: 0;
    line-height: normal;
    text-align: center;
    position: relative;
    margin: 20px 0 20px 0;
    z-index: 1;
}
 
.paginate .paging {
    text-align: center;
}
 
.paginate .paging a, .paginate .paging strong {
    margin: 0;
    padding: 0;
    width: 20px;
    height: 24px;
    line-height: 24px;
    text-align: center;
    color: #848484;
    display: inline-block;
    vertical-align: middle;
    text-align: center;
    font-size: 12px;
}
 
.paginate .paging a:hover, .paginate .paging strong {
    color: #DAA520;
    font-weight: 600;
    font-weight: normal;
}
 
.paginate .paging .direction {
    z-index: 3;
    vertical-align: middle;
    background-color: none;
    margin: 0 2px;
    border: 1px solid #777;
    border-radius: 2px;
    width: 28px;
}
 
.paginate .paging .direction:hover {
    border: 1px solid #C40639;
}
 
.paginate .paging .direction.prev {
    margin-right: 4px;
}
 
.paginate .paging .direction.next {
    margin-left: 4px;
    cursor: pointer;
}
 
.paginate .paging img {
    vertical-align: middle;
}
 
.paginate .right {
    position: absolute;
    top: 0;
    right: 0;
}
 
.bottom-left, .bottom-right {
    position: relative;
    z-index: 5;
}
 
.paginate ~ .bottom {
    margin-top: -50px;
}
 
.bottom select {
    background: transparent;
    color: #aaa;
    cursor: pointer;
}
 
/* paginate */
.paginate {
    padding: 0;
    line-height: normal;
    text-align: center;
    position: relative;
    margin: 20px 0 20px 0;
}
 
.paginate .paging {
    text-align: center;
}
 
.paginate .paging a, .paginate .paging strong {
    margin: 0;
    padding: 0;
    width: 20px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    color: #999;
    display: inline-block;
    vertical-align: middle;
    text-align: center;
    font-size: 14px;
}
 
.paginate .paging a:hover, .paginate .paging strong {
    color: #C40639;
    font-weight: 600;
    font-weight: normal;
}
 
.paginate .paging .direction {
    z-index: 3;
    vertical-align: middle;
    background-color: none;
    margin: 0 2px;
}
 
.paginate .paging .direction:hover {
    background-color: transparent;
}
 
.paginate .paging .direction.prev {
    margin-right: 4px;
}
 
.paginate .paging .direction.next {
    margin-left: 4px;
}
 
.paginate .paging img {
    vertical-align: middle;
}
 
.paginate .right {
    position: absolute;
    top: 0;
    right: 0;
}
</style>
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js 
         IE의 버전이 낮은 경우에는 html5에 대한 태그가 인식X ->인식을 시켜주는 옵션
    -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<header>
			<h2 class="text-success">자유게시판(FreeBoard)</h2>
		</header>

		<!-- 상단 네비게이션   -->
		<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">navigation title</span>
				</button>
				<a class="navbar-brand glyphicon" href="main.jsp"> <span
					class="glyphicon glyphicon-home"></span>
				</a>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<!--  -->
					<li class="dropdown"><a data-toggle="dropdown" href="#">회원
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">회원로그인</a></li>
							<li><a href="#">마이페이지</a></li>
							<li><a href="#">장바구니</a></li>
						</ul></li>

					<!-- 드랍다운부분을 추가2  -->
					<li class="dropdown"><a data-toggle="dropdown" href="#">게시판
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="freeBoard.html">자유게시판</a></li>
							<li><a href="geulimBoard1.html">그림게시판</a></li>
						</ul></li>
					<!-- 드롭다운부분 추가3  -->
					<li class="dropdown"><a data-toggle="dropdown" href="#">이달의아이템
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">인테리어</a></li>
							<li><a href="#">생활용품</a></li>
							<li><a href="#">책상</a></li>
							<li><a href="#">의자</a></li>
							<li><a href="#">조명</a></li>
						</ul></li>
				</ul>
				<!--검색창-->
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control"
							placeholder="item name search">
					</div>
					<button type="button" class="btn btn-default">Submit</button>
				</form>
			</div>
		</nav>


		<!-- 게시판 리스트  -->
		<table class="table table-bordered table-hover">
			<colgroup>
				<col width="200px" height="100px">
			</colgroup>
			<thead>
				<tr>
					<td class="align-middle  text-align:center">번호</td>
					<td>글제목</td>
					<td>작성자</td>
					<td>게시글날짜</td>
					<td>조회수</td>
				</tr>
			</thead>
			<tbody>
				<!-- 추가  -->
				<%
				ArrayList list = (ArrayList) request.getAttribute("list2");//${list}
				if (list != null) {//데이터가 존재한다면
					Iterator iter = list.iterator();
					while (iter.hasNext()) {//꺼낼 데이터가 존재한다면
						//Board data=(Board)iter.next();//Object ->(Board)형변환
						BoardCommand data = (BoardCommand) iter.next();
						//-------------------------------------------------------
						int num = data.getNum();
						String title = data.getTitle();
						String author = data.getAuthor();
						//String writeday=data.getDate();//날짜출력 ->10글자뿐만 출력하라
						String writeday = data.getWriteday();
						//------------------------------------------------
						int readcnt = data.getReadcnt();//조회수
				%>

				<tr>
					<td align="center"><%=num%></td>
					<td><a href="retrieve.do?num=<%=num%>"><%=title%></a></td>
					<td><%=author%></td>
					<td><%=writeday.substring(0, 10)%></td>
					<td><%=readcnt%></td>
				</tr>

				<%
				} //end while
				} //end if
				%>
			</tbody>
		</table>

		<!-- 게시판리스트 끝  -->

		<!-- 버튼 -->
		<div class="text-center">
			<a href="write.jsp" class="btn btn-primary" role="button">글작성</a>
			<!--  <a href="update.jsp" class="btn btn-primary" role="button">글수정</a> -->
			<a href="Delete.html" class="btn btn-primary" role="button"
				onclick="alert('삭제페이지로 이동합니다')">글삭제</a>
		</div>



		<!-- 페이징 (이전,다음 게시물 이동) -->
		<!--paginate -->
<div class="paginate">
        <div class="paging">
            <a class="direction prev" href="javascript:void(0);"
                onclick="movePage(1,${pagination.cntPerPage},${pagination.pageSize});">
                &lt;&lt; </a> <a class="direction prev" href="javascript:void(0);"
                onclick="movePage(${pagination.currentPage}<c:if test="${pagination.hasPreviousPage == true}">-1</c:if>,${pagination.cntPerPage},${pagination.pageSize});">
                &lt; </a>
 
            <c:forEach begin="${pagination.firstPage}"
                end="${pagination.lastPage}" var="idx">
                <a
                    style="color:<c:out value="${pagination.currentPage == idx ? '#cc0000; font-weight:700; margin-bottom: 2px;' : ''}"/> "
                    href="javascript:void(0);"
                    onclick="movePage(${idx},${pagination.cntPerPage},${pagination.pageSize});"><c:out
                        value="${idx}" /></a>
            </c:forEach>
            <a class="direction next" href="javascript:void(0);"
                onclick="movePage(${pagination.currentPage}<c:if test="${pagination.hasNextPage == true}">+1</c:if>,${pagination.cntPerPage},${pagination.pageSize});">
                &gt; </a> <a class="direction next" href="javascript:void(0);"
                onclick="movePage(${pagination.totalRecordCount},${pagination.cntPerPage},${pagination.pageSize});">
                &gt;&gt; </a>
        </div>
    </div>
<br>
<div class="bottom">
        <div class="bottom-left">
            <select id="cntSelectBox" name="cntSelectBox"
                onchange="changeSelectBox(${pagination.currentPage},${pagination.cntPerPage},${pagination.pageSize});"
                class="form-control" style="width: 100px;">
                <option value="10"
                    <c:if test="${pagination.cntPerPage == '10'}">selected</c:if>>10개씩</option>
                <option value="20"
                    <c:if test="${pagination.cntPerPage == '20'}">selected</c:if>>20개씩</option>
                <option value="30"
                    <c:if test="${pagination.cntPerPage == '30'}">selected</c:if>>30개씩</option>
            </select>
        </div>
    </div>
    <!-- /paginate -->
		
		<!-- 검색 기능 -->
		<center>
			<tr>
				<td colspan="5" align="center">
					<form action="search.do">
						<select name="searchName" size="1">
							<option value="author">작성자</option>
							<option value="title">제목</option>
						</select> <input type="text" name="searchValue"><input
							type="submit" value="검색">
					</form>
				</td>
			</tr>
		</center>
		<!--  하단으로 정리  -->
		<footer class="footer">
			<li><a href="/commerce/agreement/">이용약관</a> <span
				class="division-line">|</span> <span
				onclick="location.href='mailto:info@osquarecorp.com';"
				style="cursor: pointer;">제휴문의</span> <span class="division-line">|</span>
				<a href="/partner/store_inquiry/">입점문의</a> <span
				class="division-line">|</span> <a href="/commerce/privacy/">개인정보취급방침</a>
			</li> <br>
			<li><span>조장:김은지</span> <span>조원:권상규</span> <span>조원:김태민</span>
				<span>조원:김범근</span></li> <i class="fab fa-java fa-2x"></i><i
				class="fab  fa-html5 fa-2x"></i><i class="fab fa-github-alt fa-2x"></i>
		</footer>

	</div>
	<script>
//10,20,30개씩 selectBox 클릭 이벤트
function changeSelectBox(currentPage, cntPerPage, pageSize){
    var selectValue = $("#cntSelectBox").children("option:selected").val();
    movePage(currentPage, selectValue, pageSize);
    
}
 
//페이지 이동
function movePage(currentPage, cntPerPage, pageSize){
    
    var url = "${pageContext.request.contextPath}/list.do";
    url = url + "?currentPage="+currentPage;
    url = url + "&cntPerPage="+cntPerPage;
    url = url + "&pageSize="+pageSize;
    
    location.href=url;
}
 
</script>
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>