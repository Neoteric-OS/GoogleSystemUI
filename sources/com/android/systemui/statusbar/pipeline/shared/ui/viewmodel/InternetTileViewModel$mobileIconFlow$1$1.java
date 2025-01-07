package com.android.systemui.statusbar.pipeline.shared.ui.viewmodel;

import android.content.Context;
import android.text.Html;
import com.android.settingslib.graph.SignalDrawable;
import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import com.android.systemui.statusbar.pipeline.shared.ui.model.InternetTileModel;
import com.android.systemui.statusbar.pipeline.shared.ui.model.SignalIcon;
import com.android.wm.shell.R;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class InternetTileViewModel$mobileIconFlow$1$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    int label;
    final /* synthetic */ InternetTileViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InternetTileViewModel$mobileIconFlow$1$1(InternetTileViewModel internetTileViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = internetTileViewModel;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        InternetTileViewModel$mobileIconFlow$1$1 internetTileViewModel$mobileIconFlow$1$1 = new InternetTileViewModel$mobileIconFlow$1$1(this.this$0, (Continuation) obj4);
        internetTileViewModel$mobileIconFlow$1$1.L$0 = (NetworkNameModel) obj;
        internetTileViewModel$mobileIconFlow$1$1.L$1 = (SignalIconModel) obj2;
        internetTileViewModel$mobileIconFlow$1$1.L$2 = (CharSequence) obj3;
        return internetTileViewModel$mobileIconFlow$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String string;
        int state;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        NetworkNameModel networkNameModel = (NetworkNameModel) this.L$0;
        SignalIconModel signalIconModel = (SignalIconModel) this.L$1;
        CharSequence charSequence = (CharSequence) this.L$2;
        if (!(signalIconModel instanceof SignalIconModel.Cellular)) {
            if (!(signalIconModel instanceof SignalIconModel.Satellite)) {
                throw new NoWhenBranchMatchedException();
            }
            SignalIconModel.Satellite satellite = (SignalIconModel.Satellite) signalIconModel;
            Icon.Resource resource = satellite.icon;
            Context context = this.this$0.context;
            ContentDescription contentDescription = resource.contentDescription;
            if (contentDescription == null) {
                string = null;
            } else if (contentDescription instanceof ContentDescription.Loaded) {
                string = ((ContentDescription.Loaded) contentDescription).description;
            } else {
                if (!(contentDescription instanceof ContentDescription.Resource)) {
                    throw new NoWhenBranchMatchedException();
                }
                string = context.getString(((ContentDescription.Resource) contentDescription).res);
            }
            String str = string;
            return new InternetTileModel.Active(str, null, new Integer(satellite.icon.res), null, new ContentDescription.Loaded(str), new ContentDescription.Loaded(this.this$0.internetLabel), 10);
        }
        InternetTileViewModel internetTileViewModel = this.this$0;
        CharSequence name = networkNameModel.getName();
        InternetTileModel.Inactive inactive = InternetTileViewModel.NOT_CONNECTED_NETWORKS_UNAVAILABLE;
        internetTileViewModel.getClass();
        if (charSequence != null) {
            name = name == null ? Html.fromHtml(charSequence.toString(), 0) : Html.fromHtml(internetTileViewModel.context.getString(R.string.mobile_carrier_text_format, name, charSequence), 0);
        } else if (name == null) {
            name = "";
        }
        CharSequence charSequence2 = name;
        SignalIconModel.Cellular cellular = (SignalIconModel.Cellular) signalIconModel;
        boolean z = cellular.carrierNetworkChange;
        int i = cellular.numberOfLevels;
        if (z) {
            int i2 = SignalDrawable.$r8$clinit;
            state = (i << 8) | 196608;
        } else {
            state = SignalDrawable.getState(cellular.level, i, cellular.showExclamationMark);
        }
        return new InternetTileModel.Active(charSequence2, null, null, new SignalIcon(state), new ContentDescription.Loaded(charSequence2.toString()), new ContentDescription.Loaded(this.this$0.internetLabel), 6);
    }
}
