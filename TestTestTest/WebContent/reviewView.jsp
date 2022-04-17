<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>지오코드 테스트</title>
  <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=0d68crwih8"></script>
</head>
<body>

<div id="map" style="width:100%;height:400px">
	
</div>

 <script type="text/javascript">
 
 var HOME_PATH = window.HOME_PATH || '.';

 var cityhall = new naver.maps.LatLng(37.5666805, 126.9784147),
     map = new naver.maps.Map('map', {
         center: cityhall.destinationPoint(0, 500),
         zoom: 15
     }),
     marker = new naver.maps.Marker({
         map: map,
         position: cityhall
     });

 var contentString = [
         '<div class="iw_inner">',
         '   <h3>서울특별시청</h3>',
         '   <p>서울특별시 중구 태평로1가 31 | 서울특별시 중구 세종대로 110 서울특별시청<br />',
         '       <img src="'+ HOME_PATH +'/img/example/hi-seoul.jpg" width="55" height="55" alt="서울시청" class="thumb" /><br />',
         '       02-120 | 공공,사회기관 &gt; 특별,광역시청<br />',
         '       <a href="http://www.seoul.go.kr" target="_blank">www.seoul.go.kr/</a>',
         '   </p>',
         '</div>'
     ].join('');

 var infowindow = new naver.maps.InfoWindow({
     content: contentString
 });

 naver.maps.Event.addListener(marker, "click", function(e) {
     if (infowindow.getMap()) {
         infowindow.close();
     } else {
         infowindow.open(map, marker);
     }
 });

 infowindow.open(map, marker);</script>

</body>
</html>