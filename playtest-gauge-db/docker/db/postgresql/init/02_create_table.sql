\c test_db;

CREATE SCHEMA test;

CREATE TABLE test.todos
(
    id UUID PRIMARY KEY,
    memo    VARCHAR(100),
    priority INTEGER,
    progress_rate numeric,
    registered_date date,
    updated_date timestamp
);