# Object-oriented programming course - Assignment 1

#### By : Zohar Simhon, ID:211871868  and  Linor Ronen, ID:322216045

## **Description**

### Observer Pattern
The Observer Pattern defines a one to many dependency between objects so that one object changes state, all of its dependents are notified and updated automatically.  
One to many dependency is between Subject(One) and Observer(Many).  
All observers who need the data need to implement observer interface.                                                                                                 
In this case the GroupAdmin class implements the Sender interface which describes the sender of the updates (Observerable) and the ConcreteMember class implements the Member interface which describes the getting of the updates(Observer).  
The state pool is the UndoableStringBuilder and once the data changes within it all the observers are updated on the change.

### GroupAdmin
The GroupAdmin class implements the Sender interface which describes the sender of the updates (Observerable).  
<u>The interface Sender contains the abstract methods:</u>
* void register(Member obj); -method to add observers respectively.
* void unregister(Member obj); -method to remove observers respectively.
* void insert(int offset, String obj); - method to insert the string into the character sequence in the usb (UndoableStringBuilder).
* void append(String obj); - method to appends the specified string to the character sequence in the usb (UndoableStringBuilder).
* void delete(int start, int end); - method to Remove the characters in a substring of the sequence in the usb (UndoableStringBuilder).
* void undo(); - this method takes us back one situation in the usb (UndoableStringBuilder).

<u>Additional methods in this class:</u>
* public GroupAdmin()-constructive method.
* public int getSizeOfMembers(); - return sizeOfMembers.
* public void notifyObserver(); - notifyObserver() is called when the data is changed and the observers need to be supplied with new data.
* public String toString(); - a string representing the collection of the members.


The GroupAdmin class contains the implementation for these methods.  
In addition, contains usb (UndoableStringBuilder usb) which is the state pool and members (HashSet<Member> members) which is the observers who should receive updates on any changes made to the pool.  
The collection of the members implemented using HashSet (the HashSet class implements the Set interface,backed by a hash table) and the usb implemented using the class UndoableStringBuilder.

### ConcreteMember
ConcreteMember class implements the Member interface which describes the getting of the updates(Observer).  
<u>The interface Member contains the abstract method:</u>
* public void update(UndoableStringBuilder usb)

<u>Additional methods in this class:</u>
* public ConcreteMember()- constructive method.
* public String toString()- a string representing the member.

The ConcreteMember class contains the implementation for these methods.  
In addition, contains member (UndoableStringBuilder member - sallow copy) which is the state pool.

### UndoableStringBuilder
The UndoableStringBuilder class describes our state pool.  
This class use the StringBuilder Java class.   
We create methods by using the StringBuilder methods.  
The addition of this class is to save all the previous
changes the user has done in the code StringBuilder.  
Therefore, the user can cancel his last changes.

<u> This UndoableStringBuilder Supports the methods: </u>

* insert(int offset, String obj)
* append(String obj)
* delete(int start, int end)
* undo()
* replace(int start,int end, String str)
* reverse()

## **Tests**
A test class is a normal Java class that is either public or package private.  
A test method is a method that isn't private and doesn't return anything.  
In our assignment we create test class with JUnit 5.

<u>JUnit 5:</u>  
An open and free tool for unit testing in java programming language.  
This tool gives us the ability to run the tests automatically.

### Tests.java
In Tests class we test the GroupAdmin class and ConcreteMember class.
At first, we create a new GroupAdmin object for the test.    
Then, we perform various tests in order to check the integrity of our code.  
Among our tests, we make sure that the members in the group are getting all the updates,
and that there isn't a duplicate member in the group.  
Also, we check the size of the objects that we have created in the memory.  
In order to check this we use the object Logger.  
So, we can make sure that all the changes are saved.  
Moreover, we check edge cases that can disrupt our code execution.

## **Running instructions**
First, you need to make sure that you have Maven and JDK installed.
Now, you have two options:

##### First option:
* Download zip from our reposetory : push on code -> Download zip.
* Extract all on yor computer.
* Open a new java project.
* Open the file in your java's workspace.
* Press on pom.xml.
* Press ok.
* Run the project.

##### Second option:
* Clone the repository: git@github.com:ZoharSimhon/OOP_Ex1.git
* open the project (in IntelliJ) and then follow these steps:
1. press build
2. Rebuild Project
3. Run the Tests.java
