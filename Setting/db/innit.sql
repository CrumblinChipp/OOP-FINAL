-- Create the database
CREATE DATABASE IF NOT EXISTS city_simulator;

-- Switch to the database
USE city_simulator;

-- Table for player details
CREATE TABLE IF NOT EXISTS players (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Table for game records
CREATE TABLE IF NOT EXISTS game_records (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    date_played DATE NOT NULL,
    score INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES players(user_id)
);

-- Table for rankings
CREATE TABLE IF NOT EXISTS rankings (
    rank_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    best_score INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES players(user_id)
);

INSERT IGNORE INTO players (username, password) VALUES
('player1', 'password1'),
('player2', 'password2'),
('player3', 'password3'),
('player4', 'password4'),
('player5', 'password5');
