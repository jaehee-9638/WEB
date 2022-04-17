<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/css/bootstrap.css">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.js"></script>

<style type="text/css">
        * {
            padding: 1px;
            margin: 1px;
            width: auto;
            align-content: center;
        }
        
        header {
            height: 30%
        }
        
        #body {
            margin-top: 150px;
            height: 600px;
            background-color: #E9E9E9;
            border: 1px solid #CFCECE;
        }
        </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
	
	
<div class="container ">
        <div class="row">
    
            <div class="col-md-5 col-md-offset-7 text-center" id="body">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 " style="margin-top:100px;">
                        <img src="resources/image/logo-banner.png" class="img-responsive"
                            alt="Responsive image">
    
    
                        <div class="form-group">
    
                            <div class="col-sm-12">
                                <input type="text" class="form-control" id="inputEmail3"
                                    placeholder="아이디를 입력하세요">
                            </div>
                            <div align="center" id="loginChk" style="color: red;"></div>
                        </div>
                        <div class="form-group">
    
                            <div class="col-sm-12">
                                <input type="password" class="form-control" id="inputPassword3"
                                    placeholder="비밀번호를 입력하세요">
                            </div>
                        </div>
    
                        <div class="form-group">
                            <div class="col-sm-12">
                                <br />
                                <button onclick="login();" type="button"
                                    class="btn btn-info btn-block">로그인</button>
                            </div>
                            <br /> <br /> <br /> <br />
                            <p>
                                계정이 없으신가요?
                                <button onclick="location.href='registform.do'" type="button"
                                    class="btn btn-link">회원가입</button>
                            </p>
                            <a href="findidform.do"><button type="button"
                                    class="btn btn-link">아이디 찾기</button></a> <a href="findpwform.do"><button
                                    type="button" class="btn btn-link">비밀번호 찾기</button></a> <br> <br>
                            <div class="loginform" style="align-content: center;">
                                <a href="javascript:kakaoLogin();"><img
                                    src="resources/image/kakaolog.png" alt="Kakao image"
                                    style="height: inherit; width: inherit; margin-right: 10px;"></a>
                                <br>
                                <form class="form-horizontal" action="naverlogin.do">
                                    <a href="${naverUrl }"><img
                                        src="resources/image/naverlog.png" alt="Naver image"
                                        style="height: 35%; width: 30%; margin-right: 10px;"></a>
                                </form>
                                <div id="googleBtn" class="g-signin2" data-onsuccess="onSignIn"></div>
                            </div>
                        </div>
    
                    </div>
                </div>
            </div>
        </div>
    
    </div>



여기에 로그인 화면 처음 만들고 회원가입하기 버튼 만들자 

</body>
</html>