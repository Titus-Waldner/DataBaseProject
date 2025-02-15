import csv

def remove_character_from_csv(input_file_name, output_file_name, character_to_remove):
    """
    Removes a specific character from all fields in a CSV file and saves the result to a new file.

    Parameters:
    - input_file_name: The name of the input CSV file.
    - output_file_name: The name of the output CSV file where the modified data will be saved.
    - character_to_remove: The character to remove from the CSV file.
    """
    with open(input_file_name, mode='r', newline='', encoding='utf-8') as input_file:
        csv_reader = csv.reader(input_file)
        modified_rows = []

        for row in csv_reader:
            modified_row = [field.replace(character_to_remove, '') for field in row]
            modified_rows.append(modified_row)

    with open(output_file_name, mode='w', newline='', encoding='utf-8') as output_file:
        csv_writer = csv.writer(output_file)
        csv_writer.writerows(modified_rows)

# Example usage
character_to_remove = "," 
input_file_name = "example.csv" 
output_file_name = "modified_example.csv"

remove_character_from_csv(input_file_name, output_file_name, character_to_remove)
