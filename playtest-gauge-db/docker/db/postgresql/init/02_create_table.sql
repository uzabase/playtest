\c test_db;

CREATE SCHEMA test;

CREATE TABLE test.todos
(
    todo_id INTEGER PRIMARY KEY,
    memo    VARCHAR(100)
);