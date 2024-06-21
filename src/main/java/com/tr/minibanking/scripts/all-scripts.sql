-- Create database
create database banking;

--Create schema
create database banking;

-- Add uuid extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- User Model
CREATE TABLE banking.user (
                              id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                              username VARCHAR(50) NOT NULL,
                              password VARCHAR(64) NOT NULL,
                              email VARCHAR(255) NOT NULL UNIQUE,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP
);

-- Account Model
CREATE TABLE banking.account (
                                 id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                 user_id UUID NOT NULL,
                                 account_number VARCHAR(30) NOT NULL UNIQUE,
                                 account_name VARCHAR(100) NOT NULL UNIQUE,  -- Adjusted length for account name
                                 balance NUMERIC(15, 2) NOT NULL DEFAULT 0,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP,
                                 CONSTRAINT fk_user
                                     FOREIGN KEY(user_id)
                                         REFERENCES banking.user(id)
);

-- Enums details
CREATE TYPE banking.transaction_status AS ENUM ('SUCCESS', 'FAILED');

-- Transaction Model
CREATE TABLE banking.transaction (
                                     id BIGSERIAL PRIMARY KEY,
                                     from_account UUID NOT NULL,
                                     to_account UUID NOT NULL,
                                     amount NUMERIC(15, 2) NOT NULL,
                                     transaction_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     status int4 NOT NULL,  -- Use ENUM type for status
                                     CONSTRAINT fk_from_account
                                         FOREIGN KEY(from_account)
                                             REFERENCES banking.account(id),
                                     CONSTRAINT fk_to_account
                                         FOREIGN KEY(to_account)
                                             REFERENCES banking.account(id)
);

-- Indexes for performance
CREATE INDEX idx_user_email ON banking.user(email);
CREATE INDEX idx_account_user_id ON banking.account(user_id);
CREATE INDEX idx_transaction_from_account ON banking.transaction(from_account);
CREATE INDEX idx_transaction_to_account ON banking.transaction(to_account);

-- Unique constraint
ALTER TABLE banking.user ADD CONSTRAINT unique_username UNIQUE (username);
ALTER TABLE banking.account ADD CONSTRAINT unique_account_number UNIQUE (account_number);

