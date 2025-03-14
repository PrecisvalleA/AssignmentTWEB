from sqlalchemy import create_engine
import pandas as pd
import psycopg2 as ps
from sqlalchemy.sql import text
from dotenv import load_dotenv
import os

load_dotenv()

# Function to create the engine
def get_db_engine():
    db_username = os.getenv("DB_USERNAME")
    db_password = os.getenv("DB_PASSWORD")
    db_host = os.getenv("DB_HOST")
    db_port = os.getenv("DB_PORT")
    db_name = os.getenv("DB_NAME")

    engine = create_engine(
        f"postgresql://{db_username}:{db_password}@{db_host}:{db_port}/{db_name}?client_encoding=utf8")

    return engine


# Test the connection
engine = get_db_engine()

try:
    with engine.connect() as conn:
        print("Connection Successful!")
except Exception as e:
    print(f"Error: {e}")


# Function to execute INSERT/UPDATE/DELETE queries
def execute_query(sql):
    engine = get_db_engine()

    try:
        with engine.connect() as conn:
            conn.execute(text(sql))
            conn.commit()
            print("Query Executed Successfully!")
    except Exception as e:
        print(f"Error: {e}")


# Function to SELECT and return DataFrame
def get_dataframe(query):
    engine = get_db_engine()

    try:
        with engine.connect() as conn:
            df = pd.read_sql(query, conn)
            return df
    except Exception as e:
        print(f"Error: {e}")
        return None
