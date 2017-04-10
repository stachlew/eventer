-- DEMO CUSTOMER
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(1, 'abacki','spamer@yahoo.com', TO_DATE('2017-02-11', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(2, 'cabacki','mejlik123@gmail.com', TO_DATE('2017-02-12', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(3, 'babacki','pustostan@wp.pl', TO_DATE('2017-02-13', 'yyyy-mm-dd'));

-- UZYTKOWNICY
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres, PHONE) VALUES (EVE_USER_SEQ.NEXTVAL, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2017-01-01','658254985');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2017-01-01');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2017-01-01');


-- RODZAJE UPRAWNIEN
INSERT INTO EVE_AUTHORITY (ID, NAME) VALUES (EVE_AUTH_SEQ.NEXTVAL, 'ROLE_USER');
INSERT INTO EVE_AUTHORITY (ID, NAME) VALUES (EVE_AUTH_SEQ.NEXTVAL, 'ROLE_ADMIN');

-- PRZYDZIELONE UPRAWNIENIA
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);

-- WOJEWODZTWA
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (1,'Dolnośląskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (2,'Kujawsko-pomorskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (3,'Lubelskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (4,'Lubuskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (5,'Łódzkie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (6,'Małopolskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (7,'Mazowieckie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (8,'Opolskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (9,'Podkarpackie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (10,'Podlaskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (11,'Pomorskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (12,'Śląskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (13,'Świętokrzyskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (14,'Warmińsko-mazurskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (15,'Wielkopolskie');
INSERT INTO EVE_REGIONS (ID_REGION, REGION_NAME) VALUES (16,'Zachodniopomorskie');

-- MIASTA
INSERT INTO EVE_CITIES (ID_CITY, CITY_NAME, ID_REGION) VALUES (EVE_CITY_SEQ.NEXTVAL,'Warszawa',7);
INSERT INTO EVE_CITIES (ID_CITY, CITY_NAME, ID_REGION) VALUES (EVE_CITY_SEQ.NEXTVAL,'Kraków',6);
INSERT INTO EVE_CITIES (ID_CITY, CITY_NAME, ID_REGION) VALUES (EVE_CITY_SEQ.NEXTVAL,'Gdańsk',11);

-- MIEJSCA
INSERT INTO EVE_PLACES (ID_PLACE, STREET_NAME, STREET_NO, ID_CITY) VALUES (EVE_PLACE_SEQ.NEXTVAL,'Aleje Jerozolimskie','123A m23',1);
INSERT INTO EVE_PLACES (ID_PLACE, STREET_NAME, STREET_NO, ID_CITY) VALUES (EVE_PLACE_SEQ.NEXTVAL,'Grunwaldzka','16c',3);
INSERT INTO EVE_PLACES (ID_PLACE, STREET_NAME, STREET_NO, ID_CITY) VALUES (EVE_PLACE_SEQ.NEXTVAL,'Podwawelska','69',2);

-- SZABLONY
INSERT INTO EVE_TEMPLATES (ID_TEMPLATE, TEMPLATE_NAME) VALUES (1,'Szablon1');
INSERT INTO EVE_TEMPLATES (ID_TEMPLATE, TEMPLATE_NAME) VALUES (2,'Szablon2');
INSERT INTO EVE_TEMPLATES (ID_TEMPLATE, TEMPLATE_NAME) VALUES (3,'Szablon3');

-- TYPY WYDARZEN
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (1,'Koncert');
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (2,'Konferencja biznesowa');
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (3,'Konferencja technologiczna');

-- WYDARZENIA
INSERT INTO EVE_EVENTS (ID_EVENT, TITLE, DESCRIPTION, ID_PLACE, ID_USER, ID_EVENT_TYPE, ID_TEMPLATE, START_TIME, END_TIME, CAPACITY ) VALUES (EVE_EVENT_SEQ.NEXTVAL,'Przykładowy tytuł wydarzenia 2017','Opis wydarzenia długi i ciekawy musi być, by przjść chciał ktoś',1,2,3,1,TO_TIMESTAMP('2017-06-10 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2017-06-10 19:50:00', 'YYYY-MM-DD HH24:MI:SS'),50);

-- PRELEGENCI
INSERT INTO EVE_SPEAKERS (ID_SPEAKER, FIRSTNAME, LASTNAME, EMAIL, DESCRIPTION, PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Adam','Abacki','Profesor.abacki@wp.pl','Gość zna się na robocie, a po robocie nie gada o robocie','354634523');
INSERT INTO EVE_SPEAKERS (ID_SPEAKER, FIRSTNAME, LASTNAME, EMAIL, DESCRIPTION, PHONE) VALUES (EVE_SPEAKER_SEQ.NEXTVAL,'Jan','Nowak','mejl@prelegent.pl','Lorem ipsum sobie gada - lorem ipsum sobie spiewa.','594235846');

-- PRELEKCJE
INSERT INTO EVE_LECTURES (ID_LECTURE, START_TIME, END_TIME, LECTURE_NAME, DESCRIPTION, ID_SPEAKER, ID_EVENT) VALUES (EVE_LECTURE_SEQ.NEXTVAL,TO_TIMESTAMP('2017-06-10 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2017-06-10 15:40:00', 'YYYY-MM-DD HH24:MI:SS'),'Używanie manuala w systemach operacyjnych','Komenda man polecenie ratuje na życzenie.',1,1);
INSERT INTO EVE_LECTURES (ID_LECTURE, START_TIME, END_TIME, LECTURE_NAME, DESCRIPTION, ID_SPEAKER, ID_EVENT) VALUES (EVE_LECTURE_SEQ.NEXTVAL,TO_TIMESTAMP('2017-06-10 16:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_TIMESTAMP('2017-06-10 17:40:00', 'YYYY-MM-DD HH24:MI:SS'),'Pomoce studenckie XXI wieku','FTP elenx jako przykład sprawnej studenckiej olewki.',2,1);

-- OPINIE
INSERT INTO EVE_OPINIONS (ID_OPINION, CONTENT, EMAIL, CREATE_DATE, ID_EVENT) VALUES (EVE_OPINION_SEQ.NEXTVAL,'Było zajebiście choć pizza się szybko skończyła i piwko było ciepłe. Hostessy nadrobiły wszystko swoją wiedzą na tematy branżowe. POLECAM','cezarycezary@spamer.gov.pl',TO_TIMESTAMP('2017-06-10 13:40:00', 'YYYY-MM-DD HH24:MI:SS'),1);

-- UCZESTNICY
INSERT INTO EVE_PARTICIPANTS(ID_PARTICIPANT,FIRSTNAME,LASTNAME,EMAIL,ID_EVENT) VALUES (EVE_PARTICIPANT_SEQ.NEXTVAL,'Jan','Kowalski','kowal@wp.pl',1);
INSERT INTO EVE_PARTICIPANTS(ID_PARTICIPANT,FIRSTNAME,LASTNAME,EMAIL,ID_EVENT) VALUES (EVE_PARTICIPANT_SEQ.NEXTVAL,'Barbara','Grawara','baskaGie@onet.pl',1);
INSERT INTO EVE_PARTICIPANTS(ID_PARTICIPANT,FIRSTNAME,LASTNAME,EMAIL,ID_EVENT) VALUES (EVE_PARTICIPANT_SEQ.NEXTVAL,'Stefan','Klocuch','klocuch12@youtube.pl',1);

-- DOKUMENTY
INSERT INTO EVE_DOCUMENTS (ID_DOCUMENT,NAME,PATH,TYPE) VALUES (EVE_DOCUMENT_SEQ.NEXTVAL,'Pozwolenie urzędu miasta','C/dok/mia/...','docx');
INSERT INTO EVE_DOCUMENTS (ID_DOCUMENT,NAME,PATH,TYPE) VALUES (EVE_DOCUMENT_SEQ.NEXTVAL,'Zawiadomienie do policji','C/dok/pol/...','docx');
INSERT INTO EVE_DOCUMENTS (ID_DOCUMENT,NAME,PATH,TYPE) VALUES (EVE_DOCUMENT_SEQ.NEXTVAL,'Zezwolenie wójta na wesele','C/dok/inn/...','docx');

-- TYP WYDARZENIA I ICH DOKUMENTY
INSERT INTO EVE_EVENT_TYPE_DOCUMENTS (ID_EVENT_TYPE_DOCUMENT,ID_EVENT_TYPE,ID_DOCUMENT) VALUES (EVE_EVENT_TYPE_DOCUMENT_SEQ.NEXTVAL,1,3);
INSERT INTO EVE_EVENT_TYPE_DOCUMENTS (ID_EVENT_TYPE_DOCUMENT,ID_EVENT_TYPE,ID_DOCUMENT) VALUES (EVE_EVENT_TYPE_DOCUMENT_SEQ.NEXTVAL,3,2);
INSERT INTO EVE_EVENT_TYPE_DOCUMENTS (ID_EVENT_TYPE_DOCUMENT,ID_EVENT_TYPE,ID_DOCUMENT) VALUES (EVE_EVENT_TYPE_DOCUMENT_SEQ.NEXTVAL,2,1);