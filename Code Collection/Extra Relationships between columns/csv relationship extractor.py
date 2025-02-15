import csv

# Path to your original CSV file
input_csv_path = 'C:/Users/Comrade Of Light/Downloads/games.csv'
# Path where the new CSV file will be saved
output_csv_path = 'C:/Users/Comrade Of Light/Downloads/game2.csv'

# Open the original CSV file for reading
with open(input_csv_path, mode='r', encoding='utf-8') as infile:
    # Open the new CSV file for writing
    with open(output_csv_path, mode='w', encoding='utf-8', newline='') as outfile:
        # CSV reader to read the original file
        reader = csv.DictReader(infile)
        # CSV writer to write to the new file
        writer = csv.writer(outfile)
        
        # change desired variables for table
        writer.writerow(['AppID', 'Language'])
        
        # Iterate through each row in the original CSV
        for row in reader:
            app_id = row['AppID'] 

            languages = row['Full audio languages'].strip("[]").replace("'", "").split(', ')
            
            # Write an entry 
            for language in languages:
                if language:
                    writer.writerow([app_id, language])

print("New CSV file has been created with AppID-Language pairs.")
