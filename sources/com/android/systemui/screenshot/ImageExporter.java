package com.android.systemui.screenshot;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import androidx.exifinterface.media.ExifInterface;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.UUID;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ImageExporter {
    public static final Duration PENDING_ENTRY_TTL = Duration.ofHours(24);
    public static final String SCREENSHOTS_PATH = Environment.DIRECTORY_PICTURES + File.separator + Environment.DIRECTORY_SCREENSHOTS;
    public final Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.PNG;
    public final int mQuality = 100;
    public final ContentResolver mResolver;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.screenshot.ImageExporter$1, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$CompressFormat;

        static {
            int[] iArr = new int[Bitmap.CompressFormat.values().length];
            $SwitchMap$android$graphics$Bitmap$CompressFormat = iArr;
            try {
                iArr[Bitmap.CompressFormat.JPEG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.PNG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP_LOSSLESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$CompressFormat[Bitmap.CompressFormat.WEBP_LOSSY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ImageExportException extends IOException {
        public ImageExportException(String str) {
            super(str);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Result {
        public String fileName;
        public Bitmap.CompressFormat format;
        public UUID requestId;
        public long timestamp;
        public Uri uri;

        public final String toString() {
            return "Result{uri=" + this.uri + ", requestId=" + this.requestId + ", fileName='" + this.fileName + "', timestamp=" + this.timestamp + ", format=" + this.format + '}';
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Task {
        public final Bitmap mBitmap;
        public final ZonedDateTime mCaptureTime;
        public final String mFileName;
        public final Bitmap.CompressFormat mFormat;
        public final UserHandle mOwner;
        public final int mQuality;
        public final UUID mRequestId;
        public final ContentResolver mResolver;

        public Task(ContentResolver contentResolver, UUID uuid, Bitmap bitmap, ZonedDateTime zonedDateTime, Bitmap.CompressFormat compressFormat, int i, UserHandle userHandle, String str) {
            this.mResolver = contentResolver;
            this.mRequestId = uuid;
            this.mBitmap = bitmap;
            this.mCaptureTime = zonedDateTime;
            this.mFormat = compressFormat;
            this.mQuality = i;
            this.mOwner = userHandle;
            this.mFileName = str;
        }

        public final Result execute() {
            Uri uri;
            String str = this.mFileName;
            Trace.beginSection("ImageExporter_execute");
            Result result = new Result();
            try {
                try {
                    uri = ImageExporter.m852$$Nest$smcreateEntry(this.mResolver, this.mFormat, this.mCaptureTime, str, this.mOwner);
                } catch (ImageExportException e) {
                    e = e;
                    uri = null;
                }
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    ImageExporter.m855$$Nest$smwriteImage(this.mResolver, this.mBitmap, this.mFormat, this.mQuality, uri);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    ImageExporter.m854$$Nest$smwriteExif(this.mResolver, uri, this.mRequestId, this.mBitmap.getWidth(), this.mBitmap.getHeight(), this.mCaptureTime);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    ImageExporter.m853$$Nest$smpublishEntry(this.mResolver, uri);
                    result.timestamp = this.mCaptureTime.toInstant().toEpochMilli();
                    result.requestId = this.mRequestId;
                    result.uri = uri;
                    result.fileName = str;
                    result.format = this.mFormat;
                    return result;
                } catch (ImageExportException e2) {
                    e = e2;
                    if (uri != null) {
                        this.mResolver.delete(uri, null);
                    }
                    throw e;
                }
            } finally {
                Trace.endSection();
            }
        }

        public final String toString() {
            return "export [" + this.mBitmap + "] to [" + this.mFormat + "] at quality " + this.mQuality;
        }
    }

    /* renamed from: -$$Nest$smcreateEntry, reason: not valid java name */
    public static Uri m852$$Nest$smcreateEntry(ContentResolver contentResolver, Bitmap.CompressFormat compressFormat, ZonedDateTime zonedDateTime, String str, UserHandle userHandle) {
        Trace.beginSection("ImageExporter_createEntry");
        try {
            Uri insert = contentResolver.insert(ContentProvider.maybeAddUserId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, userHandle.getIdentifier()), createMetadata(zonedDateTime, compressFormat, str));
            Log.d("Screenshot", "Inserted new URI: " + insert);
            if (insert != null) {
                return insert;
            }
            throw new ImageExportException("ContentResolver#insert returned null.");
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: -$$Nest$smpublishEntry, reason: not valid java name */
    public static void m853$$Nest$smpublishEntry(ContentResolver contentResolver, Uri uri) {
        Trace.beginSection("ImageExporter_publishEntry");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_pending", (Integer) 0);
            contentValues.putNull("date_expires");
            if (contentResolver.update(uri, contentValues, null) >= 1) {
            } else {
                throw new ImageExportException("Failed to publish entry. ContentResolver#update reported no rows updated.");
            }
        } finally {
            Trace.endSection();
        }
    }

    /* renamed from: -$$Nest$smwriteExif, reason: not valid java name */
    public static void m854$$Nest$smwriteExif(ContentResolver contentResolver, Uri uri, UUID uuid, int i, int i2, ZonedDateTime zonedDateTime) {
        Trace.beginSection("ImageExporter_writeExif");
        try {
            try {
                ParcelFileDescriptor openFile = contentResolver.openFile(uri, "rw", null);
                if (openFile == null) {
                    throw new ImageExportException("ContentResolver#openFile returned null.");
                }
                try {
                    ExifInterface exifInterface = new ExifInterface(openFile.getFileDescriptor());
                    updateExifAttributes(exifInterface, uuid, i, i2, zonedDateTime);
                    try {
                        exifInterface.saveAttributes();
                        FileUtils.closeQuietly(openFile);
                        Trace.endSection();
                    } catch (IOException e) {
                        throw new ImageExportException("ExifInterface threw an exception writing to the file descriptor.", e);
                    }
                } catch (IOException e2) {
                    throw new ImageExportException("ExifInterface threw an exception reading from the file descriptor.", e2);
                }
            } catch (FileNotFoundException e3) {
                throw new ImageExportException("ContentResolver#openFile threw an exception.", e3);
            }
        } catch (Throwable th) {
            FileUtils.closeQuietly((AutoCloseable) null);
            Trace.endSection();
            throw th;
        }
    }

    /* renamed from: -$$Nest$smwriteImage, reason: not valid java name */
    public static void m855$$Nest$smwriteImage(ContentResolver contentResolver, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i, Uri uri) {
        Trace.beginSection("ImageExporter_writeImage");
        try {
            try {
                OutputStream openOutputStream = contentResolver.openOutputStream(uri);
                try {
                    SystemClock.elapsedRealtime();
                    if (!bitmap.compress(compressFormat, i, openOutputStream)) {
                        throw new ImageExportException("Bitmap.compress returned false. (Failure unknown)");
                    }
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                } catch (Throwable th) {
                    if (openOutputStream != null) {
                        try {
                            openOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException e) {
                throw new ImageExportException("ContentResolver#openOutputStream threw an exception.", e);
            }
        } finally {
            Trace.endSection();
        }
    }

    public ImageExporter(ContentResolver contentResolver) {
        this.mResolver = contentResolver;
    }

    public static String createFilename(ZonedDateTime zonedDateTime, Bitmap.CompressFormat compressFormat, int i) {
        return i == 0 ? String.format("Screenshot_%1$tY%<tm%<td-%<tH%<tM%<tS.%2$s", zonedDateTime, fileExtension(compressFormat)) : String.format("Screenshot_%1$tY%<tm%<td-%<tH%<tM%<tS-display-%2$d.%3$s", zonedDateTime, Integer.valueOf(i), fileExtension(compressFormat));
    }

    public static ContentValues createMetadata(ZonedDateTime zonedDateTime, Bitmap.CompressFormat compressFormat, String str) {
        String str2;
        ContentValues contentValues = new ContentValues();
        contentValues.put("relative_path", SCREENSHOTS_PATH);
        contentValues.put("_display_name", str);
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$CompressFormat[compressFormat.ordinal()];
        if (i == 1) {
            str2 = "image/jpeg";
        } else if (i == 2) {
            str2 = "image/png";
        } else {
            if (i != 3 && i != 4 && i != 5) {
                throw new IllegalArgumentException("Unknown CompressFormat!");
            }
            str2 = "image/webp";
        }
        contentValues.put("mime_type", str2);
        contentValues.put("date_added", Long.valueOf(zonedDateTime.toEpochSecond()));
        contentValues.put("date_modified", Long.valueOf(zonedDateTime.toEpochSecond()));
        contentValues.put("date_expires", Long.valueOf(zonedDateTime.plus((TemporalAmount) PENDING_ENTRY_TTL).toEpochSecond()));
        contentValues.put("is_pending", (Integer) 1);
        return contentValues;
    }

    public static String createSystemFileDisplayName(String str, Bitmap.CompressFormat compressFormat) {
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, ".");
        m.append(fileExtension(compressFormat));
        return m.toString();
    }

    public static String fileExtension(Bitmap.CompressFormat compressFormat) {
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$CompressFormat[compressFormat.ordinal()];
        if (i == 1) {
            return "jpg";
        }
        if (i == 2) {
            return "png";
        }
        if (i == 3 || i == 4 || i == 5) {
            return "webp";
        }
        throw new IllegalArgumentException("Unknown CompressFormat!");
    }

    public static void updateExifAttributes(ExifInterface exifInterface, UUID uuid, int i, int i2, ZonedDateTime zonedDateTime) {
        exifInterface.setAttribute("ImageUniqueID", uuid.toString());
        exifInterface.setAttribute("Software", "Android " + Build.DISPLAY);
        exifInterface.setAttribute("ImageWidth", Integer.toString(i));
        exifInterface.setAttribute("ImageLength", Integer.toString(i2));
        String format = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss").format(zonedDateTime);
        String format2 = DateTimeFormatter.ofPattern("SSS").format(zonedDateTime);
        String format3 = DateTimeFormatter.ofPattern("xxx").format(zonedDateTime);
        exifInterface.setAttribute("DateTimeOriginal", format);
        exifInterface.setAttribute("SubSecTimeOriginal", format2);
        exifInterface.setAttribute("OffsetTimeOriginal", format3);
    }

    public final CallbackToFutureAdapter.SafeFuture export(Executor executor, UUID uuid, Bitmap bitmap, UserHandle userHandle, int i) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ContentResolver contentResolver = this.mResolver;
        Bitmap.CompressFormat compressFormat = this.mCompressFormat;
        return CallbackToFutureAdapter.getFuture(new ImageExporter$$ExternalSyntheticLambda0(executor, new Task(contentResolver, uuid, bitmap, now, compressFormat, this.mQuality, userHandle, createFilename(now, compressFormat, i))));
    }
}
