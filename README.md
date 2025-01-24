# Assignment 7-1: Sum the Scores

Name:

ID:

## Problem Overview

**Problem Statement:**

Write a program that processes a CSV file containing names and scores, calculates the total score for each name, and produces an output file with the results. Specifically, you will implement a static method `sum(String inputFilename, String outputFilename)` in the `App` class to read the input file, compute the sums, and write the results to the output file.

The input file is in CSV format and contains:

- A header row with column names: `name,score1,score2,...,scoreN`, where the number of score columns (`N`) can vary.
- Subsequent rows with a name followed by the same number of integer scores for every line.

The output file should contain one row per name, with the name and the total of their scores, separated by a comma.

Your solution should dynamically handle input files with varying numbers of scores per row.

## Setup and Environment

- **WARNING:** Do not modify the `.github` or `gradle-tests` directories. These directories are essential for the automated grading process, and any changes may result in incorrect grading.

- You will need to create a new Maven project in this assignment. Refer to the provided "Maven Basics" document for instructions. The project name (_artifact ID_) should be `lab72`.
- Once the project is created, you will need to implement all the required classes and methods according to the specifications.
- To compile your program, execute `mvn compile`. To run the compiled program, execute `java -cp target/classes com.lab.App` or `mvn exec:java "-Dexec.mainClass=com.lab.App"`.

## Requirements and Specifications

### Class/Method Specifications

- `App` class:
  - Implement a static method:
    - `public static void sum(String inputFilename, String outputFilename)`
      - Input Parameters:
        - `inputFilename`: The name or path of the input CSV file.
        - `outputFilename`: The name or path of the output file.
      - Functionality:
        - Read the input file.
        - Parse the data to compute the total score for each name, dynamically handling the number of scores per row.
        - Write the name and total score for each row to the output file.
      - Throw an exception:
        - Throw `IOException` if any issues occur during file operations (e.g., input file not found, unreadable, invalid file format, or output file cannot be written).
- Main method (optional):
  - Optionally include a `main(String[] args)` method in the `App` class to test the `sum()` method.

## Input/Output Conditions

### Input Format

- A CSV file with a header row (`name,score1,score2,...,scoreN`) followed by rows with:
  - A name (string).
  - `N` integer scores (where `N` can vary but is the same for every row).

### Output Format

- A file with one row per name, containing:
  - The name (string).
  - The total score (integer).

## Examples and Usage

### Sample Input

**Input file** (`input.csv`):
```
name,score1,score2,score3,score4,score5
John,13,18,15,17,9
Jack,20,18,19,16,12
James,20,20,20,20,0
```

### Sample Output

**Output file** (`output.txt`):
```
John,72
Jack,85
James,80
```

### Example Usage
```java
public static void main(String[] args) {
    String inputFile = "input.csv";
    String outputFile = "output.csv";

    App.sum(inputFile, outputFile);
    System.out.println("Processing complete. Check the output file: " + outputFile);
}
```

## Additional Notes

- **Hints:**
  - Use `Scanner` to read the input file.
  - Use `PrintWriter` to write the output file.
  - Use the `String.split()` method to parse each line dynamically based on the number of columns.
  - Exclude the header row when calculating scores, but ensure the program works dynamically regardless of the number of scores.

- **Common Pitfalls:**
  - Ensure the program correctly skips the header row.
  - Handle files with varying numbers of scores per row, but ensure all rows have the same number of scores as the header indicates.
  - Do not include the header row in the output file.
