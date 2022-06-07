create table account (
    account_id bigint NOT NULL AUTO_INCREMENT, 
    account_number varchar(255), 
    balance decimal(19,2), 
    created_at date, 
    enabled boolean, 
    password varchar(255) not null, 
    agency_id bigint not null, 
    primary key (account_id)
);

create table agency (
    agency_id bigint NOT NULL AUTO_INCREMENT, 
    agency_number varchar(255), 
    primary key (agency_id)
);

create table bank_statement (
    statement_id bigint NOT NULL AUTO_INCREMENT, 
    action varchar(255), 
    amount decimal(19,2), 
    created_at timestamp, 
    information varchar(255), 
    receiver_id bigint, 
    payer_id bigint, 
    primary key (statement_id)
);

create table manager (
    user_id bigint NOT NULL AUTO_INCREMENT,
    agency_id bigint, 
    primary key (user_id)
);

create table user (
    user_id bigint NOT NULL AUTO_INCREMENT ,
    cpf varchar(255) not null, 
    created_at date, 
    email varchar(255), 
    enabled boolean, 
    last_name varchar(255) not null, 
    middle_name varchar(255), 
    name varchar(255) not null, 
    password varchar(255) not null, 
    user_type varchar(255) not null, 
    account_id bigint, 
    primary key (user_id)
);

alter table account add constraint UNIQUE_ACOUNT_NUMBER unique (account_number);
alter table user add constraint UNIQUE_EMAIL unique (email);
alter table account add constraint FK_ACCOUNT_AGENCY foreign key (agency_id) references agency(agency_id);
alter table bank_statement add constraint FK_STATEMENT_RECEIVER foreign key (receiver_id) references account(account_id);
alter table bank_statement add constraint FK_STATEMENT_PAYER foreign key (payer_id) references account(account_id);
alter table manager add constraint FK_MANAGER_AGENCY foreign key (agency_id) references agency(agency_id);
alter table manager add constraint FK_MANAGER_USER foreign key (user_id) references user(user_id);
alter table user add constraint FK_USER_ACCOUNT foreign key (account_id) references account(account_id);