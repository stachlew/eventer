-- DEMO CUSTOMER
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(1, 'abacki','spamer@yahoo.com', TO_DATE('2017-02-11', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(2, 'cabacki','mejlik123@gmail.com', TO_DATE('2017-02-12', 'yyyy-mm-dd'));
INSERT INTO "CUSTOMER" (ID, NAME, EMAIL, CREATED_DATE) VALUES(3, 'babacki','pustostan@wp.pl', TO_DATE('2017-02-13', 'yyyy-mm-dd'));

-- UZYTKOWNICY
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres, PHONE) VALUES (EVE_USER_SEQ.NEXTVAL, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2017-01-01','658254985');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'user1', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'nameuser1', 'lastuser1', 'enabled1@user.com', 1, '2017-01-01');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'user2', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'nameuser2', 'lastuser2', 'enabled2@user.com', 1, '2017-02-01');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'user3', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'nameuser3', 'lastuser3', 'enabled3@user.com', 1, '2017-03-01');
INSERT INTO EVE_USERS (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, lastpassres) VALUES (EVE_USER_SEQ.NEXTVAL, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2017-01-01');


-- RODZAJE UPRAWNIEN
INSERT INTO EVE_AUTHORITY (ID, NAME) VALUES (EVE_AUTH_SEQ.NEXTVAL, 'ROLE_USER');
INSERT INTO EVE_AUTHORITY (ID, NAME) VALUES (EVE_AUTH_SEQ.NEXTVAL, 'ROLE_ADMIN');

-- PRZYDZIELONE UPRAWNIENIA
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO EVE_USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (4, 1);

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
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Wrocław',  1);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Wałbrzych',  1);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Legnica',  1);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Jelenia Góra', 1);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Bolesławiec',  1);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Bydgoszcz',  2);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Grudziądz',  2);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Inowrocław',  2);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Toruń',  2);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Włocławek',  2);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Lublin',  3);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Biała Podlaska',  3);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Chełm',  3);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Zamość',  3);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Puławy',  3);

INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Zielona Góra',  4);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Gorzów Wielkopolski',  4);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Nowa Sól',  4);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Świebodzin',  4);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Żary',  4);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Łódź',  5);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Piotrków Trybunalski',  5);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Pabianice',  5);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Świebodzin',  5);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Tomaszów Mazowiecki',  5);


INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Tarnów',  6);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Nowy Sącz',  6);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Oświęcim',  6);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Chrzanów',  6);


INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Radom',  7);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Płock',  7);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Siedlce',  7);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Pruszków',  7);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Legionowo',  7);


INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Opole',  8);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Kędzierzyn-Koźle',  8);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Nysa',  8);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Brzeg',  8);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Kluczbork',  8);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Rzeszów',  9);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Przemyśl',  9);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Stalowa Wola',  9);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Mielec',  9);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Tarnobrzeg',  9);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Białystok',  10);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Suwałki',  10);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Łomża', 10);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Augustów',  10);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Bielsk Podlaski',  10);


INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Gdynia',  11);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Słupsk',  11);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Sopot',  11);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Wejherowo',  11);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Krynica Morska',  11);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Katowice',  12);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Częstochowa',  12);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Sosnowiec',  12);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Gliwice',  12);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Zabrze',  12);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Kielce',  13);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Ostrowiec Świętokrzyski',  13);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Starachowice',  13);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Skarżysko-Kamienna',  13);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Sandomierz',  13);




INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Olsztyn',  14);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Elbląg',  14);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Ełk',  14);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Ostróda',  14);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Iława',  14);



INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Poznań',  15);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Ostrów Wielkopolski',  15);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Gniezno',  15);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Piła',  15);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Konin',  15);


INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Szczecin',  16);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Koszalin',  16);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Stargard',  16);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Kołobrzeg',  16);
INSERT INTO eve_cities ( id_city,city_name,id_region ) VALUES (  eve_city_seq.nextval,  'Świnoujście',  16);

