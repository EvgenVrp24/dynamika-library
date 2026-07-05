CREATE TABLE book (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      author VARCHAR(255) NOT NULL,
                      isbn VARCHAR(13) UNIQUE NOT NULL
);

CREATE TABLE client (
                        id BIGSERIAL PRIMARY KEY,
                        full_name VARCHAR(255) NOT NULL,
                        date_of_birth DATE NOT NULL
);

CREATE TABLE borrowing (
                           id BIGSERIAL PRIMARY KEY,
                           client_id BIGINT NOT NULL,
                           book_id BIGINT NOT NULL,
                           borrowed_date DATE NOT NULL,
                           FOREIGN KEY (client_id) REFERENCES client(id),
                           FOREIGN KEY (book_id) REFERENCES book(id)
);