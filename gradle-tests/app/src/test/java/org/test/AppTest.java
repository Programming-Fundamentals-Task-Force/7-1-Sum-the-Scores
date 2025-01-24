package org.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import com.lab.*;

class ClassTest {
    private static final String TEST_INPUT_1 = "test_input1.csv";
    private static final String TEST_OUTPUT_1 = "test_output1.txt";
    private static final String TEST_INPUT_2 = "test_input2.csv";
    private static final String TEST_OUTPUT_2 = "test_output2.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Prepare test input files
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_INPUT_1))) {
            writer.write("name,score1,score2,score3\n");
            writer.write("John,10,20,30\n");
            writer.write("Jane,40,50,60\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_INPUT_2))) {
            writer.write("name,score1,score2,score3,score4\n");
            writer.write("Alice,5,10,15,20\n");
            writer.write("Bob,25,30,35,40\n");
            writer.write("James,30,25,35,40\n");
        }
    }

    @AfterEach
    public void tearDown() {
        // Delete test input and output files
        new File(TEST_INPUT_1).delete();
        new File(TEST_OUTPUT_1).delete();
        new File(TEST_INPUT_2).delete();
        new File(TEST_OUTPUT_2).delete();
    }

    @Test public void
    sum_the_scores_in_input1() throws IOException {
        App.sum(TEST_INPUT_1, TEST_OUTPUT_1);

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_1))) {
            assertEquals("John,60", reader.readLine());
            assertEquals("Jane,150", reader.readLine());
            assertNull(reader.readLine());
        }
    }

    @Test public void
    sum_the_scores_in_input2() throws IOException {
        App.sum(TEST_INPUT_2, TEST_OUTPUT_2);

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_2))) {
            assertEquals("Alice,50", reader.readLine());
            assertEquals("Bob,130", reader.readLine());
            assertEquals("James,130", reader.readLine());
            assertNull(reader.readLine());
        }
    }

    @Test public void
    throws_ioexception_when_input_file_not_found() {
        IOException exception = assertThrows(IOException.class, () -> {
            App.sum("nonexistent_input.csv", TEST_OUTPUT_1);
        });
    }

    @Test public void
    throws_ioexception_when_output_file_not_writable() throws IOException {
        // Create a read-only output file
        File outputFile = new File(TEST_OUTPUT_1);
        outputFile.createNewFile();
        outputFile.setWritable(false);

        IOException exception = assertThrows(IOException.class, () -> {
            App.sum(TEST_INPUT_1, TEST_OUTPUT_1);
        });

        // Restore writability for cleanup
        outputFile.setWritable(true);
    }

    @Test public void
    throws_ioexception_when_input_file_is_empty() throws IOException {
        // Create an empty input file
        String emptyInput = "empty_input.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(emptyInput))) {
            writer.write(""); // write nothing to make it empty
        }

        IOException exception = assertThrows(IOException.class, () -> {
            App.sum(emptyInput, TEST_OUTPUT_1);
        });

        // Cleanup
        new File(emptyInput).delete();
    }
}
