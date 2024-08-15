BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, dens, categories, den_category, posts, responses, response_user_vote, post_user_vote, favorites_dens;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	phone varchar(10) DEFAULT '0000000000',
	pfp_link varchar(200) DEFAULT 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723668864/icons8-user-90_uzwmpf.png',
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
	upvotes int DEFAULT 0,
	downvotes int DEFAULT 0,
	pinned boolean DEFAULT false,
	time_created timestamp(0) DEFAULT CURRENT_TIMESTAMP(0), 
	
	
	CONSTRAINT PK_post_id PRIMARY KEY (post_id),
	CONSTRAINT FK_posts_den FOREIGN KEY (den_id) REFERENCES dens(den_id),
	CONSTRAINT FK_users_posts FOREIGN KEY (creator_id) REFERENCES users(user_id)

);


CREATE TABLE responses (
	response_id SERIAL,
	response_desc TEXT NOT NULL,
	post_id int,
	creator_id int,
	upvotes int DEFAULT 0,
	downvotes int DEFAULT 0,
	pinned boolean DEFAULT false,
	time_created timestamp(0) DEFAULT CURRENT_TIMESTAMP(0),
	
	CONSTRAINT PK_response_id PRIMARY KEY (response_id),
	CONSTRAINT FK_users_responses FOREIGN KEY (creator_id) REFERENCES users(user_id),
	CONSTRAINT FK_responses_posts FOREIGN KEY (post_id) REFERENCES posts(post_id)
	
	
);

CREATE TABLE response_user_vote (
	response_user_vote_id SERIAL,
	toggle_status BOOLEAN,
	response_id int,
	user_id int,
	
	CONSTRAINT PK_response_user_vote_id PRIMARY KEY (response_user_vote_id),
	CONSTRAINT FK_response_user_vote_response FOREIGN KEY (response_id) REFERENCES responses(response_id),
	CONSTRAINT FK_response_user_vote_users FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE TABLE post_user_vote (
	post_user_vote_id SERIAL,
	toggle_status BOOLEAN,
	post_id int,
	user_id int,
	
	CONSTRAINT PK_post_user_vote_id PRIMARY KEY (post_user_vote_id),
	CONSTRAINT FK_post_user_vote_post FOREIGN KEY (post_id) REFERENCES posts(post_id),
	CONSTRAINT FK_post_user_vote_users FOREIGN KEY (user_id) REFERENCES users(user_id)

);

CREATE TABLE favorites_dens (
	favorites_dens_id SERIAL,
	toggle_status BOOLEAN,
	den_id int,
	user_id int,
	
	CONSTRAINT PK_favorites_dens_id PRIMARY KEY (favorites_dens_id),
	CONSTRAINT FK_favorites_dens_dens FOREIGN KEY (den_id) REFERENCES dens(den_id),
	CONSTRAINT FK_favorites_dens_users FOREIGN KEY (user_id) REFERENCES users(user_id)
);




--GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO final_capstone_appuser;
--ROLLBACK;
COMMIT TRANSACTION;
