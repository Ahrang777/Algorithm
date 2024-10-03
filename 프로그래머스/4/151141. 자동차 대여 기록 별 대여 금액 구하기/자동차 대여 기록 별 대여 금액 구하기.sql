SELECT history_id,
       round(daily_fee * (datediff(end_date, start_date) + 1) * (100 - ifnull(discount_rate, 0)) /
             100, 0) fee
FROM (SELECT *,
             CASE
                 WHEN datediff(end_date, start_date) + 1 < 7 THEN NULL
                 WHEN datediff(end_date, start_date) + 1 < 30 THEN '7일 이상'
                 WHEN datediff(end_date, start_date) + 1 < 90 THEN '30일 이상'
                 ELSE '90일 이상'
                 END duration_type
      FROM car_rental_company_rental_history) a
         JOIN car_rental_company_car b
              ON a.car_id = b.car_id
         LEFT JOIN car_rental_company_discount_plan c
                   ON c.car_type = b.car_type AND a.duration_type = c.duration_type
WHERE b.car_type = '트럭'
ORDER BY 2 DESC, 1 DESC

# -- 코드를 입력하세요
# WITH TRUCK AS (
#     SELECT CAR_ID, DAILY_FEE
#     FROM CAR_RENTAL_COMPANY_CAR
#     WHERE CAR_TYPE = '트럭'
# ), TRUCK_DISCOUNT_PLAN AS (
#     SELECT DURATION_TYPE, DISCOUNT_RATE
#     FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
#     WHERE CAR_TYPE = '트럭'
# )

# SELECT 
#     HISTORY_ID, 
#     CASE
#         WHEN DATEDIFF(H.END_DATE, H.START_DATE) >= 90 THEN ROUND(DATEDIFF(H.END_DATE, H.START_DATE) * T.DAILY_FEE * (1 - ((SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT_PLAN WHERE DURATION_TYPE = '90일 이상') / 100)), 0)
#         WHEN DATEDIFF(H.END_DATE, H.START_DATE) >= 30 THEN ROUND(DATEDIFF(H.END_DATE, H.START_DATE) * T.DAILY_FEE * (1 - ((SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT_PLAN WHERE DURATION_TYPE = '30일 이상') / 100)), 0)
#         WHEN DATEDIFF(H.END_DATE, H.START_DATE) >= 7 THEN ROUND(DATEDIFF(H.END_DATE, H.START_DATE) * T.DAILY_FEE * (1 - ((SELECT DISCOUNT_RATE FROM TRUCK_DISCOUNT_PLAN WHERE DURATION_TYPE = '7일 이상') / 100)), 0)
#         ELSE DATEDIFF(H.END_DATE, H.START_DATE) * T.DAILY_FEE
#     END AS FEE
# FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY AS H
# JOIN TRUCK AS T
# USING (CAR_ID)


# ORDER BY FEE DESC, HISTORY_ID DESC;