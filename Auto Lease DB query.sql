use autoleasedb;

create table payment(
	id varchar(20) not null PRIMARY KEY,
    amount DOUBLE not null,
    date_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    vid varchar(20) not null,
    foreign key (vid) references vehicles(id)
);
create table user(
	username varchar(20) not null PRIMARY KEY,
    name varchar(50) not null,
    contact varchar(20) not null,
    password varchar(50) not null
);
create table customer(
	nic varchar(20) not null primary key,
    name varchar(50) not null,
    contact varchar(20) not null,
    address varchar(50) not null
); 
CREATE TABLE leasing (
	id varchar(10) not null primary key,
    amount DOUBLE not null,
    vid varchar(20) not null,
    foreign key (vid) references vehicles(id)
);


CREATE TABLE vehicles (
	id varchar(10) not null primary key,
    vin VARCHAR(20) not null ,
    model VARCHAR(100) not null,
    year INT not null,
    price DOUBLE not null,
    image varchar(100) not null,
    status varchar(50) not null,
    employee varchar(20) not null,
    nic varchar(20) not null,
    FOREIGN KEY (employee) REFERENCES user(username),
    foreign key (nic) references customer(nic)
);

