-- 코드를 입력하세요
SELECT O.ANIMAL_ID, O.NAME
FROM ANIMAL_OUTS AS O
JOIN ANIMAL_INS AS I
USING (ANIMAL_ID)
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME ASC;