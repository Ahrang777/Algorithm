-- 코드를 작성해주세요
SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID IN (
    SELECT TREE.ITEM_ID
    FROM ITEM_INFO INFO
    JOIN ITEM_TREE TREE
    ON INFO.ITEM_ID = TREE.PARENT_ITEM_ID
    WHERE INFO.RARITY = 'RARE'
)
ORDER BY ITEM_ID DESC;
