import pandas as pd
import os

#input and output directory
input_dir = 'C:\temporanei\CSV_progetto'
output_dir = 'C:\Users\ricca\Desktop\UNIVERSITY\ANNO3\TWEB\Progetto_TWEB\clean_csv'

#ensure output directory exists
os.makedirs(output_dir, exist_ok=True)


def data_clean():
    print('Cleaning file: {actors.csv}')

    #reading file CSV
    file_path = os.path.join(input_dir, 'actors.csv')
    df = pd.read_csv(file_path)

    #remove duplicates
    df = df.drop_duplicates()
