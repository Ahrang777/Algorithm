-- 코드를 작성해주세요
WITH FISH_MAX_SIZE AS (
    SELECT FISH_TYPE, MAX(LENGTH) AS MAX_SIZE
    FROM FISH_INFO
    GROUP BY FISH_TYPE
)

SELECT FISH.ID, NAME.FISH_NAME, FISH.LENGTH
FROM FISH_INFO AS FISH
JOIN FISH_NAME_INFO AS NAME
ON FISH.FISH_TYPE = NAME.FISH_TYPE
JOIN FISH_MAX_SIZE MAX_SIZE
ON MAX_SIZE.FISH_TYPE = FISH.FISH_TYPE
WHERE FISH.FISH_TYPE = MAX_SIZE.FISH_TYPE AND FISH.LENGTH = MAX_SIZE.MAX_SIZE
ORDER BY ID ASC;
