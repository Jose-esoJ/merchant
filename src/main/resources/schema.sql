CREATE TABLE IF NOT EXISTS Merchant (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    municipality VARCHAR(50) NOT NULL,
    phone VARCHAR(15),
    email VARCHAR(100),
    registration_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS Establishment (
                                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                             name VARCHAR(100) NOT NULL,
    revenue DECIMAL(15, 2) NOT NULL,
    employee_count INT NOT NULL,
    merchant_id BIGINT,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(id) ON DELETE CASCADE
    );