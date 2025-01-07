package com.android.app.viewcapture;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.LauncherApps;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.window.IDumpCallback;
import com.android.app.viewcapture.ViewCapture;
import com.android.app.viewcapture.ViewCapture.WindowListener.AnonymousClass1;
import com.android.app.viewcapture.data.ExportedData;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;
import kotlin.io.CloseableKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SettingsAwareViewCapture extends ViewCapture {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Context context;
    public final SettingsAwareViewCapture$mDumpCallback$1 mDumpCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.app.viewcapture.SettingsAwareViewCapture$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ SettingsAwareViewCapture this$0;

        public /* synthetic */ AnonymousClass1(SettingsAwareViewCapture settingsAwareViewCapture, int i) {
            this.$r8$classId = i;
            this.this$0 = settingsAwareViewCapture;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ContentResolver contentResolver = this.this$0.context.getContentResolver();
                    Uri uriFor = Settings.Global.getUriFor("view_capture_enabled");
                    Handler handler = new Handler();
                    final SettingsAwareViewCapture settingsAwareViewCapture = this.this$0;
                    contentResolver.registerContentObserver(uriFor, false, new ContentObserver(handler) { // from class: com.android.app.viewcapture.SettingsAwareViewCapture.1.1
                        @Override // android.database.ContentObserver
                        public final void onChange(boolean z) {
                            SettingsAwareViewCapture settingsAwareViewCapture2 = SettingsAwareViewCapture.this;
                            int i = SettingsAwareViewCapture.$r8$clinit;
                            settingsAwareViewCapture2.getClass();
                            settingsAwareViewCapture2.mBgExecutor.execute(new AnonymousClass1(settingsAwareViewCapture2, 1));
                        }
                    });
                    break;
                default:
                    final boolean z = Settings.Global.getInt(this.this$0.context.getContentResolver(), "view_capture_enabled", 0) != 0;
                    LooperExecutor looperExecutor = ViewCapture.MAIN_EXECUTOR;
                    final SettingsAwareViewCapture settingsAwareViewCapture2 = this.this$0;
                    looperExecutor.execute(new Runnable() { // from class: com.android.app.viewcapture.SettingsAwareViewCapture$enableOrDisableWindowListeners$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            SettingsAwareViewCapture settingsAwareViewCapture3 = SettingsAwareViewCapture.this;
                            settingsAwareViewCapture3.mIsEnabled = z;
                            final int i = 0;
                            ((ArrayList) settingsAwareViewCapture3.mListeners).forEach(new Consumer() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda1
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    ViewCapture.WindowListener windowListener = (ViewCapture.WindowListener) obj;
                                    switch (i) {
                                        case 0:
                                            windowListener.mIsActive = false;
                                            View view = windowListener.mRoot;
                                            if (view != null) {
                                                view.getViewTreeObserver().removeOnDrawListener(windowListener);
                                                break;
                                            }
                                            break;
                                        default:
                                            View view2 = windowListener.mRoot;
                                            if (view2 != null) {
                                                windowListener.mIsActive = true;
                                                if (!view2.isAttachedToWindow()) {
                                                    windowListener.mRoot.addOnAttachStateChangeListener(windowListener.new AnonymousClass1());
                                                    break;
                                                } else {
                                                    View view3 = windowListener.mRoot;
                                                    if (view3 != null) {
                                                        view3.getViewTreeObserver().removeOnDrawListener(windowListener);
                                                        windowListener.mRoot.getViewTreeObserver().addOnDrawListener(windowListener);
                                                        break;
                                                    }
                                                }
                                            }
                                            break;
                                    }
                                }
                            });
                            if (settingsAwareViewCapture3.mIsEnabled) {
                                final int i2 = 1;
                                ((ArrayList) settingsAwareViewCapture3.mListeners).forEach(new Consumer() { // from class: com.android.app.viewcapture.ViewCapture$$ExternalSyntheticLambda1
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj) {
                                        ViewCapture.WindowListener windowListener = (ViewCapture.WindowListener) obj;
                                        switch (i2) {
                                            case 0:
                                                windowListener.mIsActive = false;
                                                View view = windowListener.mRoot;
                                                if (view != null) {
                                                    view.getViewTreeObserver().removeOnDrawListener(windowListener);
                                                    break;
                                                }
                                                break;
                                            default:
                                                View view2 = windowListener.mRoot;
                                                if (view2 != null) {
                                                    windowListener.mIsActive = true;
                                                    if (!view2.isAttachedToWindow()) {
                                                        windowListener.mRoot.addOnAttachStateChangeListener(windowListener.new AnonymousClass1());
                                                        break;
                                                    } else {
                                                        View view3 = windowListener.mRoot;
                                                        if (view3 != null) {
                                                            view3.getViewTreeObserver().removeOnDrawListener(windowListener);
                                                            windowListener.mRoot.getViewTreeObserver().addOnDrawListener(windowListener);
                                                            break;
                                                        }
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                });
                            }
                        }
                    });
                    LauncherApps launcherApps = (LauncherApps) this.this$0.context.getSystemService(LauncherApps.class);
                    if (!z) {
                        if (launcherApps != null) {
                            launcherApps.unRegisterDumpCallback(this.this$0.mDumpCallback);
                            break;
                        }
                    } else if (launcherApps != null) {
                        launcherApps.registerDumpCallback(this.this$0.mDumpCallback);
                        break;
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.app.viewcapture.SettingsAwareViewCapture$mDumpCallback$1] */
    public SettingsAwareViewCapture(Context context, LooperExecutor looperExecutor) {
        super(2000, 300, looperExecutor);
        this.context = context;
        this.mDumpCallback = new IDumpCallback.Stub() { // from class: com.android.app.viewcapture.SettingsAwareViewCapture$mDumpCallback$1
            public final void onDump(ParcelFileDescriptor parcelFileDescriptor) {
                try {
                    ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptor);
                    SettingsAwareViewCapture settingsAwareViewCapture = SettingsAwareViewCapture.this;
                    try {
                        Context context2 = settingsAwareViewCapture.context;
                        if (settingsAwareViewCapture.mIsEnabled) {
                            DataOutputStream dataOutputStream = new DataOutputStream(autoCloseOutputStream);
                            ExportedData exportedData = settingsAwareViewCapture.getExportedData(context2);
                            dataOutputStream.writeInt(exportedData.getSerializedSize(null));
                            exportedData.writeTo(dataOutputStream);
                        }
                        CloseableKt.closeFinally(autoCloseOutputStream, null);
                    } finally {
                    }
                } catch (Exception e) {
                    Log.e("SettingsAwareViewCapture", "failed to dump data to wm trace", e);
                }
            }
        };
        this.mBgExecutor.execute(new AnonymousClass1(this, 1));
        this.mBgExecutor.execute(new AnonymousClass1(this, 0));
    }
}
