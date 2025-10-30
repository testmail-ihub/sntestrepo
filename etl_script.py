import pandas as pd
from sqlalchemy import create_engine, text
import os

# Database connection details (replace with your actual details or environment variables)
DB_USER = os.getenv('DB_USER', 'your_db_user')
DB_PASSWORD = os.getenv('DB_PASSWORD', 'your_db_password')
DB_HOST = os.getenv('DB_HOST', 'localhost')
DB_PORT = os.getenv('DB_PORT', '5432')
DB_NAME = os.getenv('DB_NAME', 'etl_database')

# Azure Data Factory (ADF) details (replace with your actual details or environment variables)
ADF_ENDPOINT = os.getenv('ADF_ENDPOINT', 'your_adf_endpoint')
ADF_KEY = os.getenv('ADF_KEY', 'your_adf_key')

# CSV file path
CSV_FILE_PATH = 'your_data.csv' # Replace with your CSV file path

def load_csv_to_staging(file_path, engine):
    """Loads raw CSV data into a staging table."""
    try:
        df = pd.read_csv(file_path)
        df.to_sql('staging_table', engine, if_exists='replace', index=False)
        print("CSV data loaded to staging_table successfully.")
    except Exception as e:
        print(f"Error loading CSV to staging: {e}")
        raise

def execute_sql_cleaning_and_loading(engine):
    """Executes SQL queries for cleaning and loading data."""
    try:
        with open('etl_queries.sql', 'r') as f:
            sql_queries = f.read()

        with engine.connect() as connection:
            for query in sql_queries.split(';'):
                if query.strip():
                    connection.execute(text(query))
            connection.commit()
        print("SQL cleaning and loading executed successfully.")
    except Exception as e:
        print(f"Error executing SQL queries: {e}")
        raise

def push_to_adf(data):
    """Pushes data to Azure Data Factory."""
    # This is a placeholder. Actual implementation would involve ADF SDK or REST API calls.
    print(f"Pushing data to ADF endpoint: {ADF_ENDPOINT}")
    # Example: requests.post(ADF_ENDPOINT, headers={'Authorization': f'Bearer {ADF_KEY}'}, json=data)
    print("Data pushed to ADF successfully (placeholder).")

def main():
    # Create database engine
    db_connection_str = f"postgresql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    engine = create_engine(db_connection_str)

    try:
        # Step 1: Load raw CSV data into staging table
        load_csv_to_staging(CSV_FILE_PATH, engine)

        # Step 2 & 3: Clean data and load into standard table using SQL
        execute_sql_cleaning_and_loading(engine)

        # Step 4: Push data to Azure Data Factory (ADF)
        # For demonstration, we'll fetch data from the standard table to push
        with engine.connect() as connection:
            result = connection.execute(text("SELECT * FROM standard_table"))
            data_to_push = [row._asdict() for row in result]
        push_to_adf(data_to_push)

        print("ETL process completed successfully.")

    except Exception as e:
        print(f"ETL process failed: {e}")

if __name__ == "__main__":
    main()
