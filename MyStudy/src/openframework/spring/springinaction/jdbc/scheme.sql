create table SPITTER
(
  id              INTEGER not null,
  username        VARCHAR2(25) not null,
  password        VARCHAR2(25) not null,
  fullname        VARCHAR2(100) not null,
  email           VARCHAR2(50) not null,
  update_by_email CHAR(1) not null
)

alter table SPITTER
  add constraint PK_SPITTER_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
  
  
create table SPITTLE
(
  id          INTEGER not null,
  spitter_id  INTEGER not null,
  spittletext VARCHAR2(2000) not null,
  postedtime  DATE not null
)

alter table SPITTLE
  add constraint PK_SPITTLE_ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table SPITTLE
  add constraint REF_SPITTEL_ID foreign key (SPITTER_ID)
  references SPITTER (ID);
  
-- Create sequence 
create sequence SPITTER_SID
minvalue 1
maxvalue 999999999999999999999999999
start with 41
increment by 1
cache 20;

-- Create sequence 
create sequence SPITTLE_SID
minvalue 1
maxvalue 999999999999999999999999999
start with 21
increment by 1
cache 20;
