create table if not exists company(
  id  integer not null  auto_increment primary key,
  name    varchar(255) not null
);

create table if not exists employee(
   id     integer not null  auto_increment primary key ,
   name   varchar(255) not null,
   age    integer,
   salary integer,
   company_id integer,
   foreign key (company_id) REFERENCES company(id)
);