import json
import re

def parse_data(input_file, jsonl_file):
    with open(input_file, 'r', encoding='utf-8') as f:
        content = f.read()

    # Extract text between double quotes for both Input and Output
    input_texts = re.findall(r'Input: "([^"]*)"', content)
    output_texts = re.findall(r'Output: "([^"]*)"', content)

    # Write to the JSONL file
    with open(jsonl_file, 'w', encoding='utf-8') as jsonl_file:
        for input_text, output_text in zip(input_texts, output_texts):
            entry = {"input": input_text, "output": output_text}
            jsonl_file.write(json.dumps(entry) + '\n')

# Example usage
input_file_path = 'inputs.txt'
jsonl_file_path = 'output_data.jsonl'

parse_data(input_file_path, jsonl_file_path)
