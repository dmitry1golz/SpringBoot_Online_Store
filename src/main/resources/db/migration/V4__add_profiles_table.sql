create table profiles
(
    id             bigint PRIMARY KEY,
    bio            TEXT,
    phone_number   varchar(15),
    date_of_birth  date,
    loyalty_points int unsigned default 0,
    foreign key (id) references users (id)
);