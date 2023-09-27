SET SQL_MODE='ALLOW_INVALID_DATES';

CREATE TABLE worker (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255)
);

CREATE TABLE shift (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       start_time TIMESTAMP,
                       end_time TIMESTAMP,
                       worker_id INT,
                       FOREIGN KEY (worker_id) REFERENCES worker(id)
);