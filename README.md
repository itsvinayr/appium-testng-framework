# AppiumTestNGFramework
<h1>Description:</h1>
It's a Mobile test automation framework developed with Appium and TestNg, which supports automation of native, web and hybrid apps on android platform.</br>
<h2>Note:</h2>
This can also be extended to run your tests on iOS platform by updating your page objects.</br>
<h1>Getting Started</h1>
<h2>Device Configuration</h2>
To run your tests, you need to update global.properties with appName, device and deviceName.</br>
1. if device = emulator --> it runs on your emulator</br>
2. if device = real --> it runs on your physical device</br>
3. if device = browser --> it runs on your emulator browser</br>
4. if device = cloud --> it runs on cloud infrastructure which is Browser Stack.</br>
**Note:** Make sure to parameterize your username, password and app location in the script
<h2>Configure device name</h2>
device can be name of your emulator, if you are using physical device then it is "Android device"</br>
<h2>Configure bat file</h2>
Framework has a provision to invoke your simulator by running a bat file, make sure to udpate your emulator location in file "openSimulator.bat" accordingly.</br>
<h2>Features</h2>
1. Framework is incorporated with inbuilt Listeners that can be extended for any unimplemented methods</br>
2. We are using Extent reporting in this framework, which makes our reports look good and easy to understand.</br>
3. In addition, we provided flexibility to run tests in Browser stack which is a cloud infrastruture</br>
<h1>Resources</h1>
To understand basic concepts of Appium</br>
Please refer to my blog - https://vinayraghumanda.blogspot.com/2022/03/appium-mobile-test-automation.html</br>
and github location - https://github.com/itsvinayr/AppiumBasics
