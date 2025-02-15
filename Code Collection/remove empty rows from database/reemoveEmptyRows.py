import csv

def remove_empty_rows(input_file_name, output_file_name, emptiness_threshold=0.8):
    """
    Removes rows from a CSV file where a specified percentage of the entries are empty.

    Parameters:
    - input_file_name: The name of the input CSV file.
    - output_file_name: The name of the output CSV file with the rows removed.
    - emptiness_threshold: The fraction (between 0 and 1) of the row that must be empty for it to be removed.
    """
    with open(input_file_name, mode='r', newline='', encoding='utf-8') as input_file:
        csv_reader = csv.reader(input_file)
        rows = list(csv_reader)
        
        # Filter rows that meet the emptiness criterion
        filtered_rows = []
        for row in rows:
            empty_count = sum(1 for value in row if not value.strip())  # Count empty cells in the row
            if empty_count / len(row) < emptiness_threshold:
                filtered_rows.append(row)

    # Write the filtered data to a new CSV file
    with open(output_file_name, mode='w', newline='', encoding='utf-8') as output_file:
        csv_writer = csv.writer(output_file)
        csv_writer.writerows(filtered_rows)

# Example usage
input_file_name = "example.csv"  # Your input file name
output_file_name = "row_filtered_example.csv"  # Your output file name

remove_empty_rows(input_file_name, output_file_name)
