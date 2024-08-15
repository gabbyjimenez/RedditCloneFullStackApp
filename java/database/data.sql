BEGIN TRANSACTION;



INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('carter','$2a$10$9n57HHMwx1SMw5JzDidE7eTRwqBmDrAUWGm6dufvMf/Wywtxd8ole','ROLE_USER', '6149409056', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723343728/Headshot_ipay6u.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('gabby','$2a$10$y6GtBmsotRBxLLic4H38meofYnXl.HlsvNDJNefNuEUThqzkKyeES','ROLE_USER', '8056241446', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652745/gabby_linkedin_photo_ekoo93.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('nick','$2a$10$9pK5/38EJW1eAsAcOT/DkOPdEHuQJi7/IEubPK0JRopvQeIfXWsey','ROLE_USER', '7409735912', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652594/Nick_Linkedin_Photo_z09kee.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('allison','$2a$10$D94Sx7eXi3kdChP6/P6z4OTVJSLZ3sqkSVGzHfKSNOvmIJhJ8FPUi','ROLE_USER', '8478459081', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652607/Allison_Linkedin_Photo_x2duxj.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('cole','$2a$10$2WRh8HyrF5iQBBehailgqOacoPAQO.tlRZ3WGMw6VVNdSmtksnnwK','ROLE_USER', '6146701098', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652594/Cole_Linkedin_Photo_hopcv7.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('amanda','$2a$10$mQMKEXCvK0tgzA/iMEVWfuKttQpbpu8AYRg3muTYFNEIive.CEw06','ROLE_USER', '4193085642', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652798/Screenshot_20240814_112120_Reddit_qupntb.jpg');
INSERT INTO users (username,password_hash,role,phone,pfp_link) VALUES ('andy','$2a$10$xRKKx.MHFxH1DAzcFGZIE.TI3ZPWibBY2k48hRIlGBFpyh.EjG9Ti','ROLE_USER', '4074907855', 'https://res.cloudinary.com/drtlz85pc/image/upload/v1723652798/Screenshot_20240814_112120_Reddit_qupntb.jpg');



--Creating Dens


--Real Dummy Data



INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('CorporateQuestions', 'This is your go-to page for all things related to the corporate world. Whether you are seeking advice on navigating office politics, curious about best practices in management, or have questions about workplace culture and professional growth, you have come to the right place.', 3);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('OfficePets', 'This is the ultimate spot for celebrating and sharing the adorable pets that make our workdays a little brighter. Whether you’re working from home or lucky enough to have a pet-friendly office, this is the place to showcase your furry, feathered, or scaly companions.', 4);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('WorkplaceHumor','Need a laugh to brighten up your workday? You’ve found the right place! This page is dedicated to all things funny about the corporate world and office life. Share your favorite jokes, memes, and humorous stories that capture the lighter side of work.' , 5);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('TechInTheOffice','This page is dedicated to the intersection of technology and the workplace. Whether you’re a tech enthusiast, an IT professional, or simply looking to improve your office setup, this is the place to discuss all things tech in the office environment.', 6);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('OfficeHealthTips', 'Your health and well-being are crucial, even when you’re working at a desk. This page provides tips, advice, and strategies for staying healthy and balanced in the office environment. Whether you are looking to improve your posture, manage stress, or maintain a healthy diet while working, this is the place for you.', 7);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('WorkplaceNews', 'Stay informed about the latest developments in the world of work. This page is dedicated to news, updates, and discussions relevant to workplace dynamics, corporate trends, and employment issues. Whether you’re looking for insights on industry shifts or updates on workplace policies, you’ll find it here.', 8);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('WorldNews', 'Stay updated with the latest headlines and in-depth analysis from around the globe. This page provides comprehensive coverage of significant international events, global affairs, and breaking news stories.', 3);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('MotivationalQuotes', 'This is the go to subreddit for anyone seeking a boost of inspiration and positivity. Whether you are looking for a pick-me-up, a spark of motivation, or just some words of wisdom to brighten your day, you have come to the right place.', 4);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('PizzaParties', 'Who doesn’t love a good pizza party at work? This page is all about celebrating the joy of sharing pizza in the office setting. Whether it’s a casual Friday treat, a team-building event, or a special celebration, this is the place to share and discuss all things related to pizza parties at work.', 5);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('OfficeReads', 'Looking for your next great read to enjoy during your lunch break or after work? This page is dedicated to book recommendations and discussions specifically for those who spend their days in the office. Whether you are into fiction, non-fiction, or professional development books, this is the place to find and share your literary favorites.', 6);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('Mental Health', 'Looking to focus on promoting mental well-being, offering support, and reducing stigma around mental health issues.', 7);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('Disability Inclusion', 'Looking to advocate accessibility, inclusivity, and support for employees with disabilities or different abilities.', 8);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('Environmental Advocacy', 'Looking to focus on sustainability, environmental responsibility, and promoting green practices within our organization.', 3);

INSERT INTO dens (den_name, den_desc, creator_id)
VALUES('Community Outreach', 'Dedicated to volunteerism, philanthropy, and engaging with the community to create positive social impact.', 4);




--Populating Posts For Testing

--Real Dummy Data

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Success Stories: How Has Your ERG Made a Difference?', 'Hi all! I’d love to hear about some of the positive impacts your ERGs have had. Whether it’s changes in company policy, improved workplace culture, or personal growth for members, share your success stories here. It’s inspiring to see the difference these groups can make!', 1, 3, 5, 1, true, '2024-08-01 07:45:25');

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Great Tools for ERG Leadership and Management', 'Hello! I’ve compiled a list of tools and resources that have been incredibly useful for managing our ERG. From event planning to communication platforms, these tools have helped us stay organized and effective. Feel free to check them out and add any recommendations you might have!', 1, 4, 4, 2, false, '2024-08-01 07:45:25');

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Dealing with Resistance: How to Handle Pushback Against ERGs', 'Hey Redditors, we’ve encountered some resistance from upper management regarding our ERG’s initiatives. How have you handled pushback or skepticism about your ERG’s goals? What strategies or approaches have helped you gain support and overcome obstacles?', 1, 5, 6, 1, false, '2024-08-01 07:45:25');

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Upcoming Webinar on Building Inclusive ERGs – Join Us!', 'Hi everyone! We’re hosting a webinar next week on best practices for building and sustaining inclusive ERGs. The session will cover topics like leadership, member engagement, and measuring impact. If you’re interested, here’s the [link] to sign up. Hope to see you there!', 1, 6, 7, 3, false, '2024-08-01 07:45:25');

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Breaking the Silence: Addressing Mental Health Stigma in the Workplace ', 'Focusing on discussing the various stigmas associated with mental health in the workplace. The goal would be to open up a conversation about how these stigmas can negatively impact employees, why it’s important to address them, and what the organization can do to foster a more supportive and understanding environment.', 11, 7, 10, 2, true, '2024-08-01 09:00:30');
--Dummy Data 2
INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Local Volunteer Opportunities', 'Hello, I would like to start organizing events where employees can volunteer at local sherlters, food banks, or community centers. Please let me know if that sounds like something you are interested in', 14, 4, 5, 1, true, '2024-08-01 07:45:25');

INSERT INTO posts (post_title, post_desc, den_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Fundraising and Charity Drives', 'Volunteer to plan and execute fundraising events, charity runs, or donation drives to support local nonprofits or global causes', 14, 5, 1, 0, false, '2024-08-05 08:45:25');


--Test Responses

--Real Dummy Data

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Our ERG helped implement flexible work hours, which significantly improved work-life balance for many employees. It’s been a huge win for us. What positive changes has your ERG brought about?', 1, 3, 3, 1, true, '2024-08-05 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We were able to influence the company’s diversity training programs through our ERG, leading to more inclusive practices across the board. It’s been rewarding to see the impact. What success stories do you have?', 1, 7, 4, 1, false, '2024-08-05 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Our ERG initiated a mentorship program that has helped many members advance their careers. Seeing the personal and professional growth has been incredibly fulfilling. How has your ERG made a difference?', 1, 6, 5, 0, false, '2024-08-05 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We organized a series of workshops that led to a more inclusive company culture and better team dynamics. It’s great to see the positive feedback from participants. What success stories can you share from your ERG?', 1, 4, 0, 0, false, '2024-08-05 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Our ERG played a key role in launching a new employee wellness program, which has had a positive impact on overall employee satisfaction. It’s been a great achievement for us. What successes has your ERG experienced?', 1, 5, 0, -1, false, '2024-08-05 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('One approach we used was to gather data and present it to upper management, showing how our ERG’s initiatives align with company goals and improve employee engagement. Having concrete metrics helped us gain support. What strategies have worked for you?', 3, 6, 3, 1, true, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We started by holding informal meetings with key stakeholders to understand their concerns and address them directly. Building relationships and demonstrating the ERG’s value through small wins helped us overcome resistance. How do you approach such discussions?', 3, 4, 4, 1, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Highlighting successful case studies from other companies and showing the positive impact of ERGs on their culture and performance helped us make our case. Sometimes external examples can be very persuasive. What has been your experience?', 3, 3, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Engaging allies within the organization who are supportive of our ERG’s goals has been crucial. Their advocacy helped shift the perspective of skeptics. Do you have allies who have been instrumental in supporting your ERG?', 3, 4, 0, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We focused on demonstrating quick wins and tangible benefits from our ERG activities. Showing that our initiatives lead to measurable improvements in employee satisfaction and productivity helped us gain traction. Any other effective tactics you’ve tried?', 3, 5, 0, -1, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('I believe everyone should have a better understanding of the importance of reducing mental health stigma, feel more comfortable discussing mental health topics, and be empowered to advocate for a more supportive workplace culture.', 1, 5, 9, 1, true, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('I have noticed that heavy workloads and constant pressure to meet deadlines can really take a toll on mental health. It can lead to burnout, anxiety, and even depression. I would like to discuss how we can find a better balance between productivity and mental well-being. What strategies or resources can we implement to help employees manage stress and workload more effectively? How can managers be more supportive in recognizing when someone is struggling?', 1, 3, 1, 0, false, '2024-08-10 10:55:25');

--Dummy Data 2

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('This could be a great way for employees to bond while giving back to the community. We can rotate the volunteer locations monthly to keep things fresh.', 1, 5, 1, 0, true, '2024-08-15 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Let us also consider organizing family-friendly volunteer events so employees can involve their loved ones.', 1, 6, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('Let us also consider organizing family-friendly volunteer events so employees can involve their loved ones.', 1, 6, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We could tie these initiatives to our company core values, making our outreach efforts even more meaningful.', 1, 7, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('This is an excellent opportunity for employees to develop leadership skills while making a difference in young peoples lives.', 1, 8, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('It might be impactful to have employees nominate the charities we support, fostering a personal connection to the cause.', 2, 4, 1, 0, true, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We should consider matching employee donations to amplify the impact of our fundraising efforts.', 2, 5, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We should consider matching employee donations to amplify the impact of our fundraising efforts.', 2, 5, 1, 0, false, '2024-08-10 09:55:25');

INSERT INTO responses (response_desc, post_id, creator_id, upvotes, downvotes, pinned, time_created)
VALUES('We could partner with local schools or organizations to create a structured mentorship program with clear goals and outcomes.', 1, 6, 0, 0, false, '2024-08-10 09:55:25');



--Test Categories
--SELECT * FROM categories

--Real Dummy Categories
INSERT INTO categories (category_name)
VALUES('Diversity and Inclusion');

INSERT INTO categories (category_name)
VALUES('Professional Development');

INSERT INTO categories (category_name)
VALUES('Abilities and Accessibility');

INSERT INTO categories (category_name)
VALUES('Work-Life Balance');

INSERT INTO categories (category_name)
VALUES('Sustainability and Social Impact');

INSERT INTO categories (category_name)
VALUES('Health and Wellness');

INSERT INTO categories (category_name)
VALUES('Global Insights and Industry Trends');

--Linking Den and Category

INSERT INTO den_category (category_id, den_id)
VALUES (1 , 7);

INSERT INTO den_category (category_id, den_id)
VALUES (2 , 1);

INSERT INTO den_category (category_id, den_id)
VALUES (2 , 4); 

INSERT INTO den_category (category_id, den_id)
VALUES (2 , 6); 

INSERT INTO den_category (category_id, den_id)
VALUES (3 , 11);

INSERT INTO den_category (category_id, den_id)
VALUES (3 , 12);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 2);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 3);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 5);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 8);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 9);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 10);

INSERT INTO den_category (category_id, den_id)
VALUES (4 , 11);

INSERT INTO den_category (category_id, den_id)
VALUES (5 , 13);

INSERT INTO den_category (category_id, den_id)
VALUES (5 , 14);

INSERT INTO den_category (category_id, den_id)
VALUES (5 , 7);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 5);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 8);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 3);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 9);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 10);

INSERT INTO den_category (category_id, den_id)
VALUES (6 , 11);

INSERT INTO den_category (category_id, den_id)
VALUES (7 , 6);

INSERT INTO den_category (category_id, den_id)
VALUES (7 , 7);


--ADDING FAVORITES

INSERT INTO favorites_dens (toggle_status, den_id, user_id)
VALUES (true, 11, 1);

INSERT INTO favorites_dens (toggle_status, den_id, user_id)
VALUES (true, 14, 2);

INSERT INTO favorites_dens (toggle_status, den_id, user_id)
VALUES (true, 1, 3);


--ROLLBACK
COMMIT TRANSACTION;
