package com.android.systemui.qs.tiles.impl.internet.domain.interactor;

import android.content.Context;
import android.text.Html;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.qs.tiles.impl.internet.domain.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class InternetTileDataInteractor$mobileIconFlow$1$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ InternetTileDataInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileDataInteractor$mobileIconFlow$1$2(InternetTileDataInteractor internetTileDataInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = internetTileDataInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        InternetTileDataInteractor$mobileIconFlow$1$2 internetTileDataInteractor$mobileIconFlow$1$2 = new InternetTileDataInteractor$mobileIconFlow$1$2(this.this$0, continuation);
        internetTileDataInteractor$mobileIconFlow$1$2.L$0 = obj;
        return internetTileDataInteractor$mobileIconFlow$1$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((InternetTileDataInteractor$mobileIconFlow$1$2) create((Triple) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CharSequence fromHtml;
        CharSequence charSequence;
        SignalIconModel signalIconModel;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        String str = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Triple triple = (Triple) this.L$0;
            NetworkNameModel networkNameModel = (NetworkNameModel) triple.component1();
            SignalIconModel signalIconModel2 = (SignalIconModel) triple.component2();
            CharSequence charSequence2 = (CharSequence) triple.component3();
            if (!(signalIconModel2 instanceof SignalIconModel.Cellular)) {
                if (!(signalIconModel2 instanceof SignalIconModel.Satellite)) {
                    throw new NoWhenBranchMatchedException();
                }
                SignalIconModel.Satellite satellite = (SignalIconModel.Satellite) signalIconModel2;
                Icon.Resource resource = satellite.icon;
                Context context = this.this$0.context;
                ContentDescription contentDescription = resource.contentDescription;
                if (contentDescription != null) {
                    if (contentDescription instanceof ContentDescription.Loaded) {
                        str = ((ContentDescription.Loaded) contentDescription).description;
                    } else {
                        if (!(contentDescription instanceof ContentDescription.Resource)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        str = context.getString(((ContentDescription.Resource) contentDescription).res);
                    }
                }
                String str2 = str;
                return new InternetTileModel.Active(str2, null, new Integer(satellite.icon.res), null, new ContentDescription.Loaded(str2), new ContentDescription.Loaded(this.this$0.internetLabel), 10);
            }
            InternetTileDataInteractor internetTileDataInteractor = this.this$0;
            String name = networkNameModel.getName();
            InternetTileModel.Inactive inactive = InternetTileDataInteractor.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
            internetTileDataInteractor.getClass();
            if (charSequence2 == null) {
                if (name == null) {
                    name = "";
                }
                fromHtml = name;
            } else {
                fromHtml = name == null ? Html.fromHtml(charSequence2.toString(), 0) : Html.fromHtml(internetTileDataInteractor.context.getString(R.string.mobile_carrier_text_format, name, charSequence2), 0);
            }
            InternetTileDataInteractor internetTileDataInteractor2 = this.this$0;
            CoroutineContext coroutineContext = internetTileDataInteractor2.mainCoroutineContext;
            InternetTileDataInteractor$mobileIconFlow$1$2$drawable$1 internetTileDataInteractor$mobileIconFlow$1$2$drawable$1 = new InternetTileDataInteractor$mobileIconFlow$1$2$drawable$1(internetTileDataInteractor2, null);
            this.L$0 = signalIconModel2;
            this.L$1 = fromHtml;
            this.label = 1;
            Object withContext = BuildersKt.withContext(coroutineContext, internetTileDataInteractor$mobileIconFlow$1$2$drawable$1, this);
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
            charSequence = fromHtml;
            obj = withContext;
            signalIconModel = signalIconModel2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CharSequence charSequence3 = (CharSequence) this.L$1;
            signalIconModel = (SignalIconModel) this.L$0;
            ResultKt.throwOnFailure(obj);
            charSequence = charSequence3;
        }
        SignalDrawable signalDrawable = (SignalDrawable) obj;
        signalDrawable.setLevel(signalIconModel.getLevel());
        return new InternetTileModel.Active(charSequence, null, null, new Icon.Loaded(signalDrawable, null), new ContentDescription.Loaded(charSequence.toString()), new ContentDescription.Loaded(this.this$0.internetLabel), 6);
    }
}
