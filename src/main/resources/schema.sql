CREATE SEQUENCE user_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.bank_users
(
    id bigint auto_increment,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    gender varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    state varchar(255) NOT NULL,
    zip varchar(255) NOT NULL,
    country varchar(255) NOT NULL,
    phone_number varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    ip varchar(255) NULL,

    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT bank_users_pkey PRIMARY KEY (id)
);

INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('Terseer', 'Agbe', '39', 'M', 'Makurdi', 'Benue', '9923407', '09034355876', 'test@gmail.com', '172.10.207.7', '2022-09-10 14:47:55.223');
INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('John', 'Doe', '39', 'M', 'Imo', 'Owere', '9423407', '09014355876', 'imo@gmail.com', '172.10.204.7', '2022-09-10 14:47:55.223');
INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('Mark', 'John', '39', 'M', 'Abuja', 'FCT', '9923400', '09034351876', 'fct@gmail.com', '172.10.201.7', '2022-09-10 14:47:55.223');
INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('David', 'Mark', '39', 'M', 'Jos', 'Pleatue', '9900407', '09011355876', 'jos@gmail.com', '172.10.202.7', '2022-09-10 14:47:55.223');
INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('Peter', 'Luck', '39', 'M', 'Lagos', 'Lagos', '9911407', '09034355800', 'lagos@gmail.com', '172.10.203.7', '2022-09-10 14:47:55.223');
INSERT INTO bank_users (first_name, last_name, gender, city, state, zip, country, phone_number, email, ip, created_at) VALUES ('Merchant', 'Account', '39', 'M', 'Lagos', 'Lagos', '9911407', '09034355811', 'merchant@gmail.com', '172.10.203.7', '2022-09-10 14:47:55.223');

ALTER SEQUENCE user_id_seq RESTART WITH 7;


