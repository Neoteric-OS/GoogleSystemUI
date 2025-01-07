package com.google.android.systemui.columbus;

import com.google.android.systemui.columbus.fetchers.ColumbusSettingsFetcher;
import dagger.Lazy;
import java.io.PrintWriter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusStarterImpl implements ColumbusStarter {
    public final Lazy columbusManager;
    public boolean started;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.columbus.ColumbusStarterImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ColumbusSettingsFetcher $columbusSettingsFetcher;
        int label;
        final /* synthetic */ ColumbusStarterImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.google.android.systemui.columbus.ColumbusStarterImpl$1$1, reason: invalid class name and collision with other inner class name */
        final class C02731 extends SuspendLambda implements Function2 {
            /* synthetic */ boolean Z$0;
            int label;

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C02731 c02731 = new C02731(2, continuation);
                c02731.Z$0 = ((Boolean) obj).booleanValue();
                return c02731;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                return ((C02731) create(bool, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Boolean.valueOf(this.Z$0);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ColumbusSettingsFetcher columbusSettingsFetcher, ColumbusStarterImpl columbusStarterImpl, Continuation continuation) {
            super(2, continuation);
            this.$columbusSettingsFetcher = columbusSettingsFetcher;
            this.this$0 = columbusStarterImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$columbusSettingsFetcher, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = this.$columbusSettingsFetcher.columbusEnabled;
                C02731 c02731 = new C02731(2, null);
                this.label = 1;
                if (FlowKt.first(stateFlow, c02731, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ColumbusStarterImpl columbusStarterImpl = this.this$0;
            columbusStarterImpl.started = true;
            columbusStarterImpl.columbusManager.get();
            return Unit.INSTANCE;
        }
    }

    public ColumbusStarterImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Lazy lazy, ColumbusSettingsFetcher columbusSettingsFetcher) {
        this.columbusManager = lazy;
        BuildersKt.launch$default(coroutineScope, coroutineDispatcher, null, new AnonymousClass1(columbusSettingsFetcher, this, null), 2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        if (this.started) {
            ((ColumbusManager) this.columbusManager.get()).dump(printWriter, strArr);
        }
    }
}
