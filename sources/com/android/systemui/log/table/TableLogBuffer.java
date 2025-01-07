package com.android.systemui.log.table;

import android.os.Trace;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.common.buffer.RingBuffer;
import com.android.systemui.log.LogcatEchoTracker;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.table.TableChange;
import com.android.systemui.plugins.log.TableLogBufferBase;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TableLogBuffer implements Dumpable, TableLogBufferBase {
    public final RingBuffer buffer;
    public final Map lastEvictedValues;
    public final LogProxyDefault localLogcat;
    public final LogcatEchoTracker logcatEchoTracker;
    public final String name;
    public final SystemClock systemClock;
    public final TableRowLoggerImpl tempRow;

    public TableLogBuffer(int i, String str, SystemClock systemClock, LogcatEchoTracker logcatEchoTracker) {
        this.name = str;
        this.systemClock = systemClock;
        this.logcatEchoTracker = logcatEchoTracker;
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.buffer = new RingBuffer(i, new Function0() { // from class: com.android.systemui.log.table.TableLogBuffer$buffer$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new TableChange();
            }
        });
        this.lastEvictedValues = new LinkedHashMap();
        this.tempRow = new TableRowLoggerImpl(this);
    }

    @Override // com.android.systemui.Dumpable
    public final synchronized void dump(PrintWriter printWriter, String[] strArr) {
        try {
            printWriter.append("SystemUI StateChangeTableSection START: ").println(this.name);
            printWriter.append("version ").println("1");
            Iterator it = CollectionsKt.sortedWith(this.lastEvictedValues.values(), new TableLogBuffer$dump$$inlined$sortedBy$1()).iterator();
            while (it.hasNext()) {
                dump((TableChange) it.next(), printWriter);
            }
            int size = this.buffer.getSize();
            for (int i = 0; i < size; i++) {
                dump((TableChange) this.buffer.get(i), printWriter);
            }
            printWriter.append("SystemUI StateChangeTableSection END: ").println(this.name);
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void echoToDesiredEndpoints(TableChange tableChange) {
        LogLevel logLevel = LogLevel.DEBUG;
        LogcatEchoTracker logcatEchoTracker = this.logcatEchoTracker;
        String str = this.name;
        if ((logcatEchoTracker.isBufferLoggable(logLevel, str) || logcatEchoTracker.isTagLoggable(logLevel, tableChange.columnName)) && tableChange.hasData()) {
            Log.d(str, TableLogBufferKt.TABLE_LOG_DATE_FORMAT.format(Long.valueOf(tableChange.timestamp)) + "|" + tableChange.getName() + "|" + tableChange.getVal());
        }
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, Integer num) {
        TableLogBufferBase.DefaultImpls.logChange(this, str, str2, num);
    }

    public final synchronized TableChange obtain(long j, String str, String str2, boolean z) {
        TableChange tableChange;
        try {
            if (StringsKt.contains$default(str, "|")) {
                throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("columnPrefix cannot contain | but was ", str));
            }
            if (StringsKt.contains$default(str2, "|")) {
                throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("columnName cannot contain | but was ", str2));
            }
            tableChange = (TableChange) this.buffer.advance();
            if (tableChange.hasData()) {
                saveEvictedValue(tableChange);
            }
            tableChange.reset(j, str, str2, z);
        } catch (Throwable th) {
            throw th;
        }
        return tableChange;
    }

    public final void saveEvictedValue(TableChange tableChange) {
        Trace.beginSection("TableLogBuffer#saveEvictedValue");
        String name = tableChange.getName();
        TableChange tableChange2 = (TableChange) this.lastEvictedValues.get(name);
        if (tableChange2 == null) {
            tableChange2 = new TableChange();
            this.lastEvictedValues.put(name, tableChange2);
        }
        tableChange2.reset(tableChange.timestamp, tableChange.columnPrefix, tableChange.columnName, tableChange.isInitial);
        int ordinal = tableChange.type.ordinal();
        if (ordinal == 0) {
            String str = tableChange.str;
            tableChange2.type = TableChange.DataType.STRING;
            tableChange2.str = str != null ? StringsKt.take(500, str) : null;
        } else if (ordinal == 1) {
            boolean z = tableChange.bool;
            tableChange2.type = TableChange.DataType.BOOLEAN;
            tableChange2.bool = z;
        } else if (ordinal == 2) {
            Integer num = tableChange.f37int;
            tableChange2.type = TableChange.DataType.INT;
            tableChange2.f37int = num;
        }
        Trace.endSection();
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, String str3) {
        TableLogBufferBase.DefaultImpls.logChange(this, str, str2, str3);
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, boolean z) {
        TableLogBufferBase.DefaultImpls.logChange(this, str, str2, z);
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, String str3, boolean z) {
        ((SystemClockImpl) this.systemClock).getClass();
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("TableLogBuffer#logChange(string)");
        TableChange obtain = obtain(currentTimeMillis, str, str2, z);
        obtain.type = TableChange.DataType.STRING;
        obtain.str = str3 != null ? StringsKt.take(500, str3) : null;
        echoToDesiredEndpoints(obtain);
        Trace.endSection();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TableRowLoggerImpl {
        public final TableLogBuffer tableLogBuffer;
        public long timestamp = 0;
        public String columnPrefix = "";
        public boolean isInitial = false;

        public TableRowLoggerImpl(TableLogBuffer tableLogBuffer) {
            this.tableLogBuffer = tableLogBuffer;
        }

        public final void logChange(String str, String str2) {
            long j = this.timestamp;
            String str3 = this.columnPrefix;
            boolean z = this.isInitial;
            TableLogBuffer tableLogBuffer = this.tableLogBuffer;
            tableLogBuffer.getClass();
            Trace.beginSection("TableLogBuffer#logChange(string)");
            TableChange obtain = tableLogBuffer.obtain(j, str3, str, z);
            obtain.type = TableChange.DataType.STRING;
            obtain.str = str2 != null ? StringsKt.take(500, str2) : null;
            tableLogBuffer.echoToDesiredEndpoints(obtain);
            Trace.endSection();
        }

        public final void logChange(String str, boolean z) {
            long j = this.timestamp;
            String str2 = this.columnPrefix;
            boolean z2 = this.isInitial;
            TableLogBuffer tableLogBuffer = this.tableLogBuffer;
            tableLogBuffer.getClass();
            Trace.beginSection("TableLogBuffer#logChange(boolean)");
            TableChange obtain = tableLogBuffer.obtain(j, str2, str, z2);
            obtain.type = TableChange.DataType.BOOLEAN;
            obtain.bool = z;
            tableLogBuffer.echoToDesiredEndpoints(obtain);
            Trace.endSection();
        }

        public final void logChange(int i, String str) {
            long j = this.timestamp;
            String str2 = this.columnPrefix;
            Integer valueOf = Integer.valueOf(i);
            boolean z = this.isInitial;
            TableLogBuffer tableLogBuffer = this.tableLogBuffer;
            tableLogBuffer.getClass();
            Trace.beginSection("TableLogBuffer#logChange(int)");
            TableChange obtain = tableLogBuffer.obtain(j, str2, str, z);
            obtain.type = TableChange.DataType.INT;
            obtain.f37int = valueOf;
            tableLogBuffer.echoToDesiredEndpoints(obtain);
            Trace.endSection();
        }
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, boolean z, boolean z2) {
        ((SystemClockImpl) this.systemClock).getClass();
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("TableLogBuffer#logChange(boolean)");
        TableChange obtain = obtain(currentTimeMillis, str, str2, z2);
        obtain.type = TableChange.DataType.BOOLEAN;
        obtain.bool = z;
        echoToDesiredEndpoints(obtain);
        Trace.endSection();
    }

    public static void dump(TableChange tableChange, PrintWriter printWriter) {
        if (tableChange.hasData()) {
            printWriter.print(TableLogBufferKt.TABLE_LOG_DATE_FORMAT.format(Long.valueOf(tableChange.timestamp)));
            printWriter.print("|");
            printWriter.print(tableChange.getName());
            printWriter.print("|");
            printWriter.print(tableChange.getVal());
            printWriter.println();
        }
    }

    @Override // com.android.systemui.plugins.log.TableLogBufferBase
    public final void logChange(String str, String str2, Integer num, boolean z) {
        ((SystemClockImpl) this.systemClock).getClass();
        long currentTimeMillis = System.currentTimeMillis();
        Trace.beginSection("TableLogBuffer#logChange(int)");
        TableChange obtain = obtain(currentTimeMillis, str, str2, z);
        obtain.type = TableChange.DataType.INT;
        obtain.f37int = num;
        echoToDesiredEndpoints(obtain);
        Trace.endSection();
    }
}
