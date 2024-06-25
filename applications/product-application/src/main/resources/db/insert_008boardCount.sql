-- Board count Sql
INSERT INTO board_count (product_id, board_cnt)
SELECT id, 0
FROM product
WHERE id BETWEEN 1 AND 709;