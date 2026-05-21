# X-Client (Unofficial)

An unofficial X (formerly Twitter) client Android application specifically designed to solve the recent login disruptions experienced by MicroG users.

If you are using a custom ROM or a de-Googled device with MicroG and have been unable to log into X recently, this client is built to resolve those authentication issues.

## ⚠️ IMPORTANT REQUIREMENTS (MUST READ) ⚠️

To ensure the login process works smoothly, you **MUST** follow these two steps before using this app:

1. 🚫 **Uninstall the Official X App**: You **must completely delete the official X client** from your device. If the official app remains installed, it will cause conflicts and your login will fail.
2. 🌐 **Install Google Chrome**: You **must have the Google Chrome browser installed** on your phone. The underlying authentication mechanism relies on Chrome to function properly.

## 📦 Installation

1. **Uninstall** the official X app from your device.
2. Ensure **Google Chrome** is installed and updated.
3. Go to the .\app\build\outputs\apk\debug folder.
4. Download the latest `.apk` file for trial.
5. Install the APK on your Android device (you may need to enable "Install from unknown sources" in your settings).
6. Open the app and log in!


## 🛠️ Build from Source

If you prefer to build the APK yourself, you can clone this repository and build it using Gradle.

```bash
git clone https://github.com/summerlovewarum/x-client.git


Windows: Run build-release.bat or gradlew.bat assembleRelease
Linux/macOS: Run ./gradlew assembleRelease
⚠️ Disclaimer
This is an unofficial client. It is not affiliated with, endorsed, or sponsored by X Corp. Use at your own risk.
 
这是一个非官方的 X（原 Twitter）Android 客户端，专门用于解决近期 MicroG 用户遇到的登录中断/无法登录的问题。
如果你使用的是第三方 ROM 或移除了谷歌官方套件的设备，并依赖 MicroG 框架，近期可能会遇到 X 无法登录的情况，本项目正是为了解决这一痛点而生。
⚠️ 重要注意事项（使用前必读） ⚠️
为了确保你能成功登录并正常使用本软件，请务必满足以下两个条件：
🚫 必须卸载 X 官方客户端：在使用本应用前，请彻底删除/卸载手机上的 X 官方原版客户端。如果设备上保留了官方客户端，将会产生冲突，导致登录失败[1]。
🌐 必须安装 Chrome 浏览器：你的设备上必须安装有 Google Chrome 浏览器。本应用的底层登录验证机制需要依赖 Chrome 才能正常运行。
📦 安装指南
卸载你手机上的 X (Twitter) 官方 App。
确保手机中已安装 Google Chrome 浏览器。
访问 .\app\build\outputs\apk\debug 文件夹。
下载最新的 .apk 安装包。
在手机上安装该 APK（如有提示，请允许“安装未知来源应用”）。
打开应用，正常登录即可！
🛠️ 源码编译
如果你想自行编译源码，请克隆本仓库并使用 Gradle 构建：
code
Bash
git clone https://github.com/summerlovewarum/x-client.git
Windows 环境: 双击运行 build-release.bat 或在终端执行 gradlew.bat assembleRelease
Linux/macOS 环境: 在终端执行 ./gradlew assembleRelease
⚠️ 免责声明
本项目为非官方客户端。本项目与 X Corp (推特公司) 没有任何关联、赞助或认可关系。请自行承担使用风险。
