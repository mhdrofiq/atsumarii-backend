-- Sample data for Atsumarii application
-- This script will be executed on application startup to populate the database

-- Insert sample users
INSERT INTO t_users (userid, username, fullname, email, dateofbirth, bio, profileimgurl, createdat) VALUES
(1, 'alice_smith', 'Alice Smith', 'alice.smith@example.com', '1995-06-15 00:00:00', 'Software developer passionate about coding and technology.', 'https://example.com/profiles/alice.jpg', '2024-01-15 10:00:00'),
(2, 'bob_jones', 'Bob Jones', 'bob.jones@example.com', '1992-03-22 00:00:00', 'Designer and photographer who loves creating beautiful things.', 'https://example.com/profiles/bob.jpg', '2024-01-16 14:30:00'),
(3, 'carol_wilson', 'Carol Wilson', 'carol.wilson@example.com', '1998-11-08 00:00:00', 'Student studying computer science and machine learning.', 'https://example.com/profiles/carol.jpg', '2024-01-17 09:15:00'),
(4, 'david_brown', 'David Brown', 'david.brown@example.com', '1990-09-30 00:00:00', 'Project manager with experience in agile methodologies.', 'https://example.com/profiles/david.jpg', '2024-01-18 16:45:00'),
(5, 'eva_garcia', 'Eva Garcia', 'eva.garcia@example.com', '1996-12-05 00:00:00', 'Data scientist exploring the world of analytics and AI.', 'https://example.com/profiles/eva.jpg', '2024-01-19 11:20:00');

-- Insert sample groups
INSERT INTO t_groups (groupid, groupname, description, groupimgurl, createdat, creatorid) VALUES
(1, 'Tech Enthusiasts', 'A community for technology lovers to share ideas and discuss latest trends.', 'https://example.com/groups/tech.jpg', '2024-01-20 12:00:00', 1),
(2, 'Photography Club', 'Group for amateur and professional photographers to share their work.', 'https://example.com/groups/photo.jpg', '2024-01-21 15:30:00', 2),
(3, 'Study Group CS', 'Computer science students helping each other with coursework and projects.', 'https://example.com/groups/study.jpg', '2024-01-22 08:45:00', 3),
(4, 'Project Management', 'Professionals sharing best practices in project management.', 'https://example.com/groups/pm.jpg', '2024-01-23 13:15:00', 4),
(5, 'Data Science Network', 'Data scientists and analysts sharing insights and methodologies.', 'https://example.com/groups/data.jpg', '2024-01-24 10:30:00', 5);

-- Insert group memberships
INSERT INTO t_group_membership (membershipid, joinedat, userid, groupid) VALUES
-- Alice's memberships
(1, '2024-01-20 12:30:00', 1, 1), -- Alice joins Tech Enthusiasts (her own group)
(2, '2024-01-25 14:00:00', 1, 5), -- Alice joins Data Science Network
-- Bob's memberships
(3, '2024-01-21 15:45:00', 2, 2), -- Bob joins Photography Club (his own group)
(4, '2024-01-26 16:20:00', 2, 1), -- Bob joins Tech Enthusiasts
-- Carol's memberships
(5, '2024-01-22 09:00:00', 3, 3), -- Carol joins Study Group CS (her own group)
(6, '2024-01-27 11:30:00', 3, 1), -- Carol joins Tech Enthusiasts
(7, '2024-01-28 13:45:00', 3, 5), -- Carol joins Data Science Network
-- David's memberships
(8, '2024-01-23 13:30:00', 4, 4), -- David joins Project Management (his own group)
(9, '2024-01-29 15:15:00', 4, 1), -- David joins Tech Enthusiasts
-- Eva's memberships
(10, '2024-01-24 10:45:00', 5, 5), -- Eva joins Data Science Network (her own group)
(11, '2024-01-30 12:00:00', 5, 3), -- Eva joins Study Group CS
(12, '2024-01-31 14:30:00', 5, 1); -- Eva joins Tech Enthusiasts

