
create table bloodtype(
 recordid serial,
 bloodtype varchar(5),
 primary key (recordid)
);

 create table membershiptype(
 recordid serial,
 type  varchar(25) not null,
 price bigint,
 primary key (recordid)
);

create table recordtype(
    recordid serial         not null,
    type     varchar(25) not null,
    primary key (recordid)
);

create table student(
 recordid serial not null,
 createdatetime timestamp default current_timestamp,
 modifieddatetime timestamp default current_timestamp,
 identitynum varchar(30) not null,
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15) not null,
 emergencyphone varchar(15) not null,
 email varchar(15)not null unique,
 address varchar(25) not null,
 primary key (recordid)
);

create table employee(
 recordid serial not null ,
 typeid int not null,
 createdatetime timestamp default current_timestamp,
 modifieddatetime timestamp default current_timestamp,
 identitynum varchar(30) not null,
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15),
 emergencyphone varchar(15),
 email varchar(15),
 address varchar(25),
 salary bigint,
 offday int,
 primary key (recordid),
 foreign key (typeid) references recordtype(recordid)
);

 create table healthinfo(
 recordid serial not null ,
 createddatetime timestamp default current_timestamp,
 modifieddatetimet timestamp default current_timestamp,
 recordtypeid int,
 recordvalue int,
 billofhealth boolean,
 booltypeid int,
 height int,
 weight int,
 primary key (recordid),
 foreign key (recordtypeid) references recordtype(recordid),
 foreign key (booltypeid) references bloodtype(recordid)
 );

 create table membership(
 recordid serial not null ,
 createddatetime timestamp,
 modifieddatetime timestamp,
 studentid int,
 membershiptypeid int,
 status boolean,
 starteddate timestamp,
 enddate timestamp,
 ispaid boolean,
 primary key (recordid),
 foreign key (studentid) references student(recordid),
 foreign key (membershiptypeid) references membershiptype(recordid)
 );
