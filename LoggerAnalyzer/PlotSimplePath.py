
import os
import matplotlib.pyplot as plt
from read_csv_file import load_csv_file


def create_plots(filepath, filename):

    converted_dict = load_csv_file(filepath)

    if 'Segment Number' not in converted_dict:
        return

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
