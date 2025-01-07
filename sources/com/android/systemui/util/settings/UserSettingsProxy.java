package com.android.systemui.util.settings;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.systemui.dagger.SystemUIModule$$ExternalSyntheticLambda0;
import com.android.systemui.doze.DozeSensors;
import com.android.systemui.qs.ReduceBrightColorsControllerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface UserSettingsProxy extends SettingsProxy {
    default boolean getBoolForUser(int i, String str, boolean z) {
        return getIntForUser(str, z ? 1 : 0, i) != 0;
    }

    SystemUIModule$$ExternalSyntheticLambda0 getCurrentUserProvider();

    default float getFloatForUser(float f, int i, String str) {
        String stringForUser = getStringForUser(i, str);
        if (stringForUser == null) {
            return f;
        }
        try {
            return Float.parseFloat(stringForUser);
        } catch (NumberFormatException unused) {
            return f;
        }
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    default int getInt(int i, String str) {
        return getIntForUser(str, i, getUserId());
    }

    default int getIntForUser(String str, int i, int i2) {
        String stringForUser = getStringForUser(i2, str);
        if (stringForUser == null) {
            return i;
        }
        try {
            return Integer.parseInt(stringForUser);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    default int getRealUserHandle(int i) {
        return i != -2 ? i : ((UserTrackerImpl) getCurrentUserProvider().f$0).getUserId();
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    default String getString(String str) {
        return getStringForUser(getUserId(), str);
    }

    String getStringForUser(int i, String str);

    default int getUserId() {
        return getContentResolver().getUserId();
    }

    default boolean putInt(int i, String str) {
        return putIntForUser(str, i, getUserId());
    }

    default boolean putIntForUser(String str, int i, int i2) {
        return putStringForUser(str, String.valueOf(i), i2);
    }

    boolean putStringForUser(String str, String str2, int i);

    default void registerContentObserverForUserAsync(String str, DozeSensors.AnonymousClass1 anonymousClass1) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new UserSettingsProxy$registerContentObserverForUserAsync$1(this, str, anonymousClass1, -1, null), 3);
    }

    default void registerContentObserverForUserSync(String str, ContentObserver contentObserver, int i) {
        registerContentObserverForUserSync(getUriFor(str), false, contentObserver, i);
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    default void registerContentObserverSync(Uri uri, ContentObserver contentObserver) {
        registerContentObserverForUserSync(uri, false, contentObserver, getUserId());
    }

    default void registerContentObserverForUserSync(String str, boolean z, ContentObserver contentObserver, int i) {
        registerContentObserverForUserSync(getUriFor(str), z, contentObserver, i);
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    default void registerContentObserverSync(Uri uri, boolean z, ContentObserver contentObserver) {
        registerContentObserverForUserSync(uri, z, contentObserver, getUserId());
    }

    default void registerContentObserverForUserSync(Uri uri, boolean z, ContentObserver contentObserver, int i) {
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("USP#registerObserver#[" + uri + "]");
        }
        try {
            getContentResolver().registerContentObserver(uri, z, contentObserver, getRealUserHandle(i));
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }

    default void registerContentObserverForUserAsync(Uri uri, DozeParameters.SettingsObserver settingsObserver) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new UserSettingsProxy$registerContentObserverForUserAsync$2(this, uri, settingsObserver, -1, null), 3);
    }

    default void registerContentObserverForUserAsync(Uri uri, ContentObserver contentObserver, int i, Runnable runnable) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new UserSettingsProxy$registerContentObserverForUserAsync$3(this, uri, contentObserver, i, runnable, null), 3);
    }

    default void registerContentObserverForUserAsync(ReduceBrightColorsControllerImpl.AnonymousClass1 anonymousClass1, int i) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new UserSettingsProxy$registerContentObserverForUserAsync$4(this, "reduce_bright_colors_activated", false, anonymousClass1, i, null), 3);
    }

    default void registerContentObserverForUserAsync(Uri uri, ContentObserver contentObserver) {
        CoroutineDispatcher backgroundDispatcher = getBackgroundDispatcher();
        TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        backgroundDispatcher.getClass();
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(backgroundDispatcher, emptyCoroutineContext)), null, null, new UserSettingsProxy$registerContentObserverForUserAsync$5(this, uri, false, contentObserver, -1, null), 3);
    }
}
