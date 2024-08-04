BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

--Creating Dens
--SELECT * FROM dens;

INSERT INTO dens (den_name, creator_id)
VALUES('Hamburger', 1);

INSERT INTO dens (den_name, creator_id)
VALUES('AntiHamburger', 1);

INSERT INTO dens (den_name, creator_id)
VALUES('Cheeseburger', 1);

INSERT INTO dens (den_name, creator_id)
VALUES('SteamedHams', 1);






--Populating Posts For Testing

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 1', 'Hello World 1', 2, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 2', 'Hello World 2', 2, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 3', 'Hello World 3', 3, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 4', 'Hello World 4', 3, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 5', 'Hello World 5', 1, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 6', 'Hello World 6', 1, 1);

INSERT INTO posts (post_title, post_desc, den_id, creator_id)
VALUES('Test 7', 'Hello World 7', 1, 1);


--Test Responses


INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 1', 1, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 2', 1, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 3', 1, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 1', 2, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 1', 3, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 1', 4, 1);

INSERT INTO responses (response_desc, post_id, creator_id)
VALUES('Test Comment 1', 5, 1);




COMMIT TRANSACTION;
