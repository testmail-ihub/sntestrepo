-- SQL statements for creating staging, standard, and bad records tables

-- Staging Table: To load raw CSV data
CREATE TABLE IF NOT EXISTS staging_table (
    id SERIAL PRIMARY KEY,
    column1 VARCHAR(255),
    column2 VARCHAR(255),
    -- Add other columns as per your CSV structure
    load_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Standard Table: To store cleaned and transformed data
CREATE TABLE IF NOT EXISTS standard_table (
    id SERIAL PRIMARY KEY,
    column1 VARCHAR(255) UNIQUE,
    column2 VARCHAR(255),
    -- Add other columns as per your cleaned data structure
    processed_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bad Records Table: To store records that failed cleaning or validation
CREATE TABLE IF NOT EXISTS bad_records_table (
    id SERIAL PRIMARY KEY,
    original_data TEXT,
    error_message TEXT,
    error_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SQL statements for cleaning data from the staging table and inserting into the standard table

-- Function to move bad records to bad_records_table
CREATE OR REPLACE FUNCTION move_to_bad_records(p_original_data TEXT, p_error_message TEXT)
RETURNS VOID AS $$
BEGIN
    INSERT INTO bad_records_table (original_data, error_message)
    VALUES (p_original_data, p_error_message);
END;
$$ LANGUAGE plpgsql;

-- Cleaning and Loading Procedure
CREATE OR REPLACE PROCEDURE clean_and_load_data()
LANGUAGE plpgsql
AS $$
DECLARE
    r record;
BEGIN
    -- Remove duplicates from staging_table and insert into standard_table
    -- Example: Assuming 'column1' is a unique identifier
    FOR r IN (SELECT DISTINCT ON (column1) * FROM staging_table ORDER BY column1, load_timestamp DESC)
    LOOP
        BEGIN
            INSERT INTO standard_table (column1, column2)
            VALUES (r.column1, r.column2);
        EXCEPTION
            WHEN unique_violation THEN
                -- Handle duplicates or other validation errors by moving to bad records
                PERFORM move_to_bad_records(ROW_TO_JSON(r)::TEXT, 'Duplicate or invalid record for column1: ' || r.column1);
            WHEN OTHERS THEN
                PERFORM move_to_bad_records(ROW_TO_JSON(r)::TEXT, SQLERRM);
        END;
    END LOOP;

    -- Clear staging table after processing
    TRUNCATE TABLE staging_table;
END;
$$;

-- Example of how to call the cleaning and loading procedure:
-- CALL clean_and_load_data();