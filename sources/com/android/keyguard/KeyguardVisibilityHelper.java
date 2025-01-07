package com.android.keyguard;

import android.view.View;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardVisibilityHelper {
    public final LogBuffer mLogBuffer;
    public final AnonymousClass1 mSetGoneEndAction;
    public final AnonymousClass1 mSetInvisibleEndAction;
    public final KeyguardVisibilityHelper$$ExternalSyntheticLambda0 mSetVisibleEndRunnable;
    public final View mView;

    public KeyguardVisibilityHelper(View view, KeyguardStateController keyguardStateController, ScreenOffAnimationController screenOffAnimationController, boolean z, LogBuffer logBuffer) {
        final int i = 0;
        new Consumer(this) { // from class: com.android.keyguard.KeyguardVisibilityHelper.1
            public final /* synthetic */ KeyguardVisibilityHelper this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                switch (i) {
                    case 0:
                        KeyguardVisibilityHelper keyguardVisibilityHelper = this.this$0;
                        keyguardVisibilityHelper.getClass();
                        keyguardVisibilityHelper.mView.setVisibility(4);
                        this.this$0.log("Callback Set Visibility to INVISIBLE");
                        break;
                    default:
                        KeyguardVisibilityHelper keyguardVisibilityHelper2 = this.this$0;
                        keyguardVisibilityHelper2.getClass();
                        keyguardVisibilityHelper2.mView.setVisibility(8);
                        this.this$0.log("CallbackSet Visibility to GONE");
                        break;
                }
            }
        };
        final int i2 = 1;
        new Consumer(this) { // from class: com.android.keyguard.KeyguardVisibilityHelper.1
            public final /* synthetic */ KeyguardVisibilityHelper this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                switch (i2) {
                    case 0:
                        KeyguardVisibilityHelper keyguardVisibilityHelper = this.this$0;
                        keyguardVisibilityHelper.getClass();
                        keyguardVisibilityHelper.mView.setVisibility(4);
                        this.this$0.log("Callback Set Visibility to INVISIBLE");
                        break;
                    default:
                        KeyguardVisibilityHelper keyguardVisibilityHelper2 = this.this$0;
                        keyguardVisibilityHelper2.getClass();
                        keyguardVisibilityHelper2.mView.setVisibility(8);
                        this.this$0.log("CallbackSet Visibility to GONE");
                        break;
                }
            }
        };
        new Runnable() { // from class: com.android.keyguard.KeyguardVisibilityHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                KeyguardVisibilityHelper keyguardVisibilityHelper = KeyguardVisibilityHelper.this;
                keyguardVisibilityHelper.getClass();
                keyguardVisibilityHelper.mView.setVisibility(0);
                keyguardVisibilityHelper.log("Callback Set Visibility to VISIBLE");
            }
        };
        this.mView = view;
        this.mLogBuffer = logBuffer;
    }

    public final void log(String str) {
        LogBuffer logBuffer = this.mLogBuffer;
        if (logBuffer != null) {
            logBuffer.log("KeyguardVisibilityHelper", LogLevel.DEBUG, str, null);
        }
    }
}
