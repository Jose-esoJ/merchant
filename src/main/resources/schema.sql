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

CREATE TABLE IF NOT EXISTS Authentication (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   token VARCHAR(512)
);

-- Datos iniciales para la tabla Merchant
INSERT INTO Merchant (name, department, municipality, phone, email, registration_date, status) VALUES
       ('Merchant One', 'Boyaca', 'Municipality X', '1234567890', 'merchant1@example.com', '2023-01-01', 'Active'),
       ('Merchant Two', 'Quindio', 'Municipality Y', '1234567891', 'merchant2@example.com', '2023-01-05', 'Active'),
       ('Merchant Three', 'Valle', 'Municipality Z', '1234567892', 'merchant3@example.com', '2023-02-10', 'Inactive'),
       ('Merchant Four', 'Amazonas', 'Municipality W', '1234567893', 'merchant4@example.com', '2023-03-15', 'Active');

-- Datos iniciales para la tabla Establishment
INSERT INTO Establishment (name, revenue, employee_count, merchant_id) VALUES
           ('Establishment One', 15000.50, 10, 1),
           ('Establishment Two', 24500.75, 15, 1),
           ('Establishment Three', 32500.00, 8, 2),
           ('Establishment Four', 5400.60, 5, 3),
           ('Establishment Five', 15700.30, 20, 2),
           ('Establishment Six', 8900.50, 12, 4),
           ('Establishment Seven', 13400.90, 14, 1),
           ('Establishment Eight', 45500.75, 9, 3);

-- Insert initial data into `AuthenticationRequest`
INSERT INTO Authentication (email, password, token) VALUES
                     ('user1@example.com', '1234556', null);