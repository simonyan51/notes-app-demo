CREATE DATABASE notes;
CREATE DATABASE users;

INSERT INTO users (
  id,
  email,
  password
) VALUES (
  1,
  'test@example.com',
  '$2a$10$Fr50gztVCcmfSb.9kfWtyulC0x9W12KrTmBacHWRRLBWfg/Tije7y',
  '2022-05-28T22:08:44.553Z'
), (
  2,
  'test1@example.com',
  '$2a$10$Fr50gztVCcmfSb.9kfWtyulC0x9W12KrTmBacHWRRLBWfg/Tije7y',
  '2022-05-28T22:08:44.553Z'
), (
  3,
  'test2@example.com',
  '$2a$10$Fr50gztVCcmfSb.9kfWtyulC0x9W12KrTmBacHWRRLBWfg/Tije7y',
  '2022-05-28T22:08:44.553Z'
);