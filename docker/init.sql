 CREATE SCHEMA if NOT EXISTS tasks_schema;

CREATE TABLE if NOT EXISTS tasks_schema.task(
    id BIGINT primary key,
    title varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    priority INTEGER NOT NULL
    );

