# EasyClipboard
Supports copying text from your Android devices to your PC and vice versa

## Copy content from Android to PC:

adb shell " am force-stop com.thc.clipboard"

adb logcat -c

adb shell am start -n com.thc.clipboard/com.thc.clipboard.MainActivity

adb logcat Clipboard:D "*:S"

## Copy content from PC to Android:

content=$1

echo "$content"

adb shell " am force-stop com.thc.clipboard"

adb logcat -c

adb shell am start -n com.thc.clipboard/com.thc.clipboard.MainActivity --es receiver_key "$content"
