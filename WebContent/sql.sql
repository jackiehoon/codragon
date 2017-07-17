select *from Person

select *from Person_Party

alter table Person_Party add(call_Time varchar(100))

alter table Party modify(title varchar(100))

select *from lecture

alter table image add(description long)

select * from image

alter table Lecture modify(locking number(1) default 0)
update lecture set locking = 0
select *from LECTURE