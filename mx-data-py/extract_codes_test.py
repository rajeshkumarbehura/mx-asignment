from extract_codes import ExtractCodes


# Test the result for correctness, quality of result

# Created test case to validate the data result
def test_process():
    expected_result = ExtractCodes.process()
    # Assert size of result
    assert len(expected_result) == 5, "Length of Result is not equal to 5"

    # Assert all values in the array are either 1 to 5
    assert all(x in [1, 2, 3, 4, 5] for x in expected_result), "Array contains values other than 1 to 5"
