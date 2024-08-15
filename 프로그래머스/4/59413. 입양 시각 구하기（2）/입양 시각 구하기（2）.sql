-- 코드를 입력하세요
WITH RECURSIVE CTE(HOUR) AS (
    SELECT 0
    
    UNION ALL
    
    SELECT HOUR + 1
    FROM CTE
    WHERE HOUR < 23
)


SELECT HOUR, COUNT(O.ANIMAL_ID) AS COUNT
FROM ANIMAL_OUTS AS O
RIGHT JOIN CTE AS C
ON HOUR(O.DATETIME) = C.HOUR
GROUP BY HOUR
ORDER BY HOUR ASC;