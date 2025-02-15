-- Start the transaction
BEGIN TRANSACTION;

-- Create Reviews Table
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Reviews' AND type = 'U')
    CREATE TABLE Reviews (
        app_id INT,
        app_name VARCHAR(MAX),
        review_text VARCHAR(MAX),
        review_score INT,
        review_votes INT
    );

-- Create Game Table
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Game' AND type = 'U')
    CREATE TABLE Game (
        AppID INT,
        Name VARCHAR(MAX),
        Releasedate VARCHAR(MAX),
        PackageID FLOAT,
        Estimatedowners VARCHAR(MAX),
        PeakCCU INT,
        DLCcount INT,
        Price FLOAT,
        Description VARCHAR(MAX),
        Requiredage INT,
        Headerimage VARCHAR(MAX),
        Website VARCHAR(MAX),
        Supporturl VARCHAR(MAX),
        Supportemail VARCHAR(MAX),
        Achievements INT,
        Metacriticscore INT,
        Metacriticurl VARCHAR(MAX),
        Positive INT,
        Negative INT,
        Notes VARCHAR(MAX)
);
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Category' AND type = 'U')
CREATE TABLE Category (
    AppID INT,
    CategoriesName VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Developer' AND type = 'U')
CREATE TABLE Developer (
    AppID INT,
    DevelopersName VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'LanguageAudio' AND type = 'U')
CREATE TABLE LanguageAudio (
    AppID INT,
    Language VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'LanguageInterface' AND type = 'U')
CREATE TABLE LanguageInterface (
    AppID INT,
    Language VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Movie' AND type = 'U')
CREATE TABLE Movie (
    AppID INT,
    MovieLink VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'OperatingSystem' AND type = 'U')
CREATE TABLE OperatingSystem (
    AppID INT,
    SupportedSystem VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Publisher' AND type = 'U')
CREATE TABLE Publisher (
    AppID INT,
    PublishersName VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Tag' AND type = 'U')
CREATE TABLE Tag (
    AppID INT,
    TagName VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Screenshot' AND type = 'U')
CREATE TABLE Screenshot (
    AppID INT,
    ScreenshotLinks VARCHAR(MAX)
);

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Genre' AND type = 'U')
CREATE TABLE Genre (
    AppID INT,
    GenreName VARCHAR(MAX)
);
-- Insert into Reviews Table (ensure to replace TEXT with appropriate VARCHAR or NVARCHAR types as needed)
INSERT INTO Reviews (app_id, app_name, review_text, review_score, review_votes) VALUES (10, 'Counter-Strike', 'Ruined my life.', 1, 0);

-- Commit the transaction
COMMIT;

INSERT INTO "Reviews" VALUES (10,'Counter-Strike','This will be more of a ''''my experience with this game'''' type of review, because saying things like ''''great gameplay'''' will not suit something I''ve experienced with Counter-Strike. Here you go:  I remember back in 2002 I was at a friend''s house and he was playing a game. I didn''t know the name of the game nor I had internet to find it. A few weeks passed by and another friend came over. He didn''t have a computer, so he brought a disc with a game in it. He told me that it was one of the best games and from that very moment I knew that it is going to be the game I saw at the other friend''s house. When I saw the Counter-Strike logo I was filled with gamegasm (?) and I was so happy. I was playing it hardcore. Made friends, clans, was involved in communities and even made two myself. Counter-Strike is my first game which I played competitively and it was a such an experience. Playing public servers with mods were very fun, but playing it competitively made it very intense and stressful. In a pleasant way, ofcourse. Looking at the current e-sport scene it might not seem like much but back then it was different.  Shooters these days try to be different, advanced in a way. Sometimes the most simple games like Counter-Strike are the ones that live to this day. Also, there are plenty of mods to keep your attention to this game. The gameplay is very simple - defend as a Counter-Terrorist, attack as a Terrorist to plant the bomb or save the hostages as a CT. I am sure most of you already know this and I doubt there are gamers that haven''t heard or know the gameplay of Counter-Strike, so I am sharing here more of my experience.  I wish I could find my CS Anthology account which I''ve lost since 2008. So, I decided I am going to buy this game again and here you go - more than a thousand hours played. I still play it from time to time to this day and it brings back many great memories and I sometimes even stumble upon people I''ve played with years ago. I think Counter-Strike changed gaming in a major way and we wouldn''t have many games like we have today, if this game wouldn''t exist.   I am sure many of people already have played games like CS:GO but never the roots. I doubt any of you will play it for more than an hour, because it''s much more simple and it differs a lot in my opinion from CS:GO and modern games. It''s harder though.',1,1);
INSERT INTO "Reviews" VALUES (10,'Counter-Strike','This game saved my virginity.',1,0);
INSERT INTO "Reviews" VALUES (10,'Counter-Strike','• Do you like original games? • Do you like games that don''t lag? • Do you like games you can run on low end PC''s? • Do you like games where you don''t meet children who have slept with your mother?  Then this is for you!',1,0);
INSERT INTO "Reviews" VALUES (10,'Counter-Strike','Easy to learn, hard to master.',1,1);
COMMIT;
