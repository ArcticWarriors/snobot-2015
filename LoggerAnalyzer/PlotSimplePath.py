
import os
import csv
import numpy
import matplotlib.pyplot as plt


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
                converted_dict[col_name][i] = row[col_name]

    return converted_dict


def create_plots(filepath):

    converted_dict = load_csv_file(filepath)
    print converted_dict.keys()

    segment = converted_dict['Segment Number']
    time = converted_dict['Time']
    position = converted_dict['Position']
    velocity = converted_dict['Velocity']
    acceleration = converted_dict['Acceleration']

    plt.figure(1)
    plt.title("Position")
    plt.scatter(time, position, c=segment)

    plt.figure(2)
    plt.title("Velocity")
    plt.scatter(time, velocity, c=segment)

    plt.figure(3)
    plt.title("Acceleration")
    plt.scatter(time, acceleration, c=segment)

    plt.show()

if __name__ == "__main__":
#     file_path = r"../snobot2015/resources/paths/TestTurn90.csv"
    file_path = r"../snobot2015/resources/paths/TestDriveStraight.csv"
    create_plots(os.path.abspath(file_path))
