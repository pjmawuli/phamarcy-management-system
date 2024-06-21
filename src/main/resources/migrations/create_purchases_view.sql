CREATE VIEW purchases_view AS (
    SELECT
        purchases.*,
        drugs.name AS drug_name,
        drugs.drug_code AS drug_code,
        drugs.quantity AS drug_quantity,
        drugs.price AS drug_price,
        drugs.supplier_id AS drug_supplier_id,
        drugs.created_at AS drug_created_at,
        drugs.updated_at AS drug_updated_at
    FROM purchases
    JOIN drugs
    ON purchases.drug_id = drugs.id
);