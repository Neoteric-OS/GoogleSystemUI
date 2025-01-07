package com.android.systemui.statusbar.phone;

import android.R;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.kotlin.JavaAdapter;
import java.io.PrintWriter;
import java.util.function.Consumer;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarTouchableRegionManager implements Dumpable {
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public final Context mContext;
    public int mDisplayCutoutTouchableRegionSize;
    public final HeadsUpManager mHeadsUpManager;
    public View mNotificationPanelView;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public NotificationShadeWindowView mNotificationShadeWindowView;
    public final StatusBarTouchableRegionManager$$ExternalSyntheticLambda3 mOnComputeInternalInsetsListener;
    public final PrimaryBouncerInteractor mPrimaryBouncerInteractor;
    public int mStatusBarHeight;
    public final UnlockedScreenOffAnimationController mUnlockedScreenOffAnimationController;
    public boolean mIsStatusBarExpanded = false;
    public boolean mShouldAdjustInsets = false;
    public boolean mForceCollapsedUntilLayout = false;
    public final Region mTouchableRegion = new Region();

    /* JADX WARN: Type inference failed for: r2v7, types: [com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager$$ExternalSyntheticLambda3] */
    public StatusBarTouchableRegionManager(Context context, NotificationShadeWindowController notificationShadeWindowController, ConfigurationController configurationController, HeadsUpManager headsUpManager, ShadeInteractor shadeInteractor, JavaAdapter javaAdapter, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, PrimaryBouncerInteractor primaryBouncerInteractor, AlternateBouncerInteractor alternateBouncerInteractor) {
        this.mContext = context;
        initResources();
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager.1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                StatusBarTouchableRegionManager.this.initResources();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                StatusBarTouchableRegionManager.this.initResources();
            }
        });
        this.mHeadsUpManager = headsUpManager;
        ((BaseHeadsUpManager) headsUpManager).addListener(new OnHeadsUpChangedListener() { // from class: com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager.2
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpPinnedModeChanged(boolean z) {
                if (Log.isLoggable("TouchableRegionManager", 5)) {
                    Log.w("TouchableRegionManager", "onHeadsUpPinnedModeChanged");
                }
                StatusBarTouchableRegionManager.this.updateTouchableRegion();
            }
        });
        ((HeadsUpManagerPhone) headsUpManager).mHeadsUpPhoneListeners.add(new StatusBarTouchableRegionManager$$ExternalSyntheticLambda0(this));
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        ((NotificationShadeWindowControllerImpl) notificationShadeWindowController).mForcePluginOpenListener = new StatusBarTouchableRegionManager$$ExternalSyntheticLambda1(this);
        this.mUnlockedScreenOffAnimationController = unlockedScreenOffAnimationController;
        javaAdapter.alwaysCollectFlow(((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isAnyExpanded(), new Consumer() { // from class: com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StatusBarTouchableRegionManager statusBarTouchableRegionManager = StatusBarTouchableRegionManager.this;
                Boolean bool = (Boolean) obj;
                statusBarTouchableRegionManager.getClass();
                if (bool.booleanValue() != statusBarTouchableRegionManager.mIsStatusBarExpanded) {
                    statusBarTouchableRegionManager.mIsStatusBarExpanded = bool.booleanValue();
                    if (bool.booleanValue()) {
                        statusBarTouchableRegionManager.mForceCollapsedUntilLayout = false;
                    }
                    statusBarTouchableRegionManager.updateTouchableRegion();
                }
            }
        });
        this.mPrimaryBouncerInteractor = primaryBouncerInteractor;
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mOnComputeInternalInsetsListener = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager$$ExternalSyntheticLambda3
            public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                StatusBarTouchableRegionManager statusBarTouchableRegionManager = StatusBarTouchableRegionManager.this;
                if (statusBarTouchableRegionManager.shouldMakeEntireScreenTouchable()) {
                    return;
                }
                internalInsetsInfo.setTouchableInsets(3);
                internalInsetsInfo.touchableRegion.set(statusBarTouchableRegionManager.calculateTouchableRegion());
            }
        };
    }

    public final Region calculateTouchableRegion() {
        HeadsUpManagerPhone headsUpManagerPhone = (HeadsUpManagerPhone) this.mHeadsUpManager;
        BaseHeadsUpManager.HeadsUpEntry topHeadsUpEntry = headsUpManagerPhone.getTopHeadsUpEntry();
        Region region = null;
        NotificationEntry notificationEntry = topHeadsUpEntry != null ? topHeadsUpEntry.mEntry : null;
        if (headsUpManagerPhone.mHasPinnedNotification && notificationEntry != null) {
            ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
            if (expandableNotificationRow != null && expandableNotificationRow.isChildInGroup()) {
                headsUpManagerPhone.mGroupMembershipManager.getClass();
                NotificationEntry groupSummary = GroupMembershipManagerImpl.getGroupSummary(notificationEntry);
                if (groupSummary != null) {
                    notificationEntry = groupSummary;
                }
            }
            ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
            int[] iArr = new int[2];
            expandableNotificationRow2.getLocationOnScreen(iArr);
            int i = iArr[0];
            int width = expandableNotificationRow2.getWidth() + i;
            int intrinsicHeight = expandableNotificationRow2.getIntrinsicHeight();
            int i2 = iArr[1];
            headsUpManagerPhone.mTouchableRegion.set(i, i2 <= headsUpManagerPhone.mHeadsUpInset ? 0 : i2, width, i2 + intrinsicHeight);
            region = headsUpManagerPhone.mTouchableRegion;
        }
        if (region != null) {
            this.mTouchableRegion.set(region);
        } else {
            this.mTouchableRegion.set(0, 0, this.mNotificationShadeWindowView.getWidth(), this.mStatusBarHeight);
            updateRegionForNotch(this.mTouchableRegion);
        }
        return this.mTouchableRegion;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("StatusBarTouchableRegionManager state:");
        printWriter.print("  mTouchableRegion=");
        printWriter.println(this.mTouchableRegion);
    }

    public final void initResources() {
        this.mDisplayCutoutTouchableRegionSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.edit_text_inset_horizontal_material);
        this.mStatusBarHeight = SystemBarUtils.getStatusBarHeight(this.mContext);
    }

    public boolean shouldMakeEntireScreenTouchable() {
        return this.mIsStatusBarExpanded || ((Boolean) ((StateFlowImpl) this.mPrimaryBouncerInteractor.isShowing.$$delegate_0).getValue()).booleanValue() || this.mAlternateBouncerInteractor.isVisibleState() || this.mUnlockedScreenOffAnimationController.lightRevealAnimationPlaying;
    }

    public final void updateRegionForNotch(Region region) {
        WindowInsets rootWindowInsets = this.mNotificationShadeWindowView.getRootWindowInsets();
        if (rootWindowInsets == null) {
            Log.w("TouchableRegionManager", "StatusBarWindowView is not attached.");
            return;
        }
        DisplayCutout displayCutout = rootWindowInsets.getDisplayCutout();
        if (displayCutout == null) {
            return;
        }
        Rect rect = new Rect();
        ScreenDecorations.DisplayCutoutView.boundsFromDirection(48, rect, displayCutout);
        rect.offset(0, this.mDisplayCutoutTouchableRegionSize);
        region.union(rect);
    }

    public final void updateTouchableRegion() {
        NotificationShadeWindowView notificationShadeWindowView = this.mNotificationShadeWindowView;
        boolean z = (notificationShadeWindowView == null || notificationShadeWindowView.getRootWindowInsets() == null || this.mNotificationShadeWindowView.getRootWindowInsets().getDisplayCutout() == null) ? false : true;
        HeadsUpManager headsUpManager = this.mHeadsUpManager;
        boolean z2 = ((BaseHeadsUpManager) headsUpManager).mHasPinnedNotification || ((Boolean) ((HeadsUpManagerPhone) headsUpManager).mHeadsUpAnimatingAway.getValue()).booleanValue() || this.mForceCollapsedUntilLayout || z || ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mCurrentState.forcePluginOpen;
        if (z2 == this.mShouldAdjustInsets) {
            return;
        }
        StatusBarTouchableRegionManager$$ExternalSyntheticLambda3 statusBarTouchableRegionManager$$ExternalSyntheticLambda3 = this.mOnComputeInternalInsetsListener;
        if (z2) {
            this.mNotificationShadeWindowView.getViewTreeObserver().addOnComputeInternalInsetsListener(statusBarTouchableRegionManager$$ExternalSyntheticLambda3);
            this.mNotificationShadeWindowView.requestLayout();
        } else {
            this.mNotificationShadeWindowView.getViewTreeObserver().removeOnComputeInternalInsetsListener(statusBarTouchableRegionManager$$ExternalSyntheticLambda3);
        }
        this.mShouldAdjustInsets = z2;
    }
}
