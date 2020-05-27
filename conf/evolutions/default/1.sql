-- phonebook Schema

-- !Ups

CREATE TABLE phonebook (
  "id" bigserial PRIMARY KEY,
  "phone" varchar(15) UNIQUE,
  "name" varchar(50)
);

-- !Downs

DROP TABLE IF EXISTS phonebook;