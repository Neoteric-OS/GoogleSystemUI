package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.NotificationIconContainer;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.android.systemui.util.ui.AnimatedValue;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodNotificationIconsSection extends KeyguardSection {
    public final ConfigurationState configurationState;
    public final Context context;
    public final StatusBarIconViewBindingFailureTracker iconBindingFailureTracker;
    public NotificationIconContainer nic;
    public final AlwaysOnDisplayNotificationIconViewStore nicAodIconViewStore;
    public final NotificationIconContainerAlwaysOnDisplayViewModel nicAodViewModel;
    public RepeatWhenAttachedKt$repeatWhenAttached$1 nicBindingDisposable;
    public final KeyguardRootViewModel rootViewModel;
    public final SystemBarUtilsState systemBarUtilsState;

    public AodNotificationIconsSection(Context context, ConfigurationState configurationState, StatusBarIconViewBindingFailureTracker statusBarIconViewBindingFailureTracker, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel, AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore, SystemBarUtilsState systemBarUtilsState, KeyguardRootViewModel keyguardRootViewModel) {
        this.context = context;
        this.configurationState = configurationState;
        this.iconBindingFailureTracker = statusBarIconViewBindingFailureTracker;
        this.nicAodViewModel = notificationIconContainerAlwaysOnDisplayViewModel;
        this.nicAodIconViewStore = alwaysOnDisplayNotificationIconViewStore;
        this.systemBarUtilsState = systemBarUtilsState;
        this.rootViewModel = keyguardRootViewModel;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        NotificationIconContainer notificationIconContainer = new NotificationIconContainer(this.context, null);
        notificationIconContainer.setId(R.id.aod_notification_icon_container);
        notificationIconContainer.setPaddingRelative(notificationIconContainer.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_start_icons), 0, 0, 0);
        notificationIconContainer.setVisibility(4);
        this.nic = notificationIconContainer;
        constraintLayout.addView(notificationIconContainer);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        Object obj;
        int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.keyguard_status_view_bottom_margin);
        AnimatedValue animatedValue = (AnimatedValue) ((StateFlowImpl) this.rootViewModel.isNotifIconContainerVisible.$$delegate_0).getValue();
        constraintSet.connect(R.id.aod_notification_icon_container, 3, R.id.smart_space_barrier_bottom, 4, dimensionPixelSize);
        constraintSet.setGoneMargin(R.id.aod_notification_icon_container, 4, dimensionPixelSize);
        if (animatedValue instanceof AnimatedValue.Animating) {
            obj = ((AnimatedValue.Animating) animatedValue).value;
        } else {
            if (!(animatedValue instanceof AnimatedValue.NotAnimating)) {
                throw new NoWhenBranchMatchedException();
            }
            obj = ((AnimatedValue.NotAnimating) animatedValue).value;
        }
        constraintSet.setVisibility(R.id.aod_notification_icon_container, ((Boolean) obj).booleanValue() ? 0 : 8);
        constraintSet.connect(R.id.aod_notification_icon_container, 6, 0, 6, this.context.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal));
        constraintSet.connect(R.id.aod_notification_icon_container, 7, 0, 7, this.context.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal));
        constraintSet.constrainHeight(R.id.aod_notification_icon_container, this.context.getResources().getDimensionPixelSize(R.dimen.notification_shelf_height));
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.nicBindingDisposable;
        if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
        }
        NotificationIconContainer notificationIconContainer = this.nic;
        if (notificationIconContainer == null) {
            notificationIconContainer = null;
        }
        AlwaysOnDisplayNotificationIconViewStore alwaysOnDisplayNotificationIconViewStore = this.nicAodIconViewStore;
        this.nicBindingDisposable = NotificationIconContainerViewBinder.bindWhileAttached(notificationIconContainer, this.nicAodViewModel, this.configurationState, this.systemBarUtilsState, this.iconBindingFailureTracker, alwaysOnDisplayNotificationIconViewStore);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.aod_notification_icon_container);
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.nicBindingDisposable;
        if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
        }
    }
}
