package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.qs.tiles.impl.internet.domain.model.InternetTileModel;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class InternetTileDataInteractor$notConnectedFlow$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ InternetTileDataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileDataInteractor$notConnectedFlow$1(InternetTileDataInteractor internetTileDataInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = internetTileDataInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        InternetTileDataInteractor$notConnectedFlow$1 internetTileDataInteractor$notConnectedFlow$1 = new InternetTileDataInteractor$notConnectedFlow$1(this.this$0, (Continuation) obj3);
        internetTileDataInteractor$notConnectedFlow$1.Z$0 = booleanValue;
        internetTileDataInteractor$notConnectedFlow$1.Z$1 = booleanValue2;
        return internetTileDataInteractor$notConnectedFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        if (this.Z$1) {
            String string = this.this$0.context.getString(R.string.status_bar_airplane);
            return new InternetTileModel.Inactive(string, null, new Integer(R.drawable.ic_qs_no_internet_unavailable), new ContentDescription.Loaded(string), 10);
        }
        if (!z) {
            return InternetTileDataInteractor.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
        }
        String string2 = this.this$0.context.getString(R.string.quick_settings_networks_available);
        return new InternetTileModel.Inactive(string2, null, new Integer(R.drawable.ic_qs_no_internet_available), new ContentDescription.Loaded(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(this.this$0.internetLabel, ",", string2)), 10);
    }
}
