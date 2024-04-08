--NULL 처리
SELECT * FROM EMP;
SELECT COMM*1.1 FROM EMP;
SELECT NVL(COMM, 0) FROM EMP;	--NVL(해당열, 대체값)
SELECT NVL(COMM, 0)*1.2 FROM EMP;
SELECT NVL2(COMM, COMM*1.1, 0)	--NVL2(해당열,정상값,NULL일 경우 대체값) 
FROM EMP;
--DECODE 함수: 조건에 따라서 값을 선택
SELECT EMPNO, ENAME, JOB, SAL,
DECODE(JOB, 	--해당열
'MANAGER', SAL*1.1, 	--'MANAGER'가 적용되는 값
'SALESMAN', SAL*1.05,	--'SALESMAN'가 적용되는 값
'ALALYST', SAL, 		--'ALALYST'가 적용되는 값
SAL*1.03) AS UPSAL		--그외 나머지 JOB이 적용되는 값
FROM EMP;
SELECT EMPNO, ENAME, JOB, SAL,
CASE JOB
WHEN 'MANAGER' THEN SAL*1.1
WHEN 'SALESMAN' THEN SAL*1.5
WHEN 'ALALYST' THEN SAL
ELSE SAL*1.03
END AS UPSAL
FROM EMP;

--행 제한하기
SELECT * FROM EMP;
SELECT ROWNUM FROM EMP;

--다중함수(집계함수): SUM, MIN, MAX, COUNT, AVG
SELECT * FROM EMP;
SELECT COUNT(ENAME) FROM EMP;
SELECT COUNT(COMM) FROM EMP;
SELECT COUNT(DEPTNO) FROM EMP WHERE DEPTNO = 30;
SELECT SUM(COMM) FROM EMP;
SELECT SUM(SAL) FROM EMP;
SELECT SUM(DISTINCT SAL),	--중복제거
	   SUM(ALL SAL),		--중복포함전체
	   SUM(SAL)				--중복포함전체
FROM EMP;
SELECT COUNT(SAL),
	   COUNT(ALL SAL),
	   COUNT(DISTINCT SAL)
FROM EMP;
-----------------------------------------------------------
SELECT MAX(SAL), MIN(SAL) FROM EMP WHERE DEPTNO=10;
--20번 부서에서 신입과 최고참의 입사일조회
SELECT * FROM EMP;
SELECT MAX(HIREDATE), MIN(HIREDATE) FROM EMP WHERE DEPTNO=20;
--30번 부서 월급 평균
SELECT AVG(SAL) FROM EMP WHERE DEPTNO = 30;
--GROUP BY
SELECT AVG(SAL), '10' AS DEPTNO FROM EMP WHERE DEPTNO = 10
UNION
SELECT AVG(SAL), '20' AS DEPTNO FROM EMP WHERE DEPTNO = 20
UNION
SELECT AVG(SAL), '30' AS DEPTNO FROM EMP WHERE DEPTNO = 30;

SELECT AVG(SAL), DEPTNO FROM EMP GROUP BY DEPTNO;
SELECT JOB ,DEPTNO ,AVG(SAL) FROM EMP 
GROUP BY DEPTNO, JOB
ORDER BY DEPTNO, JOB;
--코딩순서 SELECT ~ FROM ~ WHERE ~ GROUP BY ~ ORDER BY ~ ;
--실행순서 FROM ~ WHERE ~ GROUP BY ~ SELECT ~ ORDER BY ~ ;
SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
--WHERE AVG(SAL) >= 2000		--WHERE에서는 집계함수를 사용할 수 없다
GROUP BY DEPTNO, JOB
HAVING AVG(SAL) >= 2000
ORDER BY DEPTNO, JOB;

SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
GROUP BY DEPTNO, JOB
HAVING AVG(SAL) >= 2000			--WHERE 대신 HAVING을 사용
ORDER BY DEPTNO, JOB;

SELECT DEPTNO, JOB, AVG(SAL)
FROM EMP
WHERE SAL <= 3000				--단독으로는 사용가능
GROUP BY DEPTNO, JOB
HAVING AVG(SAL) >= 2000	
ORDER BY DEPTNO, JOB;

--JOIN(두개이상의 테이블을 연결하여 하나의 테이블로 만듦)
SELECT * FROM DEPT;
SELECT * FROM EMP;
--FROM으로 테이블로 조인
SELECT * FROM EMP , DEPT;
--WHERE에서 열 이름을 비교하는 조건식으로 조인
SELECT * FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
ORDER BY EMPNO;

