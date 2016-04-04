insert into spitter
  (id, username, password, fullname, email, update_by_email)
values
  (spitter_sid.nextval,
   'habuma',
   'password',
   'Craig Walls',
   'craig@habuma.com',
   'F');

insert into spitter
  (id, username, password, fullname, email, update_by_email)
values
  (spitter_sid.nextval,
   'artnames',
   'password',
   'Art Names',
   'artnames@habuma.com',
   'Y');

insert into spittle
  (id, spitter_id, spittleText, postedTime)
values
  (spittle_sid.nextval,
   1,
   'Have you read Spring in Action 3? I hear it is awesome!',
   to_date('2010-06-09', 'yyyy-mm-dd'));

insert into spittle
  (id, spitter_id, spittleText, postedTime)
values
  (spittle_sid.nextval,
   2,
   'Trying out Spring''s new expression language.',
   to_date('2010-06-11', 'yyyy-mm-dd'));
insert into spittle
  (id, spitter_id, spittleText, postedTime)
values
  (spittle_sid.nextval,
   1,
   'Who''s going to SpringOne/2GX this year?',
   to_date('2010-06-19', 'yyyy-mm-dd'));
