-- 코드를 입력하세요
WITH REST AS(
    SELECT REST_ID, FOOD_TYPE
    FROM REST_INFO
    WHERE (FOOD_TYPE, FAVORITES) IN 
        (SELECT FOOD_TYPE, MAX(FAVORITES) FROM REST_INFO GROUP BY FOOD_TYPE)
    GROUP BY REST_ID, FOOD_TYPE
)
    
SELECT R.FOOD_TYPE, R.REST_ID, I.REST_NAME, I.FAVORITES
FROM REST_INFO I
JOIN REST R
ON I.REST_ID = R.REST_ID
ORDER BY R.FOOD_TYPE DESC;
