DROP TABLE KOREABIKE;

--이름 주소 위도 경도 자건거갯수 
CREATE TABLE KOREABIKE(
	NAME VARCHAR2(1000) NOT NULL,
	ADDR VARCHAR2(2000) NOT NULL,
	LATITUDE NUMBER NOT NULL,
	LONGITUDE NUMBER NOT NULL,
	BIKE_COUNT NUMBER NOT NULL
);

SELECT NAME,ADDR, LATITUDE, LONGITUDE,BIKE_COUNT 
FROM KOREABIKE;

SELECT COUNT(*) FROM KOREABIKE;