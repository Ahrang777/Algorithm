-- 코드를 입력하세요
SELECT f.flavor "FLAVOR"
from first_half f
join (select flavor, sum(total_order) as july_total_order
     from july
     group by flavor) j
on f.flavor = j.flavor
order by f.total_order + july_total_order desc
limit 3;



-- 7월 총 주문량 + 상반기 총 주문량 큰 순서대로 조회

-- first_half >> flavor: pk, shipment_id: fk
-- july >> shipment_id: pk, flavor: fk