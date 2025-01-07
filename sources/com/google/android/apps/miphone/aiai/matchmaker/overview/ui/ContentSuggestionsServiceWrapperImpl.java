package com.google.android.apps.miphone.aiai.matchmaker.overview.ui;

import android.app.contentsuggestions.ClassificationsRequest;
import android.app.contentsuggestions.ContentClassification;
import android.app.contentsuggestions.ContentSelection;
import android.app.contentsuggestions.ContentSuggestionsManager;
import android.app.contentsuggestions.SelectionsRequest;
import android.content.Context;
import android.os.Bundle;
import androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceClient;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl;
import com.google.android.apps.miphone.aiai.matchmaker.overview.ui.utils.LogUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContentSuggestionsServiceWrapperImpl {
    static final int MAX_CONTENT_SELECTIONS_WAIT_DURATION_MS = 400;
    public final Executor asyncExecutor;
    public final ProfileInstallReceiver$$ExternalSyntheticLambda0 callbackExecutor;
    public final ContentSuggestionsManager contentSuggestionsManager;
    public final Executor loggingExecutor;
    public final Map pendingCallbacks;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BundleCallbackWrapper implements ContentSuggestionsServiceWrapper$BundleCallback {
        public final Object key;
        public final WeakReference parentRef;

        public BundleCallbackWrapper(ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback, ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl) {
            Object obj = new Object();
            this.key = obj;
            contentSuggestionsServiceWrapperImpl.pendingCallbacks.put(obj, contentSuggestionsServiceWrapper$BundleCallback);
            this.parentRef = new WeakReference(contentSuggestionsServiceWrapperImpl);
        }

        @Override // com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapper$BundleCallback
        public final void onResult(Bundle bundle) {
            ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback;
            ContentSuggestionsServiceWrapperImpl contentSuggestionsServiceWrapperImpl = (ContentSuggestionsServiceWrapperImpl) this.parentRef.get();
            if (contentSuggestionsServiceWrapperImpl == null || (contentSuggestionsServiceWrapper$BundleCallback = (ContentSuggestionsServiceWrapper$BundleCallback) contentSuggestionsServiceWrapperImpl.pendingCallbacks.remove(this.key)) == null) {
                return;
            }
            contentSuggestionsServiceWrapper$BundleCallback.onResult(bundle);
        }
    }

    public ContentSuggestionsServiceWrapperImpl(Context context, Executor executor, Executor executor2) {
        ProfileInstallReceiver$$ExternalSyntheticLambda0 profileInstallReceiver$$ExternalSyntheticLambda0 = new ProfileInstallReceiver$$ExternalSyntheticLambda0();
        this.asyncExecutor = executor;
        this.loggingExecutor = executor2;
        this.callbackExecutor = profileInstallReceiver$$ExternalSyntheticLambda0;
        this.pendingCallbacks = Collections.synchronizedMap(new WeakHashMap());
        new ConcurrentLinkedQueue();
        this.contentSuggestionsManager = (ContentSuggestionsManager) context.getSystemService(ContentSuggestionsManager.class);
    }

    public final void classifyContentSelections(Bundle bundle, ContentSuggestionsServiceClient.AnonymousClass1.C02701 c02701) {
        try {
            ClassificationsRequest build = new ClassificationsRequest.Builder(new ArrayList()).setExtras(bundle).build();
            final BundleCallbackWrapper bundleCallbackWrapper = new BundleCallbackWrapper(c02701, this);
            this.contentSuggestionsManager.classifyContentSelections(build, this.callbackExecutor, new ContentSuggestionsManager.ClassificationsCallback() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda0
                public final void onContentClassificationsAvailable(int i, List list) {
                    ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper.this.onResult(((ContentClassification) list.get(0)).getExtras());
                }
            });
        } catch (Throwable th) {
            LogUtils.e("Failed to classifyContentSelections", th);
        }
    }

    public final void suggestContentSelections(int i, Bundle bundle, ContentSuggestionsServiceWrapper$BundleCallback contentSuggestionsServiceWrapper$BundleCallback) {
        SelectionsRequest build = new SelectionsRequest.Builder(i).setExtras(bundle).build();
        try {
            final BundleCallbackWrapper bundleCallbackWrapper = new BundleCallbackWrapper(contentSuggestionsServiceWrapper$BundleCallback, this);
            this.contentSuggestionsManager.suggestContentSelections(build, this.callbackExecutor, new ContentSuggestionsManager.SelectionsCallback() { // from class: com.google.android.apps.miphone.aiai.matchmaker.overview.ui.ContentSuggestionsServiceWrapperImpl$$ExternalSyntheticLambda2
                public final void onContentSelectionsAvailable(int i2, List list) {
                    ContentSuggestionsServiceWrapperImpl.BundleCallbackWrapper.this.onResult(((ContentSelection) list.get(0)).getExtras());
                }
            });
        } catch (Throwable th) {
            LogUtils.e("Failed to suggestContentSelections", th);
        }
    }
}
