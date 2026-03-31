# GreenHub Launcher

A feature-rich, all-in-one Android launcher that replaces your default home screen while providing integrated tools for everyday tasks.

## 🌟 Features

### 📱 Launcher Basics
- **Custom Home Screen**: Clean, green-themed interface with app shortcuts and widgets
- **App Drawer**: Organized app listing with search functionality
- **Wallpaper Support**: Set wallpapers directly from the launcher
- **Shortcut Management**: Create and manage app shortcuts

### 🛠️ Integrated Productivity Tools
1. **📁 File Manager**
   - Browse files/folders on internal storage
   - View images, videos, documents
   - Copy, move, delete files
   - Full storage access for Android 11+

2. **🌐 Web Browser**
   - Built-in web browsing
   - Network state monitoring
   - Internet connectivity check

3. **🔢 Calculator**
   - Basic arithmetic operations
   - Clean, easy-to-use interface

4. **📝 Notepad**
   - Create and edit text notes
   - Save notes locally
   - Simple text editor with essential features

5. **🎵 Music Player**
   - Play local audio files
   - Integrated music visualizer with equalizer
   - Background playback service
   - Record audio permission for visualizer effects

6. **📞 Phone & Contacts**
   - Make phone calls directly
   - Full contacts management
   - Read phone state for call handling
   - Add, edit, and delete contacts

7. **📥 Video Downloader**
   - Download videos from supported sources
   - Download without notification option
   - Install downloaded APKs

## 🔐 Required Permissions
The app requests permissions for:
- Storage access (READ/WRITE_EXTERNAL_STORAGE, MANAGE_EXTERNAL_STORAGE)
- Phone capabilities (CALL_PHONE, READ_PHONE_STATE)
- Contacts (READ/WRITE_CONTACTS)
- Internet and network access
- Media access (READ_MEDIA_*)
- Audio controls (RECORD_AUDIO, MODIFY_AUDIO_SETTINGS)
- Special permissions (SET_WALLPAPER, INSTALL_SHORTCUT, REQUEST_INSTALL_PACKAGES, etc.)

## ⚙️ Technical Specifications
- **Language**: Java
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Build Tools**: Gradle 8.2
- **AndroidX**: Enabled
- **Architecture**: Single-module Android app

## 🔨 Building Instructions
**Requirements**:
- Java 17
- Android SDK 34

**Debug APK**:
```bash
./gradlew assembleDebug --no-daemon
```

**Release APK**:
```bash
./gradlew assembleRelease --no-daemon
```

Output APKs located in: `app/build/outputs/apk/`

## 📱 Installation
1. Download latest APK from Releases
2. Enable "Install from unknown sources" in Settings > Security
3. Install the APK
4. Set as default home screen when prompted

## 📄 License
[MIT License](LICENSE)

## 👤 Author
- Your Name Here

<details>
<summary>💡 Development Notes</summary>

This is a clean implementation based on the original GreenHub Launcher concept. All core features from the README have been implemented with proper structure.

## 📁 Directory Structure
```
/app
  /src
    /main
      /java
        /com/glauncher/launcher
          /adapters    - AppAdapter, ToolsPagerAdapter
          /models       - AppInfo, Note
          /utils        - FileUtils, NoteManager, PermissionHelper
      /res
        /drawable     - Icons, UI elements
        /layout       - Activity and fragment layouts
        /values       - Strings, colors, styles
        /xml            - Resource files
```

Happy coding! 🚀
</details>