package com.android.systemui.temporarydisplay;

import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.util.wakelock.WakeLock;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TemporaryViewDisplayController implements CoreStartable, Dumpable {
    public final AccessibilityManager accessibilityManager;
    public final List activeViews;
    public final WindowManager.LayoutParams commonWindowLayoutParams;
    public final ConfigurationController configurationController;
    public final Context context;
    public final TemporaryViewDisplayController$displayScaleListener$1 displayScaleListener;
    public final DumpManager dumpManager;
    public final Set listeners;
    public final TemporaryViewLogger logger;
    public final DelayableExecutor mainExecutor;
    public final PowerManager powerManager;
    public final SystemClock systemClock;
    public final TemporaryViewUiEventLogger tempViewUiEventLogger;
    public final int viewLayoutRes;
    public final WakeLock.Builder wakeLockBuilder;
    public final ViewCaptureAwareWindowManager windowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DisplayInfo {
        public ExecutorImpl.ExecutionToken cancelViewTimeout;
        public TemporaryViewInfo info;
        public long timeExpirationMillis;
        public ViewGroup view;
        public WakeLock wakeLock;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onInfoPermanentlyRemoved(String str, String str2);
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.temporarydisplay.TemporaryViewDisplayController$displayScaleListener$1] */
    public TemporaryViewDisplayController(Context context, TemporaryViewLogger temporaryViewLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, DelayableExecutor delayableExecutor, AccessibilityManager accessibilityManager, ConfigurationController configurationController, DumpManager dumpManager, PowerManager powerManager, int i, WakeLock.Builder builder, SystemClock systemClock, TemporaryViewUiEventLogger temporaryViewUiEventLogger) {
        this.context = context;
        this.logger = temporaryViewLogger;
        this.windowManager = viewCaptureAwareWindowManager;
        this.mainExecutor = delayableExecutor;
        this.accessibilityManager = accessibilityManager;
        this.configurationController = configurationController;
        this.dumpManager = dumpManager;
        this.powerManager = powerManager;
        this.viewLayoutRes = i;
        this.wakeLockBuilder = builder;
        this.systemClock = systemClock;
        this.tempViewUiEventLogger = temporaryViewUiEventLogger;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.type = 2010;
        layoutParams.flags = 40;
        layoutParams.format = -3;
        layoutParams.setTrustedOverlay();
        this.commonWindowLayoutParams = layoutParams;
        this.activeViews = new ArrayList();
        this.listeners = new LinkedHashSet();
        this.displayScaleListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.temporarydisplay.TemporaryViewDisplayController$displayScaleListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                TemporaryViewDisplayController.access$reinflateView(TemporaryViewDisplayController.this);
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                TemporaryViewDisplayController.access$reinflateView(TemporaryViewDisplayController.this);
            }
        };
    }

    public static final void access$reinflateView(TemporaryViewDisplayController temporaryViewDisplayController) {
        synchronized (temporaryViewDisplayController) {
            DisplayInfo displayInfo = (DisplayInfo) CollectionsKt.getOrNull(0, temporaryViewDisplayController.activeViews);
            if (displayInfo == null) {
                return;
            }
            ViewGroup viewGroup = displayInfo.view;
            if (viewGroup == null) {
                throw new IllegalStateException("First item in activeViews list must have a valid view");
            }
            temporaryViewDisplayController.logger.logViewRemovedFromWindowManager(displayInfo.info, viewGroup, true);
            temporaryViewDisplayController.windowManager.removeView(viewGroup);
            temporaryViewDisplayController.inflateAndUpdateView(displayInfo);
        }
    }

    public abstract void animateViewIn$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup);

    public abstract void animateViewOut$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup, String str, TemporaryViewDisplayController$removeViewFromWindow$1 temporaryViewDisplayController$removeViewFromWindow$1);

    public final synchronized void displayView(TemporaryViewInfo temporaryViewInfo) {
        int recommendedTimeoutMillis = this.accessibilityManager.getRecommendedTimeoutMillis(temporaryViewInfo.getTimeoutMs(), 7);
        ((SystemClockImpl) this.systemClock).getClass();
        long j = recommendedTimeoutMillis;
        long currentTimeMillis = System.currentTimeMillis() + j;
        int i = 0;
        DisplayInfo displayInfo = (DisplayInfo) CollectionsKt.getOrNull(0, this.activeViews);
        if (displayInfo != null && Intrinsics.areEqual(displayInfo.info.getId(), temporaryViewInfo.getId())) {
            ViewGroup viewGroup = displayInfo.view;
            if (viewGroup == null) {
                throw new IllegalStateException("First item in activeViews list must have a valid view");
            }
            this.logger.logViewUpdate(temporaryViewInfo);
            displayInfo.info = temporaryViewInfo;
            displayInfo.timeExpirationMillis = currentTimeMillis;
            ExecutorImpl.ExecutionToken executeDelayed = this.mainExecutor.executeDelayed(new TemporaryViewDisplayController$updateTimeout$cancelViewTimeout$1(this, displayInfo), j);
            ExecutorImpl.ExecutionToken executionToken = displayInfo.cancelViewTimeout;
            if (executionToken != null) {
                executionToken.run();
            }
            displayInfo.cancelViewTimeout = executeDelayed;
            updateView(temporaryViewInfo, viewGroup);
            return;
        }
        DisplayInfo displayInfo2 = new DisplayInfo();
        displayInfo2.view = null;
        displayInfo2.info = temporaryViewInfo;
        displayInfo2.timeExpirationMillis = currentTimeMillis;
        displayInfo2.wakeLock = null;
        displayInfo2.cancelViewTimeout = null;
        if (displayInfo == null) {
            ((ConfigurationControllerImpl) this.configurationController).addCallback(this.displayScaleListener);
            this.activeViews.add(displayInfo2);
            showNewView(displayInfo2, recommendedTimeoutMillis);
            return;
        }
        if (displayInfo.info.getPriority().compareTo(temporaryViewInfo.getPriority()) <= 0) {
            hideView(displayInfo);
            removeFromActivesIfNeeded(displayInfo2.info.getId());
            this.activeViews.add(0, displayInfo2);
            showNewView(displayInfo2, recommendedTimeoutMillis);
            return;
        }
        this.logger.logViewAdditionDelayed(temporaryViewInfo);
        removeFromActivesIfNeeded(temporaryViewInfo.getId());
        while (i < ((ArrayList) this.activeViews).size() && ((DisplayInfo) ((ArrayList) this.activeViews).get(i)).info.getPriority().compareTo(temporaryViewInfo.getPriority()) > 0) {
            i++;
        }
        this.activeViews.add(i, displayInfo2);
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    public final synchronized void dump(PrintWriter printWriter, String[] strArr) {
        try {
            ((SystemClockImpl) this.systemClock).getClass();
            printWriter.println("Current time millis: " + System.currentTimeMillis());
            printWriter.println("Active views size: " + ((ArrayList) this.activeViews).size());
            int i = 0;
            for (Object obj : this.activeViews) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                    throw null;
                }
                DisplayInfo displayInfo = (DisplayInfo) obj;
                printWriter.println("View[" + i + "]:");
                printWriter.println("  info=" + displayInfo.info);
                printWriter.println("  hasView=" + (displayInfo.view != null));
                printWriter.println("  timeExpiration=" + displayInfo.timeExpirationMillis);
                i = i2;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public abstract void getTouchableRegion(Rect rect, View view);

    public abstract WindowManager.LayoutParams getWindowLayoutParams$frameworks__base__packages__SystemUI__android_common__SystemUI_core();

    public final void hideView(DisplayInfo displayInfo) {
        TemporaryViewInfo temporaryViewInfo = displayInfo.info;
        TemporaryViewLogger temporaryViewLogger = this.logger;
        temporaryViewLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        TemporaryViewLogger$logViewHidden$2 temporaryViewLogger$logViewHidden$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.TemporaryViewLogger$logViewHidden$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("View hidden in favor of newer view. Hidden view id=", str1, " window=", str2, " priority=");
                m.append(str3);
                return m.toString();
            }
        };
        String str = temporaryViewLogger.tag;
        LogBuffer logBuffer = temporaryViewLogger.buffer;
        LogMessage obtain = logBuffer.obtain(str, logLevel, temporaryViewLogger$logViewHidden$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = temporaryViewInfo.getId();
        logMessageImpl.str2 = temporaryViewInfo.getWindowTitle();
        logMessageImpl.str3 = temporaryViewInfo.getPriority().name();
        logBuffer.commit(obtain);
        ViewGroup viewGroup = displayInfo.view;
        if (viewGroup == null) {
            temporaryViewLogger.logViewRemovalIgnored(displayInfo.info.getId(), "View is null");
        } else {
            displayInfo.view = null;
            animateViewOut$frameworks__base__packages__SystemUI__android_common__SystemUI_core(viewGroup, null, new TemporaryViewDisplayController$removeViewFromWindow$1(this, displayInfo, viewGroup));
        }
    }

    public final void inflateAndUpdateView(DisplayInfo displayInfo) {
        TemporaryViewInfo temporaryViewInfo = displayInfo.info;
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this.context).inflate(this.viewLayoutRes, (ViewGroup) null);
        displayInfo.view = viewGroup;
        new TouchableRegionViewController(viewGroup, new TemporaryViewDisplayController$inflateAndUpdateView$newViewController$1(2, this, TemporaryViewDisplayController.class, "getTouchableRegion", "getTouchableRegion(Landroid/view/View;Landroid/graphics/Rect;)V", 0)).init$9();
        updateView(temporaryViewInfo, viewGroup);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindowLayoutParams$frameworks__base__packages__SystemUI__android_common__SystemUI_core());
        layoutParams.setTitle(temporaryViewInfo.getWindowTitle());
        viewGroup.setKeepScreenOn(true);
        TemporaryViewInfo temporaryViewInfo2 = displayInfo.info;
        TemporaryViewLogger temporaryViewLogger = this.logger;
        temporaryViewLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        TemporaryViewLogger$logViewAddedToWindowManager$2 temporaryViewLogger$logViewAddedToWindowManager$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.TemporaryViewLogger$logViewAddedToWindowManager$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                String hexString = Integer.toHexString(logMessage.getInt1());
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Adding view to window manager. id=", str1, " window=", str2, " view=");
                m.append(str3);
                m.append("(id=");
                m.append(hexString);
                m.append(")");
                return m.toString();
            }
        };
        String str = temporaryViewLogger.tag;
        LogBuffer logBuffer = temporaryViewLogger.buffer;
        LogMessage obtain = logBuffer.obtain(str, logLevel, temporaryViewLogger$logViewAddedToWindowManager$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = temporaryViewInfo2.getId();
        logMessageImpl.str2 = temporaryViewInfo2.getWindowTitle();
        logMessageImpl.str3 = viewGroup.getClass().getName();
        logMessageImpl.int1 = System.identityHashCode(viewGroup);
        logBuffer.commit(obtain);
        this.windowManager.addView(viewGroup, layoutParams);
        animateViewIn$frameworks__base__packages__SystemUI__android_common__SystemUI_core(viewGroup);
    }

    public final synchronized void removeFromActivesIfNeeded(String str) {
        Object obj;
        try {
            Iterator it = this.activeViews.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((DisplayInfo) obj).info.getId(), str)) {
                        break;
                    }
                }
            }
            DisplayInfo displayInfo = (DisplayInfo) obj;
            if (displayInfo != null) {
                ExecutorImpl.ExecutionToken executionToken = displayInfo.cancelViewTimeout;
                if (executionToken != null) {
                    executionToken.run();
                }
                this.activeViews.remove(displayInfo);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void removeTimedOutViews() {
        try {
            List list = this.activeViews;
            ArrayList<DisplayInfo> arrayList = new ArrayList();
            for (Object obj : list) {
                long j = ((DisplayInfo) obj).timeExpirationMillis;
                ((SystemClockImpl) this.systemClock).getClass();
                if (j < System.currentTimeMillis() + 1000) {
                    arrayList.add(obj);
                }
            }
            for (DisplayInfo displayInfo : arrayList) {
                this.activeViews.remove(displayInfo);
                this.logger.logViewExpiration(displayInfo.info);
                Iterator it = this.listeners.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onInfoPermanentlyRemoved(displayInfo.info.getId(), "TIMEOUT_EXPIRED_BEFORE_REDISPLAY");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void removeView(String str, String str2) {
        Object obj;
        try {
            TemporaryViewLogger temporaryViewLogger = this.logger;
            temporaryViewLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            TemporaryViewLogger$logViewRemoval$2 temporaryViewLogger$logViewRemoval$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.TemporaryViewLogger$logViewRemoval$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    LogMessage logMessage = (LogMessage) obj2;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("View with id=", logMessage.getStr2(), " is removed due to: ", logMessage.getStr1());
                }
            };
            LogBuffer logBuffer = temporaryViewLogger.buffer;
            LogMessage obtain = logBuffer.obtain(temporaryViewLogger.tag, logLevel, temporaryViewLogger$logViewRemoval$2, null);
            ((LogMessageImpl) obtain).str1 = str2;
            ((LogMessageImpl) obtain).str2 = str;
            logBuffer.commit(obtain);
            Iterator it = this.activeViews.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it.next();
                    if (Intrinsics.areEqual(((DisplayInfo) obj).info.getId(), str)) {
                        break;
                    }
                }
            }
            DisplayInfo displayInfo = (DisplayInfo) obj;
            if (displayInfo == null) {
                this.logger.logViewRemovalIgnored(str, "View not found in list");
                return;
            }
            DisplayInfo displayInfo2 = (DisplayInfo) ((ArrayList) this.activeViews).get(0);
            this.activeViews.remove(displayInfo);
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((Listener) it2.next()).onInfoPermanentlyRemoved(str, str2);
            }
            ExecutorImpl.ExecutionToken executionToken = displayInfo.cancelViewTimeout;
            if (executionToken != null) {
                executionToken.run();
            }
            if (displayInfo.view == null) {
                this.logger.logViewRemovalIgnored(str, "No view to remove");
                return;
            }
            if (!Intrinsics.areEqual(displayInfo2.info.getId(), str)) {
                this.logger.logViewRemovalIgnored(str, "View isn't the currently displayed view");
                return;
            }
            ViewGroup viewGroup = displayInfo.view;
            if (viewGroup == null) {
                this.logger.logViewRemovalIgnored(displayInfo.info.getId(), "View is null");
            } else {
                displayInfo.view = null;
                animateViewOut$frameworks__base__packages__SystemUI__android_common__SystemUI_core(viewGroup, str2, new TemporaryViewDisplayController$removeViewFromWindow$1(this, displayInfo, viewGroup));
            }
            removeTimedOutViews();
            DisplayInfo displayInfo3 = (DisplayInfo) CollectionsKt.getOrNull(0, this.activeViews);
            if (displayInfo3 != null) {
                long j = displayInfo3.timeExpirationMillis;
                ((SystemClockImpl) this.systemClock).getClass();
                showNewView(displayInfo3, (int) (j - System.currentTimeMillis()));
            } else {
                ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.displayScaleListener);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void showNewView(DisplayInfo displayInfo, int i) {
        WakeLock build;
        TemporaryViewInfo temporaryViewInfo = displayInfo.info;
        TemporaryViewLogger temporaryViewLogger = this.logger;
        temporaryViewLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        TemporaryViewLogger$logViewAddition$2 temporaryViewLogger$logViewAddition$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.TemporaryViewLogger$logViewAddition$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("View added. id=", str1, " window=", str2, " priority=");
                m.append(str3);
                return m.toString();
            }
        };
        String str = temporaryViewLogger.tag;
        LogBuffer logBuffer = temporaryViewLogger.buffer;
        LogMessage obtain = logBuffer.obtain(str, logLevel, temporaryViewLogger$logViewAddition$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = temporaryViewInfo.getId();
        logMessageImpl.str2 = temporaryViewInfo.getWindowTitle();
        logMessageImpl.str3 = temporaryViewInfo.getPriority().name();
        logBuffer.commit(obtain);
        this.tempViewUiEventLogger.logger.log(TemporaryViewUiEvent.TEMPORARY_VIEW_ADDED, displayInfo.info.getInstanceId());
        boolean isScreenOn = this.powerManager.isScreenOn();
        WakeLock.Builder builder = this.wakeLockBuilder;
        if (isScreenOn) {
            builder.mTag = displayInfo.info.getWindowTitle();
            builder.mLevelsAndFlags = 10;
            build = builder.build();
        } else {
            builder.mTag = displayInfo.info.getWindowTitle();
            builder.mLevelsAndFlags = 268435482;
            build = builder.build();
        }
        displayInfo.wakeLock = build;
        build.acquire(displayInfo.info.getWakeReason());
        ExecutorImpl.ExecutionToken executeDelayed = this.mainExecutor.executeDelayed(new TemporaryViewDisplayController$updateTimeout$cancelViewTimeout$1(this, displayInfo), i);
        ExecutorImpl.ExecutionToken executionToken = displayInfo.cancelViewTimeout;
        if (executionToken != null) {
            executionToken.run();
        }
        displayInfo.cancelViewTimeout = executeDelayed;
        inflateAndUpdateView(displayInfo);
    }

    @Override // com.android.systemui.CoreStartable
    public void start() {
        this.dumpManager.registerNormalDumpable(this);
    }

    public abstract void updateView(TemporaryViewInfo temporaryViewInfo, ViewGroup viewGroup);

    public static /* synthetic */ void getActiveViews$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
