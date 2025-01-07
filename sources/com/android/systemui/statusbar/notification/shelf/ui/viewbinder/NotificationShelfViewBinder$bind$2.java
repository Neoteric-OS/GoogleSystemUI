package com.android.systemui.statusbar.notification.shelf.ui.viewbinder;

import android.os.Trace;
import com.android.app.tracing.TraceUtilsKt;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerShelfViewBinder;
import com.android.systemui.statusbar.notification.row.ui.viewbinder.ActivatableNotificationViewBinder;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationShelfViewBinder$bind$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FalsingManager $falsingManager;
    final /* synthetic */ NotificationIconContainerShelfViewBinder $nicBinder;
    final /* synthetic */ NotificationShelf $shelf;
    final /* synthetic */ NotificationShelfViewModel $viewModel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationShelfViewBinder$bind$2(FalsingManager falsingManager, NotificationShelf notificationShelf, NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder, NotificationShelfViewModel notificationShelfViewModel, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = notificationShelfViewModel;
        this.$shelf = notificationShelf;
        this.$falsingManager = falsingManager;
        this.$nicBinder = notificationIconContainerShelfViewBinder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationShelfViewModel notificationShelfViewModel = this.$viewModel;
        NotificationShelfViewBinder$bind$2 notificationShelfViewBinder$bind$2 = new NotificationShelfViewBinder$bind$2(this.$falsingManager, this.$shelf, this.$nicBinder, notificationShelfViewModel, continuation);
        notificationShelfViewBinder$bind$2.L$0 = obj;
        return notificationShelfViewBinder$bind$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationShelfViewBinder$bind$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        ActivatableNotificationViewBinder.bind(this.$viewModel, this.$shelf, this.$falsingManager);
        NotificationShelf notificationShelf = this.$shelf;
        NotificationShelfViewModel notificationShelfViewModel = this.$viewModel;
        NotificationIconContainerShelfViewBinder notificationIconContainerShelfViewBinder = this.$nicBinder;
        boolean isEnabled = Trace.isEnabled();
        if (isEnabled) {
            TraceUtilsKt.beginSlice("NotifShelf#bindShelfIcons");
        }
        try {
            BuildersKt.launch$default(coroutineScope, null, null, new NotificationShelfViewBinder$bind$2$1$1$1(notificationIconContainerShelfViewBinder, notificationShelf, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new NotificationShelfViewBinder$bind$2$1$2(notificationShelfViewModel, notificationShelf, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new NotificationShelfViewBinder$bind$2$1$3(notificationShelfViewModel, notificationShelf, null), 3);
            NotificationShelfViewBinder notificationShelfViewBinder = NotificationShelfViewBinder.INSTANCE;
            this.L$0 = notificationShelf;
            this.label = 1;
            NotificationShelfViewBinder.access$registerViewListenersWhileAttached(notificationShelfViewBinder, notificationShelf, notificationShelfViewModel, this);
            return coroutineSingletons;
        } finally {
            if (isEnabled) {
                TraceUtilsKt.endSlice();
            }
        }
    }
}
