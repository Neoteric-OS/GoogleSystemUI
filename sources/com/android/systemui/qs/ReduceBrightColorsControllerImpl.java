package com.android.systemui.qs;

import android.content.Context;
import android.database.ContentObserver;
import android.hardware.display.ColorDisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerExecutor;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.settings.SecureSettings;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReduceBrightColorsControllerImpl implements ReduceBrightColorsController {
    public final AnonymousClass1 mContentObserver;
    public UserTracker.Callback mCurrentUserTrackerCallback;
    public final ArrayList mListeners = new ArrayList();
    public final ColorDisplayManager mManager;
    public final SecureSettings mSecureSettings;
    public final UserTracker mUserTracker;

    /* JADX WARN: Type inference failed for: r4v1, types: [com.android.systemui.qs.ReduceBrightColorsControllerImpl$1] */
    public ReduceBrightColorsControllerImpl(UserTracker userTracker, Handler handler, ColorDisplayManager colorDisplayManager, SecureSettings secureSettings) {
        this.mManager = colorDisplayManager;
        this.mUserTracker = userTracker;
        this.mSecureSettings = secureSettings;
        this.mContentObserver = new ContentObserver(handler) { // from class: com.android.systemui.qs.ReduceBrightColorsControllerImpl.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                super.onChange(z, uri);
                String lastPathSegment = uri == null ? null : uri.getLastPathSegment();
                synchronized (ReduceBrightColorsControllerImpl.this.mListeners) {
                    if (lastPathSegment != null) {
                        try {
                            if (ReduceBrightColorsControllerImpl.this.mListeners.size() != 0 && lastPathSegment.equals("reduce_bright_colors_activated")) {
                                ReduceBrightColorsControllerImpl reduceBrightColorsControllerImpl = ReduceBrightColorsControllerImpl.this;
                                boolean isReduceBrightColorsActivated = reduceBrightColorsControllerImpl.mManager.isReduceBrightColorsActivated();
                                Iterator it = new ArrayList(reduceBrightColorsControllerImpl.mListeners).iterator();
                                while (it.hasNext()) {
                                    ((ReduceBrightColorsController.Listener) it.next()).onActivated(isReduceBrightColorsActivated);
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
        };
        UserTracker.Callback callback = new UserTracker.Callback() { // from class: com.android.systemui.qs.ReduceBrightColorsControllerImpl.2
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context) {
                synchronized (ReduceBrightColorsControllerImpl.this.mListeners) {
                    try {
                        if (ReduceBrightColorsControllerImpl.this.mListeners.size() > 0) {
                            ReduceBrightColorsControllerImpl reduceBrightColorsControllerImpl = ReduceBrightColorsControllerImpl.this;
                            reduceBrightColorsControllerImpl.mSecureSettings.unregisterContentObserverAsync(reduceBrightColorsControllerImpl.mContentObserver);
                            ReduceBrightColorsControllerImpl reduceBrightColorsControllerImpl2 = ReduceBrightColorsControllerImpl.this;
                            reduceBrightColorsControllerImpl2.mSecureSettings.registerContentObserverForUserAsync(reduceBrightColorsControllerImpl2.mContentObserver, i);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.mCurrentUserTrackerCallback = callback;
        ((UserTrackerImpl) userTracker).addCallback(callback, new HandlerExecutor(handler));
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        ReduceBrightColorsController.Listener listener = (ReduceBrightColorsController.Listener) obj;
        synchronized (this.mListeners) {
            try {
                if (!this.mListeners.contains(listener)) {
                    this.mListeners.add(listener);
                    if (this.mListeners.size() == 1) {
                        this.mSecureSettings.registerContentObserverForUserAsync(this.mContentObserver, ((UserTrackerImpl) this.mUserTracker).getUserId());
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        ReduceBrightColorsController.Listener listener = (ReduceBrightColorsController.Listener) obj;
        synchronized (this.mListeners) {
            try {
                if (this.mListeners.remove(listener) && this.mListeners.size() == 0) {
                    this.mSecureSettings.unregisterContentObserverAsync(this.mContentObserver);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
