package com.android.systemui.media.controls.ui.controller;

import android.content.Context;
import android.content.res.Configuration;
import android.util.IndentingPrintWriter;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.MediaContainerView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda3;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.animation.UniqueObjectHostView;
import java.io.PrintWriter;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardMediaController implements Dumpable {
    public final KeyguardBypassController bypassController;
    public final Context context;
    public int lastUsedStatusBarState = -1;
    public final KeyguardMediaControllerLogger logger;
    public final MediaHost mediaHost;
    public MediaContainerView singlePaneContainer;
    public ViewGroup splitShadeContainer;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final SysuiStatusBarStateController statusBarStateController;
    public boolean useSplitShade;
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda3 visibilityChangedListener;
    public boolean visible;

    public KeyguardMediaController(MediaHost mediaHost, KeyguardBypassController keyguardBypassController, SysuiStatusBarStateController sysuiStatusBarStateController, Context context, ConfigurationController configurationController, SplitShadeStateControllerImpl splitShadeStateControllerImpl, KeyguardMediaControllerLogger keyguardMediaControllerLogger, DumpManager dumpManager) {
        this.mediaHost = mediaHost;
        this.bypassController = keyguardBypassController;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.context = context;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.logger = keyguardMediaControllerLogger;
        dumpManager.registerDumpable(this);
        ((StatusBarStateControllerImpl) sysuiStatusBarStateController).addCallback(new StatusBarStateController.StateListener() { // from class: com.android.systemui.media.controls.ui.controller.KeyguardMediaController.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                KeyguardMediaController.this.refreshMediaPosition("StatusBarState.onDozingChanged");
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                KeyguardMediaController.this.refreshMediaPosition("StatusBarState.onStateChanged");
            }
        });
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.media.controls.ui.controller.KeyguardMediaController.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                KeyguardMediaController keyguardMediaController = KeyguardMediaController.this;
                boolean shouldUseSplitNotificationShade = keyguardMediaController.splitShadeStateController.shouldUseSplitNotificationShade(keyguardMediaController.context.getResources());
                if (keyguardMediaController.useSplitShade == shouldUseSplitNotificationShade) {
                    return;
                }
                keyguardMediaController.useSplitShade = shouldUseSplitNotificationShade;
                keyguardMediaController.reattachHostView();
                keyguardMediaController.refreshMediaPosition("useSplitShade changed");
            }
        });
        mediaHost.setExpansion(1.0f);
        mediaHost.setShowsOnlyActiveMedia(true);
        mediaHost.setFalsingProtectionNeeded(true);
        mediaHost.init(2);
        boolean shouldUseSplitNotificationShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(context.getResources());
        if (this.useSplitShade == shouldUseSplitNotificationShade) {
            return;
        }
        this.useSplitShade = shouldUseSplitNotificationShade;
        reattachHostView();
        refreshMediaPosition("useSplitShade changed");
    }

    public final void attachSinglePaneContainer(MediaContainerView mediaContainerView) {
        boolean z = this.singlePaneContainer == null;
        this.singlePaneContainer = mediaContainerView;
        MediaHost mediaHost = this.mediaHost;
        if (z) {
            mediaHost.visibleChangedListeners.add(new KeyguardMediaController$attachSinglePaneContainer$1(1, this, KeyguardMediaController.class, "onMediaHostVisibilityChanged", "onMediaHostVisibilityChanged(Z)V", 0));
        }
        reattachHostView();
        onMediaHostVisibilityChanged(mediaHost.state.visible);
        MediaContainerView mediaContainerView2 = this.singlePaneContainer;
        if (mediaContainerView2 == null) {
            return;
        }
        mediaContainerView2.setImportantForAccessibility(2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        asIndenting.println("KeyguardMediaController");
        asIndenting.increaseIndent();
        try {
            DumpUtilsKt.println(asIndenting, "Self", this);
            DumpUtilsKt.println(asIndenting, "visible", Boolean.valueOf(this.visible));
            DumpUtilsKt.println(asIndenting, "useSplitShade", Boolean.valueOf(this.useSplitShade));
            DumpUtilsKt.println(asIndenting, "bypassController.bypassEnabled", Boolean.valueOf(this.bypassController.getBypassEnabled()));
            DumpUtilsKt.println(asIndenting, "isDozeWakeUpAnimationWaiting", Boolean.FALSE);
            DumpUtilsKt.println(asIndenting, "singlePaneContainer", this.singlePaneContainer);
            DumpUtilsKt.println(asIndenting, "splitShadeContainer", this.splitShadeContainer);
            int i = this.lastUsedStatusBarState;
            if (i != -1) {
                DumpUtilsKt.println(asIndenting, "lastUsedStatusBarState", StatusBarState.toString(i));
            }
            DumpUtilsKt.println(asIndenting, "statusBarStateController.state", StatusBarState.toString(((StatusBarStateControllerImpl) this.statusBarStateController).mState));
            asIndenting.decreaseIndent();
        } catch (Throwable th) {
            asIndenting.decreaseIndent();
            throw th;
        }
    }

    public final void onMediaHostVisibilityChanged(boolean z) {
        refreshMediaPosition("onMediaHostVisibilityChanged");
        if (!z || this.useSplitShade) {
            return;
        }
        UniqueObjectHostView uniqueObjectHostView = this.mediaHost.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        ViewGroup.LayoutParams layoutParams = uniqueObjectHostView.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -1;
    }

    public final void reattachHostView() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        if (this.useSplitShade) {
            viewGroup2 = this.splitShadeContainer;
            viewGroup = this.singlePaneContainer;
        } else {
            viewGroup = this.splitShadeContainer;
            viewGroup2 = this.singlePaneContainer;
        }
        if (viewGroup != null && viewGroup.getChildCount() == 1) {
            viewGroup.removeAllViews();
        }
        if (viewGroup2 == null || viewGroup2.getChildCount() != 0) {
            return;
        }
        MediaHost mediaHost = this.mediaHost;
        UniqueObjectHostView uniqueObjectHostView = mediaHost.hostView;
        if (uniqueObjectHostView == null) {
            uniqueObjectHostView = null;
        }
        ViewParent parent = uniqueObjectHostView.getParent();
        if (parent != null) {
            ViewGroup viewGroup3 = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup3 != null) {
                UniqueObjectHostView uniqueObjectHostView2 = mediaHost.hostView;
                if (uniqueObjectHostView2 == null) {
                    uniqueObjectHostView2 = null;
                }
                viewGroup3.removeView(uniqueObjectHostView2);
            }
        }
        UniqueObjectHostView uniqueObjectHostView3 = mediaHost.hostView;
        viewGroup2.addView(uniqueObjectHostView3 != null ? uniqueObjectHostView3 : null);
    }

    public final void refreshMediaPosition(String str) {
        int i = ((StatusBarStateControllerImpl) this.statusBarStateController).mState;
        boolean z = i == 1;
        boolean z2 = this.mediaHost.state.visible;
        boolean bypassEnabled = this.bypassController.getBypassEnabled();
        int i2 = !bypassEnabled ? 1 : 0;
        boolean z3 = this.useSplitShade;
        boolean z4 = !z3 ? true : !r0.mIsDozing;
        boolean z5 = z2 && !bypassEnabled && z && z4;
        this.visible = z5;
        KeyguardMediaControllerLogger keyguardMediaControllerLogger = this.logger;
        LogLevel logLevel = LogLevel.DEBUG;
        KeyguardMediaControllerLogger$logRefreshMediaPosition$2 keyguardMediaControllerLogger$logRefreshMediaPosition$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.KeyguardMediaControllerLogger$logRefreshMediaPosition$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String statusBarState = StatusBarState.toString(logMessage.getInt1());
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                boolean bool3 = logMessage.getBool3();
                boolean bool4 = logMessage.getBool4();
                boolean z6 = logMessage.getInt2() == 1;
                String str2 = logMessage.getStr2();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("refreshMediaPosition(reason=", str1, ", currentState=", statusBarState, ", visible=");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool1, ", useSplitShade=", bool2, ", keyguardOrUserSwitcher=");
                BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(m, bool3, ", mediaHostVisible=", bool4, ", bypassNotEnabled=");
                m.append(z6);
                m.append(", shouldBeVisibleForSplitShade=");
                m.append(str2);
                m.append(")");
                return m.toString();
            }
        };
        LogBuffer logBuffer = keyguardMediaControllerLogger.logBuffer;
        LogMessage obtain = logBuffer.obtain("KeyguardMediaControllerLog", logLevel, keyguardMediaControllerLogger$logRefreshMediaPosition$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.bool1 = z5;
        logMessageImpl.bool2 = z3;
        logMessageImpl.int1 = i;
        logMessageImpl.bool3 = z;
        logMessageImpl.bool4 = z2;
        logMessageImpl.int2 = i2;
        logMessageImpl.str2 = String.valueOf(z4);
        logBuffer.commit(obtain);
        Object obj = this.useSplitShade ? this.splitShadeContainer : this.singlePaneContainer;
        KeyguardMediaControllerLogger$logActiveMediaContainer$2 keyguardMediaControllerLogger$logActiveMediaContainer$2 = new Function1() { // from class: com.android.systemui.media.controls.ui.controller.KeyguardMediaControllerLogger$logActiveMediaContainer$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return MotionLayout$$ExternalSyntheticOutline0.m("activeMediaContainerVisibility(reason=", logMessage.getStr1(), ", activeContainer=", logMessage.getStr2(), ")");
            }
        };
        LogMessage obtain2 = logBuffer.obtain("KeyguardMediaControllerLog", logLevel, keyguardMediaControllerLogger$logActiveMediaContainer$2, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.str1 = "before refreshMediaPosition";
        logMessageImpl2.str2 = String.valueOf(obj);
        logBuffer.commit(obtain2);
        if (!this.visible) {
            setVisibility(8, this.splitShadeContainer);
            setVisibility(8, this.singlePaneContainer);
        } else if (this.useSplitShade) {
            setVisibility(0, this.splitShadeContainer);
            setVisibility(8, this.singlePaneContainer);
        } else {
            setVisibility(0, this.singlePaneContainer);
            setVisibility(8, this.splitShadeContainer);
        }
        LogMessage obtain3 = logBuffer.obtain("KeyguardMediaControllerLog", logLevel, keyguardMediaControllerLogger$logActiveMediaContainer$2, null);
        LogMessageImpl logMessageImpl3 = (LogMessageImpl) obtain3;
        logMessageImpl3.str1 = "after refreshMediaPosition";
        logMessageImpl3.str2 = String.valueOf(obj);
        logBuffer.commit(obtain3);
        this.lastUsedStatusBarState = i;
    }

    public final void setVisibility(int i, ViewGroup viewGroup) {
        NotificationStackScrollLayoutController$$ExternalSyntheticLambda3 notificationStackScrollLayoutController$$ExternalSyntheticLambda3;
        if (viewGroup == null) {
            return;
        }
        boolean z = i == 0;
        if (!(viewGroup instanceof MediaContainerView)) {
            viewGroup.setVisibility(i);
            return;
        }
        MediaContainerView mediaContainerView = (MediaContainerView) viewGroup;
        int visibility = mediaContainerView.getVisibility();
        ExpandableViewState expandableViewState = mediaContainerView.mViewState;
        if (expandableViewState instanceof MediaContainerView.MediaContainerViewState) {
            ((MediaContainerView.MediaContainerViewState) expandableViewState).shouldBeVisible = z;
        }
        mediaContainerView.setVisibility(z ? 0 : 8);
        if (visibility == i || (notificationStackScrollLayoutController$$ExternalSyntheticLambda3 = this.visibilityChangedListener) == null) {
            return;
        }
        notificationStackScrollLayoutController$$ExternalSyntheticLambda3.invoke(Boolean.valueOf(z));
    }

    public static /* synthetic */ void getUseSplitShade$annotations() {
    }
}
