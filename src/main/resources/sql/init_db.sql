CREATE TABLE LOGINLOGS(USER VARCHAR(50), ACTION VARCHAR(7), LOG_DATE DATETIME)

-- insert some data
insert into LOGINLOGS values ('admin@site.tn', 'LOGIN', '2020-09-22 01:00:00.00+00');
insert into LOGINLOGS values ('admin@site.tn', 'LOGOUT', '2020-09-22 01:30:00.00+00');
insert into LOGINLOGS values ('admin@site.tn', 'LOGIN', '2020-09-22 01:31:00.00+00');
insert into LOGINLOGS values ('user@site.tn', 'LOGOUT', '2019-10-10 02:00:01.00+00');
