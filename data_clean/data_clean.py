import csv
import numpy as np
import pandas as pd
import os
import re

#input and output directory
input_dir = r'C:\temporanei\CSV_progetto'
output_dir = r'C:\Users\ricca\Desktop\UNIVERSITY\ANNO3\TWEB\Progetto_TWEB\clean_csv'

#ensure output directory exists
os.makedirs(output_dir, exist_ok=True)

#dictionary of DataFrames
dataframes = {}

def load_csv_files(input_dir, files):
    for file_name in files:
        try:
            file_path = os.path.join(input_dir, file_name)
            print(f'Downloading {file_name} in a DataFrame...')
            dataframes[file_name] = pd.read_csv(file_path)
        except Exception as e:
            print(f'Error during downloading of {file_name}: {e}')

def save_cleaned_dataframes(output_dir):
    for file_name, df in dataframes.items():
        try:
            output_path = os.path.join(output_dir, file_name)
            df.to_csv(output_path, index=False)
            print(f'Saved {file_name} to {output_path}')
        except Exception as e:
            print(f'Error during saving {file_name}: {e}')

def clean_actors_csv(input_path, output_path):
    print(f'Cleaning file: actors.csv')


    try:
        #reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip().str.replace(';;', '', regex=False)
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    #remove duplicates
    df = df.drop_duplicates()

    #remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_actor','name','role'])

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
    print(f'Cleaning file: countries.csv')


    try:
        #reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_country', 'country'])

# remove rows with invalid types:

    # remove rows where id_country is not INT type
    df = df[pd.to_numeric(df['id_country'], errors='coerce').notnull()]
    df['id_country'] = df['id_country'].astype(int)

    # remove rows where country is not a valid String
    df = df[df['country'].apply(lambda x: isinstance(x, str) and bool(re.match(r"^[a-zA-Z\s'’-]+$", x)))]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned countries.csv saved to {output_path}")

def clean_crew_csv(input_path, output_path):
    print(f'Cleaning file: crew.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
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
    print(f'Cleaning file: genres.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_genre', 'genre'])

# remove rows with invalid types:

    # remove rows where id_genre is not INT type
    df = df[pd.to_numeric(df['id_genre'], errors='coerce').notnull()]
    df['id_genre'] = df['id_genre'].astype(int)

    # remove rows where genre is not String type
    df = df[df['genre'].apply(lambda x: isinstance(x, str) and x.isalpha())]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned genres.csv saved to {output_path}")

