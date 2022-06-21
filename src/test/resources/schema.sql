create sequence CUS_CUSTOMER_ID_SEQ start with 1 increment by 50;
create sequence ACC_ACCOUNT_ACTIVITY_ID_SEQ start with 1 increment by 50;
create sequence ACC_ACCOUNT_ID_SEQ start with 1 increment by 50;
create sequence ACC_MONEY_TRANSFER_ID_SEQ start with 1 increment by 50;

create table ACC_ACCOUNT (id bigint not null, CREATE_DATE timestamp, CREATED_BY bigint, UPDATE_DATE timestamp, UPDATED_BY bigint, ACCOUNT_TYPE varchar(30), CANCEL_DATE timestamp, CURRENCY_TYPE varchar(30), CURRENT_BALANCE numeric(19,2), IBAN_NO varchar(26), STATUS varchar(30), ID_CUS_CUSTOMER bigint, primary key (id));
create table ACC_ACCOUNT_ACTIVITY (id bigint not null, CREATE_DATE timestamp, CREATED_BY bigint, UPDATE_DATE timestamp, UPDATED_BY bigint, ID_ACC_ACCOUNT bigint, ACTIVITY_TYPE varchar(30), AMOUNT numeric(19,2), CURRENT_BALANCE numeric(19,2), TRANSACTION_DATE timestamp, primary key (id));
create table ACC_MONEY_TRANSFER (id bigint not null, CREATE_DATE timestamp, CREATED_BY bigint, UPDATE_DATE timestamp, UPDATED_BY bigint, ID_ACC_ACCOUNT_FROM bigint, ID_ACC_ACCOUNT_TO bigint, AMOUNT numeric(19,2), DESCRIPTION varchar(100), MONEY_TRANSFER_TYPE varchar(30), TRANSACTION_DATE timestamp, primary key (id));
create table CUS_CUSTOMER (id bigint not null, CREATE_DATE timestamp, CREATED_BY bigint, UPDATE_DATE timestamp, UPDATED_BY bigint, IDENTITY_NO bigint not null, NAME varchar(100) not null, PASSWORD varchar(255) not null, SURNAME varchar(100) not null, primary key (id));