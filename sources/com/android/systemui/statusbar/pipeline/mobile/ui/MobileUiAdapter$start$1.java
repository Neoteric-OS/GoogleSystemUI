package com.android.systemui.statusbar.pipeline.mobile.ui;

import android.R;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.statusbar.phone.StatusBarIconHolder;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.phone.ui.StatusBarIconList;
import java.util.Collections;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class MobileUiAdapter$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ MobileUiAdapter this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter$start$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ MobileUiAdapter this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MobileUiAdapter mobileUiAdapter, Continuation continuation) {
            super(2, continuation);
            this.this$0 = mobileUiAdapter;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((List) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<Integer> list = (List) this.L$0;
            MobileViewLogger mobileViewLogger = this.this$0.logger;
            mobileViewLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            MobileViewLogger$logUiAdapterSubIdsSentToIconController$2 mobileViewLogger$logUiAdapterSubIdsSentToIconController$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.MobileViewLogger$logUiAdapterSubIdsSentToIconController$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Sub IDs in MobileUiAdapter being sent to icon controller: ", ((LogMessage) obj2).getStr1());
                }
            };
            LogBuffer logBuffer = mobileViewLogger.buffer;
            LogMessage obtain = logBuffer.obtain("MobileViewLogger", logLevel, mobileViewLogger$logUiAdapterSubIdsSentToIconController$2, null);
            ((LogMessageImpl) obtain).str1 = list.toString();
            logBuffer.commit(obtain);
            MobileUiAdapter mobileUiAdapter = this.this$0;
            mobileUiAdapter.lastValue = list;
            StatusBarIconControllerImpl statusBarIconControllerImpl = (StatusBarIconControllerImpl) mobileUiAdapter.iconController;
            String string = statusBarIconControllerImpl.mContext.getString(R.string.suspended_widget_accessibility);
            StatusBarIconList statusBarIconList = statusBarIconControllerImpl.mStatusBarIconList;
            StatusBarIconList.Slot slot = (StatusBarIconList.Slot) statusBarIconList.mSlots.get(statusBarIconList.findOrInsertSlot(string));
            statusBarIconControllerImpl.removeAllIconsForSlot(string, true);
            Collections.reverse(list);
            for (Integer num : list) {
                if (slot.getHolderForTag(num.intValue()) == null) {
                    int intValue = num.intValue();
                    StatusBarIconHolder statusBarIconHolder = new StatusBarIconHolder();
                    statusBarIconHolder.type = 3;
                    statusBarIconHolder.tag = intValue;
                    statusBarIconControllerImpl.setIcon(string, statusBarIconHolder);
                }
            }
            ShadeCarrierGroupController shadeCarrierGroupController = this.this$0.shadeCarrierGroupController;
            if (shadeCarrierGroupController != null) {
                shadeCarrierGroupController.updateModernMobileIcons(list);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MobileUiAdapter$start$1(MobileUiAdapter mobileUiAdapter, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mobileUiAdapter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MobileUiAdapter$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MobileUiAdapter$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MobileUiAdapter mobileUiAdapter = this.this$0;
            mobileUiAdapter.isCollecting = true;
            ReadonlyStateFlow readonlyStateFlow = mobileUiAdapter.mobileIconsViewModel.subscriptionIdsFlow;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(mobileUiAdapter, null);
            this.label = 1;
            if (FlowKt.collectLatest(readonlyStateFlow, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
