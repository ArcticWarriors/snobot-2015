'''
Created on Mar 19, 2015

@author: PJ
'''
import csv
import numpy


def load_csv_file(file_name, special_parsers={}):

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

                if col_name in special_parsers:
                    special_parsers[col_name](row[col_name])
                else:
                    converted_dict[col_name][i] = row[col_name]

    return converted_dict
