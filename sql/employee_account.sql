-- project.employee_account definition

-- Drop table

-- DROP TABLE project.employee_account;

CREATE TABLE project.employee_account (
	id int4 NOT NULL,
	username varchar(50) NOT NULL,
	"password" varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	"position" varchar(50) NULL,
	department varchar(50) NULL,
	CONSTRAINT employee_account_password_check CHECK ((char_length((password)::text) >= 6)),
	CONSTRAINT employee_account_pkey PRIMARY KEY (id),
	CONSTRAINT employee_account_username_key UNIQUE (username)
);

-- DML
SELECT id, username, "password", first_name, last_name, "position", department
FROM project.employee_account;

INSERT INTO project.employee_account
(id, username, "password", first_name, last_name, "position", department)
VALUES(0, '', '', '', '', '', '');

UPDATE project.employee_account
SET id=0, "password"='', first_name='', last_name='', "position"='', department=''
WHERE username='';
