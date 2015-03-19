
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

    directions = ["Left", "Right"]

    if "LeftPos" not in converted_dict:
        return

    dt = converted_dict['LeftDt']
    position = converted_dict['LeftPos']
    velocity = converted_dict['LeftVelocity']
    acceleration = converted_dict['LeftAcceleration']

    time = []
    sum_time = 0
    for i in range(len(dt)):
        time.append(sum_time)
        sum_time += dt[i]

    num_rows = 5
    num_cols = 2

    for i, direction in enumerate(directions):

        position = converted_dict[direction + 'Pos']
        velocity = converted_dict[direction + 'Velocity']
        acceleration = converted_dict[direction + 'Acceleration']

        plt.figure(filename)
        plt.subplot(num_rows, num_cols, i + 1)
        plt.title(direction + " Position")
        plt.scatter(time, position)

        plt.subplot(num_rows, num_cols, i + 3)
        plt.title(direction + " Velocity")
        plt.scatter(time, velocity)

        plt.subplot(num_rows, num_cols, i + 5)
        plt.title(direction + " Acceleration")
        plt.scatter(time, acceleration)

    heading = converted_dict['LeftHeading']
    left_x = converted_dict['LeftX']
    left_y = converted_dict['LeftY']
    right_x = converted_dict['RightX']
    right_y = converted_dict['RightY']

    plt.subplot(num_rows, num_cols, i + 7)
    plt.title("Heading")
    plt.scatter(time, heading)

    plt.subplot(num_rows, num_cols, i + 9)
    plt.title("Position")
    plt.scatter(left_x, left_y, c=time)
    plt.scatter(right_x, right_y, c=time)

#         plt.subplot(num_rows, num_cols, i + 7)
#         plt.title(direction + " X")
#         plt.scatter(time, x)
#
#         plt.subplot(num_rows, num_cols, i + 9)
#         plt.title(direction + " Y")
#         plt.scatter(time, y)


if __name__ == "__main__":

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
