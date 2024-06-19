CREATE VIEW drugs_view AS (
    SELECT
        drugs.*,
        suppliers.name AS supplier_name,
        suppliers.contact AS supplier_contact,
        suppliers.location AS supplier_location,
        suppliers.created_at AS supplier_created_at,
        suppliers.updated_at AS supplier_updated_at
    FROM drugs
    JOIN suppliers
    ON drugs.supplier_id = suppliers.id
);