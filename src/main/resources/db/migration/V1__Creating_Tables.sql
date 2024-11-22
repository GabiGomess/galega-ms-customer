CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE customer (
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    cpf varchar(11) UNIQUE,
    name varchar(256),
    email varchar(256) UNIQUE
);