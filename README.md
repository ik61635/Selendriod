# Selendriod dummy project
```
Example project using 'Selendriod' with 'TestNG' and Page Objects, project have logging capaiblities and ability
to start avd and selendriod server.
'ObjectRepository folder' where all the object of app located
'Pages folder' where all the methods related to page placed
'MobileEvent is class' which is wrapper to start app and has method releated to app interactions
'CreateSession is class' to start Selendriod and avd
'TestClass' located in 'scr/main/test' folder to start test
'Log Class' for logging is used
'Utils folder' having all the common utility methods
'property file and excel file' is used located inside 'Resources' it is used to drive test cases
 Activities
 It has 4 test Cases
• TestCase 1 TestDisplayTextViewFunctionality to check text is display on home page
• TestCase 2 TestDisplayAndFocusLayoutFunctionality to check Display And FocusLayout Functionality on home page
• TestCase 3 TestWebViewFunctionality to check WebView Functionality is working 
• TestCase 4 TestRegistrationFunctionality to check Registration Functionality is working 
```





## Getting Started

### Clone Repository

```
git clone https://github.com/ik61635/Selendriod.git
cd Selendriod
```

### Install Dependencies

```
mvn clean install
```

### Start Selendriod Server and test Execution

```
mvn test
```
