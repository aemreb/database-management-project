
create table blood_type(
 record_id serial,
 blood_type varchar(10),
 primary key (record_id)
);

 create table membership_type(
 record_id serial,
 type  varchar(25) not null,
 price bigint,
 primary key (record_id)
);

create table record_type(
    record_id serial         not null,
    type     varchar(25) not null,
    primary key (record_id)
);

create table student(
 record_id serial not null,
 created_datetime timestamp default current_timestamp,
 modified_datetime timestamp default current_timestamp,
 student_num int not null,
 identity_num serial not null ,/*varchar(30) not null,*/
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15) not null,
 emergency_phone varchar(15) not null,
 email varchar(50)not null unique,
 address varchar(25) not null,
 primary key (record_id),
 /*sayı kısıtı madde-3*/
 constraint CHK_Age check (age>=15)
);

create table employee(
 record_id serial not null ,
 type_id serial not null,
 created_datetime timestamp default current_timestamp,
 modified_datetime timestamp default current_timestamp,
 identity_num serial not null ,/*varchar(30) not null,*/
 name varchar(25) not null,
 surname varchar(25) not null,
 age int not null,
 phone varchar(15),
 emergency_phone varchar(15),
 email varchar(50),
 address varchar(25),
 salary bigint,
 offday int,
 primary key (record_id),
 foreign key (type_id) references record_type(record_id)
);

 create table health_info(
 record_id serial not null ,
 created_datetime timestamp default current_timestamp,
 modified_datetimet timestamp default current_timestamp,
 record_type_id serial,
 record_value int unique,
 bill_of_health boolean,
 blood_type_id serial,
 height int,
 weight int,
 primary key (record_id),
 foreign key (record_type_id) references record_type(record_id),
 foreign key (blood_type_id) references blood_type(record_id)
 );

 create table membership(
 record_id serial not null ,
 created_datetime timestamp default current_timestamp,
 modified_datetime timestamp default current_timestamp,
 student_id serial,
 membership_type_id serial,
 status boolean,
 started_date timestamp,
 end_date timestamp,
 ispaid boolean,
 primary key (record_id),
 foreign key (student_id) references student(record_id),
 foreign key (membership_type_id) references membership_type(record_id)
 );

/*sequence kullanımı öğrenci no için madde-7*/
create sequence seq_studentnum
start with 10
increment by 10;

/*arayüzden çağrılan offday report view sorgusu kullanımı madde-6 -ibrahim*/
create view offday_report as
select name || ' ' || surname, offday
from employee;

/*cursor function1 - kan grubu x olan öğrencilerin ortalama yaşı madde-10*/
create or replace function bloodtype_avg(blood numeric)
    returns numeric as $$
declare
    avg_age numeric;
    sum_age numeric;
    i numeric;
    curs cursor for select age from student s, health_info h
                    where h.record_type_id = 100 and h.record_value = s.record_id and h.blood_type_id = blood;
begin
    avg_age := 0;
    sum_age := 0;
    i := 0;
    for itr in curs loop
            sum_age := sum_age + itr.age;
            i := i + 1;
        end loop;

    if i=0 then
        return 0;
    else
        avg_age := sum_age / i;
        return avg_age;
    end if;
end;
$$ language 'plpgsql';


/*record function2 record_id girilen çalışanın isim soyisim telefon ve izin günlerini getirir madde-10*/

create type emp_info_type as (name varchar(20), surname varchar(20), phone varchar(15), offday integer);

create or replace function get_emp_info(rec_id integer)
    returns emp_info_type as $$
declare
    ret_info emp_info_type;
begin
    select name, surname, phone, offday into ret_info from employee e where e.record_id = rec_id;
    return ret_info;
end;
$$ language 'plpgsql';

/*cursor function3 membership type'a göre üyelerin kuruma toplam getirisi madde-10*/
create or replace function income(mem_type_rid numeric)
    returns numeric as $$
declare
    total_income numeric;
    cur cursor for select price from membership m, membership_type mt where m.membership_type_id = mem_type_rid
                                                                        and m.membership_type_id = mt.record_id;
begin
    total_income := 0;
    for ite in cur loop
            total_income := total_income + ite.price;
        end loop;
    return total_income;
end;
$$ language 'plpgsql';

/*Yaşları 20 nin üstünde olan öğrwnciler ile yaşları 30 un üstünde olan çalışaların "union" ile listelenmesi-ibrahim*/

select * from student where  age > 20 union select * from employee where age > 30;

/*Girilen son tarihten itibaren üye olan kullanıcı sayısı ve toplam ücret bilgisi -ibrahim*/

select count(mem.*) , sum(memt.price)
from membership mem, membership_type memt
where mem.started_date >= 'yyyy-mm-dd hh:mm:ss' and mem.ispaid = true and memt.record_id = mem.record_id;

/*Çalışanları tiplerine göre 2 den fazla izin günü -ibrahim
geçirenlerin sayısını sıralı olarak listeleme*/

select count(emp.offday) , rec.type
from employee emp
         join record_type rec
              on emp.type_id = rec.record_id
group by rec.type
having count(emp.offday) > 2
order by count(emp.offday) > 2;

/*Membershipteki ispaid true (check box) olduğunda üyelik aktife çekilir trigger1 -
  arayüzde kayıt alırken ödeme check box ı
  tıklandığında "üyelik aktif edildi" uyarısı verir*/

create trigger Activate_Membership
after update or insert on membership
for each row execute procedure Info_Message();

create or replace function Info_Message()
    returns trigger as $$
begin
    if(new.ispaid) then
        raise info '% nolu kullanıcının üyeliği aktifleşti',new.record_id;
    end if;
    return new;
end;
$$ language'plpgsql';

/*Uyelik bilgisi icerisinde end_date ayarlanmasi icin trigger*/

create trigger Assing_End_Date
after insert on membership
for each row execute  procedure update_Enddate();

create or replace function update_Enddate()
returns trigger as $$
declare
    time_for_membership bigint;
begin
    if(new.started_date is not null and new.membership_type_id is not null) then
        case new.membership_type_id
            when 1 then
                time_for_membership = 2678400; /*Bir ay*/
            when 2 then
                time_for_membership = 15724800; /*Alti ay*/
            when 3 then
                time_for_membership = 31536000; /*On iki ay*/
            when 4 then
                time_for_membership = 63072000; /*Yirmi dort ay*/
            else
                time_for_membership = 160463160; /*Bes yil*/
        end case;
    end if;
    
    update membership
    set new.end_date = to_timestamp(round(extract(epoch from  new.started_date)) + time_for_membership)
    where record_id = new.record_id;

    raise info '% nolu kullanicni sontarihi atandi',new.record_id;

    return new;
end;
$$ language 'plpgsql';

