package com.android.systemui.shade;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.content.res.Configuration;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Bundle;
import android.os.Trace;
import android.os.UserHandle;
import android.permission.PermissionGroupUsage;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.ActiveUnlockConfig$$ExternalSyntheticOutline0;
import com.android.settingslib.Utils;
import com.android.systemui.Dumpable;
import com.android.systemui.FontSizeUtils;
import com.android.systemui.appops.AppOpsControllerImpl;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.battery.BatteryMeterViewController;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyChipEvent;
import com.android.systemui.privacy.PrivacyConfig;
import com.android.systemui.privacy.PrivacyDialog;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyDialogV2;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.carrier.ShadeCarrier;
import com.android.systemui.shade.carrier.ShadeCarrierGroup;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.HoverTheme;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListener;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.phone.ui.TintedIconManager;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.NextAlarmControllerImpl;
import com.android.systemui.statusbar.policy.VariableDateView;
import com.android.systemui.statusbar.policy.VariableDateViewController;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeHeaderController extends ViewController implements Dumpable {
    public static final Intent DEFAULT_CLOCK_INTENT = new Intent("android.intent.action.SHOW_ALARMS");
    public final ActivityStarter activityStarter;
    public final BatteryMeterView batteryIcon;
    public final BatteryMeterViewController batteryMeterViewController;
    public List carrierIconSlots;
    public final ShadeHeaderController$updateListeners$1 chipVisibilityListener;
    public final Clock clock;
    public final CombinedShadeHeadersConstraintManagerImpl combinedShadeHeadersConstraintManager;
    public final ConfigurationController configurationController;
    public final ShadeHeaderController$configurationControllerListener$1 configurationControllerListener;
    public boolean customizing;
    public DisplayCutout cutout;
    public final TextView date;
    public final DemoModeController demoModeController;
    public final ShadeHeaderController$demoModeReceiver$1 demoModeReceiver;
    public final DumpManager dumpManager;
    public final MotionLayout header;
    public final StatusIconContainer iconContainer;
    public TintedIconManager iconManager;
    public final ShadeHeaderController$insetListener$1 insetListener;
    public final StatusBarContentInsetsProvider insetsProvider;
    public boolean largeScreenActive;
    public WindowInsets lastInsets;
    public final ShadeCarrierGroup mShadeCarrierGroup;
    public ShadeCarrierGroupController mShadeCarrierGroupController;
    public final ShadeHeaderController$nextAlarmCallback$1 nextAlarmCallback;
    public final NextAlarmController nextAlarmController;
    public PendingIntent nextAlarmIntent;
    public final HeaderPrivacyIconsController privacyIconsController;
    public final QsBatteryModeController qsBatteryModeController;
    public boolean qsDisabled;
    public float qsExpandedFraction;
    public int qsScrollY;
    public boolean qsVisible;
    public final ShadeCarrierGroupController.Builder shadeCarrierGroupControllerBuilder;
    public NotificationPanelViewController$$ExternalSyntheticLambda0 shadeCollapseAction;
    public float shadeExpandedFraction;
    public boolean singleCarrier;
    public final StatusBarIconController statusBarIconController;
    public final StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory;
    public final View systemIconsHoverContainer;
    public final TintedIconManager.Factory tintedIconManagerFactory;
    public final VariableDateViewController.Factory variableDateViewControllerFactory;
    public boolean visible;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CustomizerAnimationListener extends AnimatorListenerAdapter {
        public final boolean enteringCustomizing;

        public CustomizerAnimationListener(boolean z) {
            this.enteringCustomizing = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            ShadeHeaderController.this.header.animate().setListener(null);
            if (this.enteringCustomizing) {
                ShadeHeaderController shadeHeaderController = ShadeHeaderController.this;
                if (!shadeHeaderController.customizing) {
                    shadeHeaderController.customizing = true;
                    shadeHeaderController.updateVisibility$2();
                }
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            if (this.enteringCustomizing) {
                return;
            }
            ShadeHeaderController shadeHeaderController = ShadeHeaderController.this;
            if (shadeHeaderController.customizing) {
                shadeHeaderController.customizing = false;
                shadeHeaderController.updateVisibility$2();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.systemui.shade.ShadeHeaderController$insetListener$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.systemui.shade.ShadeHeaderController$demoModeReceiver$1] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.shade.ShadeHeaderController$configurationControllerListener$1] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.systemui.shade.ShadeHeaderController$nextAlarmCallback$1] */
    public ShadeHeaderController(MotionLayout motionLayout, StatusBarIconController statusBarIconController, TintedIconManager.Factory factory, HeaderPrivacyIconsController headerPrivacyIconsController, StatusBarContentInsetsProvider statusBarContentInsetsProvider, ConfigurationController configurationController, VariableDateViewController.Factory factory2, BatteryMeterViewController batteryMeterViewController, DumpManager dumpManager, ShadeCarrierGroupController.Builder builder, CombinedShadeHeadersConstraintManagerImpl combinedShadeHeadersConstraintManagerImpl, DemoModeController demoModeController, QsBatteryModeController qsBatteryModeController, NextAlarmController nextAlarmController, ActivityStarter activityStarter, StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory) {
        super(motionLayout);
        this.header = motionLayout;
        this.statusBarIconController = statusBarIconController;
        this.tintedIconManagerFactory = factory;
        this.privacyIconsController = headerPrivacyIconsController;
        this.insetsProvider = statusBarContentInsetsProvider;
        this.configurationController = configurationController;
        this.variableDateViewControllerFactory = factory2;
        this.batteryMeterViewController = batteryMeterViewController;
        this.dumpManager = dumpManager;
        this.shadeCarrierGroupControllerBuilder = builder;
        this.combinedShadeHeadersConstraintManager = combinedShadeHeadersConstraintManagerImpl;
        this.demoModeController = demoModeController;
        this.qsBatteryModeController = qsBatteryModeController;
        this.nextAlarmController = nextAlarmController;
        this.activityStarter = activityStarter;
        this.statusOverlayHoverListenerFactory = statusOverlayHoverListenerFactory;
        this.batteryIcon = (BatteryMeterView) motionLayout.requireViewById(R.id.batteryRemainingIcon);
        this.clock = (Clock) motionLayout.requireViewById(R.id.clock);
        this.date = (TextView) motionLayout.requireViewById(R.id.date);
        this.iconContainer = (StatusIconContainer) motionLayout.requireViewById(R.id.statusIcons);
        this.mShadeCarrierGroup = (ShadeCarrierGroup) motionLayout.requireViewById(R.id.carrier_group);
        this.systemIconsHoverContainer = motionLayout.requireViewById(R.id.hover_system_icons_container);
        this.shadeExpandedFraction = -1.0f;
        this.qsExpandedFraction = -1.0f;
        this.insetListener = new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.shade.ShadeHeaderController$insetListener$1
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                ShadeHeaderController.access$updateConstraintsForInsets(ShadeHeaderController.this, (MotionLayout) view, windowInsets);
                ShadeHeaderController.this.lastInsets = new WindowInsets(windowInsets);
                return view.onApplyWindowInsets(windowInsets);
            }
        };
        this.demoModeReceiver = new DemoMode() { // from class: com.android.systemui.shade.ShadeHeaderController$demoModeReceiver$1
            @Override // com.android.systemui.demomode.DemoMode
            public final List demoCommands() {
                return Collections.singletonList("clock");
            }

            @Override // com.android.systemui.demomode.DemoModeCommandReceiver
            public final void dispatchDemoCommand(Bundle bundle, String str) {
                ShadeHeaderController.this.clock.dispatchDemoCommand(bundle, str);
            }

            @Override // com.android.systemui.demomode.DemoModeCommandReceiver
            public final void onDemoModeFinished() {
                ShadeHeaderController.this.clock.onDemoModeFinished();
            }

            @Override // com.android.systemui.demomode.DemoModeCommandReceiver
            public final void onDemoModeStarted() {
                ShadeHeaderController.this.clock.mDemoMode = true;
            }
        };
        this.chipVisibilityListener = new ShadeHeaderController$updateListeners$1(this);
        this.configurationControllerListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.shade.ShadeHeaderController$configurationControllerListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                ShadeHeaderController shadeHeaderController = ShadeHeaderController.this;
                int dimensionPixelSize = shadeHeaderController.header.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_left_padding);
                MotionLayout motionLayout2 = shadeHeaderController.header;
                motionLayout2.setPadding(dimensionPixelSize, motionLayout2.getPaddingTop(), motionLayout2.getPaddingRight(), motionLayout2.getPaddingBottom());
                shadeHeaderController.systemIconsHoverContainer.setPaddingRelative(shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.hover_system_icons_container_padding_start), shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.hover_system_icons_container_padding_top), shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.hover_system_icons_container_padding_end), shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.hover_system_icons_container_padding_bottom));
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                ShadeHeaderController shadeHeaderController = ShadeHeaderController.this;
                shadeHeaderController.clock.setTextAppearance(R.style.TextAppearance_QS_Status);
                shadeHeaderController.date.setTextAppearance(R.style.TextAppearance_QS_Status);
                ShadeCarrierGroup shadeCarrierGroup = shadeHeaderController.mShadeCarrierGroup;
                FontSizeUtils.updateFontSizeFromStyle((TextView) shadeCarrierGroup.findViewById(R.id.no_carrier_text), R.style.TextAppearance_QS_Status_Carriers);
                FontSizeUtils.updateFontSizeFromStyle(((ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier1)).mCarrierText, R.style.TextAppearance_QS_Status_Carriers);
                FontSizeUtils.updateFontSizeFromStyle(((ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier2)).mCarrierText, R.style.TextAppearance_QS_Status_Carriers);
                FontSizeUtils.updateFontSizeFromStyle(((ShadeCarrier) shadeCarrierGroup.findViewById(R.id.carrier3)).mCarrierText, R.style.TextAppearance_QS_Status_Carriers);
                MotionLayout motionLayout2 = shadeHeaderController.header;
                motionLayout2.getConstraintSet(R.id.qqs_header_constraint).load(shadeHeaderController.mView.getContext(), shadeHeaderController.mView.getResources().getXml(R.xml.qqs_header));
                motionLayout2.getConstraintSet(R.id.qs_header_constraint).load(shadeHeaderController.mView.getContext(), shadeHeaderController.mView.getResources().getXml(R.xml.qs_header));
                motionLayout2.getConstraintSet(R.id.large_screen_header_constraint).load(shadeHeaderController.mView.getContext(), shadeHeaderController.mView.getResources().getXml(R.xml.large_screen_shade_header));
                int dimensionPixelSize = shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_min_height);
                if (dimensionPixelSize != motionLayout2.mMinHeight) {
                    motionLayout2.mMinHeight = dimensionPixelSize;
                    motionLayout2.requestLayout();
                }
                WindowInsets windowInsets = shadeHeaderController.lastInsets;
                if (windowInsets != null) {
                    ShadeHeaderController.access$updateConstraintsForInsets(shadeHeaderController, motionLayout2, windowInsets);
                }
                shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.rounded_corner_content_padding);
                int dimensionPixelSize2 = shadeHeaderController.mView.getResources().getDimensionPixelSize(R.dimen.qs_panel_padding);
                motionLayout2.setPadding(dimensionPixelSize2, motionLayout2.getPaddingTop(), dimensionPixelSize2, motionLayout2.getPaddingBottom());
                shadeHeaderController.updateQQSPaddings();
                shadeHeaderController.qsBatteryModeController.updateResources();
                shadeHeaderController.updateCarrierGroupPadding();
                shadeHeaderController.clock.onDensityOrFontScaleChanged();
            }
        };
        this.nextAlarmCallback = new NextAlarmController.NextAlarmChangeCallback() { // from class: com.android.systemui.shade.ShadeHeaderController$nextAlarmCallback$1
            @Override // com.android.systemui.statusbar.policy.NextAlarmController.NextAlarmChangeCallback
            public final void onNextAlarmChanged(AlarmManager.AlarmClockInfo alarmClockInfo) {
                ShadeHeaderController.this.nextAlarmIntent = alarmClockInfo != null ? alarmClockInfo.getShowIntent() : null;
            }
        };
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void access$updateConstraintsForInsets(com.android.systemui.shade.ShadeHeaderController r8, androidx.constraintlayout.motion.widget.MotionLayout r9, android.view.WindowInsets r10) {
        /*
            r8.getClass()
            android.view.DisplayCutout r10 = r10.getDisplayCutout()
            r8.cutout = r10
            com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider r0 = r8.insetsProvider
            android.graphics.Insets r1 = r0.getStatusBarContentInsetsForCurrentRotation()
            int r2 = r1.left
            int r3 = r1.right
            boolean r0 = r0.currentRotationHasCornerCutout()
            r8.updateQQSPaddings()
            boolean r4 = r9.isLayoutRtl()
            if (r4 == 0) goto L22
            r4 = r3
            goto L23
        L22:
            r4 = r2
        L23:
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r8.header
            int r6 = r5.getPaddingStart()
            boolean r7 = r9.isLayoutRtl()
            if (r7 == 0) goto L30
            goto L31
        L30:
            r2 = r3
        L31:
            int r3 = r5.getPaddingEnd()
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl r5 = r8.combinedShadeHeadersConstraintManager
            r5.getClass()
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$edgesGuidelinesConstraints$change$1 r5 = new com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$edgesGuidelinesConstraints$change$1
            r5.<init>()
            r2 = 0
            if (r10 == 0) goto La4
            android.graphics.Rect r10 = r10.getBoundingRectTop()
            boolean r3 = r10.isEmpty()
            if (r3 != 0) goto L8f
            if (r0 == 0) goto L4f
            goto L8f
        L4f:
            boolean r0 = r9.isLayoutRtl()
            int r2 = r9.getWidth()
            int r3 = r9.getPaddingLeft()
            int r2 = r2 - r3
            int r3 = r9.getPaddingRight()
            int r2 = r2 - r3
            int r10 = r10.width()
            int r2 = r2 - r10
            int r2 = r2 / 2
            r10 = 2131362249(0x7f0a01c9, float:1.8344273E38)
            r3 = 2131362248(0x7f0a01c8, float:1.8344271E38)
            if (r0 != 0) goto L72
            r4 = r3
            goto L73
        L72:
            r4 = r10
        L73:
            if (r0 != 0) goto L76
            goto L77
        L76:
            r10 = r3
        L77:
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$centerCutoutConstraints$1 r0 = new com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$centerCutoutConstraints$1
            r0.<init>()
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$centerCutoutConstraints$2 r3 = new com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$centerCutoutConstraints$2
            r3.<init>()
            com.android.systemui.shade.ConstraintsChanges r10 = new com.android.systemui.shade.ConstraintsChanges
            kotlin.jvm.functions.Function1 r0 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r0)
            kotlin.jvm.functions.Function1 r2 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r3)
            r10.<init>(r0, r2, r5)
            goto Lb8
        L8f:
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1 r10 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1
                static {
                    /*
                        com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1 r0 = new com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1) com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1.INSTANCE com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r8) {
                    /*
                        r7 = this;
                        androidx.constraintlayout.widget.ConstraintSet r8 = (androidx.constraintlayout.widget.ConstraintSet) r8
                        r7 = 2131362387(0x7f0a0253, float:1.8344553E38)
                        r0 = 7
                        r1 = 2131362074(0x7f0a011a, float:1.8343918E38)
                        r2 = 6
                        r8.connect(r7, r0, r1, r2)
                        r3 = 2131363659(0x7f0a074b, float:1.8347133E38)
                        r4 = 2131363393(0x7f0a0641, float:1.8346594E38)
                        int[] r5 = new int[]{r3, r4}
                        r6 = 0
                        r8.createBarrier(r1, r2, r6, r5)
                        r8.connect(r3, r2, r7, r0)
                        r8.connect(r4, r2, r7, r0)
                        r0 = -2
                        r8.constrainWidth(r3, r0)
                        androidx.constraintlayout.widget.ConstraintSet$Constraint r7 = r8.get(r7)
                        androidx.constraintlayout.widget.ConstraintSet$Layout r7 = r7.layout
                        r0 = 1
                        r7.constrainedWidth = r0
                        androidx.constraintlayout.widget.ConstraintSet$Constraint r7 = r8.get(r3)
                        androidx.constraintlayout.widget.ConstraintSet$Layout r7 = r7.layout
                        r7.constrainedWidth = r0
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            com.android.systemui.shade.ConstraintsChanges r0 = new com.android.systemui.shade.ConstraintsChanges
            kotlin.jvm.functions.Function1 r10 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r10)
            kotlin.jvm.functions.Function1 r3 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r2)
            kotlin.jvm.functions.Function1 r2 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r2)
            r0.<init>(r10, r3, r2)
        La2:
            r10 = r0
            goto Lb8
        La4:
            com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1 r10 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerImpl$emptyCutoutConstraints$1.INSTANCE
            com.android.systemui.shade.ConstraintsChanges r0 = new com.android.systemui.shade.ConstraintsChanges
            kotlin.jvm.functions.Function1 r10 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r10)
            kotlin.jvm.functions.Function1 r3 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r2)
            kotlin.jvm.functions.Function1 r2 = com.android.systemui.shade.CombinedShadeHeadersConstraintManagerKt.plus(r5, r2)
            r0.<init>(r10, r3, r2)
            goto La2
        Lb8:
            int r0 = r9.getPaddingLeft()
            int r1 = r1.top
            int r2 = r9.getPaddingRight()
            int r3 = r9.getPaddingBottom()
            r9.setPadding(r0, r1, r2, r3)
            kotlin.jvm.functions.Function1 r0 = r10.qqsConstraintsChanges
            if (r0 == 0) goto Ld3
            r1 = 2131363429(0x7f0a0665, float:1.8346667E38)
            updateConstraints(r9, r1, r0)
        Ld3:
            kotlin.jvm.functions.Function1 r0 = r10.qsConstraintsChanges
            if (r0 == 0) goto Ldd
            r1 = 2131363437(0x7f0a066d, float:1.8346683E38)
            updateConstraints(r9, r1, r0)
        Ldd:
            kotlin.jvm.functions.Function1 r10 = r10.largeScreenConstraintsChanges
            if (r10 == 0) goto Le7
            r0 = 2131362850(0x7f0a0422, float:1.8345492E38)
            updateConstraints(r9, r0, r10)
        Le7:
            r8.updateBatteryMode()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.shade.ShadeHeaderController.access$updateConstraintsForInsets(com.android.systemui.shade.ShadeHeaderController, androidx.constraintlayout.motion.widget.MotionLayout, android.view.WindowInsets):void");
    }

    public static void updateConstraints(MotionLayout motionLayout, int i, Function1 function1) {
        ConstraintSet constraintSet = motionLayout.getConstraintSet(i);
        Intrinsics.checkNotNull(constraintSet);
        function1.invoke(constraintSet);
        motionLayout.updateState(i, constraintSet);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("visible: ", this.visible, printWriter);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("shadeExpanded: ", this.qsVisible, printWriter);
        printWriter.println("shadeExpandedFraction: " + this.shadeExpandedFraction);
        ActiveUnlockConfig$$ExternalSyntheticOutline0.m("active: ", this.largeScreenActive, printWriter);
        printWriter.println("qsExpandedFraction: " + this.qsExpandedFraction);
        printWriter.println("qsScrollY: " + this.qsScrollY);
        int i = this.header.mCurrentState;
        FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter, "currentState: ", i == R.id.qqs_header_constraint ? "QQS Header" : i == R.id.qs_header_constraint ? "QS Header" : i == R.id.large_screen_header_constraint ? "Large Screen Header" : AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown state "));
    }

    public final void launchClockActivity$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        PendingIntent pendingIntent = this.nextAlarmIntent;
        ActivityStarter activityStarter = this.activityStarter;
        if (pendingIntent != null) {
            activityStarter.postStartActivityDismissingKeyguard(pendingIntent);
        } else {
            activityStarter.postStartActivityDismissingKeyguard(DEFAULT_CLOCK_INTENT, 0);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        VariableDateView variableDateView = (VariableDateView) this.date;
        VariableDateViewController.Factory factory = this.variableDateViewControllerFactory;
        new VariableDateViewController(factory.systemClock, factory.broadcastDispatcher, factory.shadeInteractor, factory.shadeLogger, factory.handler, variableDateView).init$9();
        BatteryMeterViewController batteryMeterViewController = this.batteryMeterViewController;
        batteryMeterViewController.init$9();
        batteryMeterViewController.mIgnoreTunerUpdates = true;
        if (batteryMeterViewController.mIsSubscribedForTunerUpdates) {
            batteryMeterViewController.mTunerService.removeTunable(batteryMeterViewController.mTunable);
            batteryMeterViewController.mIsSubscribedForTunerUpdates = false;
        }
        MotionLayout motionLayout = this.header;
        int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(android.R.attr.textColorPrimary, 0, motionLayout.getContext());
        int colorAttrDefaultColor2 = Utils.getColorAttrDefaultColor(android.R.attr.textColorPrimaryInverse, 0, motionLayout.getContext());
        TintedIconManager create = this.tintedIconManagerFactory.create(this.iconContainer, StatusBarLocation.QS);
        this.iconManager = create;
        create.setTint(colorAttrDefaultColor, colorAttrDefaultColor2);
        this.batteryIcon.updateColors(colorAttrDefaultColor, colorAttrDefaultColor2, colorAttrDefaultColor);
        this.carrierIconSlots = Collections.singletonList(motionLayout.getContext().getString(android.R.string.suspended_widget_accessibility));
        ShadeCarrierGroupController.Builder builder = this.shadeCarrierGroupControllerBuilder;
        builder.getClass();
        this.mShadeCarrierGroupController = new ShadeCarrierGroupController(this.mShadeCarrierGroup, builder.mActivityStarter, builder.mHandler, builder.mLooper, builder.mLogger, builder.mNetworkController, builder.mCarrierTextControllerBuilder, builder.mContext, builder.mSlotIndexResolver, builder.mMobileUiAdapter, builder.mMobileContextProvider, builder.mStatusBarPipelineFlags);
        final HeaderPrivacyIconsController headerPrivacyIconsController = this.privacyIconsController;
        headerPrivacyIconsController.getClass();
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.systemui.qs.HeaderPrivacyIconsController$onParentVisible$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (((DeviceProvisionedControllerImpl) HeaderPrivacyIconsController.this.deviceProvisionedController).deviceProvisioned.get()) {
                    HeaderPrivacyIconsController.this.uiEventLogger.log(PrivacyChipEvent.ONGOING_INDICATORS_CHIP_CLICK);
                    HeaderPrivacyIconsController headerPrivacyIconsController2 = HeaderPrivacyIconsController.this;
                    if (!headerPrivacyIconsController2.safetyCenterEnabled) {
                        final Context context = headerPrivacyIconsController2.privacyChip.getContext();
                        final PrivacyDialogController privacyDialogController = headerPrivacyIconsController2.privacyDialogController;
                        PrivacyDialog privacyDialog = privacyDialogController.dialog;
                        if (privacyDialog != null) {
                            privacyDialog.dismiss();
                        }
                        privacyDialogController.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.privacy.PrivacyDialogController$showDialog$1
                            /* JADX WARN: Multi-variable type inference failed */
                            /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.CharSequence, java.lang.Object] */
                            @Override // java.lang.Runnable
                            public final void run() {
                                PrivacyType privacyType;
                                PrivacyType privacyType2;
                                Object obj;
                                String packageName;
                                Intent intent;
                                ActivityInfo activityInfo;
                                PrivacyDialogController privacyDialogController2 = PrivacyDialogController.this;
                                List<PermissionGroupUsage> indicatorAppOpUsageData = privacyDialogController2.permissionManager.getIndicatorAppOpUsageData(((AppOpsControllerImpl) privacyDialogController2.appOpsController).mMicMuted);
                                List userProfiles = ((UserTrackerImpl) PrivacyDialogController.this.userTracker).getUserProfiles();
                                PrivacyDialogController.this.privacyLogger.logUnfilteredPermGroupUsage(indicatorAppOpUsageData);
                                PrivacyDialogController privacyDialogController3 = PrivacyDialogController.this;
                                final ArrayList arrayList = new ArrayList();
                                for (PermissionGroupUsage permissionGroupUsage : indicatorAppOpUsageData) {
                                    String permissionGroupName = permissionGroupUsage.getPermissionGroupName();
                                    privacyDialogController3.getClass();
                                    int hashCode = permissionGroupName.hashCode();
                                    PrivacyDialog.PrivacyElement privacyElement = null;
                                    if (hashCode == -1140935117) {
                                        if (permissionGroupName.equals("android.permission-group.CAMERA")) {
                                            privacyType = PrivacyType.TYPE_CAMERA;
                                        }
                                        privacyType = null;
                                    } else if (hashCode != 828638019) {
                                        if (hashCode == 1581272376 && permissionGroupName.equals("android.permission-group.MICROPHONE")) {
                                            privacyType = PrivacyType.TYPE_MICROPHONE;
                                        }
                                        privacyType = null;
                                    } else {
                                        if (permissionGroupName.equals("android.permission-group.LOCATION")) {
                                            privacyType = PrivacyType.TYPE_LOCATION;
                                        }
                                        privacyType = null;
                                    }
                                    if (privacyType != null) {
                                        PrivacyType privacyType3 = PrivacyType.TYPE_CAMERA;
                                        PrivacyItemController privacyItemController = privacyDialogController3.privacyItemController;
                                        if (((privacyType != privacyType3 && privacyType != PrivacyType.TYPE_MICROPHONE) || !privacyItemController.privacyConfig.micCameraAvailable) && (privacyType != PrivacyType.TYPE_LOCATION || !privacyItemController.privacyConfig.locationAvailable)) {
                                            privacyType = null;
                                        }
                                        privacyType2 = privacyType;
                                    } else {
                                        privacyType2 = null;
                                    }
                                    Iterator it = userProfiles.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            obj = it.next();
                                            if (((UserInfo) obj).id == UserHandle.getUserId(permissionGroupUsage.getUid())) {
                                                break;
                                            }
                                        } else {
                                            obj = null;
                                            break;
                                        }
                                    }
                                    UserInfo userInfo = (UserInfo) obj;
                                    if ((userInfo != null || permissionGroupUsage.isPhoneCall()) && privacyType2 != null) {
                                        if (permissionGroupUsage.isPhoneCall()) {
                                            packageName = "";
                                        } else {
                                            packageName = permissionGroupUsage.getPackageName();
                                            try {
                                                ?? loadLabel = privacyDialogController3.packageManager.getApplicationInfoAsUser(packageName, 0, UserHandle.getUserId(permissionGroupUsage.getUid())).loadLabel(privacyDialogController3.packageManager);
                                                Intrinsics.checkNotNull(loadLabel);
                                                packageName = loadLabel;
                                            } catch (PackageManager.NameNotFoundException unused) {
                                                Log.w("PrivacyDialogController", "Label not found for: ".concat(packageName));
                                            }
                                        }
                                        String str = packageName;
                                        int userId = UserHandle.getUserId(permissionGroupUsage.getUid());
                                        String packageName2 = permissionGroupUsage.getPackageName();
                                        CharSequence attributionTag = permissionGroupUsage.getAttributionTag();
                                        CharSequence attributionLabel = permissionGroupUsage.getAttributionLabel();
                                        CharSequence proxyLabel = permissionGroupUsage.getProxyLabel();
                                        long lastAccessTimeMillis = permissionGroupUsage.getLastAccessTimeMillis();
                                        boolean isActive = permissionGroupUsage.isActive();
                                        boolean isManagedProfile = userInfo != null ? userInfo.isManagedProfile() : false;
                                        boolean isPhoneCall = permissionGroupUsage.isPhoneCall();
                                        String permissionGroupName2 = permissionGroupUsage.getPermissionGroupName();
                                        String packageName3 = permissionGroupUsage.getPackageName();
                                        String permissionGroupName3 = permissionGroupUsage.getPermissionGroupName();
                                        CharSequence attributionTag2 = permissionGroupUsage.getAttributionTag();
                                        boolean z = permissionGroupUsage.getAttributionLabel() != null;
                                        if (attributionTag2 != null && z && privacyDialogController3.locationManager.isProviderPackage(null, packageName3, attributionTag2.toString())) {
                                            intent = new Intent("android.intent.action.MANAGE_PERMISSION_USAGE");
                                            intent.setPackage(packageName3);
                                            intent.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", permissionGroupName3.toString());
                                            intent.putExtra("android.intent.extra.ATTRIBUTION_TAGS", new String[]{attributionTag2.toString()});
                                            intent.putExtra("android.intent.extra.SHOWING_ATTRIBUTION", true);
                                            ResolveInfo resolveActivity = privacyDialogController3.packageManager.resolveActivity(intent, PackageManager.ResolveInfoFlags.of(0L));
                                            if (resolveActivity != null && (activityInfo = resolveActivity.activityInfo) != null && Intrinsics.areEqual(activityInfo.permission, "android.permission.START_VIEW_PERMISSION_USAGE")) {
                                                intent.setComponent(new ComponentName(packageName3, resolveActivity.activityInfo.name));
                                                privacyElement = new PrivacyDialog.PrivacyElement(privacyType2, packageName2, userId, str, attributionTag, attributionLabel, proxyLabel, lastAccessTimeMillis, isActive, isManagedProfile, isPhoneCall, permissionGroupName2, intent);
                                            }
                                        }
                                        intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
                                        intent.putExtra("android.intent.extra.PACKAGE_NAME", packageName3);
                                        intent.putExtra("android.intent.extra.USER", UserHandle.of(userId));
                                        privacyElement = new PrivacyDialog.PrivacyElement(privacyType2, packageName2, userId, str, attributionTag, attributionLabel, proxyLabel, lastAccessTimeMillis, isActive, isManagedProfile, isPhoneCall, permissionGroupName2, intent);
                                    }
                                    if (privacyElement != null) {
                                        arrayList.add(privacyElement);
                                    }
                                }
                                final PrivacyDialogController privacyDialogController4 = PrivacyDialogController.this;
                                Executor executor = privacyDialogController4.uiExecutor;
                                final Context context2 = context;
                                executor.execute(new Runnable() { // from class: com.android.systemui.privacy.PrivacyDialogController$showDialog$1.1
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        Iterable singletonList;
                                        PrivacyDialogController privacyDialogController5 = PrivacyDialogController.this;
                                        List list = arrayList;
                                        privacyDialogController5.getClass();
                                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                                        for (Object obj2 : list) {
                                            PrivacyType privacyType4 = ((PrivacyDialog.PrivacyElement) obj2).type;
                                            Object obj3 = linkedHashMap.get(privacyType4);
                                            if (obj3 == null) {
                                                obj3 = new ArrayList();
                                                linkedHashMap.put(privacyType4, obj3);
                                            }
                                            ((List) obj3).add(obj2);
                                        }
                                        TreeMap treeMap = new TreeMap(linkedHashMap);
                                        ArrayList arrayList2 = new ArrayList();
                                        Iterator it2 = treeMap.entrySet().iterator();
                                        while (true) {
                                            Object obj4 = null;
                                            if (!it2.hasNext()) {
                                                break;
                                            }
                                            List list2 = (List) ((Map.Entry) it2.next()).getValue();
                                            Intrinsics.checkNotNull(list2);
                                            ArrayList arrayList3 = new ArrayList();
                                            for (Object obj5 : list2) {
                                                if (((PrivacyDialog.PrivacyElement) obj5).active) {
                                                    arrayList3.add(obj5);
                                                }
                                            }
                                            if (arrayList3.isEmpty()) {
                                                Iterator it3 = list2.iterator();
                                                if (it3.hasNext()) {
                                                    obj4 = it3.next();
                                                    if (it3.hasNext()) {
                                                        long j = ((PrivacyDialog.PrivacyElement) obj4).lastActiveTimestamp;
                                                        do {
                                                            Object next = it3.next();
                                                            long j2 = ((PrivacyDialog.PrivacyElement) next).lastActiveTimestamp;
                                                            if (j < j2) {
                                                                obj4 = next;
                                                                j = j2;
                                                            }
                                                        } while (it3.hasNext());
                                                    }
                                                }
                                                PrivacyDialog.PrivacyElement privacyElement2 = (PrivacyDialog.PrivacyElement) obj4;
                                                singletonList = privacyElement2 != null ? Collections.singletonList(privacyElement2) : EmptyList.INSTANCE;
                                            } else {
                                                singletonList = CollectionsKt.sortedWith(arrayList3, new PrivacyDialogController$filterAndSelect$lambda$6$$inlined$sortedByDescending$1());
                                            }
                                            CollectionsKt__MutableCollectionsKt.addAll(singletonList, arrayList2);
                                        }
                                        if (arrayList2.isEmpty()) {
                                            Log.w("PrivacyDialogController", "Trying to show empty dialog");
                                            return;
                                        }
                                        PrivacyDialogController.DialogProvider dialogProvider = PrivacyDialogController.this.dialogProvider;
                                        Context context3 = context2;
                                        PrivacyDialogController$showDialog$1$1$d$1 privacyDialogController$showDialog$1$1$d$1 = new PrivacyDialogController$showDialog$1$1$d$1(4, PrivacyDialogController.this, PrivacyDialogController.class, "startActivity", "startActivity(Ljava/lang/String;ILjava/lang/CharSequence;Landroid/content/Intent;)V", 0);
                                        ((PrivacyDialogControllerKt$defaultDialogProvider$1) dialogProvider).getClass();
                                        PrivacyDialog privacyDialog2 = new PrivacyDialog(context3, arrayList2, privacyDialogController$showDialog$1$1$d$1);
                                        SystemUIDialog.setShowForAllUsers(privacyDialog2);
                                        PrivacyDialogController$onDialogDismissed$1 privacyDialogController$onDialogDismissed$1 = PrivacyDialogController.this.onDialogDismissed;
                                        if (privacyDialog2.dismissed.get()) {
                                            PrivacyDialogController privacyDialogController6 = privacyDialogController$onDialogDismissed$1.this$0;
                                            privacyDialogController6.privacyLogger.logPrivacyDialogDismissed();
                                            privacyDialogController6.uiEventLogger.log(PrivacyDialogEvent.PRIVACY_DIALOG_DISMISSED);
                                            privacyDialogController6.dialog = null;
                                        } else {
                                            privacyDialog2.dismissListeners.add(new WeakReference(privacyDialogController$onDialogDismissed$1));
                                        }
                                        privacyDialog2.show();
                                        PrivacyDialogController.this.privacyLogger.logShowDialogContents(arrayList2);
                                        PrivacyDialogController.this.dialog = privacyDialog2;
                                    }
                                });
                            }
                        });
                        return;
                    }
                    if (!((FeatureFlagsClassicRelease) headerPrivacyIconsController2.featureFlags).isEnabled(Flags.ENABLE_NEW_PRIVACY_DIALOG)) {
                        HeaderPrivacyIconsController headerPrivacyIconsController3 = HeaderPrivacyIconsController.this;
                        headerPrivacyIconsController3.backgroundExecutor.execute(new HeaderPrivacyIconsController.AnonymousClass1(headerPrivacyIconsController3, 1));
                        return;
                    }
                    HeaderPrivacyIconsController headerPrivacyIconsController4 = HeaderPrivacyIconsController.this;
                    final PrivacyDialogControllerV2 privacyDialogControllerV2 = headerPrivacyIconsController4.privacyDialogControllerV2;
                    final Context context2 = headerPrivacyIconsController4.privacyChip.getContext();
                    final OngoingPrivacyChip ongoingPrivacyChip = HeaderPrivacyIconsController.this.privacyChip;
                    PrivacyDialogV2 privacyDialogV2 = privacyDialogControllerV2.dialog;
                    if (privacyDialogV2 != null) {
                        privacyDialogV2.dismiss();
                    }
                    privacyDialogControllerV2.backgroundExecutor.execute(new Runnable() { // from class: com.android.systemui.privacy.PrivacyDialogControllerV2$showDialog$1
                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Removed duplicated region for block: B:26:0x00fb  */
                        /* JADX WARN: Removed duplicated region for block: B:41:0x016e  */
                        /* JADX WARN: Removed duplicated region for block: B:50:0x01b8  */
                        /* JADX WARN: Removed duplicated region for block: B:53:0x01c3  */
                        /* JADX WARN: Removed duplicated region for block: B:61:0x01df  */
                        /* JADX WARN: Removed duplicated region for block: B:62:0x01bb  */
                        /* JADX WARN: Removed duplicated region for block: B:67:0x018d  */
                        /* JADX WARN: Removed duplicated region for block: B:73:0x00fe  */
                        /* JADX WARN: Type inference failed for: r17v0, types: [com.android.systemui.privacy.PrivacyType] */
                        /* JADX WARN: Type inference failed for: r7v8, types: [java.lang.CharSequence, java.lang.Object] */
                        @Override // java.lang.Runnable
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void run() {
                            /*
                                Method dump skipped, instructions count: 515
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.privacy.PrivacyDialogControllerV2$showDialog$1.run():void");
                        }
                    });
                }
            }
        };
        OngoingPrivacyChip ongoingPrivacyChip = headerPrivacyIconsController.privacyChip;
        ongoingPrivacyChip.setOnClickListener(onClickListener);
        headerPrivacyIconsController.setChipVisibility(ongoingPrivacyChip.getVisibility() == 0);
        PrivacyConfig privacyConfig = headerPrivacyIconsController.privacyItemController.privacyConfig;
        headerPrivacyIconsController.micCameraIndicatorsEnabled = privacyConfig.micCameraAvailable;
        headerPrivacyIconsController.locationIndicatorsEnabled = privacyConfig.locationAvailable;
        headerPrivacyIconsController.updatePrivacyIconSlots();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.privacyIconsController.chipVisibilityListener = this.chipVisibilityListener;
        updateVisibility$2();
        updateTransition();
        updateCarrierGroupPadding();
        this.header.setOnApplyWindowInsetsListener(this.insetListener);
        ShadeHeaderController$onViewAttached$1 shadeHeaderController$onViewAttached$1 = ShadeHeaderController$onViewAttached$1.INSTANCE;
        Clock clock = this.clock;
        clock.addOnLayoutChangeListener(shadeHeaderController$onViewAttached$1);
        clock.setOnClickListener(new ShadeHeaderController$onViewAttached$2(this, 0));
        this.dumpManager.registerDumpable(this);
        ((ConfigurationControllerImpl) this.configurationController).addCallback(this.configurationControllerListener);
        this.demoModeController.addCallback((DemoMode) this.demoModeReceiver);
        TintedIconManager tintedIconManager = this.iconManager;
        if (tintedIconManager == null) {
            tintedIconManager = null;
        }
        ((StatusBarIconControllerImpl) this.statusBarIconController).addIconGroup(tintedIconManager);
        ((NextAlarmControllerImpl) this.nextAlarmController).addCallback(this.nextAlarmCallback);
        View view = this.systemIconsHoverContainer;
        StatusOverlayHoverListenerFactory statusOverlayHoverListenerFactory = this.statusOverlayHoverListenerFactory;
        statusOverlayHoverListenerFactory.getClass();
        view.setOnHoverListener(new StatusOverlayHoverListener(view, statusOverlayHoverListenerFactory.configurationController, statusOverlayHoverListenerFactory.resources, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(HoverTheme.LIGHT)));
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.clock.setOnClickListener(null);
        this.privacyIconsController.chipVisibilityListener = null;
        this.dumpManager.unregisterDumpable("ShadeHeaderController");
        ((ConfigurationControllerImpl) this.configurationController).removeCallback(this.configurationControllerListener);
        this.demoModeController.removeCallback((DemoMode) this.demoModeReceiver);
        TintedIconManager tintedIconManager = this.iconManager;
        if (tintedIconManager == null) {
            tintedIconManager = null;
        }
        StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) this.statusBarIconController;
        statusBarIconControllerImpl.getClass();
        tintedIconManager.destroy();
        statusBarIconControllerImpl.mIconGroups.remove(tintedIconManager);
        ((NextAlarmControllerImpl) this.nextAlarmController).removeCallback(this.nextAlarmCallback);
        this.systemIconsHoverContainer.setOnHoverListener(null);
    }

    public final void simulateViewDetached$frameworks__base__packages__SystemUI__android_common__SystemUI_core() {
        onViewDetached();
    }

    public final void updateBatteryMode() {
        Integer num;
        DisplayCutout displayCutout = this.cutout;
        float f = this.qsExpandedFraction;
        QsBatteryModeController qsBatteryModeController = this.qsBatteryModeController;
        int i = 3;
        if (f > qsBatteryModeController.fadeInStartFraction) {
            num = 3;
        } else if (f < qsBatteryModeController.fadeOutCompleteFraction) {
            if (displayCutout != null && !qsBatteryModeController.insetsProvider.currentRotationHasCornerCutout() && !displayCutout.getBoundingRectTop().isEmpty()) {
                i = 1;
            }
            num = Integer.valueOf(i);
        } else {
            num = null;
        }
        if (num != null) {
            this.batteryIcon.setPercentShowMode(num.intValue());
        }
    }

    public final void updateCarrierGroupPadding() {
        Clock clock = this.clock;
        if (!clock.isLaidOut() || clock.isLayoutRequested()) {
            clock.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.shade.ShadeHeaderController$updateCarrierGroupPadding$$inlined$doOnLayout$1
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    view.removeOnLayoutChangeListener(this);
                    ShadeHeaderController.this.mShadeCarrierGroup.setPaddingRelative((int) (ShadeHeaderController.this.mView.getResources().getFloat(R.dimen.qqs_expand_clock_scale) * ShadeHeaderController.this.clock.getWidth()), 0, 0, 0);
                }
            });
            return;
        }
        this.mShadeCarrierGroup.setPaddingRelative((int) (this.mView.getResources().getFloat(R.dimen.qqs_expand_clock_scale) * clock.getWidth()), 0, 0, 0);
    }

    public final void updateIgnoredSlots() {
        List<String> list;
        boolean z;
        boolean z2 = this.singleCarrier;
        StatusIconContainer statusIconContainer = this.iconContainer;
        if (z2 || (!this.largeScreenActive && this.qsExpandedFraction < 0.5d)) {
            List list2 = this.carrierIconSlots;
            list = list2 != null ? list2 : null;
            statusIconContainer.getClass();
            Iterator it = list.iterator();
            boolean z3 = false;
            while (it.hasNext()) {
                z3 |= statusIconContainer.mIgnoredSlots.remove((String) it.next());
            }
            if (z3) {
                statusIconContainer.requestLayout();
                return;
            }
            return;
        }
        List list3 = this.carrierIconSlots;
        list = list3 != null ? list3 : null;
        statusIconContainer.getClass();
        boolean z4 = false;
        for (String str : list) {
            if (statusIconContainer.mIgnoredSlots.contains(str)) {
                z = false;
            } else {
                statusIconContainer.mIgnoredSlots.add(str);
                z = true;
            }
            z4 |= z;
        }
        if (z4) {
            statusIconContainer.requestLayout();
        }
    }

    public final void updatePosition$2() {
        if (this.largeScreenActive || !this.visible) {
            return;
        }
        Trace.instantForTrack(4096L, "LargeScreenHeaderController", "updatePosition: " + this.qsExpandedFraction);
        this.header.setProgress(this.qsExpandedFraction);
        updateBatteryMode();
    }

    public final void updateQQSPaddings() {
        int dimensionPixelSize = this.mView.getResources().getDimensionPixelSize(R.dimen.status_bar_left_clock_starting_padding);
        int dimensionPixelSize2 = this.mView.getResources().getDimensionPixelSize(R.dimen.status_bar_left_clock_end_padding);
        Clock clock = this.clock;
        clock.setPaddingRelative(dimensionPixelSize, clock.getPaddingTop(), dimensionPixelSize2, clock.getPaddingBottom());
    }

    public final void updateTransition() {
        boolean z = this.largeScreenActive;
        MotionLayout motionLayout = this.header;
        if (z) {
            Trace.instantForTrack(4096L, "LargeScreenHeaderController", "Large screen constraints set");
            motionLayout.setTransition(R.id.large_screen_header_transition);
            this.systemIconsHoverContainer.setClickable(true);
            this.systemIconsHoverContainer.setOnClickListener(new ShadeHeaderController$onViewAttached$2(this, 1));
        } else {
            Trace.instantForTrack(4096L, "LargeScreenHeaderController", "Small screen constraints set");
            motionLayout.setTransition(R.id.header_transition);
            this.systemIconsHoverContainer.setOnClickListener(null);
            this.systemIconsHoverContainer.setClickable(false);
        }
        int i = motionLayout.mBeginState;
        if (!motionLayout.isAttachedToWindow()) {
            motionLayout.mCurrentState = i;
        }
        if (motionLayout.mBeginState == i) {
            motionLayout.setProgress(0.0f);
        } else if (motionLayout.mEndState == i) {
            motionLayout.setProgress(1.0f);
        } else {
            motionLayout.setTransition(i, i);
        }
        updatePosition$2();
        if (this.largeScreenActive) {
            return;
        }
        motionLayout.setScrollY(this.qsScrollY);
    }

    public final void updateVisibility$2() {
        int i = this.qsDisabled ? 8 : (!this.qsVisible || this.customizing) ? 4 : 0;
        MotionLayout motionLayout = this.header;
        if (motionLayout.getVisibility() != i) {
            motionLayout.setVisibility(i);
            boolean z = i == 0;
            if (this.visible == z) {
                return;
            }
            this.visible = z;
            ShadeCarrierGroupController shadeCarrierGroupController = this.mShadeCarrierGroupController;
            if (shadeCarrierGroupController == null) {
                shadeCarrierGroupController = null;
            }
            if (z != shadeCarrierGroupController.mListening) {
                shadeCarrierGroupController.mListening = z;
                shadeCarrierGroupController.mBgHandler.post(new ShadeCarrierGroupController$$ExternalSyntheticLambda0(shadeCarrierGroupController, 0));
            }
            if (!this.visible) {
                ShadeCarrierGroupController shadeCarrierGroupController2 = this.mShadeCarrierGroupController;
                if (shadeCarrierGroupController2 == null) {
                    shadeCarrierGroupController2 = null;
                }
                shadeCarrierGroupController2.mOnSingleCarrierChangedListener = null;
                return;
            }
            ShadeCarrierGroupController shadeCarrierGroupController3 = this.mShadeCarrierGroupController;
            if (shadeCarrierGroupController3 == null) {
                shadeCarrierGroupController3 = null;
            }
            this.singleCarrier = shadeCarrierGroupController3.mIsSingleCarrier;
            updateIgnoredSlots();
            ShadeCarrierGroupController shadeCarrierGroupController4 = this.mShadeCarrierGroupController;
            (shadeCarrierGroupController4 != null ? shadeCarrierGroupController4 : null).mOnSingleCarrierChangedListener = new ShadeHeaderController$updateListeners$1(this);
        }
    }
}
