package com.android.systemui.shared.settings.data.repository;

import android.database.ContentObserver;
import android.provider.Settings;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SecureSettingsRepositoryImpl$intSetting$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $name;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ SecureSettingsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SecureSettingsRepositoryImpl$intSetting$1(SecureSettingsRepositoryImpl secureSettingsRepositoryImpl, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = secureSettingsRepositoryImpl;
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SecureSettingsRepositoryImpl$intSetting$1 secureSettingsRepositoryImpl$intSetting$1 = new SecureSettingsRepositoryImpl$intSetting$1(this.this$0, this.$name, continuation);
        secureSettingsRepositoryImpl$intSetting$1.L$0 = obj;
        return secureSettingsRepositoryImpl$intSetting$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SecureSettingsRepositoryImpl$intSetting$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProducerScope producerScope;
        final SecureSettingsRepositoryImpl$intSetting$1$observer$1 secureSettingsRepositoryImpl$intSetting$1$observer$1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope2 = (ProducerScope) this.L$0;
            ContentObserver contentObserver = new ContentObserver() { // from class: com.android.systemui.shared.settings.data.repository.SecureSettingsRepositoryImpl$intSetting$1$observer$1
                {
                    super(null);
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }
            };
            this.this$0.contentResolver.registerContentObserver(Settings.Secure.getUriFor(this.$name), false, contentObserver);
            this.L$0 = producerScope2;
            this.L$1 = contentObserver;
            this.label = 1;
            if (((ProducerCoroutine) producerScope2)._channel.send(unit, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            producerScope = producerScope2;
            secureSettingsRepositoryImpl$intSetting$1$observer$1 = contentObserver;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            SecureSettingsRepositoryImpl$intSetting$1$observer$1 secureSettingsRepositoryImpl$intSetting$1$observer$12 = (SecureSettingsRepositoryImpl$intSetting$1$observer$1) this.L$1;
            producerScope = (ProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            secureSettingsRepositoryImpl$intSetting$1$observer$1 = secureSettingsRepositoryImpl$intSetting$1$observer$12;
        }
        final SecureSettingsRepositoryImpl secureSettingsRepositoryImpl = this.this$0;
        Function0 function0 = new Function0() { // from class: com.android.systemui.shared.settings.data.repository.SecureSettingsRepositoryImpl$intSetting$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SecureSettingsRepositoryImpl.this.contentResolver.unregisterContentObserver(secureSettingsRepositoryImpl$intSetting$1$observer$1);
                return Unit.INSTANCE;
            }
        };
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        return ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons ? coroutineSingletons : unit;
    }
}
