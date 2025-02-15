import csv


import pandas as pd

# Path to your Excel file
input_excel_path = 'C:/Users/Comrade Of Light/Downloads/backup - Copy.xlsx'

# Output directory where CSV files will be saved
output_dir = 'C:/Users/Comrade Of Light/Downloads/output/'

# Load the Excel file
xls = pd.ExcelFile(input_excel_path)

# Loop through each sheet in the Excel file
for sheet_name in xls.sheet_names:
    # Read the sheet into a DataFrame
    df = pd.read_excel(xls, sheet_name=sheet_name)
    
    # Define the output CSV file path
    output_csv_path = f'{output_dir}{sheet_name}.csv'
    
    # Save the DataFrame to a CSV file
    df.to_csv(output_csv_path, index=False)
    print(f'Sheet "{sheet_name}" has been saved to "{output_csv_path}"')

print("All sheets have been exported to CSV files.")
