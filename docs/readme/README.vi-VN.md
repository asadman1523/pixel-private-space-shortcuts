# Pixel Private Space Shortcuts

Lối tắt màn hình chính gốc cho ứng dụng Không gian riêng tư trên Pixel, thông qua LSPosed.

[![Build](https://img.shields.io/github/actions/workflow/status/asadman1523/pixel-private-space-shortcuts/build.yml?branch=main&style=flat&logo=githubactions&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/actions/workflows/build.yml)
[![Release](https://img.shields.io/github/v/release/asadman1523/pixel-private-space-shortcuts?include_prereleases&style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Downloads](https://img.shields.io/github/downloads/asadman1523/pixel-private-space-shortcuts/total?style=flat&logo=github&logoColor=white)](https://github.com/asadman1523/pixel-private-space-shortcuts/releases)
[![Android](https://img.shields.io/badge/Android-17%20%2F%20API%2037%20experimental-orange?style=flat&logo=android&logoColor=white)](#compatibility)
[![License](https://img.shields.io/badge/License-Apache--2.0-blue?style=flat&logo=apache&logoColor=white)](../../LICENSE)

Read this in other languages: [English](../../README.md), [简体中文](README.zh-CN.md), [繁體中文](README.zh-TW.md), [한국어](README.ko-KR.md), [日本語](README.ja-JP.md), [Polski](README.pl-PL.md), [Français](README.fr-FR.md), [Español](README.es-ES.md), [Português](README.pt-BR.md), [Русский](README.ru-RU.md), [Türkçe](README.tr-TR.md), [Italiano](README.it-IT.md), [Bahasa Indonesia](README.id-ID.md), [Українська](README.uk-UA.md), [العربية](README.ar-AR.md), **Tiếng Việt**, [Deutsch](README.de-DE.md), [Uzbek](README.uz-UZ.md), [עברית](README.he-IL.md)

Video minh họa sẽ được ghi sau khi kiểm chứng trên thiết bị; không dùng mô phỏng thay cho kết quả thực tế.

<a id="features"></a>

## Tính năng

Nhấn giữ ứng dụng trong Không gian riêng tư đã mở khóa và chọn **Thêm vào màn hình chính**, hoặc kéo trực tiếp ra màn hình chính. Thao tác này cũng hoạt động với các ứng dụng riêng tư ở hàng đề xuất trên đầu Tất cả ứng dụng. Mô-đun dùng cách bố trí, cơ sở dữ liệu, biểu tượng và dấu khóa của Pixel Launcher. Số sê-ri hồ sơ cùng thành phần khởi chạy xác định đích và ngăn trùng lặp. Hỗ trợ di chuyển, thư mục và xóa.

Khi khóa, lối tắt được thiết kế để giữ nguyên vị trí và yêu cầu xác thực hệ thống khi chạm. Nếu đã mở khóa, ứng dụng riêng tư mở trực tiếp. Mỗi yêu cầu chỉ chạy một lần và bị xóa khi hủy, hết hạn hoặc Launcher bị hủy. Không chuyển sang bản trong hồ sơ chính. Chỉ thay đổi mục do mô-đun tạo; không gồm tiện ích hay lối tắt nội bộ. Tên và biểu tượng vẫn hiển thị khi Không gian riêng tư bị khóa.

Lối tắt bị khóa giữ màu gốc và dấu khóa gốc. Trang thông tin theo chế độ sáng hoặc tối của hệ thống với bảng màu đen, trắng và xám cố định.

<a id="compatibility"></a>

## Tương thích

**Bản alpha thử nghiệm; kiểm chứng thiết bị chưa hoàn tất.** Pixel 10a, Android 17 / API 37, `CP2A.260805.005`, Pixel Launcher 17 (`907`), Magisk, LSPosed 2.2.0 (7854). Phải khớp chính xác dấu vân tay APK trong [biên bản](../TESTING.md). Phiên bản khác sẽ tắt bộ thích ứng và ghi lý do. Biên dịch thành công không chứng minh tương thích.

<a id="installation"></a>

## Cài đặt

Cài APK từ [bản phát hành](https://github.com/asadman1523/pixel-private-space-shortcuts/releases) vào hồ sơ chính. Bật **Pixel Private Space Shortcuts** trong LSPosed, chỉ chọn `com.google.android.apps.nexuslauncher`, rồi khởi động lại Launcher hoặc điện thoại. APK debug từ CI dùng để thử nghiệm và có thể mang chữ ký khác.

Trang có nút **Mở LSPosed**. Trình quản lý độc lập mở trực tiếp; bản tích hợp cần cấp quyền Magisk lần đầu. Quyền chỉ dùng khi nhấn nút này, không dùng để mở ứng dụng riêng tư.

<a id="usage"></a>

## Sử dụng

Mở khóa Không gian riêng tư, nhấn giữ ứng dụng và chọn **Thêm vào màn hình chính**, hoặc kéo ra màn hình chính. Các ứng dụng riêng tư trong hàng đề xuất cũng có thể được thêm theo cách tương tự. Chạm biểu tượng để mở đúng bản riêng tư, xác thực bằng Android nếu cần. Hủy sẽ bỏ yêu cầu. Nhấn giữ biểu tượng để di chuyển, đưa vào thư mục hoặc xóa. Thêm lại sẽ báo lối tắt đã tồn tại. Có thể cài ứng dụng thông tin của mô-đun trong Không gian riêng tư để thử mà không dùng dữ liệu nhạy cảm.

<a id="build"></a>

## Biên dịch

Dùng JDK 17, SDK `platforms;android-37.0`, Build Tools `36.0.0` và Gradle wrapper đi kèm. Chạy lệnh bên dưới (`gradlew.bat` trên Windows). CI biên dịch, kiểm thử trạng thái mở khóa, chạy Android lint và kiểm tra mọi README cùng liên kết cục bộ. Bốn biến `PPSS_*` trong [BUILDING](../BUILDING.md) cấu hình chữ ký; giữ khóa ngoài kho mã.

```sh
python3 -X utf8 tools/check_docs.py
./gradlew testDebugUnitTest lintDebug assembleDebug
```

<a id="disable"></a>

## Tắt hoặc gỡ bỏ

Xóa lối tắt không cần qua Launcher, tắt mô-đun trong LSPosed và khởi động lại Launcher, rồi gỡ APK nếu muốn. Không xóa dữ liệu Launcher. Các mục vẫn là mục gốc nhưng xử lý khóa của mô-đun không còn hoạt động. Việc xóa ứng dụng hoặc hồ sơ đã xác nhận tuân theo cơ chế dọn dẹp gốc.

<a id="license"></a>

## Giấy phép

[Apache-2.0](../../LICENSE). Không liên kết với Google hoặc LSPosed. Không phát hành APK Google, tệp dịch ngược, nhật ký thiết bị, thông tin xác thực hay khóa. Xem [kiến trúc](../ARCHITECTURE.md) và [kiểm chứng](../TESTING.md).
