package com.google.android.systemui.columbus.fetchers;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContentFetcher {
    public final CoroutineDispatcher bgDispatcher;
    public final Map cachedBooleanFlows = new LinkedHashMap();
    public final Map cachedIntFlows = new LinkedHashMap();
    public final Map cachedStringFlows = new LinkedHashMap();
    public final ContentResolver contentResolver;
    public final CoroutineScope coroutineScope;
    public final Handler mainHandler;
    public final UserFetcher userFetcher;

    public ContentFetcher(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Context context, UserFetcher userFetcher, Handler handler) {
        this.coroutineScope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.userFetcher = userFetcher;
        this.mainHandler = handler;
        this.contentResolver = context.getContentResolver();
    }

    public final StateFlow getStringSecureSettingFlow(final String str, final String str2) {
        final Uri uriFor = Settings.Secure.getUriFor(str);
        return (StateFlow) this.cachedStringFlows.computeIfAbsent(uriFor, new Function() { // from class: com.google.android.systemui.columbus.fetchers.ContentFetcher$getStringSecureSettingFlow$1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                ContentFetcher contentFetcher = ContentFetcher.this;
                String str3 = str;
                Uri uri = uriFor;
                String str4 = str2;
                return FlowKt.stateIn(FlowKt.flowOn(FlowKt.mapLatest(new ContentFetcher$createStringSecureSettingFlow$1(contentFetcher, str3, str4, null), FlowKt.transformLatest(contentFetcher.userFetcher.currentUserHandle, new ContentFetcher$getSettingChangesForCurrentUser$$inlined$flatMapLatest$1(null, contentFetcher, uri))), contentFetcher.bgDispatcher), ContentFetcher.this.coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), str2);
            }
        });
    }
}
