import csv
import re

def parse_data(input_file, csv_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Extract text between double quotes for both Input and Output
    input_texts = re.findall(r'Input: "([^"]*)"', content)
    output_texts = re.findall(r'Output: "([^"]*)"', content)

    # Write to the CSV file
    with open(csv_file, 'w', encoding='utf-8', newline='') as csvfile:
        csv_writer = csv.writer(csvfile)
        csv_writer.writerow(["Input", "Output"])

        for input_text, output_text in zip(input_texts, output_texts):
            csv_writer.writerow([input_text, output_text])

# Example usage
input_file_path = 'inputs.txt'
csv_file_path = 'output_data.csv'

parse_data(input_file_path, csv_file_path)