import pandas as pd
import os
import re

#input and output directory
input_dir = 'C:\temporanei\CSV_progetto'
output_dir = 'C:\Users\ricca\Desktop\UNIVERSITY\ANNO3\TWEB\Progetto_TWEB\clean_csv'

#ensure output directory exists
os.makedirs(output_dir, exist_ok=True)


def clean_actors_csv(input_path, output_path):
    print('Cleaning file: {actors.csv}')

    try:
        #reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print("Error reading file {input_path}: {e}")
        return

    #remove duplicates
    df = df.drop_duplicates()

    #remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_actor', 'name', 'role'])




#remove rows with invalid types:

    #remove rows where id_actor is not INT type
    df = df[pd.to_numeric(df['id_actor'], errors='coerce').notnull()]
    df['id_actor'] = df['id_actor'].astype(int)

    #remove rows where name or role is not String type
    df = df[df['name'].apply(lambda x: isinstance(x, str) and x.isalpha())]
    df = df[df['role'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned actors.csv saved to {output_path}")

def clean_countries_csv(input_path, output_path):
    print('Cleaning file: {countries.csv}')

    try:
        #reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print("Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_country', 'country'])

# remove rows with invalid types:

    # remove rows where id_country is not INT type
    df = df[pd.to_numeric(df['id_country'], errors='coerce').notnull()]
    df['id_country'] = df['id_country'].astype(int)

    # remove rows where country is not String type
    df = df[df['country'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned countries.csv saved to {output_path}")

def clean_crew_csv(input_path, output_path):
    print('Cleaning file: {crew.csv}')

    try:
        # reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print("Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_crew', 'role', 'name'])

# remove rows with invalid types:

    # remove rows where id_crew is not INT type
    df = df[pd.to_numeric(df['id_crew'], errors='coerce').notnull()]
    df['id_crew'] = df['id_crew'].astype(int)

    # remove rows where role or name is not String type
    df = df[df['role'].apply(lambda x: isinstance(x, str) and x.isalpha())]
    df = df[df['name'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned crew.csv saved to {output_path}")


def clean_genres_csv(input_path, output_path):
    print('Cleaning file: {genres.csv}')

    try:
        # reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print("Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_genre', 'genre'])

    # remove rows with invalid types:

    # remove rows where id_country is not INT type
    df = df[pd.to_numeric(df['id_genre'], errors='coerce').notnull()]
    df['id_genre'] = df['id_genre'].astype(int)

    # remove rows where genre is not String type
    df = df[df['genre'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned genres.csv saved to {output_path}")

def clean_languages_csv(input_path, output_path):
    print('Cleaning file: {languages.csv}')

    try:
        # reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print("Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_language', 'type', 'language'])

    # remove rows with invalid types:

    # remove rows where id_language is not INT type
    df = df[pd.to_numeric(df['id_language'], errors='coerce').notnull()]
    df['id_language'] = df['id_language'].astype(int)

    # remove rows where type or language is not String type
    df = df[df['type'].apply(lambda x: isinstance(x, str) and x.isalpha())]
    df = df[df['language'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned languages.csv saved to {output_path}")

def clean_movies_csv(input_path, output_path):
    print('Cleaning file: {movies.csv}')

    try:
        # reading file CSV
        df = pd.read_csv(input_path)
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_movie', 'name', 'date', 'tagline', 'description', 'minute', 'rating'])

    # remove rows with invalid types:

    # remove rows where id_movie is not INT type
    df = df[pd.to_numeric(df['id_movie'], errors='coerce').notnull()]
    df['id_movie'] = df['id_movie'].astype(int)

    #remove rows where date isn't INT and not represents a valid year (1800-2100)
    df = df[pd.to_numeric(df['date'], errors='coerce').notnull()]
    df['date'] = df['date'].astype(int)
    df = df[(df['date']>=1800) & (df['date']<=2100)]

    #remove rows where minute is not a valid number
    df = df[pd.to_numeric(df['minute'], errors='coerce').notnull()]
    df['minute'] = df['minute'].astype(float)

    #remove rows where rating is not a valid number and not in range [0-10]
    df = df[pd.to_numeric(df['rating'], errors='coerce').notnull()]
    df['rating'] = df['rating'].astype(float)
    df = df[(df['rating']>=0) & (df['rating']<=10)]

    #remove rows where name or tagline or description is not string type
    def is_valid_string(value):
        if not isinstance(value, str):
            return False
        # Allow only letters, numbers, and spaces
        return bool(re.match(r'^[a-zA-Z0-9\s@._-]+$', value))

    df = df[df['name'].apply(is_valid_string)]
    df = df[df['tagline'].apply(is_valid_string)]
    df = df[df['description'].apply(is_valid_string)]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned languages.csv saved to {output_path}")
