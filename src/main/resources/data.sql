/*The ChannelMembers table represents the many-to-many relationship 
between channels and members. Each row in this table represents a 
membership of a user in a channel. The channelId and memberId
fields are foreign keys that reference the id fields in the 
Channels and Members tables, respectively.  In Posts table, channelId is a 
foreign key that references the id field in the Channels table.
this means that for every record in the Posts table, 
the value of channelId must match the id of a record in the Channels table.*/

DROP TABLE IF EXISTS Channels;
CREATE TABLE Channels (
    id INT PRIMARY KEY,
    name VARCHAR(255)

);

DROP TABLE IF EXISTS Members;
CREATE TABLE Members (
    id INT PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255)
);


DROP TABLE IF EXISTS ChannelMembers;
CREATE TABLE ChannelMembers (
    channelId INT,
    memberId INT,
    PRIMARY KEY (channelId, memberId),
    FOREIGN KEY (channelId) REFERENCES Channels(id),
    FOREIGN KEY (memberId) REFERENCES Members(id)
);

DROP TABLE IF EXISTS Posts;
CREATE TABLE Posts (
    id INT PRIMARY KEY,
    channelId INT,
    content TEXT,
    creatorId INT,
    FOREIGN KEY (channelId) REFERENCES Channels(id),
    FOREIGN KEY (creatorId) REFERENCES Members(id)
);

INSERT INTO Channels (id, name) VALUES (100, 'Happeo_channel'), (200, 'Tech_channel');
INSERT INTO Channels (id, name) VALUES (300, 'Chess Grandmasters'), (400, 'Climate Change');
INSERT INTO Channels (id, name) VALUES (500, 'Travelling'), (600, 'Fashionistas');
INSERT INTO Channels (id, name) VALUES (700, 'the_ethics'), (800, 'Fitness/Exercise');
INSERT INTO Channels (id, name) VALUES (900, 'AI'), (1000, 'Entertainment');


INSERT INTO Members (id, email, name) VALUES (1, 'member1@example.com', 'Member 1'), (2, 'member2@example.com', 'Member 2');
INSERT INTO Members (id, email, name) VALUES (3, 'membe3@example.com', 'Member 3'), (4, 'member4@example.com', 'Member 4');
INSERT INTO Members (id, email, name) VALUES (5, 'member5@example.com', 'Member 5'), (6, 'member6@example.com', 'Member 6');
INSERT INTO Members (id, email, name) VALUES (7, 'membe7@example.com', 'Member 7'), (8, 'member8@example.com', 'Member 8');
INSERT INTO Members (id, email, name) VALUES (9, 'member9@example.com', 'Member 9'), (10, 'member10@example.com', 'Member 10');


INSERT INTO ChannelMembers (channelId, memberId) VALUES (100, 1), (100, 2), (200, 1), (700, 6), (500, 10), (400, 10);
INSERT INTO ChannelMembers (channelId, memberId) VALUES (300, 1), (400, 4), (700, 7), (800, 1), (1000, 10), (200, 2);
INSERT INTO ChannelMembers (channelId, memberId) VALUES (600, 1), (600, 6), (800, 3), (1000, 7), (700, 2), (200, 10);
INSERT INTO ChannelMembers (channelId, memberId) VALUES (500, 5), (900, 9), (500, 4), (900, 5), (800, 8), (400, 5);
INSERT INTO ChannelMembers (channelId, memberId) VALUES (1000, 6), (300, 3), (700, 1), (100, 10), (400, 9), (700, 3);


INSERT INTO Posts (id, channelId, content, creatorId) VALUES (10, 100, 'This is for Happeo', 1), (20, 200, 'This is for the techies', 2);
INSERT INTO Posts (id, channelId, content, creatorId) VALUES (30, 300, 'Is the Queens Gambit overrated?', 3), (40, 400, 'Geoengineering is for sure a viable solution for CC', 4);
INSERT INTO Posts (id, channelId, content, creatorId) VALUES (50, 500, 'I find it supreme to immerse myself in local culture of places I visit', 5), (60, 600, 'Louis Vuitton is unmatched argue with your keyboard', 6);
INSERT INTO Posts (id, channelId, content, creatorId) VALUES (70, 700, 'My people is morality subjective or objective?', 7), (80, 800, 'Home workout hits more than gym one', 8);
INSERT INTO Posts (id, channelId, content, creatorId) VALUES (90, 900, 'AI is incredible but is it a Foe in disguise? Man I am confused', 9), (100, 1000, 'Breaking Bad is the GOAT of TV', 10);