# SELECT history_id,
#        round(daily_fee * (datediff(end_date, start_date) + 1) * (100 - ifnull(discount_rate, 0)) /
#              100, 0) fee
# FROM (SELECT *,
#              CASE
#                  WHEN datediff(end_date, start_date) + 1 < 7 THEN NULL
#                  WHEN datediff(end_date, start_date) + 1 < 30 THEN '7일 이상'
#                  WHEN datediff(end_date, start_date) + 1 < 90 THEN '30일 이상'
#                  ELSE '90일 이상'
#                  END duration_type
#       FROM car_rental_company_rental_history) a
#          JOIN car_rental_company_car b
#               ON a.car_id = b.car_id
#          LEFT JOIN car_rental_company_discount_plan c
#                    ON c.car_type = b.car_type AND a.duration_type = c.duration_type
# WHERE b.car_type = '트럭'
# ORDER BY 2 DESC, 1 DESC

# WITH CTE AS (
#     SELECT DISCOUNT_RATE, DURATION_TYPE	
#     FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
#     WHERE CAR_TYPE = '트럭'
# )
# SELECT H.HISTORY_ID, 
# CONVERT((C.DAILY_FEE * H.DATE ) / 100 * (100 -
# (CASE
#     WHEN H.DATE >= 90 THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '90일 이상')
#     WHEN H.DATE >= 30 THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '30일 이상')
#     WHEN H.DATE >= 7  THEN (SELECT DISCOUNT_RATE FROM CTE WHERE DURATION_TYPE = '7일 이상')
#     ELSE 0 
# END)), UNSIGNED) FEE 
# FROM 
# CAR_RENTAL_COMPANY_CAR C JOIN (SELECT *, (DATEDIFF(END_DATE, START_DATE) + 1) DATE
#                                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY) H
# ON C.CAR_ID = H.CAR_ID 
# WHERE C.CAR_TYPE = '트럭'
# ORDER BY 2 DESC, 1 DESC




-- 코드를 입력하세요
WITH TRUCK_DISCOUNT AS (
    SELECT DURATION_TYPE, DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE CAR_TYPE = '트럭'
), HISTORY AS (
    SELECT HISTORY_ID, CAR_ID, DATEDIFF(END_DATE, START_DATE) + 1 AS DATE
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
)


SELECT 
    HISTORY_ID,
    CONVERT((C.DAILY_FEE * H.DATE ) / 100 * (100 -
        (CASE  
            WHEN H.DATE >= 90 THEN (SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT WHERE DURATION_TYPE = '90일 이상')
            WHEN H.DATE >= 30 THEN (SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT WHERE DURATION_TYPE = '30일 이상')
            WHEN H.DATE >= 7 THEN (SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT WHERE DURATION_TYPE = '7일 이상')
            ELSE 0
         END)), UNSIGNED) AS FEE
FROM CAR_RENTAL_COMPANY_CAR AS C
JOIN HISTORY AS H
USING (CAR_ID)
WHERE C.CAR_TYPE = '트럭'
ORDER BY FEE DESC, HISTORY_ID DESC;

