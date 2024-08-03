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









COMMIT TRANSACTION;
