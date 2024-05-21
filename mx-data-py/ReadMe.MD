# Solution

### 1. What tools/language do you propose to solve the problem? Please provide the reasoning
     I ran into a lot of challenges working with Java for data engineering. 
     It required writing a significant amount of code manually, and there wasn't as much library support available. 
     That's why I switched to Python. Python, along with its powerful data manipulation library pandas, has become a much better fit for my data engineering tasks.

### 2. Implement the solution accordingly.
    Solution Implementation Steps

    Step 1. Read the File and Convert to DataFrame:
        Read the input file and convert it into a DataFrame for support with the pandas library.

    Step 2. Filter Data Based on Domain Name:
        Apply a filter to the DataFrame to include only rows that match the specified domain name.

    Step 3. Filter Data Based on ID's First Character:
        Further filter the DataFrame to include only rows where the first character of the ID is not lowercase.

    Step 4.Sort by Email Address:
        Sort the filtered data by email address in ascending order. This is done as the last step to maximize efficiency by sorting a smaller subset of data.

    Step 5. Extract Codes from First 5 Rows:
        Extract the code values from the first five rows of the sorted DataFrame.

    Testing : Test cases have been written to verify the correctness of the results.


## Priorities
   1. Correctness - Added testcase for checking correctness

   2. Implementation Time - It dragged bit more due to choose wrong programming language as Java. Later moved to python and it turns out to be easier more. 

   3. Readability -  Designed & documented well for coding readability.

   4. Performance - Its not measured yet. But coding is structured in way that to improve performance.

## Note - 
 Internet Google help is taken.