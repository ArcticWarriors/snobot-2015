
import csv
import matplotlib.pyplot as plt
import numpy
import time
import os
from matplotlib.cm import cmap_d
from matplotlib.pyplot import subplot


def load_csv_file(file_name):

    with open(file_name) as csvfile:
        reader = csv.DictReader(csvfile)

        col_names = reader.fieldnames
        rows = list(reader)

        num_rows = len(rows)

        converted_dict = {}
        for col_name in col_names:
            converted_dict[col_name] = numpy.zeros(num_rows)

        for i, row in enumerate(rows):
            for col_name in col_names:

                if col_name == 'Date and Time':
                    converted_dict[col_name][i] = parse_datetime(row[col_name])
                elif col_name == 'Drive Mode':
                    if converted_dict[col_name][i] == "Tank":
                        row[col_name] = 0
                    else:
                        row[col_name] = 1
                   
                else:
                    converted_dict[col_name][i] = row[col_name]

    return converted_dict