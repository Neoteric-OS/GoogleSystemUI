package com.android.systemui.util.settings;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.qs.SettingObserver;
import com.android.systemui.qs.SettingObserver$$ExternalSyntheticLambda0;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SettingsProxy {
    CoroutineDispatcher getBackgroundDispatcher();

    ContentResolver getContentResolver();

    default int getInt(int i, String str) {
        String string = getString(str);
        if (string == null) {
            return i;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    String getString(String str);

    Uri getUriFor(String str);

    default void registerContentObserverAsync(Uri uri, SettingObserver settingObserver, SettingObserver$$ExternalSyntheticLambda0 settingObserver$$ExternalSyntheticLambda0) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new SettingsProxy$registerContentObserverAsync$8(this, uri, false, settingObserver, settingObserver$$ExternalSyntheticLambda0, null), 3);
    }

    default void registerContentObserverSync(String str, ContentObserver contentObserver) {
        registerContentObserverSync(getUriFor(str), contentObserver);
    }

    default void unregisterContentObserverAsync(ContentObserver contentObserver) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new SettingsProxy$unregisterContentObserverAsync$1(this, contentObserver, null), 3);
    }

    default void unregisterContentObserverSync(ContentObserver contentObserver) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("SP#unregisterObserver");
        }
        try {
            getContentResolver().unregisterContentObserver(contentObserver);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    default void registerContentObserverSync(Uri uri, ContentObserver contentObserver) {
        registerContentObserverSync(uri, false, contentObserver);
    }

    default void registerContentObserverSync(Uri uri, boolean z, ContentObserver contentObserver) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("SP#registerObserver#[" + uri + "]");
        }
        try {
            getContentResolver().registerContentObserver(uri, z, contentObserver);
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
