package com.android.systemui.dreams.homecontrols.domain.interactor;

import android.content.pm.UserInfo;
import android.os.UserHandle;
import com.android.systemui.controls.panels.SelectedComponentRepositoryImpl;
import com.android.systemui.util.kotlin.FlowKt$emitOnStart$1;
import com.android.systemui.util.kotlin.SharedPreferencesExt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ HomeControlsComponentInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1(HomeControlsComponentInteractor homeControlsComponentInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = homeControlsComponentInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1 homeControlsComponentInteractor$special$$inlined$flatMapLatest$1 = new HomeControlsComponentInteractor$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        homeControlsComponentInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        homeControlsComponentInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return homeControlsComponentInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            UserInfo userInfo = (UserInfo) this.L$1;
            final SelectedComponentRepositoryImpl selectedComponentRepositoryImpl = this.this$0.selectedComponentRepository;
            final UserHandle userHandle = userInfo.getUserHandle();
            selectedComponentRepositoryImpl.getClass();
            final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new FlowKt$emitOnStart$1(2, null), SharedPreferencesExt.observe(selectedComponentRepositoryImpl.getSharedPreferencesForUser(userHandle.getIdentifier())));
            Flow flowOn = FlowKt.flowOn(new Flow() { // from class: com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ UserHandle $userHandle$inlined;
                    public final /* synthetic */ SelectedComponentRepositoryImpl this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1$2$1, reason: invalid class name */
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

                    public AnonymousClass2(FlowCollector flowCollector, SelectedComponentRepositoryImpl selectedComponentRepositoryImpl, UserHandle userHandle) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = selectedComponentRepositoryImpl;
                        this.$userHandle$inlined = userHandle;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                        /*
                            r4 = this;
                            boolean r0 = r6 instanceof com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r6
                            com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1$2$1 r0 = (com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1$2$1 r0 = new com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1$2$1
                            r0.<init>(r6)
                        L18:
                            java.lang.Object r6 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L47
                        L27:
                            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                            r4.<init>(r5)
                            throw r4
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlin.Unit r5 = (kotlin.Unit) r5
                            android.os.UserHandle r5 = r4.$userHandle$inlined
                            com.android.systemui.controls.panels.SelectedComponentRepositoryImpl r6 = r4.this$0
                            com.android.systemui.controls.panels.SelectedComponentRepository$SelectedComponent r5 = r6.getSelectedComponent(r5)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                            java.lang.Object r4 = r4.emit(r5, r0)
                            if (r4 != r1) goto L47
                            return r1
                        L47:
                            kotlin.Unit r4 = kotlin.Unit.INSTANCE
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.controls.panels.SelectedComponentRepositoryImpl$selectedComponentFlow$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector2, Continuation continuation) {
                    Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector2, selectedComponentRepositoryImpl, userHandle), continuation);
                    return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }, selectedComponentRepositoryImpl.bgDispatcher);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowOn, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
