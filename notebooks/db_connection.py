from sqlalchemy import create_engine
import pandas.io.sql as psql
import psycopg2 as ps
from sqlalchemy.sql import text

#functions to connect PyCharm to PostgreSQL

def get_db_engine(): #Create the connection
    db_username = "riccardocutro"
    db_password = "riccardo"
    db_host = "localhost"
    db_port = "5432"
    db_name = "Progetto_TWEB"

    engine = create_engine(f"postgresql://{db_username}:{db_password}@{db_host}:{db_port}/{db_name}?client_encoding=utf8")

    return engine

engine = get_db_engine()

try: #test if the connection is established
    with engine.connect() as conn:
        print("Connection!")

except Exception as e:
    print(f"Error {e}")


def execute_query(sql): #execute the SQL query in the files ipynb
    engine = get_db_engine()


    try:
        with engine.connect() as conn:
            conn.execute(text(sql))
            conn.commit()
            print("Success!")
    except Exception as e:
        print(f"Error {e}")
