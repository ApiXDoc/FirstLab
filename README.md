# FirstLab
FirstLab
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:


allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

  
  Step 2. Add the dependency
  
  ...gradle
  dependencies {
	        implementation 'com.github.pawandeepZin:FirstLab:1.0.5'
	}

...
