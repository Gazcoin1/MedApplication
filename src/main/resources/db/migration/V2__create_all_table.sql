create table medicine.public.users
(
    id           uuid        not null
        primary key,
    last_name    varchar(50) not null
        constraint users_last_name_check
            check ((last_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    first_name   varchar(50) not null
        constraint users_first_name_check
            check ((first_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    middle_name  varchar(50)
        constraint users_middle_name_check
            check ((middle_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    sex          varchar(10) not null
        constraint users_sex_check
            check ((sex)::text = ANY ((ARRAY ['Мужской'::character varying, 'Женский'::character varying])::text[])),
    birth_date   date,
    address      text,
    photo        bytea,
    phone_number varchar(11) not null
        constraint users_phone_number_check
            check ((phone_number)::text ~ '^7[0-9]{10}$'::text),
    password     varchar not null,
    email        varchar(50) not null
        unique
        constraint users_email_check
            check ((email)::text ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'::text),
    created_time timestamp   not null,
    updated_time timestamp
);

create table medicine.public.doctors
(
    id             uuid         not null
        primary key,
    last_name      varchar(50)  not null
        constraint doctors_last_name_check
            check ((last_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    first_name     varchar(50)  not null
        constraint doctors_first_name_check
            check ((first_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    middle_name    varchar(50)
        constraint doctors_middle_name_check
            check ((middle_name)::text ~ '^[А-Яа-яЁё]+$'::text),
    specialization varchar(255) not null
        constraint doctors_specialization_check
            check ((specialization)::text ~ '^[А-Яа-яЁё]+( [А-Яа-яЁё]+)*$'::text)
);

create table medicine.public.allergies
(
    id                   uuid not null
        primary key,
    patient              uuid not null
        references users,
    clinical_status      varchar(255)
        constraint allergies_clinical_status_check
            check ((clinical_status)::text ~ '^[А-Яа-яЁё]+$'::text),
    type                 varchar(255),
    category             varchar(255),
    code                 varchar(255),
    criticality          varchar(50),
    description          text,
    onset_date           date,
    recorded_date        date,
    doctor               uuid
        references doctors,
    recorder             uuid
        references doctors,
    last_occurrence_date date,
    reaction_severity    varchar(255)
        constraint allergies_reaction_severity_check
            check ((reaction_severity)::text ~ '^[А-Яа-яЁё]+$'::text),
    reaction_description text
);

create table medicine.public.conditions
(
    id              uuid not null
        primary key,
    patient         uuid not null
        references users,
    clinical_status varchar(255),
    code            varchar(255),
    body_site       varchar(255),
    severity        varchar(255),
    description     text,
    onset_date      date,
    abatement_date  date,
    recorded_date   date,
    doctor          uuid
        references doctors,
    recorder        uuid
        references doctors
);

create table medicine.public.procedures
(
    id             uuid not null
        primary key,
    patient        uuid not null
        references users,
    category       varchar(255),
    code           varchar(255),
    description    text,
    doctor         uuid
        references doctors,
    recorder       uuid
        references doctors,
    performed_date date,
    body_site      varchar(255),
    outcome        text,
    report         text
);