INSERT INTO user (USER_ID, USERNAME, PASSWORD, NICKNAME, EMAIL, ACTIVATED)
VALUES (1, "admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "admin", "admin", 1);
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');

INSERT INTO keyword (keyword_name) VALUE ('Test');

INSERT INTO user_keyword (user_id, keyword_id) VALUE (1, 1);
