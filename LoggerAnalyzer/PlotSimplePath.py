
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


def create_plots(filepath, filename):

    converted_dict = load_csv_file(filepath)

    segment = converted_dict['Segment Number']
    dt = converted_dict['Time']
    position = converted_dict['Position']
    velocity = converted_dict['Velocity']
    acceleration = converted_dict['Acceleration']

    time = []
    sum_time = 0
    for i in range(len(dt)):
        time.append(sum_time)
        sum_time += dt[i]

    plt.figure(filename)
    plt.subplot(3, 1, 1)
    plt.title("Position")
    plt.scatter(time, position, c=segment)

    plt.subplot(3, 1, 2)
    plt.title("Velocity")
    plt.scatter(time, velocity, c=segment)

    plt.subplot(3, 1, 3)
    plt.title("Acceleration")
    plt.scatter(time, acceleration, c=segment)

if __name__ == "__main__":
<<<<<<< HEAD
#     file_path = r"../snobot2015/resources/paths/TestTurn90.csv"
    file_path = r"../snobot2015/resources/paths/TestTurn90Slow.csv"
    create_plots(os.path.abspath(file_path))
=======

    path_files = []

    path_dir = r"../snobot2015/resources/paths/"

    for root, _, files in os.walk(path_dir):
        for f in files:
            path_files.append(os.path.join(root, f))
#
    for f in path_files:
        create_plots(f, f)
#
    plt.show()
>>>>>>> branch 'master' of https://github.com/ArcticWarriors/snobot2015.git
