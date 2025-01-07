package com.android.systemui.media.controls.ui.animation;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationBindHandler extends Animatable2.AnimationCallback {
    public Integer rebindId;
    public final List onAnimationsComplete = new ArrayList();
    public final List registrations = new ArrayList();

    public final boolean isAnimationRunning() {
        List list = this.registrations;
        if (list != null && list.isEmpty()) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((Animatable2) it.next()).isRunning()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.graphics.drawable.Animatable2.AnimationCallback
    public final void onAnimationEnd(Drawable drawable) {
        super.onAnimationEnd(drawable);
        if (isAnimationRunning()) {
            return;
        }
        Iterator it = this.onAnimationsComplete.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
        this.onAnimationsComplete.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void tryRegister(Drawable drawable) {
        if (drawable instanceof Animatable2) {
            Animatable2 animatable2 = (Animatable2) drawable;
            animatable2.registerAnimationCallback(this);
            this.registrations.add(animatable2);
        }
    }

    public final void unregisterAll() {
        Iterator it = this.registrations.iterator();
        while (it.hasNext()) {
            ((Animatable2) it.next()).unregisterAnimationCallback(this);
        }
        this.registrations.clear();
    }
}
