# AppiumTestNGFramework
<h1>Description:</h1>
It's a Mobile test automation framework developed with Appium 7, which supports automation of native, web and hybrid apps on android platform.</br>
<h2>Note:</h2>
This can also be extended to run your tests on iOS platform as well by updating your page objects.</br>
<h1>Getting Started</h1>
<h2>Device Configuration</h2>
To run your tests, you need to update global.properties with appName, device and deviceName.</br>
1. if device = emulator --> it runs on your emulator</br>
2. if device = real --> it runs on your physical device</br>
3. if device = browser --> it runs on your emulator browser</br>
<h2>Configure device name</h2>
device can be name of your emulator, if you are using physical device then it is "Android device"</br>
<h2>Configure bat file</h2>
Framework has a provision to invoke your simulator by running a bat file, make sure to udpate your emulator location in file "openSimulator.bat" accordingly.</br>
<h2>Features</h2>
Framework is incorporated with inbuilt Listeners that can be extended for any unimplemented methods</br>
We are using Extent reporting in this framework, which makes our reports look good and easy to understand.</br>
