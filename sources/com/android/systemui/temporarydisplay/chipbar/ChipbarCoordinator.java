package com.android.systemui.temporarydisplay.chipbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.PowerManager;
import android.os.Process;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.widget.CachingIconView;
import com.android.settingslib.Utils;
import com.android.systemui.animation.ViewHierarchyAnimator;
import com.android.systemui.animation.ViewHierarchyAnimator$Companion$createListener$1;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Text;
import com.android.systemui.common.shared.model.TintedIcon;
import com.android.systemui.common.ui.binder.IconViewBinder;
import com.android.systemui.common.ui.binder.TextViewBinder;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController;
import com.android.systemui.temporarydisplay.TemporaryViewDisplayController$removeViewFromWindow$1;
import com.android.systemui.temporarydisplay.TemporaryViewInfo;
import com.android.systemui.temporarydisplay.TemporaryViewLogger;
import com.android.systemui.temporarydisplay.TemporaryViewUiEvent;
import com.android.systemui.temporarydisplay.TemporaryViewUiEventLogger;
import com.android.systemui.temporarydisplay.chipbar.ChipbarEndItem;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.view.ViewUtil;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChipbarCoordinator extends TemporaryViewDisplayController {
    public static final VibrationAttributes VIBRATION_ATTRIBUTES = VibrationAttributes.createForUsage(50);
    public final ChipbarAnimator chipbarAnimator;
    public final FalsingCollector falsingCollector;
    public final FalsingManager falsingManager;
    public LoadingDetails loadingDetails;
    public final SwipeChipbarAwayGestureHandler swipeChipbarAwayGestureHandler;
    public final VibratorHelper vibratorHelper;
    public final ViewUtil viewUtil;
    public final WindowManager.LayoutParams windowLayoutParams;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LoadingDetails {
        public final ObjectAnimator animator;
        public final View loadingView;

        public LoadingDetails(View view, ObjectAnimator objectAnimator) {
            this.loadingView = view;
            this.animator = objectAnimator;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LoadingDetails)) {
                return false;
            }
            LoadingDetails loadingDetails = (LoadingDetails) obj;
            return Intrinsics.areEqual(this.loadingView, loadingDetails.loadingView) && Intrinsics.areEqual(this.animator, loadingDetails.animator);
        }

        public final int hashCode() {
            return this.animator.hashCode() + (this.loadingView.hashCode() * 31);
        }

        public final String toString() {
            return "LoadingDetails(loadingView=" + this.loadingView + ", animator=" + this.animator + ")";
        }
    }

    public ChipbarCoordinator(Context context, ChipbarLogger chipbarLogger, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, DelayableExecutor delayableExecutor, AccessibilityManager accessibilityManager, ConfigurationController configurationController, DumpManager dumpManager, PowerManager powerManager, ChipbarAnimator chipbarAnimator, FalsingManager falsingManager, FalsingCollector falsingCollector, SwipeChipbarAwayGestureHandler swipeChipbarAwayGestureHandler, ViewUtil viewUtil, VibratorHelper vibratorHelper, WakeLock.Builder builder, SystemClock systemClock, TemporaryViewUiEventLogger temporaryViewUiEventLogger) {
        super(context, chipbarLogger, viewCaptureAwareWindowManager, delayableExecutor, accessibilityManager, configurationController, dumpManager, powerManager, R.layout.chipbar, builder, systemClock, temporaryViewUiEventLogger);
        this.chipbarAnimator = chipbarAnimator;
        this.falsingManager = falsingManager;
        this.falsingCollector = falsingCollector;
        this.swipeChipbarAwayGestureHandler = swipeChipbarAwayGestureHandler;
        this.viewUtil = viewUtil;
        this.vibratorHelper = vibratorHelper;
        WindowManager.LayoutParams layoutParams = this.commonWindowLayoutParams;
        layoutParams.gravity = 49;
        this.windowLayoutParams = layoutParams;
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void animateViewIn$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup) {
        int i = 0;
        ChipbarCoordinator$animateViewIn$onAnimationEnd$1 chipbarCoordinator$animateViewIn$onAnimationEnd$1 = new ChipbarCoordinator$animateViewIn$onAnimationEnd$1(this, viewGroup, i);
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner);
        this.chipbarAnimator.getClass();
        ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
        ViewHierarchyAnimator.Hotspot hotspot = ViewHierarchyAnimator.Hotspot.TOP;
        Interpolator interpolator = Interpolators.EMPHASIZED_DECELERATE;
        Interpolator interpolator2 = ViewHierarchyAnimator.DEFAULT_FADE_IN_INTERPOLATOR;
        if (ViewHierarchyAnimator.Companion.occupiesSpace(viewGroup2.getVisibility(), viewGroup2.getLeft(), viewGroup2.getTop(), viewGroup2.getRight(), viewGroup2.getBottom())) {
            ((ChipbarLogger) this.logger).logAnimateInFailure();
            ChipbarAnimator.forceDisplayView((ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner));
            chipbarCoordinator$animateViewIn$onAnimationEnd$1.run();
            return;
        }
        ViewHierarchyAnimator.Companion.addListener$default(companion, viewGroup2, new ViewHierarchyAnimator$Companion$createListener$1(hotspot, false, interpolator, 500L, chipbarCoordinator$animateViewIn$onAnimationEnd$1), true);
        long j = 500 / 6;
        ViewHierarchyAnimator.Companion.createAndStartFadeInAnimator(viewGroup2, j, 0L, interpolator2);
        long j2 = 500 / 3;
        int childCount = viewGroup2.getChildCount();
        while (i < childCount) {
            View childAt = viewGroup2.getChildAt(i);
            Intrinsics.checkNotNull(childAt);
            ViewHierarchyAnimator.Companion.createAndStartFadeInAnimator(childAt, j2, j, interpolator2);
            i++;
        }
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void animateViewOut$frameworks__base__packages__SystemUI__android_common__SystemUI_core(ViewGroup viewGroup, String str, TemporaryViewDisplayController$removeViewFromWindow$1 temporaryViewDisplayController$removeViewFromWindow$1) {
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner);
        viewGroup2.setAccessibilityLiveRegion(0);
        ChipbarCoordinator$animateViewIn$onAnimationEnd$1 chipbarCoordinator$animateViewIn$onAnimationEnd$1 = new ChipbarCoordinator$animateViewIn$onAnimationEnd$1(this, temporaryViewDisplayController$removeViewFromWindow$1, 1);
        this.chipbarAnimator.getClass();
        ViewHierarchyAnimator.Companion companion = ViewHierarchyAnimator.Companion;
        ViewHierarchyAnimator.Hotspot hotspot = ViewHierarchyAnimator.Hotspot.TOP;
        if (!companion.animateRemoval(viewGroup2, Interpolators.EMPHASIZED_ACCELERATE, chipbarCoordinator$animateViewIn$onAnimationEnd$1)) {
            ((ChipbarLogger) this.logger).logAnimateOutFailure();
            chipbarCoordinator$animateViewIn$onAnimationEnd$1.run();
        }
        updateGestureListening$1();
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void getTouchableRegion(Rect rect, View view) {
        this.viewUtil.getClass();
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        rect.set(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final WindowManager.LayoutParams getWindowLayoutParams$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        return this.windowLayoutParams;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$updateGestureListening$1, kotlin.jvm.internal.Lambda] */
    public final void updateGestureListening$1() {
        final TemporaryViewDisplayController.DisplayInfo displayInfo = (TemporaryViewDisplayController.DisplayInfo) CollectionsKt.getOrNull(0, this.activeViews);
        SwipeChipbarAwayGestureHandler swipeChipbarAwayGestureHandler = this.swipeChipbarAwayGestureHandler;
        if (displayInfo == null || !((ChipbarInfo) displayInfo.info).allowSwipeToDismiss) {
            swipeChipbarAwayGestureHandler.viewFetcher = new Function0() { // from class: com.android.systemui.temporarydisplay.chipbar.SwipeChipbarAwayGestureHandler$resetViewFetcher$1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return null;
                }
            };
            swipeChipbarAwayGestureHandler.removeOnGestureDetectedCallback("ChipbarCoordinator");
        } else {
            swipeChipbarAwayGestureHandler.viewFetcher = new Function0() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$updateGestureListening$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TemporaryViewDisplayController.DisplayInfo.this.view;
                }
            };
            swipeChipbarAwayGestureHandler.addOnGestureDetectedCallback("ChipbarCoordinator", new Function1() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$updateGestureListening$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ChipbarCoordinator chipbarCoordinator = ChipbarCoordinator.this;
                    TemporaryViewDisplayController.DisplayInfo displayInfo2 = (TemporaryViewDisplayController.DisplayInfo) CollectionsKt.getOrNull(0, chipbarCoordinator.activeViews);
                    TemporaryViewLogger temporaryViewLogger = chipbarCoordinator.logger;
                    if (displayInfo2 == null) {
                        ChipbarLogger chipbarLogger = (ChipbarLogger) temporaryViewLogger;
                        chipbarLogger.getClass();
                        LogLevel logLevel = LogLevel.WARNING;
                        ChipbarLogger$logSwipeGestureError$2 chipbarLogger$logSwipeGestureError$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarLogger$logSwipeGestureError$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                LogMessage logMessage = (LogMessage) obj2;
                                return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Chipbar swipe gesture detected for incorrect state. id=", logMessage.getStr1(), " error=", logMessage.getStr2());
                            }
                        };
                        String str = chipbarLogger.tag;
                        LogBuffer logBuffer = chipbarLogger.buffer;
                        LogMessage obtain = logBuffer.obtain(str, logLevel, chipbarLogger$logSwipeGestureError$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str1 = null;
                        logMessageImpl.str2 = "No info is being displayed";
                        logBuffer.commit(obtain);
                    } else {
                        ChipbarInfo chipbarInfo = (ChipbarInfo) displayInfo2.info;
                        if (chipbarInfo.allowSwipeToDismiss) {
                            chipbarCoordinator.tempViewUiEventLogger.logger.log(TemporaryViewUiEvent.TEMPORARY_VIEW_MANUALLY_DISMISSED, chipbarInfo.instanceId);
                            chipbarCoordinator.removeView(((ChipbarInfo) displayInfo2.info).id, "SWIPE_UP_GESTURE_DETECTED");
                            chipbarCoordinator.updateGestureListening$1();
                        } else {
                            ChipbarLogger chipbarLogger2 = (ChipbarLogger) temporaryViewLogger;
                            chipbarLogger2.getClass();
                            LogLevel logLevel2 = LogLevel.WARNING;
                            ChipbarLogger$logSwipeGestureError$2 chipbarLogger$logSwipeGestureError$22 = new Function1() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarLogger$logSwipeGestureError$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    LogMessage logMessage = (LogMessage) obj2;
                                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Chipbar swipe gesture detected for incorrect state. id=", logMessage.getStr1(), " error=", logMessage.getStr2());
                                }
                            };
                            String str2 = chipbarLogger2.tag;
                            LogBuffer logBuffer2 = chipbarLogger2.buffer;
                            LogMessage obtain2 = logBuffer2.obtain(str2, logLevel2, chipbarLogger$logSwipeGestureError$22, null);
                            LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                            logMessageImpl2.str1 = chipbarInfo.id;
                            logMessageImpl2.str2 = "This view prohibits swipe-to-dismiss";
                            logBuffer2.commit(obtain2);
                        }
                    }
                    return Unit.INSTANCE;
                }
            });
        }
    }

    @Override // com.android.systemui.temporarydisplay.TemporaryViewDisplayController
    public final void updateView(TemporaryViewInfo temporaryViewInfo, ViewGroup viewGroup) {
        String m;
        String str;
        String string;
        final ChipbarInfo chipbarInfo = (ChipbarInfo) temporaryViewInfo;
        updateGestureListening$1();
        ChipbarLogger chipbarLogger = (ChipbarLogger) this.logger;
        Context context = this.context;
        Text text = chipbarInfo.text;
        String loadText = Text.Companion.loadText(text, context);
        ChipbarEndItem chipbarEndItem = chipbarInfo.endItem;
        if (chipbarEndItem == null) {
            m = "null";
        } else if (chipbarEndItem instanceof ChipbarEndItem.Loading) {
            m = "loading";
        } else if (chipbarEndItem instanceof ChipbarEndItem.Error) {
            m = "error";
        } else {
            if (!(chipbarEndItem instanceof ChipbarEndItem.Button)) {
                throw new NoWhenBranchMatchedException();
            }
            m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("button(", Text.Companion.loadText(((ChipbarEndItem.Button) chipbarEndItem).text, this.context), ")");
        }
        chipbarLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        ChipbarLogger$logViewUpdate$2 chipbarLogger$logViewUpdate$2 = new Function1() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarLogger$logViewUpdate$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                String str2 = logMessage.getStr2();
                String str3 = logMessage.getStr3();
                StringBuilder m2 = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Chipbar updated. window=", str1, " text=", str2, " endItem=");
                m2.append(str3);
                return m2.toString();
            }
        };
        String str2 = chipbarLogger.tag;
        LogBuffer logBuffer = chipbarLogger.buffer;
        LogMessage obtain = logBuffer.obtain(str2, logLevel, chipbarLogger$logViewUpdate$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = chipbarInfo.windowTitle;
        logMessageImpl.str2 = loadText;
        logMessageImpl.str3 = m;
        logBuffer.commit(obtain);
        viewGroup.setTag(R.id.tag_chipbar_info, chipbarInfo);
        ((ChipbarRootView) viewGroup.requireViewById(R.id.chipbar_root_view)).touchHandler = new ChipbarCoordinator$updateView$1(this);
        CachingIconView requireViewById = viewGroup.requireViewById(R.id.start_icon);
        TintedIcon tintedIcon = chipbarInfo.startIcon;
        IconViewBinder.bind(tintedIcon.icon, requireViewById);
        Integer num = tintedIcon.tint;
        requireViewById.setImageTintList(num != null ? Utils.getColorAttr(num.intValue(), requireViewById.getContext()) : null);
        TextView textView = (TextView) viewGroup.requireViewById(R.id.text);
        TextViewBinder.bind(textView, text);
        textView.requestLayout();
        boolean areEqual = Intrinsics.areEqual(chipbarEndItem, ChipbarEndItem.Loading.INSTANCE);
        ImageView imageView = (ImageView) viewGroup.requireViewById(R.id.loading);
        imageView.setVisibility(areEqual ? 0 : 8);
        if (areEqual) {
            LoadingDetails loadingDetails = this.loadingDetails;
            if (loadingDetails == null || !loadingDetails.loadingView.equals(imageView)) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, (Property<ImageView, Float>) View.ROTATION, 0.0f, 360.0f);
                ofFloat.setDuration(1000L);
                ofFloat.setRepeatCount(-1);
                ofFloat.setInterpolator(Interpolators.LINEAR);
                LoadingDetails loadingDetails2 = new LoadingDetails(imageView, ofFloat);
                ofFloat.start();
                LoadingDetails loadingDetails3 = this.loadingDetails;
                if (loadingDetails3 != null) {
                    loadingDetails3.animator.cancel();
                }
                this.loadingDetails = loadingDetails2;
            }
        } else {
            LoadingDetails loadingDetails4 = this.loadingDetails;
            if (loadingDetails4 != null) {
                loadingDetails4.animator.cancel();
            }
            this.loadingDetails = null;
        }
        viewGroup.requireViewById(R.id.error).setVisibility(Intrinsics.areEqual(chipbarEndItem, ChipbarEndItem.Error.INSTANCE) ? 0 : 8);
        TextView textView2 = (TextView) viewGroup.requireViewById(R.id.end_button);
        boolean z = chipbarEndItem instanceof ChipbarEndItem.Button;
        if (z) {
            TextViewBinder.bind(textView2, ((ChipbarEndItem.Button) chipbarEndItem).text);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator$updateView$onClickListener$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (ChipbarCoordinator.this.falsingManager.isFalseTap(1)) {
                        return;
                    }
                    ((ChipbarEndItem.Button) chipbarInfo.endItem).onClickListener.onClick(view);
                }
            });
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner);
        viewGroup2.setPaddingRelative(viewGroup2.getPaddingStart(), viewGroup2.getPaddingTop(), viewGroup2.getContext().getResources().getDimensionPixelSize(z ? R.dimen.chipbar_outer_padding_half : R.dimen.chipbar_outer_padding), viewGroup2.getPaddingBottom());
        ContentDescription contentDescription = tintedIcon.icon.getContentDescription();
        if (contentDescription != null) {
            Context context2 = this.context;
            if (contentDescription instanceof ContentDescription.Loaded) {
                string = ((ContentDescription.Loaded) contentDescription).description;
            } else {
                if (!(contentDescription instanceof ContentDescription.Resource)) {
                    throw new NoWhenBranchMatchedException();
                }
                string = context2.getString(((ContentDescription.Resource) contentDescription).res);
            }
            str = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(string, " ");
        } else {
            str = "";
        }
        String m2 = chipbarEndItem instanceof ChipbarEndItem.Loading ? DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(". ", this.context.getResources().getString(R.string.media_transfer_loading), ".") : "";
        ViewGroup viewGroup3 = (ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner);
        viewGroup3.setContentDescription(str + Text.Companion.loadText(text, this.context) + m2);
        viewGroup3.setAccessibilityLiveRegion(2);
        viewGroup3.setAccessibilityDelegate(new ChipbarCoordinator$updateView$2());
        if (chipbarEndItem instanceof ChipbarEndItem.Button) {
            ((ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner)).requestAccessibilityFocus();
        } else {
            ((ViewGroup) viewGroup.requireViewById(R.id.chipbar_inner)).clearAccessibilityFocus();
        }
        final VibrationEffect vibrationEffect = chipbarInfo.vibrationEffect;
        if (vibrationEffect != null) {
            final int myUid = Process.myUid();
            final String packageName = this.context.getApplicationContext().getPackageName();
            final VibrationAttributes vibrationAttributes = VIBRATION_ATTRIBUTES;
            final VibratorHelper vibratorHelper = this.vibratorHelper;
            final String str3 = chipbarInfo.windowTitle;
            if (vibratorHelper.hasVibrator()) {
                vibratorHelper.mExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.VibratorHelper$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        VibratorHelper vibratorHelper2 = VibratorHelper.this;
                        vibratorHelper2.mVibrator.vibrate(myUid, packageName, vibrationEffect, str3, vibrationAttributes);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void getLoadingDetails$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
