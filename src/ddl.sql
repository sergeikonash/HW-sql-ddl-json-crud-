-- Database: students_and_groups
CREATE DATABASE students_and_groups
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- Table: public.students
CREATE TABLE IF NOT EXISTS public.students
(
    student_id bigint NOT NULL DEFAULT nextval('students_student_id_seq'::regclass),
    student_name character varying(10) COLLATE pg_catalog."default",
    student_age integer,
    score numeric(2,1),
    olympic_gamer boolean,
    CONSTRAINT students_pkey PRIMARY KEY (student_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.students
    OWNER to postgres;

-- Table: public.groups
CREATE TABLE IF NOT EXISTS public.groups
(
    group_id bigint NOT NULL DEFAULT nextval('groups_group_id_seq'::regclass),
    group_number character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT groups_pkey PRIMARY KEY (group_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.groups
    OWNER to postgres;

-- Table: public.cross_table
CREATE TABLE IF NOT EXISTS public.cross_table
(
    student_id bigint NOT NULL DEFAULT nextval('cross_table_student_id_seq'::regclass),
    group_id bigint NOT NULL DEFAULT nextval('cross_table_group_id_seq'::regclass),
    CONSTRAINT cross_table_group_id_fkey FOREIGN KEY (group_id)
    REFERENCES public.groups (group_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT cross_table_student_id_fkey FOREIGN KEY (student_id)
    REFERENCES public.students (student_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cross_table
    OWNER to postgres;
