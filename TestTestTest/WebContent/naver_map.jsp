<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <!-- 서브모듈 로드하기 -->
  <!--  <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0d68crwih8"></script> -->
  <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0d68crwih8&submodules=geocoder"></script>
<style type="text/css">
.search { position:absolute;z-index:1000;top:20px;left:20px; }
.search #address { width:150px;height:20px;line-height:20px;border:solid 1px #555;padding:5px;font-size:12px;box-sizing:content-box; }
.search #submit { height:30px;line-height:30px;padding:0 10px;font-size:12px;border:solid 1px #555;border-radius:3px;cursor:pointer;box-sizing:content-box; }
</style>
</head>

<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!-- https://www.youtube.com/watch?v=CHgyoAYAnAE -->
<!-- 37.4807892,126.734259 -->
<div class="row" style="padding-top:30px;">

<div id="wrap" class="section col-md-7 col-md-offset-1" >  
    <div id="map" style="width:100%;height:850px;">
        <div class="search" style="">
            <input id="address" type="text" placeholder="검색할 주소" value="불정로 6" />
            <input id="submit" type="button" value="주소 검색" />
        </div>
    </div>
    <code id="snippet" class="snippet"></code>
</div>
	

<div id="serch" class="col-md-3" style="background-color:#fffff0; height:850px;" >
	<div class="row"> 경로 조회하기
	지금 내가 하는게 뭐냐면 출발 ,도착점 찍어서 경로 조회 
	그럴려면 출발점 도착점 한번에 넣어주자 그래서 input하깢?
			<!-- 위치 조회  -->
		  <form class="form-inline col-md-8 col-md-offset-2" style="padding-top:10px;">
		    <input type="text" class="form-control" placeholder="검색">
		    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
		   
		  </form>
		  
		  <!-- 출발지점 -->
		  <form class="form-inline col-md-8 col-md-offset-2" style="padding-top:10px;">
		    <input type="text" class="form-control" placeholder="출발">
		    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
		  </form>
		  <!-- 도착지점 -->
		  <form class="form-inline col-md-8 col-md-offset-2" style="padding-top:10px;">
		    <input type="text" class="form-control" placeholder="도착">
		    <button type="button" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
		  </form>
		  <div class="col-md-8 col-md-offset-2" style="padding-top:10px;">
		    <button type="button" class="btn btn-primary btn-block" >검색</button>
		  </div>
		  
		   
		  
	</div>
	
	
	
</div>
</div>
<article>

<script id="code">
/**
 * 스크립트 로드
 * <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=YOUR_CLIENT_ID&submodules=geocoder"><\/script>
 */


//접속시 가장먼저 보이는 곳 좌표  ..
var map = new naver.maps.Map("map", {
  center: new naver.maps.LatLng(37.3595316, 127.1052133),
  zoom: 15,
  mapTypeControl: true
});
//지도 위에 올리는 정보 창을 정의
var infoWindow = new naver.maps.InfoWindow({
  anchorSkew: true
});
//마우스 오버 시 포인터의 모양을 설정
map.setCursor('pointer');