SELECT EMP.EMPNO, EMP.ENAME, DEPT.DEPTNO, DEPT.DNAME 
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
ORDER BY DEPT.DEPTNO, EMP.EMPNO;	--등가조인

SELECT EMP.EMPNO, EMP.ENAME , DEPT.DEPTNO, DEPT.DNAME
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO AND SAL >= 3000;	--조인에 조건식추가

SELECT * FROM EMP e,SALGRADE s
WHERE e.SAL BETWEEN s.LOSAL AND s.HISAL;

SELECT e.EMPNO, e.ENAME, e.MGR,
e2.EMPNO AS MGR_EMPNO, e2.ENAME AS MGR_ENAME
FROM EMP e , EMP e2
WHERE e.MGR(+) = e2.EMPNO 
ORDER BY e.EMPNO;
SELECT MGR FROM EMP e;
SELECT EMPNO FROM EMP e;

SELECT e.EMPNO, e.ENAME, e.JOB , e.MGR, e.HIREDATE , e.SAL , 
e.COMM, DEPTNO, d.DNAME, d.LOC 
FROM EMP e NATURAL JOIN DEPT d;
--JOIN ON
--INNER JOIN
SELECT * FROM EMP;
SELECT *
FROM EMP e JOIN DEPT d ON(e.DEPTNO = d.DEPTNO)
WHERE SAL >= 3000
ORDER BY e.DEPTNO , e.EMPNO;
--OUTER JOIN
SELECT e.EMPNO, e.ENAME, e.MGR,
e2.EMPNO AS MGR_EMPNO, e2.ENAME AS MGR_ENAME
FROM EMP e LEFT OUTER JOIN EMP e2 --왼쪽 외부조인 MGR
ON(e.MGR = e2.EMPNO);

SELECT e.EMPNO, e.ENAME, e.MGR,
e2.EMPNO AS MGR_EMPNO, e2.ENAME AS MGR_ENAME
FROM EMP e RIGHT OUTER JOIN EMP e2 --왼쪽 외부조인 MGR
ON(e.MGR = e2.EMPNO);

SELECT e.EMPNO, e.ENAME, e.MGR,
e2.EMPNO AS MGR_EMPNO, e2.ENAME AS MGR_ENAME
FROM EMP e FULL OUTER JOIN EMP e2 ON(e.MGR = e2.EMPNO);

--서버쿼리
SELECT * FROM EMP e;
SELECT ROWNUM, E.* FROM EMP e;

SELECT * FROM (SELECT ROWNUM, E.* FROM EMP e)
WHERE ROWNUM BETWEEN 1 AND 5;
SELECT * FROM (SELECT ROWNUM N, E.* FROM EMP e)
WHERE N BETWEEN 6 AND 10;

--급여를 내림차순으로 정렬한 다음 상위 5명 정보출력
SELECT ENAME ,SAL FROM (SELECT * FROM EMP e ORDER BY SAL DESC)
WHERE ROWNUM BETWEEN 1 AND 5;
--SCOTT보다 높은 급여를 받는 사원 출력
--SALLEN의 추가 수당보다 높은 추가수당을 받는 사원
SELECT ENAME ,SAL FROM (SELECT * FROM EMP e ORDER BY SAL DESC)
WHERE ROWNUM BETWEEN 1 AND 3;

SELECT ENAME, COMM FROM
(SELECT * FROM EMP e ORDER BY COMM)
WHERE ROWNUM BETWEEN 1 AND 4;

--1. EMP 테이블에서 부서 인원이 4명보다 많은 부서의 부서번호,인원,급여의 합
SELECT EMPNO, DEPTNO, SAL FROM EMP e ORDER BY DEPTNO;

SELECT COUNT(DEPTNO) AS "인원" ,
SUM(SAL) AS "급여",
SUM(SAL)+SUM(DEPTNO)+SUM(EMPNO) AS "값"
FROM EMP e
GROUP BY DEPTNO 
HAVING COUNT(DEPTNO) > 4;

--2. EMP 테이블에서 가장 많은 사원이 속해있는 부서번호와 사원수 출력
SELECT EMPNO, DEPTNO, SAL FROM EMP e ORDER BY DEPTNO;

SELECT DEPTNO, COUNT(DEPTNO) AS "인원"
FROM EMP e
GROUP BY DEPTNO 
ORDER BY COUNT(*) DESC
;