package com.android.systemui.qs;

import android.util.Log;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.DiffUtil;
import com.android.systemui.qs.FgsManagerControllerImpl;
import com.android.systemui.qs.footer.data.repository.ForegroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FgsManagerControllerImpl$updateAppItems$4 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ FgsManagerControllerImpl$updateAppItems$4(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                FgsManagerControllerImpl fgsManagerControllerImpl = (FgsManagerControllerImpl) this.this$0;
                FgsManagerControllerImpl.AppListAdapter appListAdapter = fgsManagerControllerImpl.appListAdapter;
                final List sortedWith = CollectionsKt.sortedWith(CollectionsKt.toList(fgsManagerControllerImpl.runningApps.values()), new FgsManagerControllerImpl$updateAppItems$4$run$$inlined$sortedByDescending$1());
                appListAdapter.getClass();
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                ref$ObjectRef.element = appListAdapter.data;
                appListAdapter.data = sortedWith;
                DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: com.android.systemui.qs.FgsManagerControllerImpl$AppListAdapter$setData$1
                    @Override // androidx.recyclerview.widget.DiffUtil.Callback
                    public final boolean areContentsTheSame(int i, int i2) {
                        return ((FgsManagerControllerImpl.RunningApp) ((List) Ref$ObjectRef.this.element).get(i)).stopped == ((FgsManagerControllerImpl.RunningApp) sortedWith.get(i2)).stopped;
                    }

                    @Override // androidx.recyclerview.widget.DiffUtil.Callback
                    public final boolean areItemsTheSame(int i, int i2) {
                        return Intrinsics.areEqual(((List) Ref$ObjectRef.this.element).get(i), sortedWith.get(i2));
                    }

                    @Override // androidx.recyclerview.widget.DiffUtil.Callback
                    public final int getNewListSize() {
                        return sortedWith.size();
                    }

                    @Override // androidx.recyclerview.widget.DiffUtil.Callback
                    public final int getOldListSize() {
                        return ((List) Ref$ObjectRef.this.element).size();
                    }
                }).dispatchUpdatesTo(new AdapterListUpdateCallback(appListAdapter));
                break;
            case 1:
                ((FgsManagerControllerImpl) this.this$0).runningApps.clear();
                break;
            default:
                ForegroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1 foregroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1 = (ForegroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1) this.this$0;
                Object mo1790trySendJP2dKIU = ((ProducerCoroutine) foregroundServicesRepositoryImpl$hasNewChanges$1$dialogDismissedEvents$1$listener$1.$$this$conflatedCallbackFlow)._channel.mo1790trySendJP2dKIU(Unit.INSTANCE);
                if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                    Log.e("ForegroundServicesRepositoryImpl", "Failed to send updated state - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    break;
                }
                break;
        }
    }
}
