BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "genre" (
	"id"	INTEGER,
	"name"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "media" (
	"id"	INTEGER NOT NULL,
	"title"	TEXT NOT NULL,
	"title_type"	TEXT NOT NULL,
	"duration"	TEXT,
	"is_adult"	INTEGER,
	"year"	TEXT,
	"end_year"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "genre_media" (
	"genre_id"	INTEGER,
	"media_id"	INTEGER,
	FOREIGN KEY("media_id") REFERENCES "media"("id"),
	FOREIGN KEY("genre_id") REFERENCES "genre"("id"),
	PRIMARY KEY("genre_id","media_id")
);
CREATE TABLE IF NOT EXISTS "user_movie" (
	"email"	TEXT,
	"password"	TEXT NOT NULL,
	"first_name"	TEXT NOT NULL,
	"last_name"	TEXT NOT NULL,
	"birth_date"	TEXT NOT NULL,
	PRIMARY KEY("email")
);
CREATE TABLE IF NOT EXISTS "whatched_list" (
	"id"	INTEGER,
	"user_email"	TEXT NOT NULL,
	FOREIGN KEY("user_email") REFERENCES "user_movie"("email"),
	PRIMARY KEY("id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "wl_media" (
	"wl_id"	INTEGER,
	"media_id"	INTEGER,
	FOREIGN KEY("media_id") REFERENCES "media"("id"),
	FOREIGN KEY("wl_id") REFERENCES "whatched_list"("id"),
	PRIMARY KEY("wl_id","media_id")
);
COMMIT;
