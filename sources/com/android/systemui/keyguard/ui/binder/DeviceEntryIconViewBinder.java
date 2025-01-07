package com.android.systemui.keyguard.ui.binder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.compose.ui.graphics.Color;
import com.android.systemui.common.ui.view.LongPressHandlingView;
import com.android.systemui.keyguard.ui.view.DeviceEntryIconView;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryBackgroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.util.kotlin.DisposableHandles;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DeviceEntryIconViewBinder {
    /* renamed from: bind-9Oi015Q, reason: not valid java name */
    public static final DisposableHandles m828bind9Oi015Q(final CoroutineScope coroutineScope, DeviceEntryIconView deviceEntryIconView, final DeviceEntryIconViewModel deviceEntryIconViewModel, DeviceEntryForegroundViewModel deviceEntryForegroundViewModel, DeviceEntryBackgroundViewModel deviceEntryBackgroundViewModel, final FalsingManager falsingManager, final VibratorHelper vibratorHelper, Color color) {
        DisposableHandles disposableHandles = new DisposableHandles();
        LongPressHandlingView longPressHandlingView = deviceEntryIconView.longPressHandlingView;
        ImageView imageView = deviceEntryIconView.iconView;
        ImageView imageView2 = deviceEntryIconView.bgView;
        longPressHandlingView.listener = new LongPressHandlingView.Listener() { // from class: com.android.systemui.keyguard.ui.binder.DeviceEntryIconViewBinder$bind$1
            @Override // com.android.systemui.common.ui.view.LongPressHandlingView.Listener
            public final void onLongPressDetected(View view, boolean z) {
                if (!z && FalsingManager.this.isFalseLongTap(1)) {
                    Log.d("DeviceEntryIconViewBinder", "Long press rejected because it is not a11yAction and it is a falseLongTap");
                    return;
                }
                vibratorHelper.getClass();
                view.performHapticFeedback(16);
                BuildersKt.launch$default(coroutineScope, null, null, new DeviceEntryIconViewBinder$bind$1$onLongPressDetected$1(view, deviceEntryIconViewModel, null), 3);
            }
        };
        DeviceEntryIconViewBinder$bind$2 deviceEntryIconViewBinder$bind$2 = new DeviceEntryIconViewBinder$bind$2(deviceEntryIconViewModel, longPressHandlingView, deviceEntryIconView, vibratorHelper, coroutineScope, imageView2, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(deviceEntryIconView, emptyCoroutineContext, deviceEntryIconViewBinder$bind$2));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(imageView, emptyCoroutineContext, new DeviceEntryIconViewBinder$bind$3(imageView, color, deviceEntryIconView, deviceEntryForegroundViewModel, null)));
        disposableHandles.plusAssign(RepeatWhenAttachedKt.repeatWhenAttached(imageView2, emptyCoroutineContext, new DeviceEntryIconViewBinder$bind$4(deviceEntryBackgroundViewModel, imageView2, null)));
        return disposableHandles;
    }
}
