package com.google.android.systemui.dagger;

import android.content.Context;
import android.content.res.Resources;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDisabledDialogDelegate;
import com.android.systemui.recordissue.IssueRecordingState;
import com.android.systemui.recordissue.RecordIssueDialogDelegate;
import com.android.systemui.recordissue.TraceurMessageSender;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50 {
    public final /* synthetic */ DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider this$0;

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50(DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider) {
        this.this$0 = switchingProvider;
    }

    public final RecordIssueDialogDelegate create(Runnable runnable) {
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchingProvider = this.this$0;
        SystemUIDialog.Factory factory = (SystemUIDialog.Factory) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl).factoryProvider6.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) switchingProvider.wMComponentImpl;
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        FeatureFlagsClassic featureFlagsClassic = (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get();
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get();
        Executor executor2 = (Executor) switchingProvider.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
        Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenCaptureDevicePolicyResolverProvider);
        MediaProjectionMetricsLogger mediaProjectionMetricsLogger = (MediaProjectionMetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionMetricsLoggerProvider.get();
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new RecordIssueDialogDelegate(factory, userTracker, featureFlagsClassic, executor, executor2, lazy, mediaProjectionMetricsLogger, new ScreenCaptureDisabledDialogDelegate(context, resources), (IssueRecordingState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.issueRecordingStateProvider.get(), (TraceurMessageSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.traceurMessageSenderProvider.get(), runnable);
    }
}
