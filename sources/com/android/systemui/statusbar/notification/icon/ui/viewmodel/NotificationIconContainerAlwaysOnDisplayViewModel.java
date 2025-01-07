package com.android.systemui.statusbar.notification.icon.ui.viewmodel;

import android.content.res.Resources;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.icon.domain.interactor.AlwaysOnDisplayNotificationIconsInteractor;
import com.android.wm.shell.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconContainerAlwaysOnDisplayViewModel {
    public final Flow areContainerChangesAnimated;
    public final Flow areIconAnimationsEnabled;
    public final Flow icons;
    public final int maxIcons;
    public final Flow tintAlpha;

    public NotificationIconContainerAlwaysOnDisplayViewModel(CoroutineContext coroutineContext, AlwaysOnDisplayNotificationIconsInteractor alwaysOnDisplayNotificationIconsInteractor, KeyguardInteractor keyguardInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, Resources resources, ShadeInteractor shadeInteractor) {
        this.maxIcons = resources.getInteger(R.integer.max_notif_icons_on_aod);
        this.areContainerChangesAnimated = FlowKt.distinctUntilChanged(FlowKt.buffer$default(FlowKt.flowOn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((ShadeInteractorImpl) shadeInteractor).isShadeTouchable, keyguardInteractor.isKeyguardVisible, new NotificationIconContainerAlwaysOnDisplayViewModel$areContainerChangesAnimated$1(3, null)), coroutineContext), -1));
        this.tintAlpha = FlowKt.distinctUntilChanged(FlowKt.buffer$default(FlowKt.flowOn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NotificationIconContainerAlwaysOnDisplayViewModel$tintAlpha$1(2, null), keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.AOD)), new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NotificationIconContainerAlwaysOnDisplayViewModel$tintAlpha$2(2, null), keyguardTransitionInteractor.getTransitionValueFlow(KeyguardState.DOZING)), new NotificationIconContainerAlwaysOnDisplayViewModel$tintAlpha$3(3, null)), coroutineContext), -1));
        this.areIconAnimationsEnabled = FlowKt.distinctUntilChanged(FlowKt.buffer$default(FlowKt.flowOn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new NotificationIconContainerAlwaysOnDisplayViewModel$areIconAnimationsEnabled$2(2, null), FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2(keyguardTransitionInteractor.finishedKeyguardState, new Function1() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$areIconAnimationsEnabled$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                KeyguardState keyguardState = (KeyguardState) obj;
                return Boolean.valueOf((keyguardState == KeyguardState.AOD || keyguardState == KeyguardState.DOZING) ? false : true);
            }
        }, 1))), coroutineContext), -1));
        final Flow flow = alwaysOnDisplayNotificationIconsInteractor.aodNotifs;
        this.icons = FlowKt.distinctUntilChanged(FlowKt.buffer$default(FlowKt.flowOn(new Flow() { // from class: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ NotificationIconContainerAlwaysOnDisplayViewModel this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, NotificationIconContainerAlwaysOnDisplayViewModel notificationIconContainerAlwaysOnDisplayViewModel) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = notificationIconContainerAlwaysOnDisplayViewModel;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L6c
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.util.Set r6 = (java.util.Set) r6
                        java.lang.Iterable r6 = (java.lang.Iterable) r6
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L3f:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L57
                        java.lang.Object r2 = r6.next()
                        com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r2 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r2
                        android.graphics.drawable.Icon r4 = r2.aodIcon
                        com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconInfo r2 = com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewDataKt.toIconInfo(r2, r4)
                        if (r2 == 0) goto L3f
                        r7.add(r2)
                        goto L3f
                    L57:
                        com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel r6 = r5.this$0
                        int r6 = r6.maxIcons
                        com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData r2 = new com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconsViewData
                        r4 = 4
                        r2.<init>(r6, r4, r7)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r2, r0)
                        if (r5 != r1) goto L6c
                        return r1
                    L6c:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, coroutineContext), -1));
    }
}
