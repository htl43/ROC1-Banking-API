-- project.account_message definition

-- Drop table

-- DROP TABLE project.account_message;

CREATE TABLE project.account_message (
	id int4 NOT NULL,
	customer_id int4 NULL,
	"content" varchar(50) NOT NULL,
	CONSTRAINT account_message_pkey PRIMARY KEY (id)
);


-- project.account_message foreign keys

ALTER TABLE project.account_message ADD CONSTRAINT account_message_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES project.customer_account(id);