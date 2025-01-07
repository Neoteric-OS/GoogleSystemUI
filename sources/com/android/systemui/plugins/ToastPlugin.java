package com.android.systemui.plugins;

import android.animation.Animator;
import android.view.View;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@ProvidesInterface(action = ToastPlugin.ACTION, version = 1)
/* loaded from: classes.dex */
public interface ToastPlugin extends Plugin {
    public static final String ACTION = "com.android.systemui.action.PLUGIN_TOAST";
    public static final int VERSION = 1;

    Toast createToast(CharSequence charSequence, String str, int i);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Toast {
        default Integer getGravity() {
            return null;
        }

        default Integer getHorizontalMargin() {
            return null;
        }

        default Animator getInAnimation() {
            return null;
        }

        default Animator getOutAnimation() {
            return null;
        }

        default Integer getVerticalMargin() {
            return null;
        }

        default View getView() {
            return null;
        }

        default Integer getXOffset() {
            return null;
        }

        default Integer getYOffset() {
            return null;
        }

        default void onOrientationChange(int i) {
        }
    }
}
