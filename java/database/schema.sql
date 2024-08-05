BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, dens, categories, den_category, posts, responses;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE dens (
	den_id SERIAL,
	den_name VARCHAR(100) NOT NULL UNIQUE,
	den_desc TEXT NOT NULL,
	creator_id int, 
	
	CONSTRAINT PK_den PRIMARY KEY (den_id),
	CONSTRAINT FK_user_den FOREIGN KEY (creator_id) REFERENCES users(user_id)
	
);

CREATE TABLE categories (
	category_id SERIAL,
	category_name VARCHAR(100) NOT NULL UNIQUE,
	
	CONSTRAINT PK_categories PRIMARY KEY (category_id)
	
);

CREATE TABLE den_category (
	group_category_id SERIAL,
	category_id int NOT NULL,
	den_id int NOT NULL, 



	CONSTRAINT PK_den_category_id PRIMARY KEY (group_category_id),
	CONSTRAINT FK_category_den FOREIGN KEY (category_id) REFERENCES categories(category_id),
	CONSTRAINT FK_den_category FOREIGN KEY (den_id) REFERENCES dens(den_id)
);

CREATE TABLE posts (
	post_id SERIAL,
	post_title VARCHAR(120) NOT NULL,
	post_desc TEXT NOT NULL,
	den_id int NOT NULL,
	creator_id int NOT NULL,
	
	
	CONSTRAINT PK_post_id PRIMARY KEY (post_id),
	CONSTRAINT FK_posts_den FOREIGN KEY (den_id) REFERENCES dens(den_id),
	CONSTRAINT FK_users_posts FOREIGN KEY (creator_id) REFERENCES users(user_id)

);


CREATE TABLE responses (
	response_id SERIAL,
	response_desc TEXT NOT NULL,
	post_id int,
	creator_id int,
	
	CONSTRAINT PK_response_id PRIMARY KEY (response_id),
	CONSTRAINT FK_users_responses FOREIGN KEY (creator_id) REFERENCES users(user_id),
	CONSTRAINT FK_responses_posts FOREIGN KEY (post_id) REFERENCES posts(post_id)
	
);


--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO final_capstone_appuser;
--ROLLBACK;
COMMIT TRANSACTION;
