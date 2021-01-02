-- project.customer_account definition

-- Drop table

-- DROP TABLE project.customer_account;

CREATE TABLE project.customer_account (
	id int4 NOT NULL DEFAULT nextval('project.customer_account_sequence'::regclass),
	username varchar(50) NOT NULL,
	"password" varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(50) NULL,
	phone varchar(50) NULL,
	CONSTRAINT check_min_length CHECK ((length((username)::text) >= 1)),
	CONSTRAINT customer_account_password_check CHECK ((char_length((password)::text) >= 6)),
	CONSTRAINT customer_account_pkey PRIMARY KEY (id),
	CONSTRAINT customer_account_username_check CHECK ((char_length((username)::text) >= 1)),
	CONSTRAINT customer_account_username_key UNIQUE (username)
);

-- DML
SELECT id, username, "password", first_name, last_name, email, phone
FROM project.customer_account;

INSERT INTO project.customer_account
(username, "password", first_name, last_name, email, phone)
VALUES('', '', '', '', '', '');

UPDATE project.customer_account
SET id=nextval('project.customer_account_sequence'::regclass), "password"='', first_name='', last_name='', email='', phone=''
WHERE username='';
