
import csv
import matplotlib.pyplot as plt
import numpy
import time
import os
from matplotlib.cm import cmap_d
from matplotlib.pyplot import subplot


def parse_datetime(time_string):

    # strptime has no option for milliseconds, so ignore the last 3 characters
    time_struct = time.strptime(time_string[:-3], "%Y%m%d_%H%M%S")

    hour = time_struct.tm_hour
    minute = time_struct.tm_min
    sec = time_struct.tm_sec
    msec = int(time_string[-3:])  # ms are the last three columns

    return (msec + (sec * 1000) + (minute * 60000) + (hour * 3600000)) / 1000.0


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



converted_dict = load_csv_file(r"C:\Users\1335draco\Documents\GitHub\snobot2015\snobot2015\genTest.csv")
print converted_dict.keys()

time = converted_dict['Time']
position = converted_dict['Position']
velocity = converted_dict['Velocity']
acceleration = converted_dict['Acceleration']


    
plt.figure(1)
plt.scatter(time, position)

    
plt.figure(2)
plt.scatter(time, velocity)

    
plt.figure(3)
plt.scatter(time, acceleration)

plt.show()






