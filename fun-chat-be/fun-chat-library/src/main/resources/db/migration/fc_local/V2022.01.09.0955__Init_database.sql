CREATE TABLE IF NOT EXISTS public.role (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
    "name" varchar(255) NULL,

    CONSTRAINT role_name_unique UNIQUE ("name"),
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

INSERT INTO public.role (created_at, updated_at, "name") VALUES ( CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_USER' );
INSERT INTO public.role (created_at, updated_at, "name") VALUES ( CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'ROLE_ADMIN' );

CREATE TABLE IF NOT EXISTS public.users (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	photo_url varchar(255) NULL,
	provider varchar(255) NULL,
	"password" varchar(255) NULL,
	provider_id varchar(255) NULL,
	role_id int8 NULL,

	CONSTRAINT user_email_unique UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_fk_role FOREIGN KEY (role_id) REFERENCES public.role(id)
);

CREATE TABLE IF NOT EXISTS public.room (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	description varchar(255) NULL,
	name varchar(255) NULL,
	owner_id int8 NULL,

	CONSTRAINT room_pkey PRIMARY KEY (id),
	CONSTRAINT room_fk_user FOREIGN KEY (owner_id) REFERENCES public.users(id)
);

CREATE TABLE IF NOT EXISTS public.room_member (
	room_id int8 NOT NULL,
	user_id int8 NOT NULL,

	CONSTRAINT room_member_pkey PRIMARY KEY (room_id, user_id),
	CONSTRAINT room_member_fk_users FOREIGN KEY (user_id) REFERENCES public.users(id),
	CONSTRAINT room_member_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id)
);

CREATE TABLE IF NOT EXISTS public.message (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	"content" varchar(255) NULL,
	author_id int8 NULL,
	room_id int8 NULL,

	CONSTRAINT message_pkey PRIMARY KEY (id),
	CONSTRAINT message_fk_room FOREIGN KEY (room_id) REFERENCES public.room(id),
	CONSTRAINT message_fk_users FOREIGN KEY (author_id) REFERENCES public.users(id)
);