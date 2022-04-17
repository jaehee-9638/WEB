DROP SEQUENCE MVCBOARDSEQ;
DROP TABLE MVCBOARD;

CREATE SEQUENCE MVCBOARDSEQ;
CREATE TABLE MVCBOARD (
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(100) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);

INSERT INTO MVCBOARD
VALUES(MVCBOARDSEQ.NEXTVAL,'작성자','제목','내용',SYSDATE);

SELECT * FROM MVCBOARD ORDER BY SEQ DESC;