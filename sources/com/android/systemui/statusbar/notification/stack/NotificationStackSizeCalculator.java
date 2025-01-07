package com.android.systemui.statusbar.notification.stack;

import android.content.res.Resources;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.wm.shell.R;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.NotNullVar;
import kotlin.reflect.KProperty;
import kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1;
import kotlin.sequences.TakeWhileSequence;
import kotlin.sequences.TakeWhileSequence$iterator$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationStackSizeCalculator {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final LockscreenShadeTransitionController lockscreenShadeTransitionController;
    public final MediaDataManager mediaDataManager;
    public final Resources resources;
    public boolean saveSpaceOnLockscreen;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final SysuiStatusBarStateController statusBarStateController;
    public final NotNullVar maxKeyguardNotifications$delegate = new NotNullVar();
    public final NotNullVar dividerHeight$delegate = new NotNullVar();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class BucketTypeCounter {
        public int important;
        public int ongoing;
        public int other;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class FitResult {
        public static final /* synthetic */ FitResult[] $VALUES;
        public static final FitResult FIT;
        public static final FitResult FIT_IF_SAVE_SPACE;
        public static final FitResult NO_FIT;

        static {
            FitResult fitResult = new FitResult("FIT", 0);
            FIT = fitResult;
            FitResult fitResult2 = new FitResult("FIT_IF_SAVE_SPACE", 1);
            FIT_IF_SAVE_SPACE = fitResult2;
            FitResult fitResult3 = new FitResult("NO_FIT", 2);
            NO_FIT = fitResult3;
            FitResult[] fitResultArr = {fitResult, fitResult2, fitResult3};
            $VALUES = fitResultArr;
            EnumEntriesKt.enumEntries(fitResultArr);
        }

        public static FitResult valueOf(String str) {
            return (FitResult) Enum.valueOf(FitResult.class, str);
        }

        public static FitResult[] values() {
            return (FitResult[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SpaceNeeded {
        public final float whenEnoughSpace;
        public final float whenSavingSpace;

        public SpaceNeeded(float f, float f2) {
            this.whenEnoughSpace = f;
            this.whenSavingSpace = f2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpaceNeeded)) {
                return false;
            }
            SpaceNeeded spaceNeeded = (SpaceNeeded) obj;
            return Float.compare(this.whenEnoughSpace, spaceNeeded.whenEnoughSpace) == 0 && Float.compare(this.whenSavingSpace, spaceNeeded.whenSavingSpace) == 0;
        }

        public final int hashCode() {
            return Float.hashCode(this.whenSavingSpace) + (Float.hashCode(this.whenEnoughSpace) * 31);
        }

        public final String toString() {
            return "SpaceNeeded(whenEnoughSpace=" + this.whenEnoughSpace + ", whenSavingSpace=" + this.whenSavingSpace + ")";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StackHeight {
        public final float notifsHeight;
        public final float notifsHeightSavingSpace;
        public final float shelfHeightWithSpaceBefore;
        public final boolean shouldForceIntoShelf;

        public StackHeight(boolean z, float f, float f2, float f3) {
            this.notifsHeight = f;
            this.notifsHeightSavingSpace = f2;
            this.shelfHeightWithSpaceBefore = f3;
            this.shouldForceIntoShelf = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StackHeight)) {
                return false;
            }
            StackHeight stackHeight = (StackHeight) obj;
            return Float.compare(this.notifsHeight, stackHeight.notifsHeight) == 0 && Float.compare(this.notifsHeightSavingSpace, stackHeight.notifsHeightSavingSpace) == 0 && Float.compare(this.shelfHeightWithSpaceBefore, stackHeight.shelfHeightWithSpaceBefore) == 0 && this.shouldForceIntoShelf == stackHeight.shouldForceIntoShelf;
        }

        public final int hashCode() {
            return Boolean.hashCode(this.shouldForceIntoShelf) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.notifsHeight) * 31, this.notifsHeightSavingSpace, 31), this.shelfHeightWithSpaceBefore, 31);
        }

        public final String toString() {
            return "StackHeight(notifsHeight=" + this.notifsHeight + ", notifsHeightSavingSpace=" + this.notifsHeightSavingSpace + ", shelfHeightWithSpaceBefore=" + this.shelfHeightWithSpaceBefore + ", shouldForceIntoShelf=" + this.shouldForceIntoShelf + ")";
        }
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(NotificationStackSizeCalculator.class, "maxKeyguardNotifications", "getMaxKeyguardNotifications()I", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl, new MutablePropertyReference1Impl(NotificationStackSizeCalculator.class, "dividerHeight", "getDividerHeight()F", 0)};
    }

    public NotificationStackSizeCalculator(SysuiStatusBarStateController sysuiStatusBarStateController, LockscreenShadeTransitionController lockscreenShadeTransitionController, MediaDataManager mediaDataManager, Resources resources, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        this.statusBarStateController = sysuiStatusBarStateController;
        this.lockscreenShadeTransitionController = lockscreenShadeTransitionController;
        this.mediaDataManager = mediaDataManager;
        this.resources = resources;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        updateResources();
    }

    public static final FitResult access$canStackFitInSpace(NotificationStackSizeCalculator notificationStackSizeCalculator, StackHeight stackHeight, float f, float f2) {
        notificationStackSizeCalculator.getClass();
        float f3 = stackHeight.notifsHeight;
        float f4 = stackHeight.shelfHeightWithSpaceBefore;
        float f5 = stackHeight.notifsHeightSavingSpace;
        if (f4 == 0.0f) {
            return f3 <= f ? FitResult.FIT : f5 <= f ? FitResult.FIT_IF_SAVE_SPACE : FitResult.NO_FIT;
        }
        float f6 = f + f2;
        return f3 + f4 <= f6 ? FitResult.FIT : f5 + f4 <= f6 ? FitResult.FIT_IF_SAVE_SPACE : FitResult.NO_FIT;
    }

    public static boolean isShowable(ExpandableView expandableView, boolean z) {
        boolean z2;
        if (expandableView.getVisibility() == 8 || ((z2 = expandableView instanceof NotificationShelf))) {
            return false;
        }
        if (!z) {
            return true;
        }
        if (!(expandableView instanceof ExpandableNotificationRow)) {
            return (expandableView instanceof MediaContainerView) && ((MediaContainerView) expandableView).getHeight() != 0;
        }
        if (z2 || expandableView.getVisibility() == 8) {
            return false;
        }
        return true;
    }

    public static int lastIndexWhile(SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1, Function1 function1) {
        TakeWhileSequence$iterator$1 takeWhileSequence$iterator$1 = new TakeWhileSequence$iterator$1(new TakeWhileSequence(sequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1, function1));
        int i = 0;
        while (takeWhileSequence$iterator$1.hasNext()) {
            takeWhileSequence$iterator$1.next();
            i++;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwCountOverflow();
                throw null;
            }
        }
        return i - 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int computeMaxKeyguardNotifications(com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout r5, final float r6, final float r7, float r8) {
        /*
            r4 = this;
            float r0 = r6 + r7
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r1 = 0
            if (r0 > 0) goto L9
            return r1
        L9:
            com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1 r0 = new com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1
            r2 = 0
            r0.<init>(r4, r5, r8, r2)
            kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 r8 = new kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1
            r8.<init>(r0)
            com.android.systemui.media.controls.domain.pipeline.MediaDataManager r0 = r4.mediaDataManager
            boolean r0 = r0.hasActiveMediaOrRecommendation()
            r2 = 1
            if (r0 == 0) goto L29
            android.content.res.Resources r0 = r4.resources
            com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl r3 = r4.splitShadeStateController
            boolean r0 = r3.shouldUseSplitNotificationShade(r0)
            if (r0 != 0) goto L29
            r0 = r2
            goto L2a
        L29:
            r0 = r1
        L2a:
            com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeMaxKeyguardNotifications$maxNotifWithoutSavingSpace$1 r3 = new com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeMaxKeyguardNotifications$maxNotifWithoutSavingSpace$1
            r3.<init>()
            int r3 = lastIndexWhile(r8, r3)
            if (r0 == 0) goto L37
            r0 = 2
            goto L38
        L37:
            r0 = r2
        L38:
            if (r3 < r0) goto L3d
            r4.saveSpaceOnLockscreen = r1
            goto L48
        L3d:
            r4.saveSpaceOnLockscreen = r2
            com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeMaxKeyguardNotifications$10 r0 = new com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$computeMaxKeyguardNotifications$10
            r0.<init>()
            int r3 = lastIndexWhile(r8, r0)
        L48:
            kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1 r5 = com.android.systemui.util.ConvenienceExtensionsKt.getChildren(r5)
            com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$childrenSequence$1 r6 = com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$childrenSequence$1.INSTANCE
            kotlin.sequences.TransformingSequence r7 = new kotlin.sequences.TransformingSequence
            r7.<init>(r5, r6)
            com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$showableChildren$1 r5 = new com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator$showableChildren$1
            r5.<init>(r4)
            kotlin.sequences.FilteringSequence r5 = kotlin.sequences.SequencesKt.filter(r7, r5)
            java.util.List r5 = kotlin.sequences.SequencesKt.toList(r5)
            java.util.Iterator r5 = r5.iterator()
        L64:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L7b
            java.lang.Object r6 = r5.next()
            com.android.systemui.statusbar.notification.row.ExpandableView r6 = (com.android.systemui.statusbar.notification.row.ExpandableView) r6
            boolean r7 = r6 instanceof com.android.systemui.statusbar.notification.row.ExpandableNotificationRow
            if (r7 == 0) goto L64
            com.android.systemui.statusbar.notification.row.ExpandableNotificationRow r6 = (com.android.systemui.statusbar.notification.row.ExpandableNotificationRow) r6
            boolean r7 = r4.saveSpaceOnLockscreen
            r6.mSaveSpaceOnLockscreen = r7
            goto L64
        L7b:
            boolean r5 = r4.onLockscreen()
            if (r5 == 0) goto L95
            kotlin.properties.NotNullVar r5 = r4.maxKeyguardNotifications$delegate
            kotlin.reflect.KProperty[] r6 = com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator.$$delegatedProperties
            r6 = r6[r1]
            java.lang.Object r4 = r5.getValue(r4, r6)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            int r3 = java.lang.Math.min(r4, r3)
        L95:
            int r4 = java.lang.Math.max(r1, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator.computeMaxKeyguardNotifications(com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout, float, float, float):int");
    }

    public final SpaceNeeded getSpaceNeeded(ExpandableView expandableView, int i, ExpandableView expandableView2, NotificationStackScrollLayout notificationStackScrollLayout, boolean z) {
        isShowable(expandableView, z);
        float heightWithoutLockscreenConstraints = expandableView.getHeightWithoutLockscreenConstraints();
        if (i != 0) {
            StackScrollAlgorithm stackScrollAlgorithm = notificationStackScrollLayout.mStackScrollAlgorithm;
            NotificationSectionsManager notificationSectionsManager = notificationStackScrollLayout.mSectionsManager;
            AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
            float f = ambientState.mFractionToShade;
            boolean isOnKeyguard = ambientState.isOnKeyguard();
            stackScrollAlgorithm.getClass();
            r2 = (StackScrollAlgorithm.childNeedsGapHeight(notificationSectionsManager, i, expandableView, expandableView2) ? stackScrollAlgorithm.getGapForLocation(f, isOnKeyguard) : 0.0f) + ((Number) this.dividerHeight$delegate.getValue(this, $$delegatedProperties[1])).floatValue();
        }
        float minHeight = ((!z || ((expandableView instanceof ExpandableNotificationRow) && ((ExpandableNotificationRow) expandableView).mEntry.isStickyAndNotDemoted())) ? heightWithoutLockscreenConstraints : expandableView.getMinHeight(true)) + r2;
        if (z) {
            heightWithoutLockscreenConstraints = expandableView.getMinHeight(true);
        }
        return new SpaceNeeded(minHeight, heightWithoutLockscreenConstraints + r2);
    }

    public final boolean onLockscreen() {
        return ((StatusBarStateControllerImpl) this.statusBarStateController).mState == 1 && this.lockscreenShadeTransitionController.getFractionToShade() == 0.0f;
    }

    public final void updateResources() {
        int integer = this.resources.getInteger(R.integer.keyguard_max_notification_count);
        if (integer < 0) {
            integer = Integer.MAX_VALUE;
        }
        KProperty[] kPropertyArr = $$delegatedProperties;
        KProperty kProperty = kPropertyArr[0];
        this.maxKeyguardNotifications$delegate.value = Integer.valueOf(integer);
        float max = Math.max(1.0f, this.resources.getDimensionPixelSize(R.dimen.notification_divider_height));
        KProperty kProperty2 = kPropertyArr[1];
        this.dividerHeight$delegate.value = Float.valueOf(max);
    }
}
