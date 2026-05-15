# Smart Media Organizer
A modern Java desktop application that intelligently organizes files into categorized folders using file extensions.
Built with Java Swing and multithreading, Smart Media Organizer helps users clean messy directories quickly through a
clean graphical interface and customizable organization system.

---------------------------------------------------------------------------------------------------------------------

## Preview
Smart Media Organizer automatically sorts files such as:

- Images → Photos
- Videos → Videos
- PDFs & Office files → Documents
- Audio files → Music
- Unknown files → Unorganized

The application also allows users to:
- Create custom categories
- Assign custom file extensions
- Edit organization rules
- Track organization logs

---------------------------------------------------------------------------------------------------------------------

## Features

### Smart File Organization
Automatically scans and sorts files into categorized folders.

### Custom Categories
Create your own file categories with custom extensions.

### Editable Extensions
Modify supported file extensions at runtime.

### Multi-threaded Processing
Uses Java multithreading for faster file organization.

### Modern Desktop GUI
Clean Java Swing interface with a custom background design.

### Operation Logging
Tracks all moved files using a logging system.

---------------------------------------------------------------------------------------------------------------------

## Technologies Used

- Java
- Java Swing
- ExecutorService
- Multithreading
- File I/O
- OOP Principles
- HashMap / HashSet Collections

---------------------------------------------------------------------------------------------------------------------

## Project Structure

```text
src/
│
├── app/
│   └── Main.java
│
├── gui/
│   └── OrganizerGUI.java
│
├── processor/
│   ├── FileOrganizer.java
│   └── FileProcessor.java
│
resources/
└── background.png
