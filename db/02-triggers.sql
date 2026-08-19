DELIMITER $$
CREATE TRIGGER tg_create_streak_log
    AFTER INSERT
    ON levels
    FOR EACH ROW
BEGIN
    INSERT INTO streaks( level_id ) VALUES ( NEW.level_id );
END$$

DELIMITER ;

DELIMITER $$
    CREATE TRIGGER tg_validate_attempts
        BEFORE INSERT
        ON rounds
        FOR EACH ROW
    BEGIN
        DECLARE allowed_attempts INT;

        SELECT chances INTO allowed_attempts FROM levels WHERE level_id = NEW.level_id;

        IF NEW.attempts > allowed_attempts THEN
		SIGNAL SQLSTATE '45000'
	    SET MESSAGE_TEXT = 'Error: Round attempts exceed level chances.';
    END IF;
END$$

DELIMITER ;

DELIMITER $$
    CREATE TRIGGER tg_update_streak
        AFTER INSERT
        ON rounds
        FOR EACH ROW
    BEGIN
        DECLARE round_result VARCHAR(10);
	DECLARE level_wins INT;
	DECLARE max_level_wins INT;

        SELECT rt.name INTO round_result
        FROM rounds rs
                 INNER JOIN result_types rt
                            ON rs.result_type_id = rt.result_type_id
        WHERE rs.round_id = NEW.round_id;

        IF round_result = 'Win' THEN
        UPDATE streaks SET consecutive_wins = consecutive_wins + 1
        WHERE level_id = NEW.level_id;

        SELECT consecutive_wins INTO level_wins
        FROM streaks
        WHERE level_id = NEW.level_id;

        SELECT max_consecutive_wins INTO max_level_wins
        FROM streaks
        WHERE level_id = NEW.level_id;

        IF level_wins > max_level_wins THEN
        UPDATE streaks SET max_consecutive_wins = level_wins
        WHERE level_id = NEW.level_id;
    END IF;
    ELSE
    UPDATE streaks SET consecutive_wins = 0
    WHERE level_id = NEW.level_id;
END IF;
END$$

DELIMITER ;