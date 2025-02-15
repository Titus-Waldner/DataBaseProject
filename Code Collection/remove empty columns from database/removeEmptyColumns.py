import csv

def remove_empty_columns(input_file_name, output_file_name, emptiness_threshold=0.8):
    """
    Removes columns from a CSV file where a specified percentage of the entries are empty.

    Parameters:
    - input_file_name: The name of the input CSV file.
    - output_file_name: The name of the output CSV file with the columns removed.
    - emptiness_threshold: The fraction (between 0 and 1) of the column that must be empty for it to be removed.
    """
    with open(input_file_name, mode='r', newline='', encoding='utf-8') as input_file:
        csv_reader = csv.reader(input_file)
        rows = list(csv_reader)
        column_count = len(rows[0])
        row_count = len(rows)
        
        # Count the number of empty entries in each column
        empty_counts = [0] * column_count
        for row in rows:
            for index, value in enumerate(row):
                if not value.strip():  # Adjust condition for defining "empty" if necessary
                    empty_counts[index] += 1
        
        # Identify columns to keep
        columns_to_keep = [index for index, count in enumerate(empty_counts) if count / row_count < emptiness_threshold]

        # Filter rows to remove columns
        filtered_rows = [[value for index, value in enumerate(row) if index in columns_to_keep] for row in rows]

    # Write the filtered data to a new CSV file
    with open(output_file_name, mode='w', newline='', encoding='utf-8') as output_file:
        csv_writer = csv.writer(output_file)
        csv_writer.writerows(filtered_rows)

# Example usage
input_file_name = "example.csv"  # Your input file name
output_file_name = "filtered_example.csv"  # Your output file name

remove_empty_columns(input_file_name, output_file_name)
