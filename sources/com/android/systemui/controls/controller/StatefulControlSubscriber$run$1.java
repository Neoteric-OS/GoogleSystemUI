package com.android.systemui.controls.controller;

import android.service.controls.IControlsSubscription;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatefulControlSubscriber$run$1 implements Runnable {
    public final /* synthetic */ Object $f;
    public final /* synthetic */ int $r8$classId = 1;

    public StatefulControlSubscriber$run$1(StatefulControlSubscriber statefulControlSubscriber) {
        this.$f = statefulControlSubscriber;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((Lambda) this.$f).invoke();
                break;
            default:
                StatefulControlSubscriber statefulControlSubscriber = (StatefulControlSubscriber) this.$f;
                if (statefulControlSubscriber.subscriptionOpen) {
                    statefulControlSubscriber.subscriptionOpen = false;
                    IControlsSubscription iControlsSubscription = statefulControlSubscriber.subscription;
                    if (iControlsSubscription != null) {
                        statefulControlSubscriber.provider.cancelSubscription(iControlsSubscription);
                    }
                    ((StatefulControlSubscriber) this.$f).subscription = null;
                    break;
                }
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public StatefulControlSubscriber$run$1(Function0 function0) {
        this.$f = (Lambda) function0;
    }
}
