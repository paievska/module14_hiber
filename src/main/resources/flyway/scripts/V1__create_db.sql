CREATE TABLE client (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(201) NOT NULL,
                        CHECK (LENGTH(name) >= 3)
);

CREATE TABLE planet (
                        id VARCHAR(10) PRIMARY KEY,
                        name VARCHAR(501) NOT NULL
);

CREATE TABLE ticket (
                        id SERIAL PRIMARY KEY,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        client_id INT NOT NULL ,
                        from_planet_id VARCHAR(10) NOT NULL,
                        to_planet_id VARCHAR(10) NOT NULL,
                        FOREIGN KEY (client_id) REFERENCES client(id),
                        FOREIGN KEY (from_planet_id) REFERENCES planet(id),
                        FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);