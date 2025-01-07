package com.android.systemui.keyboard.stickykeys.data.repository;

import android.hardware.input.InputManager;
import com.android.systemui.keyboard.stickykeys.StickyKeysLogger;
import com.android.systemui.util.settings.repository.UserAwareSecureSettingsRepositoryImpl;
import com.android.systemui.util.settings.repository.UserAwareSecureSettingsRepositoryImpl$boolSettingForActiveUser$$inlined$flatMapLatest$1;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StickyKeysRepositoryImpl implements StickyKeysRepository {
    public final InputManager inputManager;
    public final Flow settingEnabled;
    public final Flow stickyKeys;
    public final StickyKeysLogger stickyKeysLogger;

    public StickyKeysRepositoryImpl(InputManager inputManager, CoroutineDispatcher coroutineDispatcher, UserAwareSecureSettingsRepositoryImpl userAwareSecureSettingsRepositoryImpl, StickyKeysLogger stickyKeysLogger) {
        this.inputManager = inputManager;
        this.stickyKeysLogger = stickyKeysLogger;
        final Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new StickyKeysRepositoryImpl$stickyKeys$1(this, null));
        this.stickyKeys = FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ StickyKeysRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, StickyKeysRepositoryImpl stickyKeysRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = stickyKeysRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto Leb
                    L28:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L30:
                        kotlin.ResultKt.throwOnFailure(r8)
                        android.hardware.input.StickyModifierState r7 = (android.hardware.input.StickyModifierState) r7
                        com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl r8 = r6.this$0
                        r8.getClass()
                        java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
                        r8.<init>()
                        boolean r2 = r7.isAltGrModifierOn()
                        r4 = 0
                        if (r2 == 0) goto L50
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.ALT_GR
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r4)
                        r8.put(r2, r5)
                    L50:
                        boolean r2 = r7.isAltGrModifierLocked()
                        if (r2 == 0) goto L60
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.ALT_GR
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r3)
                        r8.put(r2, r5)
                    L60:
                        boolean r2 = r7.isAltModifierOn()
                        if (r2 == 0) goto L70
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.ALT
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r4)
                        r8.put(r2, r5)
                    L70:
                        boolean r2 = r7.isAltModifierLocked()
                        if (r2 == 0) goto L80
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.ALT
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r3)
                        r8.put(r2, r5)
                    L80:
                        boolean r2 = r7.isCtrlModifierOn()
                        if (r2 == 0) goto L90
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.CTRL
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r4)
                        r8.put(r2, r5)
                    L90:
                        boolean r2 = r7.isCtrlModifierLocked()
                        if (r2 == 0) goto La0
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.CTRL
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r3)
                        r8.put(r2, r5)
                    La0:
                        boolean r2 = r7.isMetaModifierOn()
                        if (r2 == 0) goto Lb0
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.META
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r4)
                        r8.put(r2, r5)
                    Lb0:
                        boolean r2 = r7.isMetaModifierLocked()
                        if (r2 == 0) goto Lc0
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.META
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r3)
                        r8.put(r2, r5)
                    Lc0:
                        boolean r2 = r7.isShiftModifierOn()
                        if (r2 == 0) goto Ld0
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r2 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.SHIFT
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r5 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r5.<init>(r4)
                        r8.put(r2, r5)
                    Ld0:
                        boolean r7 = r7.isShiftModifierLocked()
                        if (r7 == 0) goto Le0
                        com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey r7 = com.android.systemui.keyboard.stickykeys.shared.model.ModifierKey.SHIFT
                        com.android.systemui.keyboard.stickykeys.shared.model.Locked r2 = new com.android.systemui.keyboard.stickykeys.shared.model.Locked
                        r2.<init>(r3)
                        r8.put(r7, r2)
                    Le0:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r8, r0)
                        if (r6 != r1) goto Leb
                        return r1
                    Leb:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }, new StickyKeysRepositoryImpl$stickyKeys$3(this, null), 0), coroutineDispatcher);
        this.settingEnabled = FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.flowOn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(userAwareSecureSettingsRepositoryImpl.userRepository.selectedUserInfo, new UserAwareSecureSettingsRepositoryImpl$boolSettingForActiveUser$$inlined$flatMapLatest$1(null, userAwareSecureSettingsRepositoryImpl, "accessibility_sticky_keys", false))), userAwareSecureSettingsRepositoryImpl.backgroundDispatcher), new StickyKeysRepositoryImpl$settingEnabled$1(this, null), 0), coroutineDispatcher);
    }
}
