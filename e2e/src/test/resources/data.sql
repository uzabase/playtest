CREATE SCHEMA IF NOT EXISTS schemaName;

CREATE TABLE IF NOT EXISTS schemaName.tableName
(
    id            UUID primary key,
    string_column text
);

INSERT INTO schemaName.tableName VALUES ('bf9626ab-6ecd-4f15-be70-ac88fe4ba0f0', 'test');