import pandas as pd


class DataUtils:

    @staticmethod
    def read_file(path):
        raw_df = pd.read_csv("input.txt", delimiter='\s+')
        return raw_df

    @staticmethod
    def filter_by_domain_length_20(df):
        df['DOMAIN'] = df['EMAIL'].apply(lambda x: x.split('@')[1])
        filtered_df = df[df['DOMAIN'].apply(lambda x: len(x)) >= 20]
        return filtered_df

    @staticmethod
    def exclude_when_id_first_char_is_lower(df):
        df['ID_FIRST_CHAR'] = df['ID'].apply(lambda x: x[0])
        # Exclude rows where ID_FIRST_CHAR is a lowercase letter
        filtered_df = df[~df['ID_FIRST_CHAR'].str.islower()]
        return filtered_df
