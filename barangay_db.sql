-- ============================================================
--  BARANGAY RESIDENT RECORD SYSTEM
--  Database Schema
--  Author: Database Engineer
-- ============================================================

-- 1. Create and select the database
CREATE DATABASE IF NOT EXISTS barangay_db;
USE barangay_db;

-- ============================================================
--  TABLE 1: households
--  Stores household/address information
-- ============================================================
CREATE TABLE IF NOT EXISTS households (
    household_id    INT           AUTO_INCREMENT PRIMARY KEY,
    sitio           VARCHAR(100)  NOT NULL,
    total_residents INT           NOT NULL,
    created_at      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================================
--  TABLE 2: residents
--  Stores individual resident information
-- ============================================================
CREATE TABLE IF NOT EXISTS residents (
    resident_id     INT           AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50)   NOT NULL,
    last_name       VARCHAR(50)   NOT NULL,
    middle_name     VARCHAR(50)   DEFAULT NULL,
    birthdate       DATE          NOT NULL,
    gender          ENUM('Male', 'Female', 'Other') NOT NULL,
    civil_status    ENUM('Single', 'Married', 'Widowed') NOT NULL,
    contact_number  VARCHAR(15)   DEFAULT NULL,
    is_voter        TINYINT(1)    DEFAULT 0,   -- 1 = Yes, 0 = No
    is_deleted      TINYINT(1)    DEFAULT 0,   -- Soft delete: 1 = deleted
    household_id    INT           DEFAULT NULL,
    created_at      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,

    -- Foreign key linking resident to a household
    CONSTRAINT fk_household
        FOREIGN KEY (household_id)
        REFERENCES households(household_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

-- ============================================================
--  SAMPLE DATA: households
-- ============================================================
INSERT INTO households (sitio, total_residents) VALUES
('Añog',2),
('Naga',3),
('Rawis',1);

-- ============================================================
--  SAMPLE DATA: residents
-- ============================================================
INSERT INTO residents
    (first_name, last_name, middle_name, birthdate, gender, civil_status, contact_number, is_voter, household_id)
VALUES
('Juan',    'Dela Cruz', 'Santos',  '1990-03-15', 'Male',   'Married',  '09171234567', 1, 1),
('Maria',   'Santos',    'Reyes',   '2000-07-22', 'Female', 'Single',   '09281234567', 0, 1),
('Pedro',   'Reyes',     NULL,      '1985-11-05', 'Male',   'Widowed',  '09391234567', 1, 2),
('Ana',     'Garcia',    'Lim',     '1995-01-30', 'Female', 'Married',  '09451234567', 1, 2),
('Carlos',  'Bautista',  'Cruz',    '2003-09-18', 'Male',   'Single',   NULL,          0, 2),
('Jake',    'Agustin',   'Mendoza', '1995-05-22',  'Male', 'Single',    '09303197483', 1, 3);

-- ============================================================
--  VERIFICATION QUERIES (run these to check your data)
-- ============================================================
-- SELECT * FROM households;
-- SELECT * FROM residents;
-- SELECT r.resident_id, r.first_name, r.last_name, h.purok
--   FROM residents r
--   LEFT JOIN households h ON r.household_id = h.household_id
--   WHERE r.is_deleted = 0;