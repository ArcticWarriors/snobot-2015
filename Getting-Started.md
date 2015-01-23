We'll be using Java to program our robot, but before you can start writing code you'll need to get through some initial setup. Read through the information below to get started!

Some of these instructions have been adapted from the [official 2015 FRC Control System documentation](https://wpilib.screenstepslive.com/s/4485/m/13809/l/145002-installing-eclipse-c-java). In some instructions I assume you are using Windows 7 or later.

Install the software in the order below! If you jump between sections some applications may not configure themselves automatically, and you will need to change some settings manually.

## GitHub

GitHub is a website for storing and sharing software source code. You're on GitHub right now!

[Create an account](https://github.com/join) by choosing an appropriate username and clicking _Create an account_. Message [emddudley](https://github.com/emddudley) with your GitHub username and he will add you to the [Arctic Warriors](https://github.com/ArcticWarriors) organization on GitHub.

## SourceTree

[SourceTree](http://www.sourcetreeapp.com/) is a free application that makes it easy to upload and download code to and from our GitHub repository.

Download and install SourceTree. You can accept all the default installation options.

When you launch SourceTree for the first time it will ask you whether you want to _Install global ignore file?_ Click Yes. Then select GitHub from the _Add an account_ dialog and enter your GitHub username and password.

If you've been added to the Arctic Warriors organization, you can select the _snobot2015_ repository on the _Clone your first repo_ dialog. Otherwise, click the _Skip Setup_ button. If you are asked about an SSH Key click No.

## Java JDK

The Java JDK is a software package that makes it possible to write Java programs.

Download [Java SE JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). You'll need to click _Accept License Agreement_ for the download links to work. Choose the appropriate version for your computer (you'll probably want the Windows x64 version, `jdk-8u25-windows-x64.exe`).

Run the installer and accept all defaults.

## Eclipse

Eclipse is a Java Integrated Development Environment (IDE). It's like a fancy text editor for writing code.

Download [Eclipse IDE for C/C++ Developers](https://www.eclipse.org/downloads/) (yes, we're doing Java, but apparently this is the version we need). You'll have to click through to a file mirror.

Unzip the file to a logical location, such as `C:\Program Files\eclipse`. You may then want to create a shortcut to `eclipse.exe` or pin it to your taskbar.

When you run Eclipse for the first time it will prompt you for a workspace location. This is where it will save files. You can use the default location, or enter a custom one.

Then you may need to add Java to Eclipse. Once Eclipse is running take the following steps (quoted from the official documentation):

> 1. Select Help, then Install New Software... from the menu bar.
> 2. Click the dropdown and select the "Luna" site as shown.
> 3. Scroll down to the Programming Languages section and click the arrow to expand.
> 4. Choose Eclipse Java Development Tools
> 5. Click Next.
> 6. Take the defaults for the other options and let Eclipse restart.
> 
> When these steps are finished, and Eclipse has restarted, Java should be an available option on the Preferences window, and all the Java perspectives will be available.

## FRC Plugins

FIRST has some plugins for Eclipse that add capabilities for programming our robot. To install them start Eclipse and then:

1. Select "Help"
2. Click "Install new software".
3. Add a software update site (the location where the plugins will be downloaded from). Click the _Add..._ button then fill in the _Add Repository_ dialog with:
    * Name: FRC Plugins
    * Location: http://first.wpi.edu/FRC/roborio/release/eclipse/
4. Click _OK_ to finish adding the software update site.
5. Expand the _WPILib Robot Development_ entry in the file list.
6. Check the _Robot Java Development_ item.
7. Click _Next_, accept license agreements, and click _Finish_.
8. Continue installation if warned about unsigned content.
9. Allow Eclipse to restart.
10. If installation is complete you should see a _WPILib_ entry on the menu bar.
