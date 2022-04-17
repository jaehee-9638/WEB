DROP TABLE BBS;
DROP SEQUENCE BBSSEQ;

CREATE SEQUENCE BBSSEQ;
CREATE TABLE BBS(
	USER_ID VARCHAR2(100) PRIMARY KEY,
	USER_PASSWORD VARCHAR2(100) NOT NULL,
	USER_NAME VARCHAR2(1000) NOT NULL,
	USER_GENDER VARCHAR2(4000) NOT NULL,
	USER_EMAIL VARCHAR2(1000) NOT NULL
	);
	
INSERT INTO BBS VALUES('gildong','123456','홍길동','남자','gildong@naver.com');

SELECT * FROM BBS ;


DROP TABLE BOARD;
DROP SEQUENCE BOARDSEQ;

CREATE SEQUENCE BOARDSEQ;
CREATE TABLE BOARD(
	BOARD_ID NUMBER PRIMARY KEY,
	BOARD_TITLE VARCHAR2(50) NOT NULL,
	USER_ID VARCHAR2(100) NOT NULL,
	BOARD_DATE DATE NOT NULL,
	BOARD_CONTENT VARCHAR2(2048) NOT NULL
	);
	
	INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL,'제목','아이디',SYSDATE,'내용입니다.');
--Available :현재글이 삭제되었는지 아닌지 볼수있는 항목 ,1이면 삭제x ,0이면 삭제된 글 
SELECT * FROM BOARD ;
