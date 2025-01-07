package com.android.wm.shell.bubbles;

import android.content.pm.UserInfo;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubbleController$BubblesImpl$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubbleController.BubblesImpl f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ BubbleController$BubblesImpl$$ExternalSyntheticLambda11(BubbleController.BubblesImpl bubblesImpl, int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = bubblesImpl;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        switch (this.$r8$classId) {
            case 0:
                BubbleController.BubblesImpl bubblesImpl = this.f$0;
                final int i = this.f$1;
                BubbleController bubbleController = BubbleController.this;
                UserInfo profileParent = bubbleController.mUserManager.getProfileParent(i);
                int identifier = profileParent != null ? profileParent.getUserHandle().getIdentifier() : -1;
                BubbleData bubbleData = bubbleController.mBubbleData;
                bubbleData.getClass();
                ArrayList arrayList = new ArrayList();
                for (Bubble bubble : bubbleData.mPendingBubbles.values()) {
                    if (i == bubble.mUser.getIdentifier()) {
                        arrayList.add(bubble);
                    }
                }
                for (Bubble bubble2 : bubbleData.mSuppressedBubbles.values()) {
                    if (i == bubble2.mUser.getIdentifier()) {
                        arrayList.add(bubble2);
                    }
                }
                for (Bubble bubble3 : bubbleData.mBubbles) {
                    if (i == bubble3.mUser.getIdentifier()) {
                        arrayList.add(bubble3);
                    }
                }
                for (Bubble bubble4 : bubbleData.mOverflowBubbles) {
                    if (i == bubble4.mUser.getIdentifier()) {
                        arrayList.add(bubble4);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    bubbleData.doRemove(16, ((Bubble) it.next()).mKey);
                }
                if (!arrayList.isEmpty()) {
                    bubbleData.dispatchPendingChanges();
                }
                BubbleDataRepository bubbleDataRepository = bubbleController.mDataRepository;
                BubbleVolatileRepository bubbleVolatileRepository = bubbleDataRepository.volatileRepository;
                synchronized (bubbleVolatileRepository) {
                    if (identifier != -1) {
                        synchronized (bubbleVolatileRepository) {
                            try {
                                if (bubbleVolatileRepository.entitiesByUser.get(identifier) != null) {
                                    z = ((List) bubbleVolatileRepository.entitiesByUser.get(identifier)).removeIf(new Predicate() { // from class: com.android.wm.shell.bubbles.storage.BubbleVolatileRepository$removeBubblesForUserWithParent$1
                                        @Override // java.util.function.Predicate
                                        public final boolean test(Object obj) {
                                            return ((BubbleEntity) obj).userId == i;
                                        }
                                    });
                                }
                            } finally {
                            }
                        }
                    } else {
                        List list = (List) bubbleVolatileRepository.entitiesByUser.get(i);
                        bubbleVolatileRepository.entitiesByUser.remove(i);
                        z = list != null;
                    }
                }
                if (z) {
                    BubbleDataRepository.persistToDisk$default(bubbleDataRepository);
                    return;
                }
                return;
            default:
                BubbleController.this.onUserChanged(this.f$1);
                return;
        }
    }
}
