# JSONPlaceholder

[![Build Status](https://app.bitrise.io/app/a1660eb3576f70bc/status.svg?token=08K5XLMEus93LhPxqI1NLA&branch=master)](https://app.bitrise.io/app/a1660eb3576f70bc)

## Modules
The project is using a feature based module structure. Because Google deprecated the `com.android.feature` gradle module
 configuration we will use the suggested `com.android.library`gradle config. This structure does not let the apps to be 
 released as an Instant App and won't allow dynamically delivered features but does not limit the possibility of it in the future.

The reason behind the library based module are that it introduces the module concept with the smallest complexity.
With the approach the dependency injection and code visibility is straight forward whereas in a dynamic feature module 
these concept can increase the development time.
### modules graph:
```text
       { app }
          |
     |==========|
 { detail } { posts }
     |          |
     |==========|
          |
       { core }
```
## Proguard

Proguard file is applied based on [Jemshit Iskenderov](https://gist.github.com/jemshit) [gist](https://gist.github.com/jemshit/767ab25a9670eb0083bafa65f8d786bb).
The proguard file is filtered according to the project need.
