drop table dummy_tbl if exists;

create table dummy_tbl (
  ID INT PRIMARY KEY, 
  NAME VARCHAR(255)
);

insert into dummy_tbl 
select * from csvread ('classpath:/scripts/testdata/dummy_tbl.csv');

drop table person if exists;

create table person (
  ID INT PRIMARY KEY,
  NAME VARCHAR(255),
  AGE INT
);

insert into person 
select * from csvread ('classpath:/scripts/testdata/person.csv');