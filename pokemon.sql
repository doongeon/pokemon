show databases;

show tables;

delete from pokemon where id = 3;

select * from pokemon where otherType like "";

select * from capturedPokemon;


create table pokemon (
    id int primary key auto_increment,
    name varchar(30) not null unique,
    type varchar(30),
    otherType varchar(30),
    hp int,
    attack int,
    defense int
);

create table capturedPokemon (
    id int primary key auto_increment,
    pokemonId int,
    name varchar(30),
    constraint foreign key(pokemonId) references pokemon(id)
);