-- MIEJSCA
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'19.94223475','50.05961845','Planty','34a','2');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'21.01227924','52.23041322','Marszałkowska','100/102','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'21.05495452','52.26248425','Mazurska','21','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'21.02193653','52.25760505','Ratuszowa','3','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'20.89626431','52.21642898','vcxxcvvxcvcxvxc','243','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'18.58670901','54.43094524','Hestii','1a','72');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'18.645636','54.354843','Rynek Główny','16c','3');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'19.937964','50.061121','Wały Jagiellońskie','2b','2');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'20.98418176','52.23232049','Plac Europejski','1','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'20.89698314','52.23437059','Lazurowa','3','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'20.98249465','52.23543832','Chłodna','51','1');
Insert into WATPROJEKT.EVE_PLACES (ID_PLACE,GEO_LENGTH,GEO_WIDTH,STREET_NAME,STREET_NO,ID_CITY) values (EVE_PLACE_SEQ.NEXTVAL,'20.99745869','52.22338304','Koszykowa','2','1');
-- SZABLONY


-- TYPY WYDARZEN
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (1,'Koncert');
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (2,'Konferencja biznesowa');
INSERT INTO EVE_EVENT_TYPES (ID_EVENT_TYPE, EVENT_TYPE_NAME) VALUES (3,'Konferencja technologiczna');

