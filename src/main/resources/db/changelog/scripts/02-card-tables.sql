CREATE TABLE if not exists  cards (
	id           INT AUTO_INCREMENT NOT NULL,
	name         VARCHAR(50)  NOT NULL,
	color        VARCHAR(6) ,
	description  VARCHAR(120) ,
	status        enum('TO_DO', 'IN_PROGRESS', 'DONE') NOT NULL,
	created_at     datetime           NULL,
	updated_at     datetime           NULL,
	app_user_id   INT NOT NULL,
	CONSTRAINT pk_cards PRIMARY KEY (id)
);

CREATE INDEX idx_cardname ON cards (name);

alter table cards add constraint fk_cards_app_users foreign key (app_user_id) references app_users (id);

CREATE INDEX idx_user ON cards (app_user_id);


