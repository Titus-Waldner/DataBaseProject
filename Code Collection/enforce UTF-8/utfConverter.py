def convert_to_utf8(input_file_name, output_file_name, current_encoding):
    """
    Reads a file with a specified encoding and writes its content to a new file in UTF-8 encoding.

    Parameters:
    - input_file_name: The name of the input file.
    - output_file_name: The name of the output file with UTF-8 encoding.
    - current_encoding: The current encoding of the input file.
    """
    # Read the content of the input file with the specified encoding
    with open(input_file_name, mode='r', encoding=current_encoding) as input_file:
        content = input_file.read()
    
    # Write the content to the output file in UTF-8 encoding
    with open(output_file_name, mode='w', encoding='utf-8') as output_file:
        output_file.write(content)

# Example usage
input_file_name = "your_input_file.txt"
output_file_name = "your_output_file_utf8.txt"
current_encoding = "ISO-8859-1"

convert_to_utf8(input_file_name, output_file_name, current_encoding)
