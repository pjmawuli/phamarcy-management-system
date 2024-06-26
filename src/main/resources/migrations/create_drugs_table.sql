CREATE TABLE IF NOT EXISTS drugs (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    drug_code VARCHAR(100) NOT NULL,
    quantity INTEGER DEFAULT 0 NOT NULL,
    price NUMERIC(10, 2) NOT NULL,
    supplier_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
    UNIQUE (drug_code)
);