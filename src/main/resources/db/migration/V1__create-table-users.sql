create table users(

    id SERIAL not null,
    name varchar(100),
    email varchar(100) unique,
    cpf varchar(14) unique,
    birth_date TIMESTAMP,
    phone varchar(100),
    password varchar(100) not null,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    occupation varchar(100),
    primary key(id)

);