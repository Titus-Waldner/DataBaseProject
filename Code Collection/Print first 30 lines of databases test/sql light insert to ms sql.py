import os

def print_first_30_lines(file_name):
    file_path = os.path.join(os.getcwd(), file_name)
    try:
        with open(file_path, 'r') as file:
            lines = file.readlines()
            for i in range(min(30, len(lines))):
                print(lines[i].strip())
    except FileNotFoundError:
        print("File not found.")

# Replace 'file_name.sql' with the name of your .sql file
file_name = 'gameDataBase.db.sql'
print_first_30_lines(file_name)
