package com.android.systemui.communal.ui.viewmodel;

import android.content.Intent;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.Parcelable;
import android.util.Log;
import androidx.activity.result.ActivityResultLauncher;
import com.android.systemui.communal.domain.model.CommunalContentModel;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalEditModeViewModel$onOpenWidgetPicker$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ActivityResultLauncher $activityLauncher;
    final /* synthetic */ Resources $resources;
    int label;
    final /* synthetic */ CommunalEditModeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalEditModeViewModel$onOpenWidgetPicker$2(CommunalEditModeViewModel communalEditModeViewModel, Resources resources, ActivityResultLauncher activityResultLauncher, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalEditModeViewModel;
        this.$resources = resources;
        this.$activityLauncher = activityResultLauncher;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalEditModeViewModel$onOpenWidgetPicker$2(this.this$0, this.$resources, this.$activityLauncher, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalEditModeViewModel$onOpenWidgetPicker$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = this.this$0.communalInteractor.widgetContent;
            this.label = 1;
            obj = FlowKt.first(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            if (obj2 instanceof CommunalContentModel.WidgetContent.Widget) {
                arrayList.add(obj2);
            }
        }
        ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((CommunalContentModel.WidgetContent.Widget) it.next()).providerInfo);
        }
        CommunalEditModeViewModel communalEditModeViewModel = this.this$0;
        Resources resources = this.$resources;
        communalEditModeViewModel.getClass();
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setPackage(communalEditModeViewModel.launcherPackage);
        intent.putExtra("desired_widget_width", resources.getDimensionPixelSize(R.dimen.communal_widget_picker_desired_width));
        intent.putExtra("desired_widget_height", resources.getDimensionPixelSize(R.dimen.communal_widget_picker_desired_height));
        intent.putExtra("categoryFilter", 3);
        UserInfo userInfo = (UserInfo) ((StateFlowImpl) communalEditModeViewModel.communalSettingsInteractor.workProfileUserDisallowedByDevicePolicy.$$delegate_0).getValue();
        if (userInfo != null) {
            intent.putExtra("filtered_user_ids", CollectionsKt__CollectionsKt.arrayListOf(Integer.valueOf(userInfo.id)));
        }
        intent.putExtra("ui_surface", "widgets_hub");
        intent.putExtra("picker_title", resources.getString(R.string.communal_widget_picker_title));
        intent.putExtra("picker_description", resources.getString(R.string.communal_widget_picker_description));
        intent.putParcelableArrayListExtra("added_app_widgets", arrayList2);
        try {
            this.$activityLauncher.launch(intent);
            return Boolean.TRUE;
        } catch (Exception e) {
            new Integer(Log.e("CommunalEditModeViewModel", "Failed to launch widget picker activity", e));
            return Boolean.FALSE;
        }
    }
}