//클릭하면 나오는 함수 
function searchCoordinateToAddress(latlng) {	//latlng = 위,경도 좌표계를 나타낸다

  infoWindow.close();
//reverseGeocode(options, callback) 
  naver.maps.Service.reverseGeocode({		//특정 좌표에 해당하는 주소를 반환하는 reversegeocode API를 호출합니다.,,안에있는애들이 parameter
    coords: latlng,		//주소 검색을 위한 입력 좌표
    orders: [		//변환 작업 이름
      naver.maps.Service.OrderType.ADDR,		//변환 작업 이름.좌표를 행정동으로 나타냅니다
      naver.maps.Service.OrderType.ROAD_ADDR	//변환 작업 이름.좌표를 지번 주소로 나타냅니다
    ].join(',')
    //status:응답 결과의 상태 코드 response:응답 본문
  }, function(status, response) {	//결과반환시 실행할 callback함수
    if (status === naver.maps.Service.Status.ERROR) {	//응답상태가 요청이 실패한 상태면 
      if (!latlng) {	//위경도 자표계 아니면 출력 ,,클릭하면 지금 여기서 오류남 
        return alert('ReverseGeocode Error, Please check latlng');
      }
      if (latlng.toString) {	
        return alert('ReverseGeocode Error, latlng:' + latlng.toString());	//여기
      }
      if (latlng.x && latlng.y) {
        return alert('ReverseGeocode Error, x:' + latlng.x + ', y:' + latlng.y);	//여기
      }
      return alert('ReverseGeocode Error, Please check latlng');
    }
	//address : 개별 주소의 전체 텍스트 
    var address = response.v2.address,	//v2: API의 응답 내용을 담고 있는 컨테이너.
        htmlAddresses = [];

    if (address.jibunAddress !== '') {	//지번주소가 null이 아닐때 
        htmlAddresses.push('[지번 주소] ' + address.jibunAddress);		//push
    }

    if (address.roadAddress !== '') {	//도로명주소가 null이 아닐때 
        htmlAddresses.push('[도로명 주소] ' + address.roadAddress);		//push
    }
    //지도 클릭시 나오는 창 : 지번주소, 도로명 주소 나옴 
    infoWindow.setContent([	
      '<div style="padding:10px;min-width:200px;line-height:150%;">',
      '<h4 style="margin-top:5px;">검색 좌표</h4><br />',
      htmlAddresses.join('<br />'),
      '</div>'
    ].join('\n'));

    infoWindow.open(map, latlng);
  });
}

//검색하면 나오는 화면 지금 검색기능 구현안되서 확인 불가 
function searchAddressToCoordinate(address) {
  naver.maps.Service.geocode({	//이 메서드를 사용해서 주소로 좌표를 검색하는 geocode api를 호출 할수있음 
    query: address
    //오류안내 geocode에러  ,geocode는 서브모듈이고 주소->좌표검색 , 좌표->주소검색 api호출 가능 함 
  }, function(status, response) {
    if (status === naver.maps.Service.Status.ERROR) {
      if (!address) {	//개별 주소의 전체 텍스트 없을때 
        return alert('Geocode Error, Please check address');
      }
      return alert('Geocode Error, address:' + address);
    }
	
    if (response.v2.meta.totalCount === 0) {
      return alert('No result.');
    }

    var htmlAddresses = [],
      item = response.v2.addresses[0],	//addresses : 주소 검색 결과 목록 ,,배열형태임 
      point = new naver.maps.Point(item.x, item.y);	//포인트의 키벨류가 x:경도 y:위도 이런식임 

    if (item.roadAddress) {	
      htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
    }

    if (item.jibunAddress) {
      htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
    }

    if (item.englishAddress) {
      htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
    }

    infoWindow.setContent([		//.setContent : 정의하고 매핑할 컨텐츠 
      '<div style="padding:10px;min-width:200px;line-height:150%;">',
      '<h4 style="margin-top:5px;">검색 주소 : '+ address +'</h4><br />',
      htmlAddresses.join('<br />'),
      '</div>'
    ].join('\n'));

    map.setCenter(point);
    infoWindow.open(map, point);
  });
}

//
function initGeocoder() {
  if (!map.isStyleMapReady) {
    return;
  }

  map.addListener('click', function(e) {
    searchCoordinateToAddress(e.coord);
  });
	//이벤트의 연결 
  $('#address').on('keydown', function(e) {		// 실행하고자 하는 jQuery 코드 ,address요소에 keydown이벤트 하면 function 실행 ???
    var keyCode = e.which;	//witch 키코드를 받을수있는 애
	
    if (keyCode === 13) { // Enter Key  검색기능할때 엔터누르면
      searchAddressToCoordinate($('#address').val());	//검색 함수 호출하고 address의 value값으로 호출 
    }
  });
	//얘는 검색창에서 검색 버튼 누르면 이벤트 
  $('#submit').on('click', function(e) {
    e.preventDefault();	//a 태그나 submit 태그는 고유의 동작이 있다. 페이지를 이동시킨다거나 form 안에 있는 input 등을 전송한다던가 그러한 동작이 있는데 e.preventDefault 는 그 동작을 중단시킨다.

    searchAddressToCoordinate($('#address').val());
  });

  searchAddressToCoordinate('정자동 178-1');
}

naver.maps.onJSContentLoaded = initGeocoder;
naver.maps.Event.once(map, 'init_stylemap', initGeocoder);

</script>

</article>
</body>
</html>