DROP TABLE MYTEST;

CREATE TABLE MYTEST(
MNO NUMBER,
MNAME VARCHAR2(20),
NICKNAME VARCHAR(20)
);

SELECT MNO,MNAME,NICKNAME
FROM MYTEST;

INSERT INTO MYTEST
VALUES(1,'������','�����ھ�����');