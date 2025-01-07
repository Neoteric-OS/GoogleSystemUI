package com.android.wm.shell.bubbles;

import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda1;
import com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda2;
import com.android.wm.shell.R;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleLogger;
import com.android.wm.shell.bubbles.BubbleOverflowContainerView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView;
import com.android.wm.shell.bubbles.bar.BubbleBarLayerView$$ExternalSyntheticLambda7;
import com.android.wm.shell.bubbles.storage.BubbleEntity;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository;
import com.android.wm.shell.bubbles.storage.BubbleVolatileRepository$removeBubbles$1$1;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.bubbles.BubbleBarUpdate;
import com.android.wm.shell.shared.bubbles.RemovedBubble;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleData {
    public static final Comparator BUBBLES_BY_SORT_KEY_DESCENDING = Comparator.comparing(new BubbleData$$ExternalSyntheticLambda0()).reversed();
    public final ShellExecutor mBgExecutor;
    public BubbleController$$ExternalSyntheticLambda7 mBubbleMetadataFlagListener;
    public final List mBubbles;
    public BubbleController$$ExternalSyntheticLambda7 mCancelledListener;
    public int mCurrentUserId;
    public final BubbleEducationController mEducationController;
    public boolean mExpanded;
    public BubbleController.AnonymousClass8 mListener;
    public final BubbleLogger mLogger;
    public final ShellExecutor mMainExecutor;
    public int mMaxBubbles;
    public int mMaxOverflowBubbles;
    public boolean mNeedsTrimming;
    public final BubbleOverflow mOverflow;
    public final List mOverflowBubbles;
    public final HashMap mPendingBubbles;
    public final BubblePositioner mPositioner;
    public BubbleViewProvider mSelectedBubble;
    public boolean mShowingOverflow;
    public Update mStateChange;
    public final ArrayMap mSuppressedBubbles = new ArrayMap();
    public final ArraySet mVisibleLocusIds = new ArraySet();
    public TimeSource mTimeSource = new BubbleData$$ExternalSyntheticLambda2();
    public final HashMap mSuppressedGroupKeys = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TimeSource {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Update {
        public Bubble addedBubble;
        public Bubble addedOverflowBubble;
        public final List bubbles;
        public boolean expanded;
        public boolean expandedChanged;
        public boolean orderChanged;
        public final List overflowBubbles;
        public final List removedBubbles = new ArrayList();
        public Bubble removedOverflowBubble;
        public BubbleViewProvider selectedBubble;
        public boolean selectionChanged;
        public boolean shouldShowEducation;
        public boolean showOverflowChanged;
        public Bubble suppressedBubble;
        public boolean suppressedSummaryChanged;
        public String suppressedSummaryGroup;
        public Bubble unsuppressedBubble;
        public Bubble updatedBubble;

        public Update(List list, List list2) {
            this.bubbles = Collections.unmodifiableList(list);
            this.overflowBubbles = Collections.unmodifiableList(list2);
        }

        public final void bubbleRemoved(int i, Bubble bubble) {
            this.removedBubbles.add(new Pair(bubble, Integer.valueOf(i)));
        }
    }

    public BubbleData(Context context, BubbleLogger bubbleLogger, BubblePositioner bubblePositioner, BubbleEducationController bubbleEducationController, ShellExecutor shellExecutor, ShellExecutor shellExecutor2) {
        this.mLogger = bubbleLogger;
        this.mPositioner = bubblePositioner;
        this.mEducationController = bubbleEducationController;
        this.mMainExecutor = shellExecutor;
        this.mBgExecutor = shellExecutor2;
        this.mOverflow = new BubbleOverflow(context, bubblePositioner);
        ArrayList arrayList = new ArrayList();
        this.mBubbles = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.mOverflowBubbles = arrayList2;
        this.mPendingBubbles = new HashMap();
        this.mStateChange = new Update(arrayList, arrayList2);
        this.mMaxBubbles = bubblePositioner.mMaxBubbles;
        this.mMaxOverflowBubbles = context.getResources().getInteger(R.integer.bubbles_max_overflow);
    }

    public static void performActionOnBubblesMatching(List list, Predicate predicate, Consumer consumer) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Bubble bubble = (Bubble) it.next();
            if (predicate.test(bubble)) {
                arrayList.add(bubble);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            consumer.accept((Bubble) it2.next());
        }
    }

    public final void dismissAll(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -7192058839625748459L, 1, Long.valueOf(i));
        }
        if (this.mBubbles.isEmpty() && this.mSuppressedBubbles.isEmpty()) {
            return;
        }
        setExpandedInternal(false);
        setSelectedBubbleInternal(null);
        while (!this.mBubbles.isEmpty()) {
            doRemove(i, ((Bubble) ((ArrayList) this.mBubbles).get(0)).mKey);
        }
        while (!this.mSuppressedBubbles.isEmpty()) {
            doRemove(i, ((Bubble) this.mSuppressedBubbles.removeAt(0)).mKey);
        }
        dispatchPendingChanges();
    }

    public final void dismissBubbleWithKey(int i, String str) {
        Bubble bubbleInStackWithKey;
        ((BubbleData$$ExternalSyntheticLambda2) this.mTimeSource).getClass();
        long currentTimeMillis = System.currentTimeMillis();
        if (i != 18 || (bubbleInStackWithKey = getBubbleInStackWithKey(str)) == null || Math.max(bubbleInStackWithKey.mLastUpdated, bubbleInStackWithKey.mLastAccessed) <= currentTimeMillis) {
            doRemove(i, str);
            dispatchPendingChanges();
        }
    }

    public final void dispatchPendingChanges() {
        BadgedImageView badgedImageView;
        BadgedImageView badgedImageView2;
        if (this.mListener != null) {
            Update update = this.mStateChange;
            if (update.expandedChanged || update.selectionChanged || update.addedBubble != null || update.updatedBubble != null || !update.removedBubbles.isEmpty() || update.addedOverflowBubble != null || update.removedOverflowBubble != null || update.orderChanged || update.suppressedBubble != null || update.unsuppressedBubble != null || update.suppressedSummaryChanged || update.suppressedSummaryGroup != null || update.showOverflowChanged) {
                Update update2 = this.mStateChange;
                BubbleViewProvider bubbleViewProvider = this.mSelectedBubble;
                boolean z = false;
                if (bubbleViewProvider != null) {
                    BubbleEducationController bubbleEducationController = this.mEducationController;
                    if (!BubbleDebugConfig.neverShowUserEducation(bubbleEducationController.context) && (bubbleViewProvider instanceof Bubble) && ((Bubble) bubbleViewProvider).mShortcutInfo != null && ((!bubbleEducationController.prefs.getBoolean("HasSeenBubblesOnboarding", false) || BubbleDebugConfig.forceShowUserEducation(bubbleEducationController.context)) && !this.mExpanded)) {
                        z = true;
                    }
                }
                update2.shouldShowEducation = z;
                BubbleController.AnonymousClass8 anonymousClass8 = this.mListener;
                Update update3 = this.mStateChange;
                anonymousClass8.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    Bubble bubble = update3.addedBubble;
                    String valueOf = String.valueOf(bubble != null ? bubble.mKey : "null");
                    boolean z2 = !update3.removedBubbles.isEmpty();
                    Bubble bubble2 = update3.updatedBubble;
                    String valueOf2 = String.valueOf(bubble2 != null ? bubble2.mKey : "null");
                    boolean z3 = update3.orderChanged;
                    boolean z4 = update3.expandedChanged;
                    boolean z5 = update3.expanded;
                    boolean z6 = update3.selectionChanged;
                    BubbleViewProvider bubbleViewProvider2 = update3.selectedBubble;
                    String valueOf3 = String.valueOf(bubbleViewProvider2 != null ? bubbleViewProvider2.getKey() : "null");
                    Bubble bubble3 = update3.suppressedBubble;
                    String valueOf4 = String.valueOf(bubble3 != null ? bubble3.mKey : "null");
                    Bubble bubble4 = update3.unsuppressedBubble;
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 5948450822880900077L, 15744972, valueOf, Boolean.valueOf(z2), valueOf2, Boolean.valueOf(z3), Boolean.valueOf(z4), Boolean.valueOf(z5), Boolean.valueOf(z6), valueOf3, valueOf4, String.valueOf(bubble4 != null ? bubble4.mKey : "null"), Boolean.valueOf(update3.shouldShowEducation), Boolean.valueOf(update3.showOverflowChanged));
                }
                ((BubbleController) anonymousClass8.this$0).ensureBubbleViewsAndWindowCreated();
                ((BubbleController) anonymousClass8.this$0).loadOverflowBubblesFromDisk();
                if (update3.showOverflowChanged) {
                    BubbleController.AnonymousClass8 anonymousClass82 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    update3.overflowBubbles.isEmpty();
                    anonymousClass82.getClass();
                }
                BubbleData bubbleData = ((BubbleController) anonymousClass8.this$0).mBubbleData;
                BubbleOverflow bubbleOverflow = bubbleData.mOverflow;
                if (bubbleOverflow != null) {
                    Iterator it = bubbleData.getOverflowBubbles().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            bubbleOverflow.showDot = false;
                            BadgedImageView badgedImageView3 = bubbleOverflow.overflowBtn;
                            if (badgedImageView3 != null && badgedImageView3.getVisibility() == 0 && (badgedImageView = bubbleOverflow.overflowBtn) != null) {
                                badgedImageView.updateDotVisibility(true);
                            }
                        } else if (((Bubble) it.next()).showDot()) {
                            bubbleOverflow.showDot = true;
                            BadgedImageView badgedImageView4 = bubbleOverflow.overflowBtn;
                            if (badgedImageView4 != null && badgedImageView4.getVisibility() == 0 && (badgedImageView2 = bubbleOverflow.overflowBtn) != null) {
                                badgedImageView2.updateDotVisibility(true);
                            }
                        }
                    }
                }
                BubbleOverflowContainerView.AnonymousClass1 anonymousClass1 = ((BubbleController) anonymousClass8.this$0).mOverflowListener;
                if (anonymousClass1 != null) {
                    anonymousClass1.getClass();
                    Bubble bubble5 = update3.removedOverflowBubble;
                    BubbleOverflowContainerView bubbleOverflowContainerView = BubbleOverflowContainerView.this;
                    if (bubble5 != null) {
                        bubble5.cleanupViews();
                        int indexOf = bubbleOverflowContainerView.mOverflowBubbles.indexOf(bubble5);
                        bubbleOverflowContainerView.mOverflowBubbles.remove(bubble5);
                        bubbleOverflowContainerView.mAdapter.mObservable.notifyItemRangeRemoved(indexOf, 1);
                    }
                    Bubble bubble6 = update3.addedOverflowBubble;
                    if (bubble6 != null) {
                        int indexOf2 = bubbleOverflowContainerView.mOverflowBubbles.indexOf(bubble6);
                        if (indexOf2 > 0) {
                            bubbleOverflowContainerView.mOverflowBubbles.remove(bubble6);
                            bubbleOverflowContainerView.mOverflowBubbles.add(0, bubble6);
                            bubbleOverflowContainerView.mAdapter.mObservable.notifyItemMoved(indexOf2, 0);
                        } else {
                            bubbleOverflowContainerView.mOverflowBubbles.add(0, bubble6);
                            bubbleOverflowContainerView.mAdapter.mObservable.notifyItemRangeInserted(0, 1);
                        }
                    }
                    bubbleOverflowContainerView.updateEmptyStateVisibility();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 2347520435196804818L, 0, String.valueOf(bubble6 != null ? bubble6.mKey : "null"), String.valueOf(bubble5 != null ? bubble5.mKey : "null"));
                    }
                }
                ArrayList arrayList = new ArrayList(update3.removedBubbles);
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    Pair pair = (Pair) it2.next();
                    Bubble bubble7 = (Bubble) pair.first;
                    int intValue = ((Integer) pair.second).intValue();
                    BubbleController.AnonymousClass8 anonymousClass83 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    switch (anonymousClass83.$r8$classId) {
                        case 0:
                            BubbleStackView bubbleStackView = ((BubbleController) anonymousClass83.this$0).mStackView;
                            if (bubbleStackView != null) {
                                int i = 0;
                                if (bubbleStackView.mIsExpanded && bubbleStackView.getBubbleCount() == 1) {
                                    bubbleStackView.mRemovingLastBubbleWhileExpanded = true;
                                    bubbleStackView.showScrim(false, new BubbleStackView$$ExternalSyntheticLambda38(bubbleStackView, bubble7, bubble7.mIconView, bubbleStackView.mExpandedBubble));
                                    bubbleStackView.logBubbleEvent(bubble7, 5);
                                    break;
                                } else {
                                    if (bubbleStackView.getBubbleCount() == 1) {
                                        bubbleStackView.mExpandedBubble = null;
                                    }
                                    while (true) {
                                        if (i < bubbleStackView.getBubbleCount()) {
                                            View childAt = bubbleStackView.mBubbleContainer.getChildAt(i);
                                            if (childAt instanceof BadgedImageView) {
                                                BubbleViewProvider bubbleViewProvider3 = ((BadgedImageView) childAt).mBubble;
                                                if ((bubbleViewProvider3 != null ? bubbleViewProvider3.getKey() : null).equals(bubble7.mKey)) {
                                                    bubbleStackView.mBubbleContainer.removeViewAt(i);
                                                    if (bubbleStackView.mBubbleData.hasOverflowBubbleWithKey(bubble7.mKey)) {
                                                        bubble7.cleanupExpandedView(true);
                                                    } else {
                                                        bubble7.cleanupViews();
                                                    }
                                                    bubbleStackView.updateExpandedView();
                                                    if (bubbleStackView.getBubbleCount() == 0 && !bubbleStackView.mIsExpanded) {
                                                        bubbleStackView.mStackAnimationController.setStackPosition(bubbleStackView.mPositioner.getRestingPosition());
                                                        bubbleStackView.mDismissView.hide();
                                                    }
                                                    bubbleStackView.logBubbleEvent(bubble7, 5);
                                                    break;
                                                }
                                            }
                                            i++;
                                        } else if (bubble7.isSuppressed()) {
                                            bubble7.cleanupViews();
                                            bubbleStackView.logBubbleEvent(bubble7, 5);
                                            break;
                                        } else {
                                            Log.w("Bubbles", "was asked to remove Bubble, but didn't find the view! " + bubble7);
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        default:
                            BubbleBarLayerView bubbleBarLayerView = ((BubbleController) anonymousClass83.this$0).mLayerView;
                            if (bubbleBarLayerView != null) {
                                BubbleBarLayerView$$ExternalSyntheticLambda7 bubbleBarLayerView$$ExternalSyntheticLambda7 = new BubbleBarLayerView$$ExternalSyntheticLambda7(bubble7, new BubbleController$6$$ExternalSyntheticLambda0(2, anonymousClass83));
                                if (Collections.unmodifiableList(bubbleBarLayerView.mBubbleData.mBubbles).isEmpty()) {
                                    bubbleBarLayerView.collapse(bubbleBarLayerView$$ExternalSyntheticLambda7);
                                    break;
                                } else {
                                    bubbleBarLayerView$$ExternalSyntheticLambda7.run();
                                    break;
                                }
                            }
                            break;
                    }
                    if (intValue != 8 && intValue != 14) {
                        if (intValue == 5 || intValue == 12) {
                            arrayList2.add(bubble7);
                        }
                        if (!((BubbleController) anonymousClass8.this$0).mBubbleData.hasBubbleInStackWithKey(bubble7.mKey)) {
                            if (((BubbleController) anonymousClass8.this$0).mBubbleData.hasOverflowBubbleWithKey(bubble7.mKey) || !(!bubble7.showInShade() || intValue == 5 || intValue == 9)) {
                                if (bubble7.mIsBubble) {
                                    BubbleController bubbleController = (BubbleController) anonymousClass8.this$0;
                                    bubbleController.getClass();
                                    bubble7.mIsBubble = false;
                                    BubblesManager.AnonymousClass5 anonymousClass5 = bubbleController.mSysuiProxy;
                                    anonymousClass5.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda1(anonymousClass5, bubble7.mKey, new BubbleController$$ExternalSyntheticLambda1(bubbleController, false, bubble7)));
                                }
                                BubblesManager.AnonymousClass5 anonymousClass52 = ((BubbleController) anonymousClass8.this$0).mSysuiProxy;
                                anonymousClass52.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass52, bubble7.mKey, 3));
                            } else {
                                BubblesManager.AnonymousClass5 anonymousClass53 = ((BubbleController) anonymousClass8.this$0).mSysuiProxy;
                                anonymousClass53.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass53, bubble7.mKey, 1));
                            }
                        }
                    }
                }
                BubbleController bubbleController2 = (BubbleController) anonymousClass8.this$0;
                BubbleDataRepository bubbleDataRepository = bubbleController2.mDataRepository;
                int i2 = bubbleController2.mCurrentUserId;
                bubbleDataRepository.getClass();
                List transform = BubbleDataRepository.transform(arrayList2);
                BubbleVolatileRepository bubbleVolatileRepository = bubbleDataRepository.volatileRepository;
                synchronized (bubbleVolatileRepository) {
                    try {
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj : transform) {
                            if (bubbleVolatileRepository.getEntities(i2).removeIf(new BubbleVolatileRepository$removeBubbles$1$1(0, (BubbleEntity) obj))) {
                                arrayList3.add(obj);
                            }
                        }
                        bubbleVolatileRepository.uncache(arrayList3);
                    } finally {
                    }
                }
                if (!transform.isEmpty()) {
                    BubbleDataRepository.persistToDisk$default(bubbleDataRepository);
                }
                Bubble bubble8 = update3.addedBubble;
                if (bubble8 != null) {
                    BubbleController bubbleController3 = (BubbleController) anonymousClass8.this$0;
                    BubbleDataRepository bubbleDataRepository2 = bubbleController3.mDataRepository;
                    int i3 = bubbleController3.mCurrentUserId;
                    bubbleDataRepository2.getClass();
                    List transform2 = BubbleDataRepository.transform(Collections.singletonList(bubble8));
                    bubbleDataRepository2.volatileRepository.addBubbles(i3, transform2);
                    if (!transform2.isEmpty()) {
                        BubbleDataRepository.persistToDisk$default(bubbleDataRepository2);
                    }
                    BubbleController.AnonymousClass8 anonymousClass84 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    Bubble bubble9 = update3.addedBubble;
                    switch (anonymousClass84.$r8$classId) {
                        case 0:
                            BubbleStackView bubbleStackView2 = ((BubbleController) anonymousClass84.this$0).mStackView;
                            if (bubbleStackView2 != null) {
                                bubbleStackView2.addBubble(bubble9);
                                break;
                            }
                            break;
                    }
                }
                Bubble bubble10 = update3.updatedBubble;
                if (bubble10 != null) {
                    BubbleController.AnonymousClass8 anonymousClass85 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    switch (anonymousClass85.$r8$classId) {
                        case 0:
                            BubbleStackView bubbleStackView3 = ((BubbleController) anonymousClass85.this$0).mStackView;
                            if (bubbleStackView3 != null) {
                                bubbleStackView3.animateInFlyoutForBubble(bubble10);
                                bubbleStackView3.requestUpdate();
                                bubbleStackView3.logBubbleEvent(bubble10, 2);
                                break;
                            }
                            break;
                    }
                }
                Bubble bubble11 = update3.suppressedBubble;
                if (bubble11 != null) {
                    ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback.suppressionChanged(bubble11, true);
                }
                Bubble bubble12 = update3.unsuppressedBubble;
                if (bubble12 != null) {
                    ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback.suppressionChanged(bubble12, false);
                }
                boolean z7 = update3.expandedChanged && !update3.expanded;
                if (update3.orderChanged) {
                    BubbleController bubbleController4 = (BubbleController) anonymousClass8.this$0;
                    BubbleDataRepository bubbleDataRepository3 = bubbleController4.mDataRepository;
                    int i4 = bubbleController4.mCurrentUserId;
                    List list = update3.bubbles;
                    bubbleDataRepository3.getClass();
                    List transform3 = BubbleDataRepository.transform(list);
                    bubbleDataRepository3.volatileRepository.addBubbles(i4, transform3);
                    if (!transform3.isEmpty()) {
                        BubbleDataRepository.persistToDisk$default(bubbleDataRepository3);
                    }
                    BubbleController.AnonymousClass8 anonymousClass86 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    List list2 = update3.bubbles;
                    boolean z8 = !z7;
                    switch (anonymousClass86.$r8$classId) {
                        case 0:
                            BubbleStackView bubbleStackView4 = ((BubbleController) anonymousClass86.this$0).mStackView;
                            if (bubbleStackView4 != null) {
                                if (bubbleStackView4.mIsGestureInProgress) {
                                    bubbleStackView4.mShouldReorderBubblesAfterGestureCompletes = true;
                                    break;
                                } else {
                                    bubbleStackView4.updateBubbleOrderInternal(list2, z8);
                                    break;
                                }
                            }
                            break;
                    }
                }
                if (z7) {
                    final boolean z9 = false;
                    ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback.expansionChanged(false);
                    final BubblesManager.AnonymousClass5 anonymousClass54 = ((BubbleController) anonymousClass8.this$0).mSysuiProxy;
                    anonymousClass54.val$sysuiMainExecutor.execute(new Runnable() { // from class: com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubblesManager.AnonymousClass5 anonymousClass55 = BubblesManager.AnonymousClass5.this;
                            ((NotificationShadeWindowControllerImpl) BubblesManager.this.mNotificationShadeWindowController).setRequestTopUi("Bubbles", z9);
                        }
                    });
                }
                if (update3.selectionChanged) {
                    BubbleController.AnonymousClass8 anonymousClass87 = ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback;
                    BubbleViewProvider bubbleViewProvider4 = update3.selectedBubble;
                    switch (anonymousClass87.$r8$classId) {
                        case 0:
                            BubbleStackView bubbleStackView5 = ((BubbleController) anonymousClass87.this$0).mStackView;
                            if (bubbleStackView5 != null) {
                                bubbleStackView5.setSelectedBubble(bubbleViewProvider4);
                                break;
                            }
                            break;
                        default:
                            BubbleBarLayerView bubbleBarLayerView2 = ((BubbleController) anonymousClass87.this$0).mLayerView;
                            if (bubbleBarLayerView2 != null && bubbleBarLayerView2.mIsExpanded) {
                                bubbleBarLayerView2.showExpandedView(bubbleViewProvider4);
                                break;
                            }
                            break;
                    }
                }
                if (update3.expandedChanged && update3.expanded) {
                    final boolean z10 = true;
                    ((BubbleController) anonymousClass8.this$0).mBubbleViewCallback.expansionChanged(true);
                    final BubblesManager.AnonymousClass5 anonymousClass55 = ((BubbleController) anonymousClass8.this$0).mSysuiProxy;
                    anonymousClass55.val$sysuiMainExecutor.execute(new Runnable() { // from class: com.android.systemui.wmshell.BubblesManager$5$$ExternalSyntheticLambda7
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubblesManager.AnonymousClass5 anonymousClass552 = BubblesManager.AnonymousClass5.this;
                            ((NotificationShadeWindowControllerImpl) BubblesManager.this.mNotificationShadeWindowController).setRequestTopUi("Bubbles", z10);
                        }
                    });
                }
                BubblesManager.AnonymousClass5 anonymousClass56 = ((BubbleController) anonymousClass8.this$0).mSysuiProxy;
                anonymousClass56.val$sysuiMainExecutor.execute(new BubblesManager$5$$ExternalSyntheticLambda2(anonymousClass56, "BubbleData.Listener.applyUpdate", 2));
                ((BubbleController) anonymousClass8.this$0).updateBubbleViews();
                BubbleController.BubblesImpl.CachedState cachedState = ((BubbleController) anonymousClass8.this$0).mImpl.mCachedState;
                synchronized (cachedState) {
                    try {
                        if (update3.selectionChanged) {
                            BubbleViewProvider bubbleViewProvider5 = update3.selectedBubble;
                            cachedState.mSelectedBubbleKey = bubbleViewProvider5 != null ? bubbleViewProvider5.getKey() : null;
                        }
                        if (update3.expandedChanged) {
                            cachedState.mIsStackExpanded = update3.expanded;
                        }
                        if (update3.suppressedSummaryChanged) {
                            String str = (String) BubbleController.this.mBubbleData.mSuppressedGroupKeys.get(update3.suppressedSummaryGroup);
                            if (str != null) {
                                cachedState.mSuppressedGroupToNotifKeys.put(update3.suppressedSummaryGroup, str);
                            } else {
                                cachedState.mSuppressedGroupToNotifKeys.remove(update3.suppressedSummaryGroup);
                            }
                        }
                        cachedState.mTmpBubbles.clear();
                        cachedState.mTmpBubbles.addAll(update3.bubbles);
                        cachedState.mTmpBubbles.addAll(update3.overflowBubbles);
                        cachedState.mSuppressedBubbleKeys.clear();
                        cachedState.mShortcutIdToBubble.clear();
                        cachedState.mAppBubbleTaskIds.clear();
                        Iterator it3 = cachedState.mTmpBubbles.iterator();
                        while (it3.hasNext()) {
                            Bubble bubble13 = (Bubble) it3.next();
                            HashMap hashMap = cachedState.mShortcutIdToBubble;
                            ShortcutInfo shortcutInfo = bubble13.mShortcutInfo;
                            hashMap.put(shortcutInfo != null ? shortcutInfo.getId() : bubble13.mMetadataShortcutId, bubble13);
                            cachedState.updateBubbleSuppressedState(bubble13);
                            if (bubble13.mIsAppBubble) {
                                cachedState.mAppBubbleTaskIds.put(bubble13.mKey, Integer.valueOf(bubble13.getTaskId()));
                            }
                        }
                    } finally {
                    }
                }
                if (((BubbleController) anonymousClass8.this$0).isShowingAsBubbleBar()) {
                    BubbleBarUpdate bubbleBarUpdate = new BubbleBarUpdate(false);
                    bubbleBarUpdate.expandedChanged = update3.expandedChanged;
                    bubbleBarUpdate.expanded = update3.expanded;
                    bubbleBarUpdate.shouldShowEducation = update3.shouldShowEducation;
                    if (update3.selectionChanged) {
                        BubbleViewProvider bubbleViewProvider6 = update3.selectedBubble;
                        bubbleBarUpdate.selectedBubbleKey = bubbleViewProvider6 != null ? bubbleViewProvider6.getKey() : null;
                    }
                    Bubble bubble14 = update3.addedBubble;
                    bubbleBarUpdate.addedBubble = bubble14 != null ? bubble14.asBubbleBarBubble() : null;
                    Bubble bubble15 = update3.updatedBubble;
                    bubbleBarUpdate.updatedBubble = bubble15 != null ? bubble15.asBubbleBarBubble() : null;
                    Bubble bubble16 = update3.suppressedBubble;
                    bubbleBarUpdate.suppressedBubbleKey = bubble16 != null ? bubble16.mKey : null;
                    Bubble bubble17 = update3.unsuppressedBubble;
                    bubbleBarUpdate.unsupressedBubbleKey = bubble17 != null ? bubble17.mKey : null;
                    for (int i5 = 0; i5 < ((ArrayList) update3.removedBubbles).size(); i5++) {
                        Pair pair2 = (Pair) ((ArrayList) update3.removedBubbles).get(i5);
                        if (((Integer) pair2.second).intValue() != 18) {
                            bubbleBarUpdate.removedBubbles.add(new RemovedBubble(((Bubble) pair2.first).mKey, ((Integer) pair2.second).intValue()));
                        }
                    }
                    if (update3.orderChanged) {
                        for (int i6 = 0; i6 < update3.bubbles.size(); i6++) {
                            bubbleBarUpdate.bubbleKeysInOrder.add(((Bubble) update3.bubbles.get(i6)).mKey);
                        }
                    }
                    bubbleBarUpdate.showOverflowChanged = update3.showOverflowChanged;
                    bubbleBarUpdate.showOverflow = !update3.overflowBubbles.isEmpty();
                    if (bubbleBarUpdate.expandedChanged || bubbleBarUpdate.selectedBubbleKey != null || bubbleBarUpdate.addedBubble != null || bubbleBarUpdate.updatedBubble != null || !bubbleBarUpdate.removedBubbles.isEmpty() || !bubbleBarUpdate.bubbleKeysInOrder.isEmpty() || bubbleBarUpdate.suppressedBubbleKey != null || bubbleBarUpdate.unsupressedBubbleKey != null || !bubbleBarUpdate.currentBubbleList.isEmpty() || bubbleBarUpdate.bubbleBarLocation != null || bubbleBarUpdate.showOverflowChanged) {
                        ((BubbleController) anonymousClass8.this$0).mBubbleStateListener.onBubbleStateChange(bubbleBarUpdate);
                    }
                }
            }
        }
        this.mStateChange = new Update(this.mBubbles, this.mOverflowBubbles);
    }

    public final void doRemove(int i, String str) {
        PendingIntent pendingIntent;
        BubbleViewInfoTask bubbleViewInfoTask;
        if (this.mPendingBubbles.containsKey(str)) {
            this.mPendingBubbles.remove(str);
        }
        boolean z = i == 5 || i == 9 || i == 7 || i == 4 || i == 12 || i == 13 || i == 8 || i == 16;
        int i2 = 0;
        while (true) {
            if (i2 >= ((ArrayList) this.mBubbles).size()) {
                i2 = -1;
                break;
            } else if (((Bubble) ((ArrayList) this.mBubbles).get(i2)).mKey.equals(str)) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 != -1) {
            Bubble bubble = (Bubble) ((ArrayList) this.mBubbles).get(i2);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -5005277508660872541L, 0, String.valueOf(bubble.mKey));
            }
            BubbleViewInfoTask bubbleViewInfoTask2 = bubble.mInflationTask;
            if (bubbleViewInfoTask2 != null) {
                bubbleViewInfoTask2.mCancelled.set(true);
            }
            overflowBubble(i, bubble);
            if (((ArrayList) this.mBubbles).size() == 1) {
                setExpandedInternal(false);
                this.mSelectedBubble = null;
            }
            if (i2 < ((ArrayList) this.mBubbles).size() - 1) {
                this.mStateChange.orderChanged = true;
            }
            this.mBubbles.remove(i2);
            this.mStateChange.bubbleRemoved(i, bubble);
            if (!this.mExpanded) {
                this.mStateChange.orderChanged |= repackAll();
            }
            if (Objects.equals(this.mSelectedBubble, bubble)) {
                setNewSelectedIndex(i2);
            }
            if (i == 1 && (pendingIntent = bubble.mDeleteIntent) != null) {
                try {
                    pendingIntent.send();
                    return;
                } catch (PendingIntent.CanceledException unused) {
                    Log.w("Bubbles", "Failed to send delete intent for bubble with key: " + bubble.mKey);
                    return;
                }
            }
            return;
        }
        if (hasOverflowBubbleWithKey(str) && z) {
            Bubble overflowBubbleWithKey = getOverflowBubbleWithKey(str);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -1868635834674103890L, 0, String.valueOf(str));
            }
            if (overflowBubbleWithKey != null && (bubbleViewInfoTask = overflowBubbleWithKey.mInflationTask) != null) {
                bubbleViewInfoTask.mCancelled.set(true);
            }
            BubbleLogger bubbleLogger = this.mLogger;
            bubbleLogger.getClass();
            if (i == 5) {
                bubbleLogger.log(overflowBubbleWithKey, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_CANCEL);
            } else if (i == 9) {
                bubbleLogger.log(overflowBubbleWithKey, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_GROUP_CANCEL);
            } else if (i == 7) {
                bubbleLogger.log(overflowBubbleWithKey, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_NO_LONGER_BUBBLE);
            } else if (i == 4) {
                bubbleLogger.log(overflowBubbleWithKey, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_BLOCKED);
            }
            this.mOverflowBubbles.remove(overflowBubbleWithKey);
            this.mStateChange.bubbleRemoved(i, overflowBubbleWithKey);
            Update update = this.mStateChange;
            update.removedOverflowBubble = overflowBubbleWithKey;
            update.showOverflowChanged = this.mOverflowBubbles.isEmpty();
        }
        if (this.mSuppressedBubbles.values().stream().anyMatch(new BubbleData$$ExternalSyntheticLambda1(0, str)) && z) {
            Bubble suppressedBubbleWithKey = getSuppressedBubbleWithKey(str);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -6737429659320042284L, 0, String.valueOf(str));
            }
            if (suppressedBubbleWithKey != null) {
                this.mSuppressedBubbles.remove(suppressedBubbleWithKey.mLocusId);
                BubbleViewInfoTask bubbleViewInfoTask3 = suppressedBubbleWithKey.mInflationTask;
                if (bubbleViewInfoTask3 != null) {
                    bubbleViewInfoTask3.mCancelled.set(true);
                }
                this.mStateChange.bubbleRemoved(i, suppressedBubbleWithKey);
            }
        }
    }

    public final void doSuppress(Bubble bubble) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 6133665050714652608L, 0, String.valueOf(bubble.mKey));
        }
        this.mStateChange.suppressedBubble = bubble;
        bubble.setSuppressBubble(true);
        int indexOf = this.mBubbles.indexOf(bubble);
        this.mStateChange.orderChanged = ((ArrayList) this.mBubbles).size() - 1 != indexOf;
        this.mBubbles.remove(indexOf);
        if (Objects.equals(this.mSelectedBubble, bubble)) {
            if (this.mBubbles.isEmpty()) {
                this.mSelectedBubble = null;
            } else {
                setNewSelectedIndex(0);
            }
        }
    }

    public final void doUnsuppress(Bubble bubble) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8932995660497406677L, 0, String.valueOf(bubble.mKey));
        }
        bubble.setSuppressBubble(false);
        this.mStateChange.unsuppressedBubble = bubble;
        this.mBubbles.add(bubble);
        if (((ArrayList) this.mBubbles).size() > 1) {
            repackAll();
            this.mStateChange.orderChanged = true;
        }
        if (((ArrayList) this.mBubbles).get(0) == bubble) {
            setNewSelectedIndex(0);
        }
    }

    public Bubble getAnyBubbleWithkey(String str) {
        Bubble bubbleInStackWithKey = getBubbleInStackWithKey(str);
        if (bubbleInStackWithKey == null) {
            bubbleInStackWithKey = getOverflowBubbleWithKey(str);
        }
        return bubbleInStackWithKey == null ? getSuppressedBubbleWithKey(str) : bubbleInStackWithKey;
    }

    public Bubble getBubbleInStackWithKey(String str) {
        for (int i = 0; i < ((ArrayList) this.mBubbles).size(); i++) {
            Bubble bubble = (Bubble) ((ArrayList) this.mBubbles).get(i);
            if (bubble.mKey.equals(str)) {
                return bubble;
            }
        }
        return null;
    }

    public final Bubble getBubbleWithView(View view) {
        for (int i = 0; i < ((ArrayList) this.mBubbles).size(); i++) {
            Bubble bubble = (Bubble) ((ArrayList) this.mBubbles).get(i);
            BadgedImageView badgedImageView = bubble.mIconView;
            if (badgedImageView != null && badgedImageView.equals(view)) {
                return bubble;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.android.wm.shell.bubbles.Bubble getOrCreateBubble(com.android.wm.shell.bubbles.BubbleEntry r9, com.android.wm.shell.bubbles.Bubble r10) {
        /*
            r8 = this;
            if (r10 == 0) goto L5
            java.lang.String r0 = r10.mKey
            goto Lb
        L5:
            android.service.notification.StatusBarNotification r0 = r9.mSbn
            java.lang.String r0 = r0.getKey()
        Lb:
            com.android.wm.shell.bubbles.Bubble r1 = r8.getBubbleInStackWithKey(r0)
            if (r1 != 0) goto L5a
            com.android.wm.shell.bubbles.Bubble r1 = r8.getBubbleInStackWithKey(r0)
            if (r1 == 0) goto L18
            goto L46
        L18:
            com.android.wm.shell.bubbles.Bubble r1 = r8.getOverflowBubbleWithKey(r0)
            if (r1 == 0) goto L36
            java.util.List r2 = r8.mOverflowBubbles
            r2.remove(r1)
            java.util.List r2 = r8.mOverflowBubbles
            r2.remove(r1)
            java.util.List r2 = r8.mOverflowBubbles
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L46
            com.android.wm.shell.bubbles.BubbleData$Update r2 = r8.mStateChange
            r3 = 1
            r2.showOverflowChanged = r3
            goto L46
        L36:
            java.util.HashMap r2 = r8.mPendingBubbles
            boolean r2 = r2.containsKey(r0)
            if (r2 == 0) goto L46
            java.util.HashMap r1 = r8.mPendingBubbles
            java.lang.Object r1 = r1.get(r0)
            com.android.wm.shell.bubbles.Bubble r1 = (com.android.wm.shell.bubbles.Bubble) r1
        L46:
            if (r1 != 0) goto L5a
            if (r9 == 0) goto L5b
            com.android.wm.shell.bubbles.Bubble r10 = new com.android.wm.shell.bubbles.Bubble
            com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda7 r4 = r8.mBubbleMetadataFlagListener
            com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda7 r5 = r8.mCancelledListener
            com.android.wm.shell.common.ShellExecutor r6 = r8.mMainExecutor
            com.android.wm.shell.common.ShellExecutor r7 = r8.mBgExecutor
            r2 = r10
            r3 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            goto L5b
        L5a:
            r10 = r1
        L5b:
            if (r9 == 0) goto L60
            r10.setEntry(r9)
        L60:
            java.util.HashMap r8 = r8.mPendingBubbles
            r8.put(r0, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.bubbles.BubbleData.getOrCreateBubble(com.android.wm.shell.bubbles.BubbleEntry, com.android.wm.shell.bubbles.Bubble):com.android.wm.shell.bubbles.Bubble");
    }

    public final Bubble getOverflowBubbleWithKey(String str) {
        for (int i = 0; i < ((ArrayList) this.mOverflowBubbles).size(); i++) {
            Bubble bubble = (Bubble) ((ArrayList) this.mOverflowBubbles).get(i);
            if (bubble.mKey.equals(str)) {
                return bubble;
            }
        }
        return null;
    }

    public List getOverflowBubbles() {
        return Collections.unmodifiableList(this.mOverflowBubbles);
    }

    public Bubble getPendingBubbleWithKey(String str) {
        for (Bubble bubble : this.mPendingBubbles.values()) {
            if (bubble.mKey.equals(str)) {
                return bubble;
            }
        }
        return null;
    }

    public Bubble getSuppressedBubbleWithKey(String str) {
        for (Bubble bubble : this.mSuppressedBubbles.values()) {
            if (bubble.mKey.equals(str)) {
                return bubble;
            }
        }
        return null;
    }

    public final boolean hasAnyBubbleWithKey(String str) {
        return hasBubbleInStackWithKey(str) || hasOverflowBubbleWithKey(str) || this.mSuppressedBubbles.values().stream().anyMatch(new BubbleData$$ExternalSyntheticLambda1(0, str));
    }

    public final boolean hasBubbleInStackWithKey(String str) {
        return getBubbleInStackWithKey(str) != null;
    }

    public final boolean hasOverflowBubbleWithKey(String str) {
        return getOverflowBubbleWithKey(str) != null;
    }

    public boolean isSummarySuppressed(String str) {
        return this.mSuppressedGroupKeys.containsKey(str);
    }

    public final void overflowBubble(int i, Bubble bubble) {
        if (bubble.mPendingIntentCanceled) {
            return;
        }
        if (i == 2 || i == 1 || i == 15) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1732412474523466625L, 0, String.valueOf(bubble.mKey));
            }
            BubbleLogger bubbleLogger = this.mLogger;
            bubbleLogger.getClass();
            if (i == 2) {
                bubbleLogger.log(bubble, BubbleLogger.Event.BUBBLE_OVERFLOW_ADD_AGED);
            } else if (i == 1) {
                bubbleLogger.log(bubble, BubbleLogger.Event.BUBBLE_OVERFLOW_ADD_USER_GESTURE);
            } else if (i == 15) {
                bubbleLogger.log(bubble, BubbleLogger.Event.BUBBLE_OVERFLOW_RECOVER);
            }
            if (this.mOverflowBubbles.isEmpty()) {
                this.mStateChange.showOverflowChanged = true;
            }
            this.mOverflowBubbles.remove(bubble);
            this.mOverflowBubbles.add(0, bubble);
            this.mStateChange.addedOverflowBubble = bubble;
            BubbleViewInfoTask bubbleViewInfoTask = bubble.mInflationTask;
            if (bubbleViewInfoTask != null) {
                bubbleViewInfoTask.mCancelled.set(true);
            }
            if (((ArrayList) this.mOverflowBubbles).size() == this.mMaxOverflowBubbles + 1) {
                Bubble bubble2 = (Bubble) CascadingMenuPopup$$ExternalSyntheticOutline0.m((ArrayList) this.mOverflowBubbles, 1);
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -6275330112042878428L, 0, String.valueOf(bubble2.mKey));
                }
                this.mStateChange.bubbleRemoved(11, bubble2);
                bubbleLogger.log(bubble, BubbleLogger.Event.BUBBLE_OVERFLOW_REMOVE_MAX_REACHED);
                this.mOverflowBubbles.remove(bubble2);
                this.mStateChange.removedOverflowBubble = bubble2;
            }
        }
    }

    public final boolean repackAll() {
        if (this.mBubbles.isEmpty()) {
            return false;
        }
        ArrayList arrayList = new ArrayList(((ArrayList) this.mBubbles).size());
        this.mBubbles.stream().sorted(BUBBLES_BY_SORT_KEY_DESCENDING).forEachOrdered(new BubbleData$$ExternalSyntheticLambda7(3, arrayList));
        if (arrayList.equals(this.mBubbles)) {
            return false;
        }
        this.mBubbles.clear();
        this.mBubbles.addAll(arrayList);
        return true;
    }

    public final void setExpanded(boolean z) {
        setExpandedInternal(z);
        dispatchPendingChanges();
    }

    public final void setExpandedInternal(boolean z) {
        if (this.mExpanded == z) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 7747077208712034159L, 3, Boolean.valueOf(z));
        }
        if (z) {
            if (this.mBubbles.isEmpty() && !this.mShowingOverflow) {
                Log.e("Bubbles", "Attempt to expand stack when empty!");
                return;
            }
            BubbleViewProvider bubbleViewProvider = this.mSelectedBubble;
            if (bubbleViewProvider == null) {
                Log.e("Bubbles", "Attempt to expand stack without selected bubble!");
                return;
            }
            String key = bubbleViewProvider.getKey();
            this.mOverflow.getClass();
            if (key.equals("Overflow") && !this.mBubbles.isEmpty()) {
                setSelectedBubbleInternal((BubbleViewProvider) ((ArrayList) this.mBubbles).get(0));
            }
            BubbleViewProvider bubbleViewProvider2 = this.mSelectedBubble;
            if (bubbleViewProvider2 instanceof Bubble) {
                Bubble bubble = (Bubble) bubbleViewProvider2;
                ((BubbleData$$ExternalSyntheticLambda2) this.mTimeSource).getClass();
                bubble.mLastAccessed = System.currentTimeMillis();
                bubble.setSuppressNotification(true);
                bubble.setShowDot(false);
            }
            this.mStateChange.orderChanged |= repackAll();
        } else if (!this.mBubbles.isEmpty()) {
            this.mStateChange.orderChanged |= repackAll();
            if (this.mBubbles.indexOf(this.mSelectedBubble) > 0 && this.mBubbles.indexOf(this.mSelectedBubble) != 0) {
                this.mBubbles.remove((Bubble) this.mSelectedBubble);
                this.mBubbles.add(0, (Bubble) this.mSelectedBubble);
                this.mStateChange.orderChanged = true;
            }
        }
        if (this.mNeedsTrimming) {
            this.mNeedsTrimming = false;
            trim();
        }
        this.mExpanded = z;
        Update update = this.mStateChange;
        update.expanded = z;
        update.expandedChanged = true;
    }

    public void setMaxOverflowBubbles(int i) {
        this.mMaxOverflowBubbles = i;
    }

    public final void setNewSelectedIndex(int i) {
        if (this.mBubbles.isEmpty()) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("Bubbles list empty when attempting to select index: ", "Bubbles", i);
        } else {
            setSelectedBubbleInternal((BubbleViewProvider) ((ArrayList) this.mBubbles).get(Math.min(i, ((ArrayList) this.mBubbles).size() - 1)));
        }
    }

    public final void setSelectedBubbleAndExpandStack(BubbleViewProvider bubbleViewProvider) {
        setSelectedBubbleInternal(bubbleViewProvider);
        setExpandedInternal(true);
        dispatchPendingChanges();
    }

    public final void setSelectedBubbleFromLauncher(BubbleViewProvider bubbleViewProvider) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, 1430521184300187429L, 0, String.valueOf(bubbleViewProvider != null ? bubbleViewProvider.getKey() : "null"));
        }
        this.mExpanded = true;
        if (Objects.equals(bubbleViewProvider, this.mSelectedBubble)) {
            return;
        }
        boolean z = bubbleViewProvider != null && "Overflow".equals(bubbleViewProvider.getKey());
        if (bubbleViewProvider != null && !this.mBubbles.contains(bubbleViewProvider) && !this.mOverflowBubbles.contains(bubbleViewProvider) && !z) {
            Log.e("Bubbles", "Cannot select bubble which doesn't exist! (" + bubbleViewProvider + ") bubbles=" + this.mBubbles);
            return;
        }
        if (bubbleViewProvider != null && !z) {
            Bubble bubble = (Bubble) bubbleViewProvider;
            ((BubbleData$$ExternalSyntheticLambda2) this.mTimeSource).getClass();
            bubble.mLastAccessed = System.currentTimeMillis();
            bubble.setSuppressNotification(true);
            bubble.setShowDot(false);
        }
        this.mSelectedBubble = bubbleViewProvider;
    }

    public final void setSelectedBubbleInternal(BubbleViewProvider bubbleViewProvider) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -8092587024843195529L, 0, String.valueOf(bubbleViewProvider != null ? bubbleViewProvider.getKey() : "null"));
        }
        if (Objects.equals(bubbleViewProvider, this.mSelectedBubble)) {
            return;
        }
        boolean z = bubbleViewProvider != null && "Overflow".equals(bubbleViewProvider.getKey());
        if (bubbleViewProvider != null && !this.mBubbles.contains(bubbleViewProvider) && !this.mOverflowBubbles.contains(bubbleViewProvider) && !z) {
            Log.e("Bubbles", "Cannot select bubble which doesn't exist! (" + bubbleViewProvider + ") bubbles=" + this.mBubbles);
            return;
        }
        if (this.mExpanded && bubbleViewProvider != null && !z) {
            Bubble bubble = (Bubble) bubbleViewProvider;
            ((BubbleData$$ExternalSyntheticLambda2) this.mTimeSource).getClass();
            bubble.mLastAccessed = System.currentTimeMillis();
            bubble.setSuppressNotification(true);
            bubble.setShowDot(false);
        }
        this.mSelectedBubble = bubbleViewProvider;
        if (z) {
            this.mShowingOverflow = true;
        }
        Update update = this.mStateChange;
        update.selectedBubble = bubbleViewProvider;
        update.selectionChanged = true;
    }

    public void setTimeSource(TimeSource timeSource) {
        this.mTimeSource = timeSource;
    }

    public final void trim() {
        if (((ArrayList) this.mBubbles).size() > this.mMaxBubbles) {
            final int size = ((ArrayList) this.mBubbles).size() - this.mMaxBubbles;
            final ArrayList arrayList = new ArrayList();
            this.mBubbles.stream().sorted(Comparator.comparingLong(new BubbleData$$ExternalSyntheticLambda4())).filter(new BubbleData$$ExternalSyntheticLambda1(2, this)).forEachOrdered(new Consumer() { // from class: com.android.wm.shell.bubbles.BubbleData$$ExternalSyntheticLambda6
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ArrayList arrayList2 = arrayList;
                    Bubble bubble = (Bubble) obj;
                    if (arrayList2.size() < size) {
                        arrayList2.add(bubble);
                    }
                }
            });
            arrayList.forEach(new BubbleData$$ExternalSyntheticLambda7(0, this));
        }
    }
}
