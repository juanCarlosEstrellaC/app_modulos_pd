CREATE TABLE public.authors
(
    id      SERIAL,
    name    VARCHAR(255),
    version INTEGER STORAGE PLAIN,
    CONSTRAINT author_key PRIMARY KEY (id)
);

insert into authors(name, version) value ('author1',1);
insert into authors(name, version) value ('author2',2);
insert into authors(name, version) value ('author3',3);