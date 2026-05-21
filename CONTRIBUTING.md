# Contributing to X-Client / 参与贡献

*English | [简体中文](#简体中文)*

First off, thank you for considering contributing to X-Client! It's people like you that make open-source tools better. 

## 🐛 Bug Reports
If you find a bug or a login failure, please open an Issue. Since this app depends on MicroG and Chrome, please include:
- Your device model and Android version.
- Your MicroG version.
- Your Google Chrome version.
- Steps to reproduce the issue.
- Logcat output (if applicable).

## 💡 Feature Requests
We are open to new ideas! Please open an Issue to discuss your idea before submitting a Pull Request, so we can ensure it aligns with the project's goals.

## 💻 Development Setup
This project uses Kotlin and Gradle. To start developing:
1. Fork this repository and clone your fork.
2. Open the project in **Android Studio**.
3. Let Gradle sync and build the project.
4. Ensure you can run the app on an emulator or a physical device (Remember: The emulator/device MUST have Chrome installed and the official X app uninstalled).

## 🔀 Submitting a Pull Request (PR)
1. Create a new branch for your feature or bugfix: `git checkout -b feature/your-feature-name` or `git checkout -b fix/your-bugfix-name`.
2. Make your changes and commit them with a descriptive message.
3. Push to your fork: `git push origin your-branch-name`.
4. Open a Pull Request against the `main` branch of this repository.

---

<h2 id="简体中文">简体中文</h2>

首先，非常感谢你考虑为 X-Client 贡献代码！正是因为有你这样的开发者，开源社区才越来越好。

## 🐛 提交 Bug 报告
如果你在使用中发现了 Bug（尤其是登录失败的问题），请提交一个 Issue。因为本应用高度依赖 MicroG 和 Chrome 环境，请务必在 Issue 中提供以下信息：
- 你的设备型号和 Android 版本。
- 你当前使用的 MicroG 版本。
- 你的 Google Chrome 版本。
- 重现该问题的具体步骤。
- Logcat 日志（如果方便获取的话）。

## 💡 提出新功能
我们非常欢迎新的想法！在直接提交 PR 之前，请先提交一个 Issue 与我们讨论你的想法，以确保它符合项目的发展方向。

## 💻 本地开发环境配置
本项目基于 Kotlin 和 Gradle 构建。要开始开发：
1. Fork 本仓库并克隆到本地。
2. 使用 **Android Studio** 打开该项目。
3. 等待 Gradle 同步并构建项目。
4. 确保你能在模拟器或真机上运行该应用（**注意**：测试用的模拟器/真机上**必须**安装了 Chrome，并且**没有**安装 X 的官方 App）。

## 🔀 提交 Pull Request (PR)
1. 为你的功能或修复创建一个新分支：`git checkout -b feature/你的功能名称` 或 `git checkout -b fix/你的修复名称`。
2. 编写代码，并编写清晰的 Commit 信息提交。
3. 推送到你的 Fork 仓库：`git push origin 你的分支名称`。
4. 在 GitHub 上向本仓库的 `main` 分支发起 Pull Request。
