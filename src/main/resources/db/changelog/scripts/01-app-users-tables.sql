CREATE TABLE if not exists app_users (
	id         INT AUTO_INCREMENT NOT NULL,
	username   VARCHAR(50)  NOT NULL,
	email      VARCHAR(100) NOT NULL,
	password   VARCHAR(120) NOT NULL,
	first_name  VARCHAR(150) NULL,
	last_name   VARCHAR(200) NULL,
	created_at datetime     NULL,
	updated_at datetime     NULL,
	UNIQUE (username),
	UNIQUE (email),
	CONSTRAINT pk_app_users PRIMARY KEY (id)
);

CREATE INDEX  idx_username ON app_users (username);

CREATE INDEX  idx_email ON app_users (email);

CREATE TABLE if not exists  app_roles (
	id INT AUTO_INCREMENT NOT NULL,
	name enum('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
	UNIQUE (name),
	CONSTRAINT pk_app_roles PRIMARY KEY (id)
);

CREATE TABLE if not exists  app_users_roles (
	app_user_id INT NOT NULL,
	app_role_id INT NOT NULL,
	CONSTRAINT pk_app_roles PRIMARY KEY (app_user_id, app_role_id)
);

insert into app_users(username, email, password, first_name, last_name, created_at, updated_at)
values ('nikleontiou@gmail.com', 'nikleontiou@gmail.com', '$2a$10$7Dq6QwfNvb0FGgvydAybPulXIZRhyXSidnAhs4EZVUuvXFdJrYy4K', 'Nikolas', 'Leontiou', '2024-05-15', '2024-05-15');

insert into app_users(username, email, password, first_name, last_name, created_at, updated_at)
values ('alibaba@gmail.com', 'alibaba@gmail.com', '$2a$05$r/74Zj2d7wrmDEB4eOiPEedQ7ec3BSr5JCBtf6pXmCWj45VsO3UUe', 'Ali', 'Baba', '2024-05-15', '2024-05-15');
insert into app_roles(name)
values ('ROLE_ADMIN');
insert into app_roles(name)
values ('ROLE_USER');

insert into app_users_roles(app_user_id, app_role_id)
values (1, 1);
insert into app_users_roles(app_user_id, app_role_id)
values (2, 2);