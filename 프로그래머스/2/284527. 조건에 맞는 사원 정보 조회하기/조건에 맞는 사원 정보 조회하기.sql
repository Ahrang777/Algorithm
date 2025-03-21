-- 코드를 작성해주세요
# SELECT SUM(SCORE) AS SCORE, G.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL
# FROM HR_EMPLOYEES E
# JOIN HR_GRADE G 
# ON E.EMP_NO = G.EMP_NO
# WHERE G.YEAR = '2022'
# GROUP BY YEAR, EMP_NO
# ORDER BY SCORE DESC
# LIMIT 1;

WITH EMPLOYEES AS (
    SELECT G.SCORE, E.EMP_NO, E.EMP_NAME, E.POSITION, E.EMAIL
    FROM HR_EMPLOYEES AS E
    JOIN (SELECT EMP_NO, SUM(SCORE) AS SCORE
         FROM HR_GRADE
         GROUP BY EMP_NO) AS G
    ON E.EMP_NO = G.EMP_NO
    ORDER BY G.SCORE DESC
) 

SELECT * FROM EMPLOYEES
LIMIT 1;