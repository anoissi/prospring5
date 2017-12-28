CREATE OR REPLACE FUNCTION getFirstNameById(in_id INT)
RETURNS VARCHAR(60) AS $firstName$
declare
	firstName VARCHAR(60);
BEGIN
  SELECT first_name into firstName FROM singer WHERE id = in_id;
  RETURN  firstName;
END;
$firstName$ LANGUAGE plpgsql;