INSERT INTO user (USER_ID, USERNAME, PASSWORD, NICKNAME, EMAIL, ACTIVATED)
VALUES (1, "admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "admin", "admin", 1);
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');

INSERT INTO keyword (keyword_name) VALUES ('한덕수');
INSERT INTO keyword (keyword_name) VALUES ('국무총리');
INSERT INTO keyword (keyword_name) VALUES ('청문회');
INSERT INTO keyword (keyword_name) VALUES ('BTS');
INSERT INTO keyword (keyword_name) VALUES ('지민');
INSERT INTO keyword (keyword_name) VALUES ('건보료');
INSERT INTO keyword (keyword_name) VALUES ('강아지');
INSERT INTO keyword (keyword_name) VALUES ('동물학대');
INSERT INTO keyword (keyword_name) VALUES ('소시지');
INSERT INTO keyword (keyword_name) VALUES ('성폭행');
INSERT INTO keyword (keyword_name) VALUES ('여고생');
INSERT INTO keyword (keyword_name) VALUES ('운전기사');
INSERT INTO keyword (keyword_name) VALUES ('문제인');
INSERT INTO keyword (keyword_name) VALUES ('남북');
INSERT INTO keyword (keyword_name) VALUES ('북한');

INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('한덕수 청문회 30분 만에 중단... 민주·정의당 "자료 부실" 보이콧', '박준석 기자, 강진구 기자', '한국일보', 'https://n.news.naver.com/article/469/0000671399?ntype=RANKING', 'https://www.hankookilbo.com/');
INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('BTS 지민 ‘건보료 체납’ 59억 아파트 압류… 회사 착오', '박성영 기자', '국민일보', 'https://n.news.naver.com/article/005/0001521360?ntype=RANKING', 'http://www.kmib.co.kr/news/index.asp');
INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('''낚시바늘 소시지'' 공원에서 또…"''흰 가루'' 흡입한 강아지, 거품 물고 쓰러져"', '윤슬기 기자', '아시아 경제', 'https://n.news.naver.com/article/277/0005080485?ntype=RANKING', 'https://www.asiae.co.kr/');
INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('''여고생 수년간 성폭행 혐의'' 통학 차량 기사 입건..."추가 피해 철저히 조사해야"', '양동훈 기자', 'YTN', 'https://n.news.naver.com/article/052/0001732345?ntype=RANKING', 'https://www.ytn.co.kr/');
INSERT INTO news (news_title, news_reporter, news_media, news_url, media_url)
VALUES ('손석희 “연락사무소 폭파…” 문재인 “뭐가 문젭니까?”', '', '채널 A', 'https://n.news.naver.com/article/449/0000226029?ntype=RANKING', 'http://www.ichannela.com/'성);

INSERT INTO user_keyword (user_id, keyword_id) VALUES (1, 1);
INSERT INTO user_keyword (user_id, keyword_id) VALUES (1, 4);

INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 1);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 2);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (1, 3);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (2, 4);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (2, 5);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (2, 6);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (3, 7);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (3, 8);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (3, 9);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (4, 10);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (4, 11);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (4, 12);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (5, 13);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (5, 14);
INSERT INTO news_keyword (news_id, keyword_id) VALUES (5, 15);
