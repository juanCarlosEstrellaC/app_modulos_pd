CREATE TABLE public.book_author
(
    book_id   INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id)
        REFERENCES public.authors (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
