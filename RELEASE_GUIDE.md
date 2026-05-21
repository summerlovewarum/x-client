# X Web Client - Google Play Store 发布指南

## 已完成的功能

### 1. 中英文国际化支持
- 创建了 `values-en/strings.xml` 英文资源文件
- 在菜单中添加了语言切换选项
- 语言选择会自动保存，下次启动时保持

### 2. 记住上次访问界面
- 应用会自动保存用户最后访问的页面（主页/私信/登录）
- 下次启动时自动打开上次访问的页面
- 通过快捷方式启动时会直接跳转到对应页面

### 3. 发布准备配置
- 配置了 ProGuard 代码混淆
- 配置了资源压缩
- 创建了自适应图标（Adaptive Icon）
- 配置了 Android App Bundle 支持

---

## 发布步骤

### 第一步：生成签名密钥

在项目根目录下运行以下命令：

```bash
keytool -genkeypair -v \
    -keystore keystore.jks \
    -alias xweb \
    -keyalg RSA \
    -keysize 2048 \
    -validity 10000 \
    -dname "CN=XWeb, OU=Development, O=XWeb, L=Beijing, ST=Beijing, C=CN"
```

**重要提示：**
- 请妥善保管 keystore.jks 文件和密码
- 丢失密钥将无法更新应用
- 不要将密钥文件提交到版本控制系统

### 第二步：配置签名

1. 在 `app/build.gradle.kts` 中取消注释 signingConfig：
```kotlin
signingConfig = signingConfigs.getByName("release")
```

2. 或者在 `local.properties` 中添加：
```properties
storeFile=../keystore.jks
storePassword=你的密码
keyAlias=xweb
keyPassword=你的密码
```

### 第三步：构建 Release 版本

```bash
# 构建 APK
./gradlew assembleRelease

# 或构建 App Bundle（推荐用于 Google Play）
./gradlew bundleRelease
```

输出位置：
- APK: `app/build/outputs/apk/release/app-release.apk`
- AAB: `app/build/outputs/bundle/release/app-release.aab`

### 第四步：Google Play Console 设置

1. 创建开发者账号
   - 访问 https://play.google.com/console
   - 支付一次性注册费用（$25）

2. 创建应用
   - 点击"创建应用"
   - 填写应用名称：X Web
   - 选择默认语言

3. 填写商店信息

**应用详情：**
- 简短描述（80字符内）：
  - 英文：Quick launcher for X (Twitter) web version
  - 中文：X (Twitter) 网页版快捷启动器

- 完整描述：
  - 英文：
    X Web is a lightweight launcher for X (Twitter) web version.
    
    Features:
    • Quick access to X Home, Messages, and Login
    • Create desktop shortcuts for direct message access
    • Supports English and Chinese
    • Remembers your last visited page
    • Material 3 design
    
    Note: This app uses Chrome Custom Tabs for the best browsing experience.

  - 中文：
    X Web 是一款轻量级的 X (Twitter) 网页版启动器。
    
    功能特点：
    • 快速访问 X 主页、私信和登录页面
    • 创建桌面快捷方式直达私信
    • 支持中英文切换
    • 自动记住上次访问的页面
    • Material 3 设计风格
    
    注意：本应用使用 Chrome Custom Tabs 提供最佳浏览体验。

4. 上传应用
   - 上传 AAB 文件（推荐）或 APK 文件
   - 填写版本信息

5. 内容分级
   - 填写问卷获取内容分级
   - 本应用无敏感内容，通常可获得较低分级

6. 定价与分发
   - 选择免费
   - 选择目标国家/地区

### 第五步：应用截图

需要提供以下尺寸的截图：
- 手机：16:9 或 9:16 比例，至少 320px
- 7英寸平板（可选）
- 10英寸平板（可选）

建议截图内容：
1. 主界面截图
2. 菜单展开截图
3. 语言选择对话框截图

### 第六步：隐私政策

本应用需要提供隐私政策 URL。由于应用仅打开 X 网页，可以声明：

```
隐私政策

X Web 应用不收集、存储或传输任何用户数据。
应用功能：
- 打开 X (Twitter) 网页
- 创建本地快捷方式
- 保存语言偏好设置（本地存储）

本应用不包含广告、分析或第三方 SDK。
```

---

## 应用图标

当前使用简单的黑白 X 图标。建议：
1. 设计更专业的图标
2. 提供高分辨率版本（512x512px）用于商店展示

---

## 版本更新

更新应用时：
1. 增加 `versionCode`（必须比之前大）
2. 更新 `versionName`
3. 重新构建并上传

```kotlin
// app/build.gradle.kts
versionCode = 2  // 增加
versionName = "1.1.0"  // 更新
```

---

## 注意事项

1. **X (Twitter) 商标**
   - 应用名称和描述中使用了 "X" 和 "Twitter"
   - 可能需要考虑商标问题
   - 建议在描述中声明 "This app is not affiliated with X Corp."

2. **应用审核**
   - Google Play 审核通常需要 1-3 天
   - 确保应用功能正常、无崩溃

3. **测试**
   - 上传前在多台设备上测试
   - 使用 Android Studio 的预发布报告

---

## 快速构建命令

```bash
# 清理项目
./gradlew clean

# 构建 Debug 版本（测试用）
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease

# 构建 Release AAB（推荐）
./gradlew bundleRelease

# 安装到设备
./gradlew installDebug
```
