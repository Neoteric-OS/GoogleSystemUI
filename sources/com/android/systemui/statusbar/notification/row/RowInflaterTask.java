package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater.AnonymousClass1;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.InflationTask;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderImpl$$ExternalSyntheticLambda0;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RowInflaterTask implements InflationTask, AsyncLayoutInflater.OnInflateFinishedListener {
    public boolean mCancelled;
    public NotificationEntry mEntry;
    public Throwable mInflateOrigin;
    public long mInflateStartTimeMs;
    public RowInflationFinishedListener mListener;
    public final RowInflaterTaskLogger mLogger;
    public final SystemClock mSystemClock;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BasicRowInflater extends LayoutInflater {
        public static final String[] sClassPrefixList = {"android.widget.", "android.webkit.", "android.app."};

        public BasicRowInflater(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public final LayoutInflater cloneInContext(Context context) {
            return new BasicRowInflater(context);
        }

        @Override // android.view.LayoutInflater
        public final View onCreateView(String str, AttributeSet attributeSet) {
            View createView;
            String[] strArr = sClassPrefixList;
            for (int i = 0; i < 3; i++) {
                try {
                    createView = createView(str, strArr[i], attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class RowAsyncLayoutInflater implements LayoutInflater.Factory2 {
        public final NotificationEntry mEntry;
        public final RowInflaterTaskLogger mLogger;
        public final SystemClock mSystemClock;

        public RowAsyncLayoutInflater(NotificationEntry notificationEntry, SystemClock systemClock, RowInflaterTaskLogger rowInflaterTaskLogger) {
            this.mEntry = notificationEntry;
            this.mSystemClock = systemClock;
            this.mLogger = rowInflaterTaskLogger;
        }

        @Override // android.view.LayoutInflater.Factory
        public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return null;
        }

        @Override // android.view.LayoutInflater.Factory2
        public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            if (!str.equals(ExpandableNotificationRow.class.getName())) {
                return null;
            }
            ((SystemClockImpl) this.mSystemClock).getClass();
            long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
            ExpandableNotificationRow expandableNotificationRow = new ExpandableNotificationRow(context, attributeSet, this.mEntry);
            ((SystemClockImpl) this.mSystemClock).getClass();
            long elapsedRealtime2 = android.os.SystemClock.elapsedRealtime() - elapsedRealtime;
            RowInflaterTaskLogger rowInflaterTaskLogger = this.mLogger;
            NotificationEntry notificationEntry = this.mEntry;
            rowInflaterTaskLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            RowInflaterTaskLogger$logCreatedRow$2 rowInflaterTaskLogger$logCreatedRow$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.RowInflaterTaskLogger$logCreatedRow$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "created row in " + logMessage.getLong1() + " ms for " + logMessage.getStr1();
                }
            };
            LogBuffer logBuffer = rowInflaterTaskLogger.buffer;
            LogMessage obtain = logBuffer.obtain("RowInflaterTask", logLevel, rowInflaterTaskLogger$logCreatedRow$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.long1 = elapsedRealtime2;
            logBuffer.commit(obtain);
            return expandableNotificationRow;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface RowInflationFinishedListener {
    }

    public RowInflaterTask(SystemClock systemClock, RowInflaterTaskLogger rowInflaterTaskLogger) {
        this.mSystemClock = systemClock;
        this.mLogger = rowInflaterTaskLogger;
    }

    @Override // com.android.systemui.statusbar.InflationTask
    public final void abort() {
        this.mCancelled = true;
    }

    public void inflate(Context context, ViewGroup viewGroup, NotificationEntry notificationEntry, Executor executor, RowInflationFinishedListener rowInflationFinishedListener) {
        this.mInflateOrigin = new Throwable("inflate requested here");
        this.mListener = rowInflationFinishedListener;
        RowInflaterTaskLogger rowInflaterTaskLogger = this.mLogger;
        SystemClock systemClock = this.mSystemClock;
        RowAsyncLayoutInflater rowAsyncLayoutInflater = new RowAsyncLayoutInflater(notificationEntry, systemClock, rowInflaterTaskLogger);
        AsyncLayoutInflater asyncLayoutInflater = new AsyncLayoutInflater();
        AsyncLayoutInflater.AnonymousClass1 anonymousClass1 = asyncLayoutInflater.new AnonymousClass1();
        AsyncLayoutInflater.BasicInflater basicInflater = new AsyncLayoutInflater.BasicInflater(context);
        asyncLayoutInflater.mInflater = basicInflater;
        basicInflater.setFactory2(rowAsyncLayoutInflater);
        asyncLayoutInflater.mHandler = new Handler(Looper.myLooper(), anonymousClass1);
        asyncLayoutInflater.mInflateThread = AsyncLayoutInflater.InflateThread.sInstance;
        this.mEntry = notificationEntry;
        notificationEntry.abortTask();
        notificationEntry.mRunningTask = this;
        LogLevel logLevel = LogLevel.DEBUG;
        RowInflaterTaskLogger$logInflateStart$2 rowInflaterTaskLogger$logInflateStart$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.RowInflaterTaskLogger$logInflateStart$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("started row inflation for ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = rowInflaterTaskLogger.buffer;
        LogMessage obtain = logBuffer.obtain("RowInflaterTask", logLevel, rowInflaterTaskLogger$logInflateStart$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        ((SystemClockImpl) systemClock).getClass();
        this.mInflateStartTimeMs = android.os.SystemClock.elapsedRealtime();
        asyncLayoutInflater.inflateInternal(R.layout.status_bar_notification_row, viewGroup, this, basicInflater, executor);
    }

    public ExpandableNotificationRow inflateSynchronously(Context context, ViewGroup viewGroup, NotificationEntry notificationEntry) {
        BasicRowInflater basicRowInflater = new BasicRowInflater(context);
        basicRowInflater.setFactory2(new RowAsyncLayoutInflater(notificationEntry, this.mSystemClock, this.mLogger));
        return (ExpandableNotificationRow) basicRowInflater.inflate(R.layout.status_bar_notification_row, viewGroup, false);
    }

    @Override // androidx.asynclayoutinflater.view.AsyncLayoutInflater.OnInflateFinishedListener
    public final void onInflateFinished(View view, ViewGroup viewGroup) {
        ((SystemClockImpl) this.mSystemClock).getClass();
        long elapsedRealtime = android.os.SystemClock.elapsedRealtime() - this.mInflateStartTimeMs;
        NotificationEntry notificationEntry = this.mEntry;
        boolean z = this.mCancelled;
        RowInflaterTaskLogger rowInflaterTaskLogger = this.mLogger;
        LogLevel logLevel = LogLevel.DEBUG;
        RowInflaterTaskLogger$logInflateFinish$2 rowInflaterTaskLogger$logInflateFinish$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.row.RowInflaterTaskLogger$logInflateFinish$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str = logMessage.getBool1() ? "cancelled " : "";
                long long1 = logMessage.getLong1();
                String str1 = logMessage.getStr1();
                StringBuilder sb = new StringBuilder("finished ");
                sb.append(str);
                sb.append("row inflation in ");
                sb.append(long1);
                return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, " ms for ", str1);
            }
        };
        LogBuffer logBuffer = rowInflaterTaskLogger.buffer;
        LogMessage obtain = logBuffer.obtain("RowInflaterTask", logLevel, rowInflaterTaskLogger$logInflateFinish$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.long1 = elapsedRealtime;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
        if (this.mCancelled) {
            return;
        }
        try {
            this.mEntry.mRunningTask = null;
            ((NotificationRowBinderImpl$$ExternalSyntheticLambda0) this.mListener).onInflationFinished((ExpandableNotificationRow) view);
        } catch (Throwable th) {
            if (this.mInflateOrigin != null) {
                Log.e("RowInflaterTask", "Error in inflation finished listener: " + th, this.mInflateOrigin);
                th.addSuppressed(this.mInflateOrigin);
            }
            throw th;
        }
    }
}
