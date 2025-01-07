package com.google.android.systemui.ailabs.ui.data.repository;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.wm.shell.R;
import com.google.android.apps.warp.lockscreen.presentation.uidata.WarpTaskItem;
import java.util.ArrayList;
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

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WarpLockscreenRepository$warps$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WarpLockscreenRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WarpLockscreenRepository$warps$1(WarpLockscreenRepository warpLockscreenRepository, Continuation continuation) {
        super(2, continuation);
        this.this$0 = warpLockscreenRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WarpLockscreenRepository$warps$1 warpLockscreenRepository$warps$1 = new WarpLockscreenRepository$warps$1(this.this$0, continuation);
        warpLockscreenRepository$warps$1.L$0 = obj;
        return warpLockscreenRepository$warps$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WarpLockscreenRepository$warps$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.content.BroadcastReceiver, com.google.android.systemui.ailabs.ui.data.repository.WarpLockscreenRepository$warps$1$warpUpdatesReceiver$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WarpLockscreenRepository warpLockscreenRepository = this.this$0;
            final ?? r1 = new BroadcastReceiver() { // from class: com.google.android.systemui.ailabs.ui.data.repository.WarpLockscreenRepository$warps$1$warpUpdatesReceiver$1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    Icon createWithResource;
                    String str;
                    Bitmap bitmap;
                    int intExtra = intent.getIntExtra("warps_count", 0);
                    ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("titles");
                    ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("icons", Bitmap.class);
                    ArrayList arrayList = new ArrayList(intExtra);
                    for (int i2 = 0; i2 < intExtra; i2++) {
                        if (parcelableArrayListExtra == null || (bitmap = (Bitmap) parcelableArrayListExtra.get(i2)) == null) {
                            createWithResource = Icon.createWithResource(context, R.drawable.ic_magic_button);
                        } else {
                            WarpLockscreenRepository.this.getClass();
                            createWithResource = Icon.createWithBitmap(bitmap);
                        }
                        if (stringArrayListExtra == null || (str = stringArrayListExtra.get(i2)) == null) {
                            str = "Empty title";
                        }
                        WarpTaskItem.TaskPosition taskPosition = WarpTaskItem.TaskPosition.SELECTED;
                        arrayList.add(i2, new WarpTaskItem(createWithResource, str));
                    }
                    ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(arrayList);
                }
            };
            BroadcastDispatcher.registerReceiver$default(this.this$0.broadcastDispatcher, r1, new IntentFilter("android.ailabs.lockwidget.action.WARPS_UPDATE"), null, null, 0, 60);
            final WarpLockscreenRepository warpLockscreenRepository2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.ailabs.ui.data.repository.WarpLockscreenRepository$warps$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    WarpLockscreenRepository.this.broadcastDispatcher.unregisterReceiver(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
