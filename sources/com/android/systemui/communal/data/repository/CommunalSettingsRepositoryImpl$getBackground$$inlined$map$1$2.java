package com.android.systemui.communal.data.repository;

import android.content.pm.UserInfo;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2 implements FlowCollector {
    public final /* synthetic */ FlowCollector $this_unsafeFlow;
    public final /* synthetic */ UserInfo $user$inlined;
    public final /* synthetic */ CommunalSettingsRepositoryImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2$1, reason: invalid class name */
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
            return CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2.this.emit(null, this);
        }
    }

    public CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2(FlowCollector flowCollector, CommunalSettingsRepositoryImpl communalSettingsRepositoryImpl, UserInfo userInfo) {
        this.$this_unsafeFlow = flowCollector;
        this.this$0 = communalSettingsRepositoryImpl;
        this.$user$inlined = userInfo;
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
            boolean r0 = r7 instanceof com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2$1 r0 = (com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2$1 r0 = new com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.ResultKt.throwOnFailure(r7)
            goto L7a
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2f:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.Unit r6 = (kotlin.Unit) r6
            com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl r6 = r5.this$0
            com.android.systemui.util.settings.SecureSettings r6 = r6.secureSettings
            com.android.systemui.communal.shared.model.CommunalBackgroundType r7 = com.android.systemui.communal.shared.model.CommunalBackgroundType.ANIMATED
            int r7 = r7.getValue()
            android.content.pm.UserInfo r2 = r5.$user$inlined
            int r2 = r2.id
            java.lang.String r4 = "glanceable_hub_background"
            int r6 = r6.getIntForUser(r4, r7, r2)
            kotlin.enums.EnumEntries r7 = com.android.systemui.communal.shared.model.CommunalBackgroundType.$ENTRIES
            kotlin.collections.AbstractList r7 = (kotlin.collections.AbstractList) r7
            r7.getClass()
            kotlin.collections.AbstractList$IteratorImpl r2 = new kotlin.collections.AbstractList$IteratorImpl
            r2.<init>()
        L54:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L68
            java.lang.Object r7 = r2.next()
            r4 = r7
            com.android.systemui.communal.shared.model.CommunalBackgroundType r4 = (com.android.systemui.communal.shared.model.CommunalBackgroundType) r4
            int r4 = r4.getValue()
            if (r4 != r6) goto L54
            goto L69
        L68:
            r7 = 0
        L69:
            com.android.systemui.communal.shared.model.CommunalBackgroundType r7 = (com.android.systemui.communal.shared.model.CommunalBackgroundType) r7
            if (r7 != 0) goto L6f
            com.android.systemui.communal.shared.model.CommunalBackgroundType r7 = com.android.systemui.communal.shared.model.CommunalBackgroundType.ANIMATED
        L6f:
            r0.label = r3
            kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
            java.lang.Object r5 = r5.emit(r7, r0)
            if (r5 != r1) goto L7a
            return r1
        L7a:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.communal.data.repository.CommunalSettingsRepositoryImpl$getBackground$$inlined$map$1$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
