create table address
(
  id bigint not null, city varchar(50) not null, country varchar(255) not null, email varchar(255),
  identifier binary not null, phone varchar(50), street varchar(50) not null,
  zip_code varchar(10) not null, primary key (id)
);
create table person
(
  id bigint not null, first_name varchar(50) not null, identifier binary not null,
  last_name varchar(50) not null, primary key (id)
);
create table person_addresses
(
  person_id bigint not null, addresses_id bigint not null
);

alter table address drop constraint if exists UK_Address_Identifier;
alter table address add constraint UK_Address_Identifier unique (identifier);
alter table person drop constraint if exists IDX_FIRST_NAME_LAST_NAME;
alter table person add constraint IDX_FIRST_NAME_LAST_NAME unique (first_name, last_name);
alter table person drop constraint if exists UK_Person_Identifier;
alter table person add constraint UK_Person_Identifier unique (identifier);
alter table person_addresses drop constraint if exists UK_Person_Address_Id;
alter table person_addresses add constraint UK_Person_Address_Id unique (addresses_id);
create sequence hibernate_sequence start with 1 increment by 1;
alter table person_addresses add constraint FK_Person_Address foreign key (addresses_id) references address;
alter table person_addresses add constraint FK_Address_Person foreign key (person_id) references person;
