-- project."transaction" definition

-- Drop table

-- DROP TABLE project."transaction";

CREATE TABLE project."transaction" (
	id int4 NOT NULL DEFAULT nextval('project.transaction_account_sequence'::regclass),
	bank_sender_id int4 NOT NULL,
	bank_receiver_id int4 NOT NULL,
	amount numeric(10,2) NOT NULL,
	status varchar(50) NOT NULL,
	date_send date NOT NULL,
	date_receive date NULL,
	CONSTRAINT transaction_pkey PRIMARY KEY (id)
);


-- project."transaction" foreign keys

ALTER TABLE project."transaction" ADD CONSTRAINT receiver_bank_id FOREIGN KEY (bank_receiver_id) REFERENCES project.bank_account(id);
ALTER TABLE project."transaction" ADD CONSTRAINT transaction_bank_sender_id_fkey FOREIGN KEY (bank_sender_id) REFERENCES project.bank_account(id);

-- DML
SELECT id, bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive
FROM project."transaction";

INSERT INTO project."transaction"
(bank_sender_id, bank_receiver_id, amount, status, date_send, date_receive)
VALUES(0, 0, 0, '', '', '');

UPDATE project."transaction"
SET bank_sender_id=0, bank_receiver_id=0, amount=0, status='', date_send='', date_receive=''
WHERE id=nextval('project.transaction_account_sequence'::regclass);
