CREATE TABLE IF NOT EXISTS purchases (
    id SERIAL PRIMARY KEY,
    purchase_code VARCHAR(100) NOT NULL,
    quantity INTEGER NOT NULL,
    total_price NUMERIC(10, 2) NOT NULL,
    customer_name VARCHAR(255),
    drug_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (drug_id) REFERENCES drugs(id),
    UNIQUE (purchase_code, drug_id)
);