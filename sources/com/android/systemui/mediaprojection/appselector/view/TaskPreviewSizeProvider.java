package com.android.systemui.mediaprojection.appselector.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.view.WindowInsets;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskPreviewSizeProvider implements CallbackController, ConfigurationController.ConfigurationListener, DefaultLifecycleObserver {
    public final ConfigurationController configurationController;
    public final Context context;
    public final WindowMetricsProviderImpl windowMetricsProvider;
    public final Rect size = calculateSize();
    public final ArrayList listeners = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TaskPreviewSizeListener {
        void onTaskSizeChanged();
    }

    public TaskPreviewSizeProvider(Context context, WindowMetricsProviderImpl windowMetricsProviderImpl, ConfigurationController configurationController) {
        this.context = context;
        this.windowMetricsProvider = windowMetricsProviderImpl;
        this.configurationController = configurationController;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        this.listeners.add((TaskPreviewSizeListener) obj);
    }

    public final Rect calculateSize() {
        WindowMetricsProviderImpl windowMetricsProviderImpl = this.windowMetricsProvider;
        Rect bounds = windowMetricsProviderImpl.windowManager.getMaximumWindowMetrics().getBounds();
        int height = bounds.height();
        int width = bounds.width();
        int i = Utilities.isLargeScreen(this.context) ? height - windowMetricsProviderImpl.windowManager.getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.tappableElement()).bottom : height;
        Rect rect = new Rect(0, 0, width, i);
        rect.scale((i / height) / 4.0f);
        return rect;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        Rect calculateSize = calculateSize();
        if (calculateSize.equals(this.size)) {
            return;
        }
        this.size.set(calculateSize);
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((TaskPreviewSizeListener) it.next()).onTaskSizeChanged();
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onCreate(LifecycleOwner lifecycleOwner) {
        ((ConfigurationControllerImpl) this.configurationController).addCallback(this);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onDestroy$1() {
        ((ConfigurationControllerImpl) this.configurationController).removeCallback(this);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.listeners.remove((TaskPreviewSizeListener) obj);
    }
}
