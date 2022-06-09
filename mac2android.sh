content=$1
echo "$content"
adb shell " am force-stop com.thc.clipboard"
adb logcat -c
adb shell am start -n com.thc.clipboard/com.thc.clipboard.MainActivity --es receiver_key "$content"