-- WYDARZENIA

Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'200',to_timestamp('17/05/16 19:17:13,160000000','RR/MM/DD HH24:MI:SSXFF'),'Najlepszy grill w Warszawie!',to_timestamp('17/05/19 10:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'0','1','1',to_timestamp('17/05/18 10:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'MegaGrill Narodowy','3',null,'1','81','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'50',to_timestamp('17/05/10 08:51:42,331000000','RR/MM/DD HH24:MI:SSXFF'),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut eget luctus libero. Curabitur eu orci lacus. Nulla et finibus erat, sit amet finibus diam. Vestibulum et ipsum bibendum, volutpat purus rhoncus, euismod tellus. Sed mollis metus sit amet libero bibendum, ut dictum elit hendrerit. Vivamus ullamcorper ipsum nec ipsum imperdiet, et rhoncus est pharetra. Ut dolor dui, lobortis ut tempus ut, convallis in ex. Proin eget enim pharetra velit sodales ultricies ac ut nibh. Vivamus ultrices sagittis ex, ut efficitur odio malesuada vel. Phasellus sodales, urna nec malesuada finibus, nisi mauris hendrerit orci, in pretium quam dolor a odio. Morbi ultrices lorem id cursus suscipit. Maecenas ut lacus fermentum, ultricies magna vitae, aliquam magna. In suscipit hendrerit luctus. Interdum et malesuada fames ac ante ipsum primis in faucibus a potem grill.',to_timestamp('17/05/14 13:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1','1',to_timestamp('17/05/12 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Modernizacja budynku Rotundy','6','xnAs8KmuihY','3','61','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'350',to_timestamp('17/05/03 01:21:44,691000000','RR/MM/DD HH24:MI:SSXFF'),'Jak co roku, tak i w tym roku nasi najlepsi raperzy rozpalą w piecach nam.. grille, by podgrzewać niezliczone ilości kiełby, którą uraczą się okoliczni mieszkańcy. Sosy zapewnia niezawodna firma Fanex z Radonic. Każdy bierze swoje pęto, bo za darmo to sobie można tylko w morde dostać. ',to_timestamp('17/05/07 18:50:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1','1',to_timestamp('17/05/06 12:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Rodzinne grillowanie przy polskim hip-hopie','16','67DbebpybsE?t=22s','1','21','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'75',to_timestamp('17/06/05 10:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris mauris velit, semper eget suscipit tincidunt, egestas eget elit. Nunc scelerisque lacinia erat, quis venenatis ante molestie sed. Mauris hendrerit cursus euismod. Integer tincidunt eu leo sit amet lacinia. Vestibulum ante ipsum primis in faucibus orci luctus.',to_timestamp('17/05/22 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'0','1','1',to_timestamp('17/05/20 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Dwa wydarzenie','25','y60wDzZt8yg','1','1','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'15',to_timestamp('17/05/10 09:03:21,568000000','RR/MM/DD HH24:MI:SSXFF'),'Nam sodales id leo ut dapibus. Proin lacus enim, condimentum gravida urna sed, tempor mattis est. Praesent luctus blandit ligula vitae pretium. Vivamus in hendrerit velit, in fringilla nibh. Nunc pharetra aliquam venenatis. Vestibulum in erat non ligula dignissim cursus et vitae nibh. Etiam risus sem, sollicitudin a vulputate in, placerat ut nibh. Morbi pellentesque rhoncus nisl a fermentum. Sed eleifend magna non risus tincidunt malesuada. Aliquam ligula est, vestibulum a finibus vitae, viverra eu lorem. Nam egestas ornare dolor, vitae ultricies eros sodales eget. Integer in leo vitae eros blandit condimentum.',to_timestamp('17/05/16 20:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'0','1','1',to_timestamp('17/05/15 16:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Mycie okien w Warsaw Spire','8','jtChVULF818','3','62','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'25',to_timestamp('17/07/31 10:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Praesent in sapien venenatis, lacinia ipsum eget, congue ante. Donec dictum mattis augue eu mattis. Vivamus interdum ante ipsum, luctus consequat ex rhoncus id. Suspendisse potenti. Nulla finibus quam metus, nec tincidunt est blandit sit amet. Maecenas facilisis viverra scelerisque. Donec luctus diam lacus, vitae volutpat neque sodales quis. ',to_timestamp('17/08/15 19:50:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1','1',to_timestamp('17/08/12 10:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Lan garaż party','16','3gMWlpR3IuM','3','2','4');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'78',to_timestamp('17/05/01 10:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Pellentesque tellus eros, placerat ut odio sit amet, ornare congue nisi. Cras convallis at arcu sit amet consequat. Fusce vel dignissim arcu. Nam posuere, sapien ut accumsan molestie, leo ligula laoreet leo, nec laoreet enim sem ac purus. Donec a odio mattis, sodales nulla vitae, tincidunt nisl. Sed mauris sem, mattis sed felis ut, rutrum lacinia sapien.',to_timestamp('17/07/04 19:50:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1','1',to_timestamp('17/07/01 10:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Przykładowy tytuł wydarzenia 2017','1','ZclaXQWVRCg','3','3','3');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'234',to_timestamp('17/05/10 10:28:52,781000000','RR/MM/DD HH24:MI:SSXFF'),'Duis diam ex, varius in magna eget, lobortis pretium lectus. Suspendisse id sapien aliquet risus ullamcorper varius vitae non nibh. Etiam ex metus, fermentum ac pulvinar quis, lacinia quis libero. ',to_timestamp('17/05/10 15:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'0','1','1',to_timestamp('17/05/10 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Test1','1',null,'2','63','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'50',to_timestamp('17/05/10 11:08:49,791000000','RR/MM/DD HH24:MI:SSXFF'),'Nam sodales id leo ut dapibus. Proin lacus enim, condimentum gravida urna sed, tempor mattis est. Praesent luctus blandit ligula vitae pretium. Vivamus in hendrerit velit, in fringilla nibh. Nunc pharetra aliquam venenatis. Vestibulum in erat non ligula dignissim cursus et vitae nibh. Etiam risus sem, sollicitudin a vulputate in, placerat ut nibh. Morbi pellentesque rhoncus nisl a fermentum. Sed eleifend magna non risus tincidunt malesuada. Aliquam ligula est, vestibulum a finibus vitae, viverra eu lorem. Nam egestas ornare dolor, vitae ultricies eros sodales eget. Integer in leo vitae eros blandit condimentum.',to_timestamp('17/05/18 16:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1','1',to_timestamp('17/05/18 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Mycie okien w WTT','1',null,'3','64','2');
Insert into WATPROJEKT.EVE_EVENTS (ID_EVENT,CAPACITY,CREATE_DATE,DESCRIPTION,END_TIME,FREE_ENTRANCE,PUBLISHED,REGISTER_ENABLED,START_TIME,TITLE,VISITS,YOUTUBE_LINK,ID_EVENT_TYPE,ID_PLACE,ID_USER) values (EVE_EVENT_SEQ.NEXTVAL,'50',to_timestamp('17/05/16 20:45:58,629000000','RR/MM/DD HH24:MI:SSXFF'),'Donec rutrum est quam, id lobortis diam volutpat et. Sed orci turpis, rutrum in tortor eget, suscipit aliquet eros. Vivamus leo tellus, eleifend a bibendum ultrices, blandit egestas lacus. Duis laoreet arcu ut pellentesque volutpat. Fusce auctor suscipit fermentum. Suspendisse finibus vitae nulla et blandit. Nunc vitae mattis odio. Nulla convallis placerat velit, fringilla tincidunt lacus hendrerit sit amet. Praesent fermentum fringilla feugiat. Aenean accumsan vehicula fermentum. Aliquam egestas iaculis ipsum, non sagittis purus accumsan accumsan. Proin efficitur dui sit amet velit fermentum, non ultricies leo egestas.',to_timestamp('17/05/20 12:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'0','1','1',to_timestamp('17/05/19 15:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Mycie elewacji Pałacu Kultury i Nauki','1',null,'3','82','2');

-- PRELEGENCI
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Truskawka na torcie tego eventu!','hajtor.tomeczek@tralala.pl','Tomasz','Hajto','425253223');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Peja, właściwie Ryszard Waldemar Andrzejewski (ur. 17 września 1976 w Poznaniu) – polski raper i producent muzyczny, a także przedsiębiorca związany z kulturą hip-hopową, występujący również jako Onomato, Onomatopeja, Rychu Peja SoLUfka, Sykuś oraz Charlie ','soloffka@gimba.pl','Rychu','Peja','5345433569');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Boczek mieszka na trzecim piętrze, nad Paździochami, choć notorycznie korzysta z toalety znajdującej się na drugim piętrze, ponieważ, jak mówi, w tej na trzecim piętrze straszy. Pracuje w rzeźni. Lubi kaloryczne potrawy (w tym boczek), popijane wysokoprocentowym alkoholem. Jego ulubione zajęcie to oglądanie baletu mongolskiego w telewizji. Jest nadzwyczaj łatwowiernym i niezbyt mądrym człowiekiem, który zawsze naiwnie bierze udział w niezbyt mądrych pomysłach sąsiadów.','boczku@kiełba.pl','Arnold','Boczek','543543643634');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Gość zna się na robocie, a po robocie nie gada o robocie','Profesor.abacki@wp.pl','Adamek','Abacki','354634523');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Duis ultricies vel tortor quis cursus. Vestibulum ultrices sapien ut sapien placerat vulputate. Donec le','mejl@mejl.pl','Rychu','Peja','453536363');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Vestibulum ullamcorper maximus nulla a posuere. Proin sollicitudin sollicitudin ipsum non ultricies. Maecenas risus augue, dignissim eget ultrices vitae, ','blabla@krzysztof.pl','Krzysztofik','Sokołowski','35435354343');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Truskawka na torcie tego eventu!','hajtor.tomeczek@tralala.pl','Tomasz','Hajto','425253223');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Gość zna się na robocie, a po robocie nie gada o robocie','Profesor.abacki@wp.pl','Adamek','Abacki','354634523');
Insert into WATPROJEKT.EVE_SPEAKERS (ID_SPEAKER,DESCRIPTION,EMAIL,FIRSTNAME,LASTNAME,PHONE) values (EVE_SPEAKER_SEQ.NEXTVAL,'Truskawka na torcie tego eventu!','hajtor.tomeczek@tralala.pl','Tomasz','Hajto','425253223');

-- PRELEKCJE
Insert into WATPROJEKT.EVE_LECTURES (ID_LECTURE,DESCRIPTION,END_TIME,LECTURE_NAME,START_TIME,ID_EVENT,ID_SPEAKER) values (EVE_LECTURE_SEQ.NEXTVAL,'Pierwsze piwko zawsze najlepiej smakuje. Posmakujcie go z ryśkiem. SLU PZDRO',to_timestamp('17/05/06 14:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Otwieranie pierwszego piwka',to_timestamp('17/05/06 13:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'21','18');
Insert into WATPROJEKT.EVE_LECTURES (ID_LECTURE,DESCRIPTION,END_TIME,LECTURE_NAME,START_TIME,ID_EVENT,ID_SPEAKER) values (EVE_LECTURE_SEQ.NEXTVAL,'Trudno złapać dobrą temperaturę jak wyngiel się nie chce palić. Razem z Arnoldem Boczkiem poznacie tajniki dobrego nagrzania i rusztu i prawdłowej konsumpcji kiełbasek z grilla.',to_timestamp('17/05/06 16:30:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Idealne przysmażenie kiełbaski',to_timestamp('17/05/06 15:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'21','19');
Insert into WATPROJEKT.EVE_LECTURES (ID_LECTURE,DESCRIPTION,END_TIME,LECTURE_NAME,START_TIME,ID_EVENT,ID_SPEAKER) values (EVE_LECTURE_SEQ.NEXTVAL,'FTP elenx jako polecenie ratuje - przykład sprawnej studenckiej olewki.',to_timestamp('17/06/10 17:40:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'Pomoce domowe LOL wieku',to_timestamp('17/06/10 16:00:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'1','1');

-- OPINIE
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Mam nadzieję, że podobnie jak w zeszłym roku zawita sporo świnek, tylko żeby kiełbaska była cieplejsza',to_timestamp('17/05/03 01:24:31,154000000','RR/MM/DD HH24:MI:SSXFF'),'kielbasiarz@sokolow.pl','4','21');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Testowa opinia 123.',to_timestamp('17/05/10 09:43:00,564000000','RR/MM/DD HH24:MI:SSXFF'),'test@mejl.pl','1','5');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Moja opinia',to_timestamp('17/05/10 10:02:54,960000000','RR/MM/DD HH24:MI:SSXFF'),'mejl@mejl.pl','4','5');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Duis diam ex, varius in magna eget, lobortis pretium lectus. Suspendisse id sapien aliquet risus ullamcorper varius vitae non nibh. Etiam ex metus, fermentum ac pulvinar quis, lacinia quis libero. ',to_timestamp('17/05/10 09:48:18,712000000','RR/MM/DD HH24:MI:SSXFF'),'meil@test.com','3','1');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Duis diam ex, varius in magna eget, lobortis pretium lectus. Suspendisse id sapien aliquet risus ullamcorper varius vitae non nibh. Etiam ex metus, fermentum ac pulvinar quis, lacinia quis libero. ',to_timestamp('17/05/10 10:01:41,897000000','RR/MM/DD HH24:MI:SSXFF'),'mejlik@gg.lp','1','5');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Było zajebiście choć pizza się szybko skończyła i piwko było ciepłe. Hostessy nadrobiły wszystko swoją wiedzą na tematy branżowe. POLECAM',to_timestamp('17/06/10 13:40:00,000000000','RR/MM/DD HH24:MI:SSXFF'),'cezarycezary@spamer.gov.pl','5','1');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Miałem grać w zeszłym roku, zamiast mnie wzieli tego co duplowi pizze zeżar. ŻENADA',to_timestamp('17/05/03 01:25:16,030000000','RR/MM/DD HH24:MI:SSXFF'),'dalibomba@dzikabomba.ru','1','21');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Testowa opinia 2 321.',to_timestamp('17/05/10 09:43:44,240000000','RR/MM/DD HH24:MI:SSXFF'),'mejl@mejlowo.com','4','5');
Insert into WATPROJEKT.EVE_OPINIONS (ID_OPINION,CONTENT,CREATE_DATE,EMAIL,RATE,ID_EVENT) values (EVE_OPINION_SEQ.NEXTVAL,'Moja opinia',to_timestamp('17/05/10 10:03:09,482000000','RR/MM/DD HH24:MI:SSXFF'),'mejl@mejl.pl','4','5');

-- UCZESTNICY
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','bleble@blibli.pl','Michał','Stachlewski','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','blablebli@hejojojo.pl','Dupelek','Elemelek','0','21');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','debil@zkonferencji.pl','Młody','Pizzożerca','1','21');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','stachlewski.m@gmail.com','michał','stachu','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','kamil.kosiorek.94@gmail.com','aaaaaaaaaaa','aaaaaaaaaaa','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','wysnilamsen@gmail.com','Gosia','Filipek','0','21');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','kowal@wp.pl','Jan','Kowalski','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','baskaGie@onet.pl','Barbara','Grawara','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','klocuch12@youtube.pl','Stefan','Klocuch','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','kamil.kosiorek.94@gmail.com','aaaddddddddddd','dddddddaaaaaaaaaa','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','kamil.kosiorek.94@gmail.com','aaaddddddddddd','dddddddaaaaaaaaaa','0','1');
Insert into WATPROJEKT.EVE_PARTICIPANTS (ID_PARTICIPANT,ACTIVATED,EMAIL,FIRSTNAME,LASTNAME,PRESENCE,ID_EVENT) values (EVE_PARTICIPANT_SEQ.NEXTVAL,'0','drugidebil@jbmt.com','Kolega','Młodego','0','21');

-- DOKUMENTY
-- TYP WYDARZENIA I ICH DOKUMENTY
