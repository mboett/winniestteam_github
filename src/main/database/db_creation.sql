-- Database Creation
CREATE DATABASE clef OWNER POSTGRES ENCODING = 'UTF8';

-- Connect to clef db to create data for its 'public' schema
\c clef

-- Table Creation

-- Author
CREATE TABLE Author(
	AuthorID INTEGER,
	Name VARCHAR NOT NULL,

	PRIMARY KEY (AuthorID)
);

COMMENT ON TABLE Author IS 'Represent the authors of the clef.';
COMMENT ON COLUMN Author.AuthorID IS 'The unique identifier of the authors.';
COMMENT ON COLUMN Author.Name IS 'The name of the author.';

-- Paper
CREATE TABLE Paper(
	PaperID INTEGER,
	Title VARCHAR NOT NULL,
	Year VARCHAR NOT NULL,
	MDate VARCHAR,
	Key VARCHAR,
	DOI VARCHAR NOT NULL,

	PRIMARY KEY (PaperID)
);


COMMENT ON TABLE Paper IS 'Represents a paper.';
COMMENT ON COLUMN Paper.PaperID IS 'The unique identifier of the paper.';
COMMENT ON COLUMN Paper.Title IS 'The title of the paper.';
COMMENT ON COLUMN Paper.Year IS 'The year of the pubblication of the paper.';
COMMENT ON COLUMN Paper.MDate IS 'The date of the last update of the paper.';
COMMENT ON COLUMN Paper.Key IS 'The category associated to the paper.';
COMMENT ON COLUMN Paper.DOI IS 'The DOI of the paper.';

-- Write
CREATE TABLE Write(
	AuthorID INTEGER,
	PaperID INTEGER,

	PRIMARY KEY (AuthorID, PaperID),
	FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID),
	FOREIGN KEY (PaperID) REFERENCES Paper(PaperID)
);

COMMENT ON TABLE Write IS 'Connect the authors to the papaers that he has wrote.';
COMMENT ON COLUMN Write.AuthorID IS 'The author that has wrote the paper.';
COMMENT ON COLUMN Write.PaperID IS 'The paper written by the author.';

-- User
CREATE TABLE ClefUser(
	Email VARCHAR NOT NULL,
	Password VARCHAR NOT NULL,

	PRIMARY KEY (Email)
);

COMMENT ON TABLE ClefUser IS 'Represents an user.';
COMMENT ON COLUMN ClefUser.Email IS 'The email of the user.';
COMMENT ON COLUMN ClefUser.Password IS 'The password of the user.';

-- Like
CREATE TABLE Likes(
	Email VARCHAR,
	AuthorID INTEGER,
	Liked BOOLEAN

	PRIMARY KEY (Email, AuthorID),
	FOREIGN KEY (Email) REFERENCES ClefUser(Email),
	FOREIGN KEY (AuthorID) REFERENCES Author(AuthorID)
);

COMMENT ON TABLE Likes IS 'Represents an user.';
COMMENT ON COLUMN Likes.Email IS 'The email of the user.';
COMMENT ON COLUMN Likes.AuthorID IS 'The ID of the author.';

-- Graph
-- Stand alone table
CREATE TABLE Graph (
	ID VARCHAR,
	GraphJ JSONB NOT NULL,

	PRIMARY KEY (ID)
);

COMMENT ON TABLE Graph IS 'Represents a graph.';
COMMENT ON COLUMN Graph.ID IS 'The email of the user.';
COMMENT ON COLUMN Graph.GraphJ IS 'The JSON of the graph.';
