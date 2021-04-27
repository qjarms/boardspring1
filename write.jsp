<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글작성</title>
    <!-- 부트스트랩 -->
    
    <link href="./css/bootstrap.min.css" rel="stylesheet">
    <style>
    header {height:100px;
                  background-color:rgba(125,211,242,0.5);/*투명도*/
                  border-radius:15px;padding:15px;margin:10px 0;
                  font-family:'Nanum Gothic',sans-serif;
                  text-align:center;       
       }
     footer{
         height:100px;
         font-family:'Nanum Gothic',sans-serif;
         border-radius:15px;padding:15px;margin:10px 0;
         background-color:#B2E4E0;
         text-align:center;  
     }  
      li {
        list-style:none;
     }
      table.table2 tr {
                 width: 50px;
                 padding: 10px;
                font-weight: bold;
                vertical-align: top;
                border-bottom: 1px solid #ccc;
        }
        table.table2 td {
                 width: 100px;
                 padding: 10px;
                 vertical-align: top;
                 border-bottom: 1px solid #ccc;
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
           <h2 class="text-success">글작성</h2>
       </header>
       
       <!-- 상단 네비게이션   -->
       <nav class="navbar navbar-default" role="navigation">
           <div class="navbar-header">
               <button type="button" class="navbar-toggle"
                                                   data-toggle="collapse"
                                                   data-target=".navbar-ex1-collapse">
                    <span class="sr-only">navigation title</span>                        
           </button>
           <a class="navbar-brand glyphicon" href="main.jsp">
               <span class="glyphicon glyphicon-home"></span>
           </a>
           </div>
           <div class="collapse navbar-collapse navbar-ex1-collapse">
              <ul class="nav navbar-nav">
              <!--  -->
                 <li class="dropdown">
                    <a data-toggle="dropdown" href="#">회원
                        <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                  <li><a href="#">회원로그인</a></li>
                  <li><a href="#">마이페이지</a></li>
                  <li><a href="#">장바구니</a></li>
                  </ul>    
                  </li>
              
              <!-- 드랍다운부분을 추가2  -->
              <li class="dropdown">
                    <a data-toggle="dropdown" href="#">게시판
                        <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                  <li><a href="freeBoard.html">자유게시판</a></li>
                  <li><a href="geulimBoard1.html">그림게시판</a></li>
                  </ul>    
                </li>
                <!-- 드롭다운부분 추가3  -->
                <li class="dropdown">
                    <a data-toggle="dropdown" href="#">이달의아이템
                        <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                  <li><a href="#">인테리어</a></li>
                  <li><a href="#">생활용품</a></li>
                  <li><a href="#">책상</a></li>
                  <li><a href="#">의자</a></li>
                  <li><a href="#">조명</a></li>
                  </ul>    
                  </li>
                </ul>
                <!--검색창-->
              <form class="navbar-form navbar-right" role="search">
                 <div class="form-group">
                    <input type="text" class="form-control" placeholder="item name search">
                 </div>
                     <button type="button" class="btn btn-default">Submit</button>
              </form>
           </div>
        </nav>
       
            
       <!--  글쓰기  -->
    <br>
     <form method = "get" action = "write.do">
        <table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >
                <tr>
                <td height=20 align= center bgcolor=#ccc><font color=white> 글쓰기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                        <tr>
                        <td>작성자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        <td><input type = text  name="author"  size=20> </td>
                        </tr>
 
                        <tr>
                        <td>제목</td>
                        <td><input type = text name ="title" size=60></td>
                        </tr>
 
                        <tr>
                        <td>내용</td>
                        <td><textarea name="content" cols=85 rows=15></textarea></td>
                        </tr>
 
                        </table>
                   
                        <center>
                        <input type="submit" value="글쓰기"/>
                        <input type="button" value="취소하기" action="list.jsp" />
                        </center>
                </td>
                </tr>
        </table>
        </form>
    
      <!--  하단으로 정리  -->
     <div class="text-center">
      <footer class="footer">
            <li>
               <a href="/commerce/agreement/">이용약관</a>
               <span class="division-line">|</span>
                <span onclick="location.href='mailto:info@osquarecorp.com';" style="cursor: pointer;">제휴문의</span>
                <span class="division-line">|</span>
                <a href="/partner/store_inquiry/">입점문의</a>
                <span class="division-line">|</span>
                <a href="/commerce/privacy/">개인정보취급방침</a>
             </li>
             <br>
             <li>
                <span>조장:김은지</span>
                <span>조원:권상규</span>
                <span>조원:김태민</span>
                <span>조원:김범근</span>
             </li>
                <i class="fab fa-java fa-2x"></i><i class="fab  fa-html5 fa-2x"></i><i class="fab fa-github-alt fa-2x"></i>
           </footer>
       
       </div>
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>