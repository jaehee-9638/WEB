<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<script type="text/javascript">
			//페이지에 표시
			//alert("hello");	//페이지 경고창 로드 
			console.log("hello world");	//개발자도구 콘솔에서 확인 가능
			
			//Math함수
			document.write("<h1>Math.함수 형태로 사용하자 </h1>");
			document.write("1.1+2 => "+1.1+2 +" 정수,실수  숫자형 (구분안함 number)<br/>"); 
			document.write("Math.pow(3,2) => "+Math.pow(3,2)+" pow:제곱 Math:수학과 관련된 명령의 카테고리 3의 2승<br/>");
			document.write("Math.round(3.2) => "+Math.round(3.2)+" round : 반올림<br/>");
			document.write("Math.ceil(3.2) => "+Math.ceil(3.2)+" ceil : 가장가까운 위에 정수로 올려줌<br/>");
			document.write("Math.floor(3.8) => "+Math.floor(3.8)+" floor : 가장가까운 아래쪽 정수로 내려줌<br/>");
			document.write("Math.sqrt(9) => "+Math.sqrt(9)+" sqrt : 제곱근<br/> ");
			document.write("Math.random() => "+Math.random()+" random : 1보다 작은 소숫점의 랜덤한 숫자 <br/>");
			document.write("소수점 보기 싫으면 Math.round()로 감싸주자 ,,*100하면 100보다 작은 난수 <br/>");
			document.write("Math.round(100*Math.random()) => "+Math.round(100*Math.random())+"<br/>");
			
			document.write(typeof 1 +" typeof로 타입 확인 가능 <br/>");	
			document.write("code".length+" 문자열 뒤에 .length 하면 문자열 길이 확인 가능 <br/>");
			document.write("code".indexOf("d")+" 몇번재 인덱스에 있는지 확인<br/>");
			
			//변수,연산자 
			var a=1;
			document.write(a+1+"<br/>");
			var first ='coding'
			document.write(first+" "+'every'+"<br/>");
			//일치연산자 ===는 정확하게 같을때 true 정확하다는말은 type까지 비슷한 경우를 말한다.
			//동등연산자 ==는 type비교는 안함 
			document.write(NaN===NaN+"<br/>");	//NaN 계산할수없음 숫자가 아님 
			//https://dorey.github.io/JavaScript-Equality-Table/ 
			// 녹색은 true 흰색은 false(==과 ===의 차이점)
			
			//조건문 if (조건){참일때 실행} else if(조건) {참일때 실행} else {참일때 실행 }
			//prompt('당신의 나이는 '); //사용자의 입력을 받기 위한 창 
			//alert(prompt('당신의 나이는 ??')*2)	//프롬프트 확인 누르면 바로 새로운 alert창으로 내가 입력한 글 나옴 
			//여기서 바로 연산도 가능
			/* id= prompt("아이디를 입력하세요");
			if (id==="jaehee"){	alert('아이디가 일치 합니다.');
				var password=prompt("비밀번호를 입력하세요");
				if (password=="1234"){ alert("로그인 완료"+id+"님 반갑습니다.");
				}else {alert("로그인 실패(비밀번호 오류)")}
			}else {alert('아이디가 일치하지 않습니다.');} */
			//위에 코드를 다른방식으로 만들어보자 
			/* var id1=prompt("아이디를 입력해주세요");
			var password1=prompt("비밀번호를 입력해 주세요");
			if (id1=="haha" && password1=="12345"){alert("로그인 성공"+id1+"님 반갑습니다.");
			}else {alert("로그인 실패 ");} */
			//앞의 if문과 다른점은 비밀번호가 틀렸다 라는 오류문구는 내보낼 수 없음 
			//or는 하나만 true면 된당.
			
			//배열 34강부터 다시 듣자 
			var member =['egoing','kkkk','hahaha'];
			function get_member1(){
				return 'egoing';
			} 
			alert (get_member1());
			document.write(member[1].toUpperCase()+"<br/>");
			member = get_member1();
			
			
			//배열 만들기 
			var member2=['kk','ll','gg'];
			member2.push('bb');	//배열에 원소 1개 추가 
			document.write(member2+"<br/>");
			var plus = ['mm','hh'];
			var memberplus = member2.concat(plus);	//배열에 원소 여러개 추가
			document.write(memberplus+"<br/>");
			memberplus.unshift('pppp');	//배열에 앞쪽에 원소 추가 
			document.write(memberplus+"<br/>");
			memberplus.splice(2,0,'ttt','kkk'); 	//배열에 원하는위치에 원소추가 -> 삭제된걸 리턴도 해줌 
			//(위치,제거할 원소 갯수,넣을것)  넣을원소는 여러개 일수도 있음 
			
			
			
			document.write(memberplus+"<br/>");
			document.write(+"<br/>");
			document.write(+"<br/>");
			document.write(+"<br/>");
			document.write(+"<br/>");
			document.write(+"<br/>");
			
			
			
		</script>
</body>
</html>