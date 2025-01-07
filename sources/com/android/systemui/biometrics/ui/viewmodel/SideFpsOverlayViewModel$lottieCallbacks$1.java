package com.android.systemui.biometrics.ui.viewmodel;

import android.R;
import com.airbnb.lottie.model.KeyPath;
import com.android.settingslib.Utils;
import com.android.systemui.biometrics.shared.model.LottieCallback;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewModel$lottieCallbacks$1 extends SuspendLambda implements Function3 {
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ SideFpsOverlayViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideFpsOverlayViewModel$lottieCallbacks$1(SideFpsOverlayViewModel sideFpsOverlayViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = sideFpsOverlayViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        SideFpsOverlayViewModel$lottieCallbacks$1 sideFpsOverlayViewModel$lottieCallbacks$1 = new SideFpsOverlayViewModel$lottieCallbacks$1(this.this$0, (Continuation) obj3);
        sideFpsOverlayViewModel$lottieCallbacks$1.Z$0 = booleanValue;
        return sideFpsOverlayViewModel$lottieCallbacks$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        ArrayList arrayList = new ArrayList();
        if (z) {
            int colorAttrDefaultColor = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorPrimaryFixed, 0, this.this$0.applicationContext);
            int colorAttrDefaultColor2 = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorPrimaryFixedDim, 0, this.this$0.applicationContext);
            int colorAttrDefaultColor3 = Utils.getColorAttrDefaultColor(R.^attr-private.materialColorOnPrimaryFixed, 0, this.this$0.applicationContext);
            arrayList.add(new LottieCallback(new KeyPath(".blue600", "**"), colorAttrDefaultColor));
            arrayList.add(new LottieCallback(new KeyPath(".blue400", "**"), colorAttrDefaultColor2));
            arrayList.add(new LottieCallback(new KeyPath(".black", "**"), colorAttrDefaultColor3));
        } else {
            if ((this.this$0.applicationContext.getResources().getConfiguration().uiMode & 48) != 32) {
                arrayList.add(new LottieCallback(new KeyPath(".black", "**"), -1));
            }
            Iterator it = CollectionsKt__CollectionsKt.listOf(".blue600", ".blue400").iterator();
            while (it.hasNext()) {
                arrayList.add(new LottieCallback(new KeyPath((String) it.next(), "**"), this.this$0.applicationContext.getColor(com.android.wm.shell.R.color.settingslib_color_blue400)));
            }
        }
        return arrayList;
    }
}
