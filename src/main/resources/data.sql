INSERT INTO user (USER_ID, USERNAME, PASSWORD, NICKNAME, EMAIL, ACTIVATED)
VALUES (1, "admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "admin", "admin", 1);
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_USER');
INSERT INTO authority (AUTHORITY_NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');

INSERT INTO keyword (keyword_name) VALUES ('키워드1');
INSERT INTO keyword (keyword_name) VALUES ('키워드2');
INSERT INTO keyword (keyword_name) VALUES ('키워드3');

INSERT INTO keyword (keyword_name) VALUES ('키워드4');
INSERT INTO keyword (keyword_name) VALUES ('키워드5');
INSERT INTO keyword (keyword_name) VALUES ('키워드6');

INSERT INTO article (article_title,
                     article_reporter,
                     article_media_name,
                     article_url,
                     article_media_url,
                     article_media_image_src,
                     article_last_modified_date)
VALUES ('''시청자 마구 때려 숨지게 한 20대 BJ…재판 넘겨져''',
        '이강 기자(leekang@sbs.co.kr)',
        'SBS',
        'https://n.news.naver.com/article/055/0000970819?ntype=RANKING',
        'http://news.sbs.co.kr/',
        'https://mimgnews.pstatic.net/image/upload/office_logo/055/2020/09/15/logo_055_6_20200915154015.png',
        '2022.05.03.');

INSERT INTO article (article_title,
                     article_reporter,
                     article_media_name,
                     article_url,
                     article_media_url,
                     article_media_image_src,
                     article_last_modified_date)
VALUES ('생애최초 LTV 80%까지…코로나 긴급 채무조정 추진',
        '김완진 기자(wanjoy@sbs.co.kr)',
        'SBS Biz',
        'https://n.news.naver.com/article/374/0000284505?ntype=RANKING',
        'https://biz.sbs.co.kr',
        'https://mimgnews.pstatic.net/image/upload/office_logo/374/2021/01/07/logo_374_6_20210107162903.png',
        '2022.05.03.');

INSERT INTO article_keyword (article_id, keyword_id) VALUES (1, 1);
INSERT INTO article_keyword (article_id, keyword_id) VALUES (1, 2);
INSERT INTO article_keyword (article_id, keyword_id) VALUES (1, 3);

INSERT INTO article_keyword (article_id, keyword_id) VALUES (2, 4);
INSERT INTO article_keyword (article_id, keyword_id) VALUES (2, 5);
INSERT INTO article_keyword (article_id, keyword_id) VALUES (2, 6);

