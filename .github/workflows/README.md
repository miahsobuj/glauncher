# GitHub Actions Workflow for Android Builds

This workflow automatically builds debug and release APKs for the glauncher project on every push to main and pull request.

## What it does:

1. Checks out the repository
2. Sets up JDK 11 with Gradle caching
3. Validates the Gradle wrapper
4. Builds debug APK (`assembleDebug`)
5. Uploads debug APK as artifact
6. Builds release APK (`assembleRelease`) 
7. Uploads release APK as artifact

## To get your APKs:

1. Go to the Actions tab in this repository
2. Select the latest workflow run
3. Wait for completion
4. Download the APK artifacts from the workflow run steps