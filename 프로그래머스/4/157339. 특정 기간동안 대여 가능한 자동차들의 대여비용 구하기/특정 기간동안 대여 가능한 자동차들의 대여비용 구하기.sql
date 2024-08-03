-- 코드를 입력하세요
# SELECT CAR.CAR_ID, CAR.CAR_TYPE, 
#     CAST(30 * CAR.DAILY_FEE * (1 - PLAN.DISCOUNT_RATE * 0.01) AS SIGNED) AS FEE
# FROM CAR_RENTAL_COMPANY_CAR AS CAR
# JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN AS PLAN
# USING (CAR_TYPE)
# WHERE CAR.CAR_ID NOT IN (
#     SELECT CAR_ID
#     FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
#     WHERE START_DATE >= '2022-11-01'
#         AND END_DATE <= '2022-11-30'
#     )
#     AND CAR.CAR_TYPE IN ('SUV', '세단') 
#     AND PLAN.DURATION_TYPE = '30일 이상'
#     AND 30 * CAR.DAILY_FEE * (1-PLAN.DISCOUNT_RATE * 0.01) >= 500000
#     AND 30 * CAR.DAILY_FEE * (1-PLAN.DISCOUNT_RATE * 0.01) < 2000000
# ORDER BY FEE DESC, CAR.CAR_TYPE ASC, CAR.CAR_ID DESC;












SELECT A.CAR_ID, A.CAR_TYPE, 
    CAST(A.DAILY_FEE*30*(1-B.DISCOUNT_RATE*0.01)AS SIGNED) AS FEE 
FROM CAR_RENTAL_COMPANY_CAR A JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN B
ON A.CAR_TYPE=B.CAR_TYPE
WHERE A.CAR_ID NOT IN (
    SELECT DISTINCT C.CAR_ID 
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY C 
    WHERE C.START_DATE<='2022-11-30' AND C.END_DATE>='2022-11-01'
)
AND A.CAR_TYPE IN('SUV', '세단') 
AND B.DURATION_TYPE = '30일 이상' 
AND A.DAILY_FEE*30*(1-B.DISCOUNT_RATE*0.01)>=500000 
AND A.DAILY_FEE*30*(1-B.DISCOUNT_RATE*0.01)<2000000
ORDER BY FEE DESC, CAR_TYPE ASC, CAR_ID DESC;
