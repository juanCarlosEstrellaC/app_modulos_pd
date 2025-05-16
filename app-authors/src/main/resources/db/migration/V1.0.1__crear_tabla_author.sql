CREATE TABLE public.authors
(
    id      SERIAL,
    name    VARCHAR(255),
    version INTEGER STORAGE PLAIN,
    CONSTRAINT author_key PRIMARY KEY (id)
);


INSERT INTO authors(name, version) VALUES ('author1', 1);
INSERT INTO authors(name, version) VALUES ('author2', 2);
INSERT INTO authors(name, version) VALUES ('author3', 3);