-- Insert sample events
INSERT INTO t_events (eventid, eventname, tags, description, eventimgurl, location, start, end, createdat, groupid) VALUES
(1, 'AI Workshop 2024', 'AI,Machine Learning,Workshop', 'Hands-on workshop covering fundamentals of artificial intelligence and machine learning.', 'https://example.com/events/ai-workshop.jpg', 'Tech Hub Conference Room A', '2024-03-15 14:00:00', '2024-03-15 17:00:00', '2024-02-01 10:00:00', 1),
(2, 'Spring Photo Walk', 'Photography,Nature,Outdoor', 'Group photo walk in the city park to capture spring blossoms and landscapes.', 'https://example.com/events/photo-walk.jpg', 'Central City Park Main Entrance', '2024-04-10 09:00:00', '2024-04-10 12:00:00', '2024-02-05 15:30:00', 2),
(3, 'Algorithm Study Session', 'Algorithms,Programming,Study', 'Weekly study session focusing on data structures and algorithms preparation.', 'https://example.com/events/algo-study.jpg', 'University Library Room 201', '2024-02-20 18:00:00', '2024-02-20 20:00:00', '2024-02-10 12:15:00', 3),
(4, 'Agile Methodology Seminar', 'Agile,Scrum,Project Management', 'Deep dive into agile practices and scrum methodology for project success.', 'https://example.com/events/agile-seminar.jpg', 'Business Center Auditorium', '2024-03-08 13:00:00', '2024-03-08 16:30:00', '2024-02-15 09:45:00', 4),
(5, 'Data Visualization Meetup', 'Data Science,Visualization,Analytics', 'Exploring modern tools and techniques for effective data visualization.', 'https://example.com/events/dataviz-meetup.jpg', 'Innovation Lab Co-working Space', '2024-03-22 17:30:00', '2024-03-22 19:30:00', '2024-02-20 16:20:00', 5),
(6, 'Python Programming Bootcamp', 'Python,Programming,Beginner', 'Intensive bootcamp covering Python basics for new programmers.', 'https://example.com/events/python-bootcamp.jpg', 'Tech Hub Training Room B', '2024-04-05 10:00:00', '2024-04-05 16:00:00', '2024-02-25 11:00:00', 1),
(7, 'Portrait Photography Workshop', 'Photography,Portrait,Lighting', 'Learn professional portrait photography techniques and lighting setups.', 'https://example.com/events/portrait-workshop.jpg', 'Photography Studio Downtown', '2024-04-18 13:00:00', '2024-04-18 17:00:00', '2024-03-01 14:45:00', 2);

-- Insert event attendances
INSERT INTO t_event_attendance (attendanceid, attendance_status, registeredat, eventid, userid) VALUES
-- AI Workshop attendees
(1, 'CONFIRMED', '2024-02-02 11:30:00', 1, 1), -- Alice attending AI Workshop
(2, 'CONFIRMED', '2024-02-03 15:45:00', 1, 3), -- Carol attending AI Workshop
(3, 'CONFIRMED', '2024-02-04 09:20:00', 1, 5), -- Eva attending AI Workshop
(4, 'PENDING', '2024-02-05 16:10:00', 1, 4), -- David pending for AI Workshop

-- Spring Photo Walk attendees
(5, 'CONFIRMED', '2024-02-06 12:00:00', 2, 2), -- Bob attending Photo Walk
(6, 'CONFIRMED', '2024-02-07 14:30:00', 2, 1), -- Alice attending Photo Walk
(7, 'DECLINED', '2024-02-08 10:15:00', 2, 4), -- David declined Photo Walk

-- Algorithm Study Session attendees
(8, 'CONFIRMED', '2024-02-11 13:45:00', 3, 3), -- Carol attending Algorithm Study
(9, 'CONFIRMED', '2024-02-12 16:20:00', 3, 5), -- Eva attending Algorithm Study
(10, 'PENDING', '2024-02-13 11:30:00', 3, 1), -- Alice pending for Algorithm Study

-- Agile Methodology Seminar attendees
(11, 'CONFIRMED', '2024-02-16 10:45:00', 4, 4), -- David attending Agile Seminar
(12, 'CONFIRMED', '2024-02-17 14:15:00', 4, 1), -- Alice attending Agile Seminar
(13, 'CONFIRMED', '2024-02-18 09:30:00', 4, 2), -- Bob attending Agile Seminar

-- Data Visualization Meetup attendees
(14, 'CONFIRMED', '2024-02-21 17:00:00', 5, 5), -- Eva attending DataViz Meetup
(15, 'CONFIRMED', '2024-02-22 12:45:00', 5, 1), -- Alice attending DataViz Meetup
(16, 'CONFIRMED', '2024-02-23 15:30:00', 5, 3), -- Carol attending DataViz Meetup

-- Python Programming Bootcamp attendees
(17, 'CONFIRMED', '2024-02-26 12:15:00', 6, 3), -- Carol attending Python Bootcamp
(18, 'PENDING', '2024-02-27 14:45:00', 6, 2), -- Bob pending for Python Bootcamp
(19, 'CONFIRMED', '2024-02-28 10:30:00', 6, 5), -- Eva attending Python Bootcamp

-- Portrait Photography Workshop attendees
(20, 'CONFIRMED', '2024-03-02 15:20:00', 7, 2), -- Bob attending Portrait Workshop
(21, 'CONFIRMED', '2024-03-03 11:40:00', 7, 1), -- Alice attending Portrait Workshop
(22, 'PENDING', '2024-03-04 13:25:00', 7, 4); -- David pending for Portrait Workshop