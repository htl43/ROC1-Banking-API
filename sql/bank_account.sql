-- project.bank_account definition

-- Drop table

-- DROP TABLE project.bank_account;

CREATE TABLE project.bank_account (
	id int4 NOT NULL DEFAULT nextval('project.bank_account_sequence'::regclass),
	customer_id int4 NOT NULL,
	card_number varchar(50) NULL,
	bank_type varchar(50) NOT NULL,
	balance numeric(10,2) NOT NULL,
	status varchar(50) NOT NULL,
	alias_name varchar NULL,
	account_number int4 NULL,
	CONSTRAINT bank_account_account_number_key UNIQUE (account_number),
	CONSTRAINT bank_account_pkey PRIMARY KEY (id),
	CONSTRAINT unique_routing_number UNIQUE (card_number)
);


-- project.bank_account foreign keys

ALTER TABLE project.bank_account ADD CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES project.customer_account(id);

-- DML
INSERT INTO project.bank_account
(customer_id, card_number, bank_type, balance, status, alias_name, account_number)
VALUES(0, '', '', 0, '', '', 0);

SELECT id, customer_id, card_number, bank_type, balance, status, alias_name, account_number
FROM project.bank_account;

UPDATE project.bank_account
SET customer_id=0, card_number='', bank_type='', balance=0, status='', alias_name='', account_number=0
WHERE id=nextval('project.bank_account_sequence'::regclass);

