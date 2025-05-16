ALTER TABLE books_authors
DROP
CONSTRAINT fk_books_authors_on_books;

ALTER TABLE inventory
DROP
CONSTRAINT fk_inventory_on_isbn;

DROP TABLE books CASCADE;

DROP TABLE inventory CASCADE;