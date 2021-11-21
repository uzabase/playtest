CREATE SCHEMA IF NOT EXISTS schemaName;

CREATE TABLE IF NOT EXISTS schemaName.tableName
(
    id            UUID primary key,
    string_column text
);

INSERT INTO schemaName.tableName VALUES ('bf9626ab-6ecd-4f15-be70-ac88fe4ba0f0', 'test');

create table if not exists schemaname.intTable
(
    id            UUID primary key,
    int_column INTEGER
);

INSERT INTO schemaName.intTable VALUES ('4d30aa18-ec51-439c-a98c-e5e2c3223509', 1);
