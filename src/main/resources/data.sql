INSERT INTO user (USER_ID, USERNAME, PASSWORD, NICKNAME, EMAIL, ACTIVATED)
VALUES (1, "admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "admin", "admin", 1);
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');

INSERT INTO keyword (keyword_name) VALUE ('한덕수');
INSERT INTO keyword (keyword_name) VALUE ('국무총리');
INSERT INTO keyword (keyword_name) VALUE ('청문회');

INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('한덕수 청문회 30분 만에 중단... 민주·정의당 "자료 부실" 보이콧', '박준석 기자, 강진구 기자', '한국일보', 'https://n.news.naver.com/article/469/0000671399?ntype=RANKING', 'https://www.hankookilbo.com/');
INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('BTS 지민 ‘건보료 체납’ 59억 아파트 압류… 회사 착오', '박성영 기자', '국민일보', 'https://n.news.naver.com/article/005/0001521360?ntype=RANKING', 'http://www.kmib.co.kr/news/index.asp');

INSERT INTO user_keyword (user_id, keyword_id) VALUES (1, 1);

INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 1);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 2);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 3);