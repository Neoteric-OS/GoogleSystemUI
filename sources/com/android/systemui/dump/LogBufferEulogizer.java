package com.android.systemui.dump;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.dump.DumpsysEntry;
import com.android.systemui.util.io.Files;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import kotlin.io.CloseableKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogBufferEulogizer {
    public final DumpManager dumpManager;
    public final Files files;
    public final Path logPath;
    public final long maxLogAgeToDump;
    public final long minWriteGap;
    public final SystemClock systemClock;

    public LogBufferEulogizer(Context context, DumpManager dumpManager, SystemClock systemClock, Files files) {
        Path path = Paths.get(context.getFilesDir().toPath().toString(), "log_buffers.txt");
        long j = LogBufferEulogizerKt.MIN_WRITE_GAP;
        long j2 = LogBufferEulogizerKt.MAX_AGE_TO_DUMP;
        this.dumpManager = dumpManager;
        this.systemClock = systemClock;
        this.files = files;
        this.logPath = path;
        this.minWriteGap = j;
        this.maxLogAgeToDump = j2;
    }

    public final long getMillisSinceLastWrite(Path path) {
        BasicFileAttributes basicFileAttributes;
        FileTime lastModifiedTime;
        try {
            this.files.getClass();
            basicFileAttributes = java.nio.file.Files.readAttributes(path, (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
        } catch (IOException unused) {
            basicFileAttributes = null;
        }
        ((SystemClockImpl) this.systemClock).getClass();
        return System.currentTimeMillis() - ((basicFileAttributes == null || (lastModifiedTime = basicFileAttributes.lastModifiedTime()) == null) ? 0L : lastModifiedTime.toMillis());
    }

    public final void record(Throwable th) {
        SystemClock systemClock = this.systemClock;
        ((SystemClockImpl) systemClock).getClass();
        long uptimeMillis = android.os.SystemClock.uptimeMillis();
        Log.i("BufferEulogizer", "Performing emergency dump of log buffers");
        long millisSinceLastWrite = getMillisSinceLastWrite(this.logPath);
        if (millisSinceLastWrite < this.minWriteGap) {
            Log.w("BufferEulogizer", "Cannot dump logs, last write was only " + millisSinceLastWrite + " ms ago");
            return;
        }
        long j = 0;
        try {
            Files files = this.files;
            Path path = this.logPath;
            OpenOption[] openOptionArr = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
            files.getClass();
            BufferedWriter newBufferedWriter = java.nio.file.Files.newBufferedWriter(path, StandardCharsets.UTF_8, openOptionArr);
            try {
                PrintWriter printWriter = new PrintWriter(newBufferedWriter);
                SimpleDateFormat simpleDateFormat = LogBufferEulogizerKt.DATE_FORMAT;
                ((SystemClockImpl) systemClock).getClass();
                printWriter.println(simpleDateFormat.format(Long.valueOf(System.currentTimeMillis())));
                printWriter.println();
                printWriter.println("Dump triggered by exception:");
                th.printStackTrace(printWriter);
                for (DumpsysEntry dumpsysEntry : this.dumpManager.getLogBuffers()) {
                    if (dumpsysEntry instanceof DumpsysEntry.DumpableEntry) {
                        DumpHandler.Companion.dumpDumpable((DumpsysEntry.DumpableEntry) dumpsysEntry, printWriter, new String[0]);
                    } else if (dumpsysEntry instanceof DumpsysEntry.LogBufferEntry) {
                        DumpHandler.Companion.dumpBuffer((DumpsysEntry.LogBufferEntry) dumpsysEntry, printWriter, 0);
                    } else if (dumpsysEntry instanceof DumpsysEntry.TableLogBufferEntry) {
                        DumpHandler.Companion.dumpTableBuffer((DumpsysEntry.TableLogBufferEntry) dumpsysEntry, printWriter, new String[0]);
                    }
                }
                ((SystemClockImpl) systemClock).getClass();
                j = android.os.SystemClock.uptimeMillis() - uptimeMillis;
                printWriter.println();
                printWriter.println("Buffer eulogy took " + j + "ms");
                CloseableKt.closeFinally(newBufferedWriter, null);
            } finally {
            }
        } catch (Exception e) {
            Log.e("BufferEulogizer", "Exception while attempting to dump buffers, bailing", e);
        }
        Log.i("BufferEulogizer", "Buffer eulogy took " + j + "ms");
    }
}
