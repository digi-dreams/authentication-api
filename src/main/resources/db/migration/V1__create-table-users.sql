create table users(

    id bigint not null auto_increment,
    name varchar(100),
    email varchar(100) unique,
    cpf varchar(14) unique,
    birth_date TIMESTAMP,
    phone varchar(100),
    password varchar(100) not null,
    registration_data TIMESTAMP,
    occupation varchar(100),
    primary key(id)

);