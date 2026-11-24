# Advanced Kurdish Keyboard — prototype 1.1

Android IME source project based on the user's Advanced Kurdish Keyboard screenshot.

## Included
- Kurdish Sorani letter layout, preserving the visible key order from the supplied screenshot.
- Dark green/black visual theme.
- Alternate symbols/numbers page, opened with `½` or `؟!`.
- Persian digits, punctuation, space, backspace, enter, and system keyboard switch.
- No autocorrect and no word suggestions.

## Build
Open this folder in Android Studio, allow Gradle sync, then choose **Build > Build APK(s)**.
The debug APK is normally created at `app/build/outputs/apk/debug/app-debug.apk`.

## Enable on Android 13
Install the APK, then open **Settings > General management > Keyboard list and default**.
Enable **Advanced Kurdish**, then select it as the default keyboard.

## Notes
This is a newly implemented standalone keyboard, not a modified Gboard or a byte-for-byte clone.
The supplied screenshot does not show every alternate-symbol key, so that page is a practical first draft.
The original APK was not modified.
