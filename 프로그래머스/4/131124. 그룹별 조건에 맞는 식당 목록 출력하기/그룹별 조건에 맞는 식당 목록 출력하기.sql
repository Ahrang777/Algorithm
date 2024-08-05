-- 코드를 입력하세요
# SELECT M.MEMBER_NAME, 
#     REVIEW_TEXT, 
#     DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
# FROM MEMBER_PROFILE AS M
# JOIN REST_REVIEW AS R
# USING (MEMBER_ID)
# WHERE M.MEMBER_ID = (
#     SELECT MEMBER_ID
#     FROM REST_REVIEW
#     GROUP BY MEMBER_ID
#     ORDER BY COUNT(*) DESC
#     LIMIT 1
# )
# ORDER BY REVIEW_DATE ASC, REVIEW_TEXT ASC;


WITH ct AS(
    SELECT MEMBER_ID,
           DENSE_RANK() OVER(ORDER BY COUNT(MEMBER_ID) DESC) AS rk
    FROM REST_REVIEW 
    GROUP BY MEMBER_ID)

SELECT M.MEMBER_NAME,REVIEW_TEXT,
DATE_FORMAT(REVIEW_DATE,'%Y-%m-%d') REVIEW_DATE
FROM MEMBER_PROFILE AS M
INNER JOIN ct AS C ON C.MEMBER_ID=M.MEMBER_ID
INNER JOIN REST_REVIEW AS R ON M.MEMBER_ID=R.MEMBER_ID
WHERE rk=1
ORDER BY REVIEW_DATE,REVIEW_TEXT