package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NotificationStackScrollLayoutSection extends KeyguardSection {
    public final Context context;
    public DisposableHandles disposableHandle;
    public final NotificationPanelView notificationPanelView;
    public final SharedNotificationContainer sharedNotificationContainer;
    public final SharedNotificationContainerBinder sharedNotificationContainerBinder;
    public final SharedNotificationContainerViewModel sharedNotificationContainerViewModel;

    public NotificationStackScrollLayoutSection(Context context, NotificationPanelView notificationPanelView, SharedNotificationContainer sharedNotificationContainer, SharedNotificationContainerViewModel sharedNotificationContainerViewModel, SharedNotificationContainerBinder sharedNotificationContainerBinder) {
        this.context = context;
        this.notificationPanelView = notificationPanelView;
        this.sharedNotificationContainer = sharedNotificationContainer;
        this.sharedNotificationContainerViewModel = sharedNotificationContainerViewModel;
        this.sharedNotificationContainerBinder = sharedNotificationContainerBinder;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        View findViewById = this.notificationPanelView.findViewById(R.id.notification_stack_scroller);
        if (findViewById != null) {
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            this.sharedNotificationContainer.addView(findViewById);
        }
        View view = new View(this.context, null);
        view.setId(R.id.nssl_placeholder);
        constraintLayout.addView(view);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        DisposableHandles disposableHandles = this.disposableHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
        this.disposableHandle = this.sharedNotificationContainerBinder.bind(this.sharedNotificationContainer, this.sharedNotificationContainerViewModel);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        DisposableHandles disposableHandles = this.disposableHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
        this.disposableHandle = null;
        ExtensionsKt.removeView(constraintLayout, R.id.nssl_placeholder);
    }
}
