drop table  IF EXISTS album ;
drop table  IF EXISTS singer ;
CREATE TABLE SINGER (
       ID SERIAL NOT NULL 
     , FIRST_NAME VARCHAR(60) NOT NULL
     , LAST_NAME VARCHAR(40) NOT NULL
     , BIRTH_DATE DATE
     , CONSTRAINT UQ_SINGER_1 UNIQUE(FIRST_NAME, LAST_NAME)
     , PRIMARY KEY (ID)
);

CREATE TABLE ALBUM (
       ID  serial NOT NULL 
     , SINGER_ID INT NOT NULL
     , TITLE VARCHAR(100) NOT NULL
     , RELEASE_DATE DATE
     , CONSTRAINT UQ_SINGER_ALBUM_1 UNIQUE (SINGER_ID, TITLE)
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_ALBUM FOREIGN KEY (SINGER_ID)
                  REFERENCES SINGER (ID)
);

ALTER SEQUENCE singer_id_seq RESTART WITH 1;
ALTER SEQUENCE album_id_seq RESTART WITH 1;


insert into singer (first_name, last_name, birth_date)
     values ('John', 'Mayer', '1977-10-16');
insert into singer (first_name, last_name, birth_date)
     values ('Eric', 'Clapton', '1945-03-30');
insert into singer (first_name, last_name, birth_date)
     values ('John', 'Butler', '1975-04-01');


     insert into album (singer_id, title, release_date)
     values (1, 'The Search For Everything', '2017-01-20');
insert into album (singer_id, title, release_date)
     values (1, 'Battle Studies', '2009-11-17');
insert into album (singer_id, title, release_date)
     values (2, ' From The Cradle ', '1994-09-13');