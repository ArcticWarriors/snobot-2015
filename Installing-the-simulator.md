# Getting the software
Grab the latest version of the simulator from [here.](https://github.com/pjreiniger/snobot2015).  There are many ways you can pull it down to your computer, if you have a github account you can use the built-in eclipse plugin.  If you have SourceTree you can follow the instructions for that [here].  If you don't have an account, you can download a zip file and place it somewhere on your computer

# Setting up Eclipse
Once you have the code on your computer you will need to open it in Eclipse and set up the dependencies

## Importing projects
In Eclipse, navigate to the 'File' menu and select 'Import'.  Under the 'Genera' tab select the 'Existing Projects into Workspace' option and hit next. Click the 'Browse...' button and navigate to the location of the source code.  If your directory structure looks like this, you would want to select 'simulator2015'

    -
    |- C:\Users\preiniger\documents  
     |- simulator2015  
      |-- MockWpiLib  
      |-- SimpleTestRobot  
      |-- SimulatorMain  

## Setting up dependencies
Since I used project references to set up the dependencies, that does not translate well over source control stuff like git [[citation needed](http://xkcd.com/285/)].  You will need to manually set them up.

Right click on the 'SimpleRobotExample' project, and navigate to 'Build Path'->'Configure Build Path...'.  Go to the 'Projects' tab and click add.  This project needs a dependency on the 'SimpleMockWpiLib' project.

Right click on the 'SimulatorTest' project, and navigate to 'Build Path'->'Configure Build Path...'.  Go to the 'Projects' tab and click add.  This project needs a dependency on the 'SimpleMockWpiLib' and  'SimpleRobotExample' projects.

# Running the simulator
Now that you have eclipse set up, you can run the simulator tool.  The 'SimulatorTest' project has the main function which you will need to start.  The default code starts up the robot from the 'SimluateRobotExample' project, but if you want to test some other robot code you simply need to add the project dependency and replace 

`Robot iter = new Robot();`
with something to instantiate your other robot.  Running this program should result in the simulation window coming up.  Whenever an actuator (Speed Controller, Solenoid, Digital Out, or Relay) get created they should be added to the window.  

Currently, the only Joystick input method provided is through the keyboard.  I tried to set up the button mapping such that each joystick has a row of buttons, and can have single axis control.  Obviously since they keyboard has no analog inputs the output of getRawAxis() or getY(), etc. will be either -1.0, 0, or 1.0.

    Joystick 1:
      Buttons 0-9              = The number row
      Axis 0, 1 forward (100%) = z
      Axis 0, 1 reverse(100%)  = x

    Joystick 2:
      Buttons 0-9              = qwertyuiop
      Axis 0, 1 forward (100%) = c
      Axis 0, 1 reverse(100%)  = v

    Joystick 3:
      Buttons 0-9              = asdfghjkl/
      Axis 0, 1 forward (100%) = b
      Axis 0, 1 reverse(100%)  = n

    Joystick 4:
      not implemented
  
# Simulating the SmartDashboard
The simulator is configured to run the SmartDashboard configuration on your machines localhost.  If you want to see the SmartDashboard results you will need to start it with special arguments so it can talk to the simulator.  Open a command window in your wpilib installation directory (mine is C:\Users\PJ\wpilib\tools).  Start the SmartDashboard with the following command

     java -jar SmartDashboard.jar ip 127.0.0.1

to start it on localhost.  You should now be able to add any fields that are being published by your simulated robot and pretend that this is your tactical smart dashboard.