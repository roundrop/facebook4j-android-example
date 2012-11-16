# Facebook4J for Android Example
An example of using [Facebook4J](https://github.com/roundrop/facebook4j) in Android app.

This example contains OAuth Authentication and Simple News Feed Reader.

Tested on HTC EVO (Android 2.3.4)

## Development Environment
This project requires set up of the Android SDK, Maven, Eclipse, and Android Development Tools(ADT) for Eclipse.

Import this project to Eclipse, and convert to Maven Project (Right-click on project > Configure > Convert to Maven Projects).

## How To Run
1. Create App in Facebook Developers Site
2. Input "http://facebook4j.org" to Site URL (NOT Native Android App!)
![Settings Image](https://github.com/roundrop/facebook4j-android-example/blob/master/facebook_app_setting.png?raw=true)
3. Get App ID and App Secret
4. Rename facebook4j.properties-template to facebook4j.properties on src/main/resources directory
5. Set 'oauth.appId' and 'oauth.appSecret'
6. Run as Android Application