def clean_languages_csv(input_path, output_path):
    print(f'Cleaning file: languages.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
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
    print(f'Cleaning file: movies.csv')

    try:
        # reading file CSV with error handling
        df = pd.read_csv(input_path,
                         sep=',',
                         quotechar='"',
                         engine='python',
                         on_bad_lines='skip',  # Skip problematic lines
                         skip_blank_lines=True,
                         dtype=str,  # Read all columns as strings initially
                         na_values=['NULL', 'NaN'],  # Values to consider as NaN
                         keep_default_na=False, # not include default NaN
                         skipinitialspace=True)
        df.columns = df.columns.str.strip()     #remove spaces into column names
        df = df.apply(lambda col: col.str.strip() if col.dtype == "object" else col)  # remove unwanted spaces
        df = df.applymap(lambda x: x.strip('"') if isinstance(x, str) else x)
    except FileNotFoundError:
        print(f"File {input_path} hasn't been found.")
        return
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    print("Nomi delle colonne prima della pulizia nel DataFrame:")
    for col in df.columns:
        print(repr(col))

    df.columns = df.columns.str.replace(';', '').str.strip()

    print("Nomi delle colonne dopo la pulizia:")
    for col in df.columns:
        print(repr(col))

    def is_valid_string(value):
        if not isinstance(value, str):
            return False
        # Permette lettere (inclusi Unicode), numeri, spazi e i seguenti caratteri speciali: @, ., _, :, &, \-',.!?()[]–—
        return bool(re.match(r"^[\w\s@._:&\-',.!¡?‘’~°%®|ç§éè^“”=*#+$£/·()\[\]–—・★☆…]+$", value, re.UNICODE))

    # Conteggio iniziale delle righe
    initial_row_count = len(df)
    print(f"Numero iniziale di righe: {initial_row_count}")

    # remove duplicates
    df = df.drop_duplicates()
    after_duplicates = len(df)
    print(
        f"Righe dopo la rimozione dei duplicati: {after_duplicates} (Saltate {initial_row_count - after_duplicates} righe)")

    # remove rows with NULL or missing values in any column
    required_columns = ['id_movie', 'name', 'date', 'tagline', 'description', 'minute']
    df = df.dropna(subset=required_columns)
    after_dropna = len(df)
    print(
        f"Righe dopo la rimozione di righe con valori mancanti: {after_dropna} (Saltate {after_duplicates - after_dropna} righe)")


    # remove rows with invalid types
    before_id_movie = len(df)
    df = df[pd.to_numeric(df['id_movie'], errors='coerce').notnull()]
    df['id_movie'] = df['id_movie'].astype(int)
    after_id_movie = len(df)
    print(
        f"Righe dopo la conversione e filtraggio di 'id_movie': {after_id_movie} (Saltate {before_id_movie - after_id_movie} righe)")

    before_name = len(df)
    valid_name_mask = df['name'].apply(is_valid_string)
    df_valid_name = df[valid_name_mask]
    df_invalid_name = df[~valid_name_mask]
    after_name = len(df_valid_name)
    print(f"Righe dopo il filtro su 'name': {after_name} (Saltate {before_name - after_name} righe)")
    if not df_invalid_name.empty:
        print("Esempi di righe rimosse dal filtro 'name':")
        print(df_invalid_name.head())


    df = df[pd.to_numeric(df['date'], errors='coerce').notnull()]
    df['date'] = df['date'].astype(int)
    df = df[(df['date'] >= 1800) & (df['date'] <= 2100)]

    df = df[df['tagline'].apply(is_valid_string)]
    df = df[df['description'].apply(is_valid_string)]

    df['minute'] = df['minute'].str.replace(',', '').str.strip()
    df = df[pd.to_numeric(df['minute'], errors='coerce').notnull()]
    df['minute'] = df['minute'].astype(float)
    df = df[df['minute'] > 0]


    df['rating'] = df['rating'].str.replace(';', '').str.strip()
    df['rating'] = pd.to_numeric(df['rating'], errors='coerce')
    df['rating'] = df['rating'].fillna(0.0)  # Riempire i NaN con 0.0
    df = df[(df['rating'].isna()) | ((df['rating'] >= 0) & (df['rating'] <= 5))]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    df_sorted = df.sort_values(by='id_movie', ascending=True)
    print(df_sorted.head())
    print(f"Cleaned movies.csv saved to {output_path}")


def clean_posters_csv(input_path, output_path):
    print(f'Cleaning file: posters.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_poster', 'link'])

# remove rows with invalid types:

    # remove rows where id_poster is not INT type
    df = df[pd.to_numeric(df['id_poster'], errors='coerce').notnull()]
    df['id_poster'] = df['id_poster'].astype(int)

    # remove rows where link is not a valid url
    def is_valid_url(value):
        if not isinstance(value, str):
            return False
        # Regex for validating a URL
        url_pattern = re.compile(
            r'^(https?://)'  # Must start with http:// or https://
            r'([a-zA-Z0-9.-]+)'  # Domain name
            r'(\.[a-zA-Z]{2,})'  # Top-level domain
            r'(/.*)?$'  # Optional path
        )
        return bool(url_pattern.match(value))

    # filter rows with is_valid_url method
    df = df[df['link'].apply(is_valid_url)]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned posters.csv saved to {output_path}")


def clean_releases_csv(input_path, output_path):
    print(f'Cleaning file: releases.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_release', 'country', 'date', 'type'])

# remove rows with invalid types:

    # remove rows where id_release is not INT type
    df = df[pd.to_numeric(df['id_release'], errors='coerce').notnull()]
    df['id_release'] = df['id_release'].astype(int)

    # remove rows where country is not a string
    df = df[df['country'].apply(lambda x: isinstance(x, str) and bool(re.match(r"^[a-zA-Z\s'’-]+$", x)))]

    #remove rows where data is not format YYYY-MM-DD
    df = df[df['date'].apply(lambda x: isinstance(x, str) and bool(re.match(r'^\d{4}-\d{2}-\d{2}$', x)))]

    #remove rows where type is not a valid string
    df = df[df['type'].apply(lambda x: isinstance(x, str))]

    # remove rows where rating is not in range [0-10]
    df['rating'] = pd.to_numeric(df['rating'], errors='coerce')
    df = df[(df['rating'].isna()) | ((df['rating'] >= 0) & (df['rating'] <= 10))]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned releases.csv saved to {output_path}")

