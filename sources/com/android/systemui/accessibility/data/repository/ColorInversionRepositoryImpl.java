package com.android.systemui.accessibility.data.repository;

import android.os.UserHandle;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SettingsProxyExt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorInversionRepositoryImpl {
    public final CoroutineContext bgCoroutineContext;
    public final CoroutineScope scope;
    public final SecureSettings secureSettings;
    public final Map userMap = new LinkedHashMap();

    public ColorInversionRepositoryImpl(SecureSettings secureSettings, CoroutineContext coroutineContext, CoroutineScope coroutineScope) {
        this.bgCoroutineContext = coroutineContext;
        this.scope = coroutineScope;
        this.secureSettings = secureSettings;
    }

    public final Flow isEnabled(final UserHandle userHandle) {
        Map map = this.userMap;
        Integer valueOf = Integer.valueOf(userHandle.getIdentifier());
        Object obj = map.get(valueOf);
        if (obj == null) {
            final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new ColorInversionRepositoryImpl$isEnabled$1$1(2, null), SettingsProxyExt.observerFlow(this.secureSettings, userHandle.getIdentifier(), "accessibility_display_inversion_enabled"));
            obj = FlowKt.shareIn(FlowKt.flowOn(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;
                    public final /* synthetic */ UserHandle $userHandle$inlined;
                    public final /* synthetic */ ColorInversionRepositoryImpl this$0;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1$2$1, reason: invalid class name */
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

                    public AnonymousClass2(FlowCollector flowCollector, ColorInversionRepositoryImpl colorInversionRepositoryImpl, UserHandle userHandle) {
                        this.$this_unsafeFlow = flowCollector;
                        this.this$0 = colorInversionRepositoryImpl;
                        this.$userHandle$inlined = userHandle;
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
                            boolean r0 = r7 instanceof com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r7
                            com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1$2$1 r0 = (com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1$2$1 r0 = new com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1$2$1
                            r0.<init>(r7)
                        L18:
                            java.lang.Object r7 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r7)
                            goto L57
                        L27:
                            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                            r5.<init>(r6)
                            throw r5
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r7)
                            kotlin.Unit r6 = (kotlin.Unit) r6
                            com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl r6 = r5.this$0
                            com.android.systemui.util.settings.SecureSettings r6 = r6.secureSettings
                            android.os.UserHandle r7 = r5.$userHandle$inlined
                            int r7 = r7.getIdentifier()
                            java.lang.String r2 = "accessibility_display_inversion_enabled"
                            r4 = 0
                            int r6 = r6.getIntForUser(r2, r4, r7)
                            if (r6 != r3) goto L48
                            r4 = r3
                        L48:
                            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                            java.lang.Object r5 = r5.emit(r6, r0)
                            if (r5 != r1) goto L57
                            return r1
                        L57:
                            kotlin.Unit r5 = kotlin.Unit.INSTANCE
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl$isEnabled$lambda$1$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    Object collect = FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1.this.collect(new AnonymousClass2(flowCollector, this, userHandle), continuation);
                    return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                }
            }), this.bgCoroutineContext), this.scope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
            map.put(valueOf, obj);
        }
        return (Flow) obj;
    }

    public final Object setIsEnabled(boolean z, UserHandle userHandle, Continuation continuation) {
        return BuildersKt.withContext(this.bgCoroutineContext, new ColorInversionRepositoryImpl$setIsEnabled$2(this, z, userHandle, null), continuation);
    }
}
