'''
Created on Jan 31, 2015

@author: 1335draco
'''
fileName = r"C:\Users\1335draco\Documents\GitHub\snobot2015\Simulator\SimulatorTest\RobotLog_20150131_093803690_log.csv"

import csv
with open(fileName) as csvfile:
    reader = csv.DictReader(csvfile)
    
    col_names = None
    print reader.fieldnames
    col_names = reader.fieldnames
    
    converted_dict = {}
    for col_name in col_names:
        converted_dict[col_name] = []
        
    for row in reader:
        for col_name in col_names:
            converted_dict[col_name].append(row[col_name])
            
            
   
    print 'Date and Time: ' + str(converted_dict['Date and Time'])
    print 'Left Y Axis: ' + str(converted_dict['Left Y Axis'])
    print 'Right Y Axis: ' + str(converted_dict['Right Y Axis'])
    print 'Speed: ' + str(converted_dict['Speed (-1 to 1)'])
    print 'Right X Axis: ' + str(converted_dict['Right X Axis'])
    print 'Upper Limit Switch: ' + str(converted_dict['UpperLimitSwitchState'])
    print 'Lower Limit Switch: ' + str(converted_dict['LowerLimitSwitchState'])
    print 'Claw Lift Pressure: ' + str(converted_dict['Claw Up/Down Pressure'])
    print 'Claw Grip Pressure: ' + str(converted_dict['Claw Open/Close Pressure'])    