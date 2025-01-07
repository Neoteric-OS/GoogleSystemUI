package com.android.wm.shell.bubbles;

import android.content.LocusId;
import android.content.pm.LauncherApps;
import android.util.SparseArray;
import com.android.wm.shell.bubbles.storage.BubbleEntity;
import com.android.wm.shell.bubbles.storage.BubblePersistentRepository;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository;
import com.android.wm.shell.common.ShellExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleDataRepository {
    public final ShellExecutor bgExecutor;
    public BubbleController$$ExternalSyntheticLambda7 bubbleMetadataFlagListener;
    public final ContextScope coroutineScope;
    public StandaloneCoroutine job;
    public final LauncherApps launcherApps;
    public final ShellExecutor mainExecutor;
    public final BubblePersistentRepository persistentRepository;
    public final BubbleVolatileRepository volatileRepository;

    public BubbleDataRepository(LauncherApps launcherApps, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, BubblePersistentRepository bubblePersistentRepository) {
        this.launcherApps = launcherApps;
        this.mainExecutor = shellExecutor;
        this.bgExecutor = shellExecutor2;
        this.persistentRepository = bubblePersistentRepository;
        this.volatileRepository = new BubbleVolatileRepository(launcherApps);
        DefaultIoScheduler defaultIoScheduler = Dispatchers.IO;
        SupervisorJobImpl SupervisorJob$default = SupervisorKt.SupervisorJob$default();
        defaultIoScheduler.getClass();
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(defaultIoScheduler, SupervisorJob$default));
    }

    public static void persistToDisk$default(BubbleDataRepository bubbleDataRepository) {
        SparseArray sparseArray;
        BubbleVolatileRepository bubbleVolatileRepository = bubbleDataRepository.volatileRepository;
        synchronized (bubbleVolatileRepository) {
            sparseArray = new SparseArray();
            int size = bubbleVolatileRepository.entitiesByUser.size();
            for (int i = 0; i < size; i++) {
                int keyAt = bubbleVolatileRepository.entitiesByUser.keyAt(i);
                List list = (List) bubbleVolatileRepository.entitiesByUser.valueAt(i);
                Intrinsics.checkNotNull(list);
                sparseArray.put(keyAt, CollectionsKt.toList(list));
            }
        }
        bubbleDataRepository.persistToDisk(sparseArray);
    }

    public static List transform(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Bubble bubble = (Bubble) it.next();
            int identifier = bubble.mUser.getIdentifier();
            String str = bubble.mPackageName;
            String str2 = bubble.mMetadataShortcutId;
            BubbleEntity bubbleEntity = null;
            if (str2 != null) {
                int i = bubble.mDesiredHeight;
                int i2 = bubble.mDesiredHeightResId;
                String str3 = bubble.mTitle;
                int taskId = bubble.getTaskId();
                LocusId locusId = bubble.mLocusId;
                String id = locusId != null ? locusId.getId() : null;
                bubbleEntity = new BubbleEntity(identifier, str, str2, bubble.mKey, i, i2, str3, taskId, id, bubble.mIsDismissable);
            }
            if (bubbleEntity != null) {
                arrayList.add(bubbleEntity);
            }
        }
        return arrayList;
    }

    public final SparseArray filterForActiveUsersAndPersist(List list, SparseArray sparseArray) {
        SparseArray sparseArray2 = new SparseArray();
        int size = sparseArray.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            if (list.contains(Integer.valueOf(keyAt))) {
                ArrayList arrayList = new ArrayList();
                for (BubbleEntity bubbleEntity : (List) sparseArray.get(keyAt)) {
                    if (list.contains(Integer.valueOf(bubbleEntity.userId))) {
                        arrayList.add(bubbleEntity);
                    } else {
                        z = true;
                    }
                }
                if (!arrayList.isEmpty()) {
                    sparseArray2.put(keyAt, arrayList);
                }
            } else {
                z = true;
            }
        }
        if (!z) {
            return sparseArray;
        }
        persistToDisk(sparseArray2);
        return sparseArray2;
    }

    public final void persistToDisk(SparseArray sparseArray) {
        this.job = BuildersKt.launch$default(this.coroutineScope, null, null, new BubbleDataRepository$persistToDisk$1(this.job, this, sparseArray, null), 3);
    }
}
