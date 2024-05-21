from tabulate import tabulate

from data_utils import DataUtils


class ExtractCodes:

    @classmethod
    def process(cls):
        # read the files
        raw_df = DataUtils.read_file("input.txt")

        # visual representation of data
        # Not require - only for viewing purpose
        print("Initial DataFrame with fully structured table:")
        print(tabulate(raw_df, headers='keys', tablefmt='psql'))

        # Step 1: Extract by email domain lenth more than & equal 20
        filtered_df = DataUtils.filter_by_domain_length_20(raw_df)

        # Step 2: Exclude rows where ID first character is a lowercase
        filtered_df = DataUtils.exclude_when_id_first_char_is_lower(filtered_df)

        # Step 3: Sort by EMAIL address
        filtered_df = filtered_df.sort_values(by=["EMAIL"])

        # Get the only result and Print
        filtered_df = filtered_df["CODE"].head(5)
        result = filtered_df.values
        print(result)
        return result


if __name__ == '__main__':
    print("Process the data")
    result = ExtractCodes.process()
