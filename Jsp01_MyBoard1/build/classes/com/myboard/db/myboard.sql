DROP TABLE MYBOARD;
DROP SEQUENCE MYBOARDSEQ;

CREATE SEQUENCE MYBOARDSEQ;
CREATE TABLE MYBOARD(
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(100) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);


INSERT INTO MYBOARD VALUES(MYBOARDSEQ.NEXTVAL,'고양이','배고프다','간식주세요',SYSDATE);

SELECT * FROM MYBOARD ORDER BY SEQ DESC;