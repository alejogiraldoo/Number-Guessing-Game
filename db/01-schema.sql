/*
 * TABLES
 */
CREATE TABLE levels(
    level_id INT AUTO_INCREMENT,
    name VARCHAR(20) UNIQUE NOT NULL,
    chances INT UNIQUE NOT NULL,

    PRIMARY KEY( level_id )
);

CREATE TABLE streaks(
    streak_id INT AUTO_INCREMENT,
    level_id INT UNIQUE NOT NULL,
    consecutive_wins INT DEFAULT 0,
    max_consecutive_wins INT DEFAULT 0,

    PRIMARY KEY( streak_id ),
    FOREIGN KEY( level_id ) REFERENCES levels( level_id ) ON DELETE CASCADE
);

CREATE TABLE result_types(
    result_type_id INT AUTO_INCREMENT,
    name VARCHAR(10) UNIQUE NOT NULL,

    PRIMARY KEY( result_type_id )
);

CREATE TABLE rounds(
    round_id INT AUTO_INCREMENT,
    level_id INT NOT NULL,
    result_type_id INT NOT NULL,
    attempts INT NOT NULL,
    taken_time TIME NOT NULL,
    guessing_number INT NOT NULL,

    PRIMARY KEY( round_id ),
    FOREIGN KEY( level_id ) REFERENCES levels( level_id ) ON DELETE CASCADE,
    FOREIGN KEY( result_type_id ) REFERENCES result_types( result_type_id )
);

/*
 * VIEWS
 */
CREATE VIEW best_round_in_levels AS
SELECT
    l.name AS difficulty_level,
    (
        SELECT
            MAX( ( 1 + l.chances - r.attempts ) * 100 / l.chances ) AS round_precision_pct
        FROM rounds r
                 INNER JOIN result_types rt
                            ON r.result_type_id = rt.result_type_id
        WHERE r.level_id = l.level_id AND rt.name = 'Win'
    ) AS best_round_precision_pct,
    (
        SELECT
            MIN( r.taken_time )
        FROM rounds r
                 INNER JOIN result_types rt
                            ON r.result_type_id = rt.result_type_id
        WHERE r.level_id = l.level_id AND r.level_id = l.level_id AND rt.name = 'Win'
        GROUP BY r.taken_time
        HAVING best_round_precision_pct = MAX( ( 1 + l.chances - r.attempts ) * 100 / l.chances )
        ORDER BY r.taken_time ASC
              LIMIT 1
    ) AS taken_time
FROM levels l;

CREATE VIEW levels_precision_rate AS
SELECT
    name AS difficulty_level,
    ( rounds_precision_pct / rounds ) AS level_precision_pct
FROM (
    SELECT
        l.name,
        SUM(
                CASE
                    WHEN rt.name = 'Loss' THEN 0
                    ELSE ( 1 + l.chances - r.attempts ) * 100 / l.chances
                    END
        ) AS rounds_precision_pct,
        COUNT(*) AS rounds
    FROM levels l
        INNER JOIN rounds r
        ON l.level_id = r.level_id
        INNER JOIN result_types rt
        ON r.result_type_id = rt.result_type_id
         GROUP BY l.name
) AS level_stats;

CREATE VIEW levels_streak AS
SELECT l.name AS difficulty_level, s.consecutive_wins, s.max_consecutive_wins
FROM streaks s
INNER JOIN levels l
ON s.level_id = l.level_id;