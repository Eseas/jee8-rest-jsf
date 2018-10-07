DROP SCHEMA IF EXISTS car CASCADE;

CREATE SCHEMA car;

CREATE TABLE car.vehicles
(
    id serial PRIMARY KEY,
    licence VARCHAR(40) UNIQUE NOT NULL,
    make VARCHAR(20) NOT NULL,
    model VARCHAR(20) NOT NULL ,
    power integer NOT NULL,
    opt_lock_version INTEGER
);

select * from car.vehicles;