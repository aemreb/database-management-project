
create table bloodtype(
 recordid int,
 bloodtype varchar(5));

 create table membershiptype(
 recordid int not null,
 type  varchar(25) not null,
 price bigint);

create table recordtype(
 recordid int not null,
 type varchar(25) not null);

create table student(
 recordid int not null unique,
 createdatetime timestamp not null,
 modifieddatetime timestamp not null,
 identitynum varchar(30) not null,
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15) not null,
 emergencyphone varchar(15) not null,
 email varchar(15)not null unique,
 address varchar(25) not null);

create table employee(
 recordid int not null unique,
 typeid int not null,
 createdatetime timestamp not null,
 modifieddatetime timestamp not null,
 identitynum varchar(30) not null,
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15),
 emergencyphone varchar(15),
 email varchar(15),
 address varchar(25),
 salary bigint,
 offday int);

 create table healthinfo(
 recorfid int unique,
 createddatetime timestamp,
 modifieddatetimet timestamp,
 recordtypeid int,
 recordvalue int,
 billofhealth boolean,
 booltypeid int,
 height int,
 weight int);

 create table membership(
 recordid int unique,
 createddatetime timestamp,
 modifieddatetime timestamp,
 studentid int,
 membershiptypeid int,
 status boolean,
 starteddate timestamp,
 enddate timestamp,
 ispaid boolean);
