def replace_quotes_in_sql(file_path, output_file_path):
    try:
        # Open the original SQL file for reading
        with open(file_path, 'r', encoding='utf-8') as file:
            content = file.read()

        # Replace all double quotes with single quotes
        modified_content = content.replace('"', "'")

        # Open the new file path for writing the modified content
        with open(output_file_path, 'w', encoding='utf-8') as modified_file:
            modified_file.write(modified_content)

        print("File processed successfully. Output saved to:", output_file_path)
    except Exception as e:
        print("An error occurred:", str(e))

input_file = 'gameDataBase.db.sql'
output_file = 'output/gameDataBase.db.sql'
replace_quotes_in_sql(input_file, output_file)