def clean_studios_csv(input_path, output_path):
    print(f'Cleaning file: studios.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip().str.replace(';', '') #correction column name
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_studio', 'studio'])

# remove rows with invalid types:

    # remove rows where id_studio is not INT type
    df = df[pd.to_numeric(df['id_studio'], errors='coerce').notnull()]
    df['id_studio'] = df['id_studio'].astype(int)
    df = df[df['studio'].apply(lambda x: isinstance(x, str) and len(x.strip()) > 0)] #verify that 'studio' is a valid type and not NULL

    # remove rows where studio is not a valid string
    df = df[df['studio'].apply(lambda x: isinstance(x, str))]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned studios.csv saved to {output_path}")

def clean_themes_csv(input_path, output_path):
    print(f'Cleaning file: themes.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['id_theme', 'theme'])

# remove rows with invalid types:

    # remove rows where id_theme is not INT type
    df = df[pd.to_numeric(df['id_theme'], errors='coerce').notnull()]
    df['id_theme'] = df['id_theme'].astype(int)

    # remove rows where theme is not a valid string
    df = df[df['theme'].apply(lambda x: isinstance(x, str) and bool(re.match(r"^[a-zA-Z0-9\s&.,'!?-]+$", x)))]

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned themes.csv saved to {output_path}")

def clean_the_oscar_awards(input_path, output_path):
    print(f'Cleaning file: the_oscar_awards.csv')


    try:
        # reading file CSV
        df = pd.read_csv(input_path)
        df.columns = df.columns.str.strip()
    except Exception as e:
        print(f"Error reading file {input_path}: {e}")
        return

    # remove duplicates
    df = df.drop_duplicates()

    # remove rows with NULL or missing values in any column
    df = df.dropna(subset=['year_film', 'year_ceremony', 'ceremony', 'category', 'name', 'film', 'winner'])

# remove rows with invalid types:

    # remove rows where year_film and year_ceremony is not INT type
    df = df[pd.to_numeric(df['year_film'], errors='coerce').notnull()]
    df['year_film'] = df['year_film'].astype(int)

    df = df[pd.to_numeric(df['year_ceremony'], errors='coerce').notnull()]
    df['year_ceremony'] = df['year_ceremony'].astype(int)

    df = df[pd.to_numeric(df['ceremony'], errors='coerce').notnull()]
    df['ceremony'] = df['ceremony'].astype(int)

    #remove rows where category is not a string type
    df = df[df['category'].apply(lambda x: isinstance(x, str))]

    #remove rows where name is not a valid string
    df = df[df['name'].apply(lambda x: isinstance(x, str) and bool(re.match(r"^[a-zA-Z\s&.,'-]+$", x)))]

    #remove rows where film is not a string type
    df = df[df['film'].apply(lambda x: pd.isna(x) or isinstance(x, str))]

    #remove rows where winner is not boolean type
    df = df[df['winner'].apply(lambda x: str(x).lower() in ['true', 'false'])]
    df['winner'] = df['winner'].apply(lambda x: True if str(x).lower() == 'true' else False)

    # save clean data in a new CSV file with path "output_path"
    df.to_csv(output_path, index=False)

    print(f"Cleaned the_oscar_awards.csv saved to {output_path}")

def main():
    input_dir = r'C:\temporanei\CSV_progetto'
    output_dir = r'C:\Users\ricca\Desktop\UNIVERSITY\ANNO3\TWEB\Progetto_TWEB\clean_csv'

    # control the existence of output directory
    os.makedirs(output_dir, exist_ok=True)

    # files to clean
    files_to_clean = {
        'actors.csv': clean_actors_csv,
        'countries.csv': clean_countries_csv,
        'crew.csv': clean_crew_csv,
        'genres.csv': clean_genres_csv,
        'languages.csv': clean_languages_csv,
        'movies.csv': clean_movies_csv,
        'posters.csv': clean_posters_csv,
        'releases.csv': clean_releases_csv,
        'studios.csv': clean_studios_csv,
        'themes.csv': clean_themes_csv,
        'the_oscar_awards.csv': clean_the_oscar_awards,
    }



    # cleaning each file
    for file_name, clean_function in files_to_clean.items():
        input_path = os.path.join(input_dir, file_name)
        output_path = os.path.join(output_dir, file_name)
        print(f"Processing {file_name}...")
        try:
            clean_function(input_path, output_path)
            print(f"Successfully cleaned {file_name}")
        except Exception as e:
            print(f"Error to clean {file_name}: {e}")

if __name__ == "__main__":
    main()