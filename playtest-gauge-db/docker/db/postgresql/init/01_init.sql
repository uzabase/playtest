CREATE USER test WITH PASSWORD 'test' SUPERUSER;
CREATE DATABASE test_db owner test encoding 'UTF8';
GRANT ALL PRIVILEGES ON DATABASE test_db TO test;

\c test_db;

