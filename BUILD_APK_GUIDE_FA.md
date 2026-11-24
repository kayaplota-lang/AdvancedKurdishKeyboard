# راهنمای ساخت APK

در این محیط فعلی Android SDK و Gradle نصب نیست؛ بنابراین APK را نمی‌توانم همین‌جا کامپایل و آزمایش کنم.
برای ساخت خودکار APK از GitHub Actions استفاده کن:

1. فایل ZIP را استخراج کن.
2. یک مخزن جدید در GitHub بساز.
3. تمام محتویات پوشه پروژه را در مخزن بارگذاری کن (از جمله پوشه مخفی `.github`).
4. وارد تب Actions شو.
5. گردش‌کار `Build Android APK` را اجرا کن (Run workflow).
6. پس از موفقیت، از بخش Artifacts فایل `AdvancedKurdishKeyboard-debug-apk` را دریافت کن.
7. فایل `app-debug.apk` را روی گوشی نصب کن.
8. در تنظیمات سامسونگ: General management > Keyboard list and default، صفحه‌کلید Advanced Kurdish را فعال و انتخاب کن.

این خروجی debug است و برای نصب شخصی مناسب است؛ برای انتشار عمومی باید نسخه release با کلید امضای اختصاصی ساخته شود.
