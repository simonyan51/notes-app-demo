CREATE DATABASE notes;
CREATE DATABASE users;

INSERT INTO users (
  id,
  email,
  password
) VALUES (
  1,
  'test@example.com',
  '$2a$12$.Qk1dr3xxP78apUhQAnGG.yUe9KGmF3J7u6f4qvTodTi8o6SgAWQe'
), (
  2,
  'test1@example.com',
  '$2a$12$.Qk1dr3xxP78apUhQAnGG.yUe9KGmF3J7u6f4qvTodTi8o6SgAWQe'
), (
  3,
  'test2@example.com',
  '$2a$12$.Qk1dr3xxP78apUhQAnGG.yUe9KGmF3J7u6f4qvTodTi8o6SgAWQe'
);