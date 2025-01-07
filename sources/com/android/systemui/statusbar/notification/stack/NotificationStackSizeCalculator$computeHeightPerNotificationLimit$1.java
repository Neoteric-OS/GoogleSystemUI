package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackSizeCalculator;
import com.android.systemui.util.ConvenienceExtensionsKt;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$FloatRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.reflect.KProperty;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1 extends RestrictedSuspendLambda implements Function2 {
    final /* synthetic */ float $shelfHeight;
    final /* synthetic */ NotificationStackScrollLayout $stack;
    float F$0;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;
    final /* synthetic */ NotificationStackSizeCalculator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1(NotificationStackSizeCalculator notificationStackSizeCalculator, NotificationStackScrollLayout notificationStackScrollLayout, float f, Continuation continuation) {
        super(continuation);
        this.this$0 = notificationStackSizeCalculator;
        this.$stack = notificationStackScrollLayout;
        this.$shelfHeight = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1 notificationStackSizeCalculator$computeHeightPerNotificationLimit$1 = new NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1(this.this$0, this.$stack, this.$shelfHeight, continuation);
        notificationStackSizeCalculator$computeHeightPerNotificationLimit$1.L$0 = obj;
        return notificationStackSizeCalculator$computeHeightPerNotificationLimit$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationStackSizeCalculator$computeHeightPerNotificationLimit$1) create((SequenceBuilderIterator) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        NotificationStackSizeCalculator.BucketTypeCounter bucketTypeCounter;
        Ref$ObjectRef ref$ObjectRef;
        Ref$FloatRef ref$FloatRef;
        Ref$FloatRef ref$FloatRef2;
        List list;
        SequenceBuilderIterator sequenceBuilderIterator;
        NotificationStackSizeCalculator notificationStackSizeCalculator;
        NotificationStackScrollLayout notificationStackScrollLayout;
        float f;
        Iterator it;
        int i;
        NotificationStackScrollLayout notificationStackScrollLayout2;
        float floatValue;
        float f2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceBuilderIterator sequenceBuilderIterator2 = (SequenceBuilderIterator) this.L$0;
            NotificationStackSizeCalculator notificationStackSizeCalculator2 = this.this$0;
            NotificationStackScrollLayout notificationStackScrollLayout3 = this.$stack;
            KProperty[] kPropertyArr = NotificationStackSizeCalculator.$$delegatedProperties;
            notificationStackSizeCalculator2.getClass();
            List list2 = SequencesKt.toList(SequencesKt.filter(new TransformingSequence(ConvenienceExtensionsKt.getChildren(notificationStackScrollLayout3), NotificationStackSizeCalculator$childrenSequence$1.INSTANCE), new NotificationStackSizeCalculator$showableChildren$1(notificationStackSizeCalculator2)));
            Ref$FloatRef ref$FloatRef3 = new Ref$FloatRef();
            Ref$FloatRef ref$FloatRef4 = new Ref$FloatRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            boolean onLockscreen = this.this$0.onLockscreen();
            this.this$0.getClass();
            NotificationStackSizeCalculator.StackHeight stackHeight = new NotificationStackSizeCalculator.StackHeight(false, 0.0f, 0.0f, this.$shelfHeight);
            this.L$0 = sequenceBuilderIterator2;
            this.L$1 = list2;
            this.L$2 = ref$FloatRef3;
            this.L$3 = ref$FloatRef4;
            this.L$4 = ref$ObjectRef2;
            this.L$5 = null;
            this.Z$0 = onLockscreen;
            this.label = 1;
            sequenceBuilderIterator2.yield(stackHeight, this);
            return coroutineSingletons;
        }
        if (i2 == 1) {
            z = this.Z$0;
            bucketTypeCounter = (NotificationStackSizeCalculator.BucketTypeCounter) this.L$5;
            ref$ObjectRef = (Ref$ObjectRef) this.L$4;
            ref$FloatRef = (Ref$FloatRef) this.L$3;
            ref$FloatRef2 = (Ref$FloatRef) this.L$2;
            list = (List) this.L$1;
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
            notificationStackSizeCalculator = this.this$0;
            notificationStackScrollLayout = this.$stack;
            f = this.$shelfHeight;
            it = list.iterator();
            i = 0;
        } else {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i = this.I$0;
            f = this.F$0;
            z = this.Z$0;
            it = (Iterator) this.L$8;
            notificationStackScrollLayout = (NotificationStackScrollLayout) this.L$7;
            notificationStackSizeCalculator = (NotificationStackSizeCalculator) this.L$6;
            bucketTypeCounter = (NotificationStackSizeCalculator.BucketTypeCounter) this.L$5;
            ref$ObjectRef = (Ref$ObjectRef) this.L$4;
            ref$FloatRef = (Ref$FloatRef) this.L$3;
            ref$FloatRef2 = (Ref$FloatRef) this.L$2;
            list = (List) this.L$1;
            sequenceBuilderIterator = (SequenceBuilderIterator) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (!it.hasNext()) {
            return Unit.INSTANCE;
        }
        Object next = it.next();
        int i3 = i + 1;
        if (i < 0) {
            CollectionsKt__CollectionsKt.throwIndexOverflow();
            throw null;
        }
        ExpandableView expandableView = (ExpandableView) next;
        NotificationStackSizeCalculator.SpaceNeeded spaceNeeded = notificationStackSizeCalculator.getSpaceNeeded(expandableView, i, (ExpandableView) ref$ObjectRef.element, notificationStackScrollLayout, z);
        boolean z2 = z;
        Iterator it2 = it;
        ref$FloatRef2.element += spaceNeeded.whenEnoughSpace;
        ref$FloatRef.element += spaceNeeded.whenSavingSpace;
        ref$ObjectRef.element = expandableView;
        if (i == CollectionsKt__CollectionsKt.getLastIndex(list)) {
            notificationStackScrollLayout2 = notificationStackScrollLayout;
            f2 = 0.0f;
        } else {
            ExpandableView expandableView2 = (ExpandableView) list.get(i3);
            if (i3 == 0) {
                notificationStackScrollLayout2 = notificationStackScrollLayout;
                floatValue = 0.0f;
            } else {
                StackScrollAlgorithm stackScrollAlgorithm = notificationStackScrollLayout.mStackScrollAlgorithm;
                NotificationSectionsManager notificationSectionsManager = notificationStackScrollLayout.mSectionsManager;
                AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
                notificationStackScrollLayout2 = notificationStackScrollLayout;
                float f3 = ambientState.mFractionToShade;
                boolean isOnKeyguard = ambientState.isOnKeyguard();
                stackScrollAlgorithm.getClass();
                floatValue = ((Number) notificationStackSizeCalculator.dividerHeight$delegate.getValue(notificationStackSizeCalculator, NotificationStackSizeCalculator.$$delegatedProperties[1])).floatValue() + (StackScrollAlgorithm.childNeedsGapHeight(notificationSectionsManager, i3, expandableView2, expandableView) ? stackScrollAlgorithm.getGapForLocation(f3, isOnKeyguard) : 0.0f);
            }
            f2 = floatValue + f;
        }
        NotificationStackSizeCalculator.StackHeight stackHeight2 = new NotificationStackSizeCalculator.StackHeight(false, ref$FloatRef2.element, ref$FloatRef.element, f2);
        this.L$0 = sequenceBuilderIterator;
        this.L$1 = list;
        this.L$2 = ref$FloatRef2;
        this.L$3 = ref$FloatRef;
        this.L$4 = ref$ObjectRef;
        this.L$5 = bucketTypeCounter;
        this.L$6 = notificationStackSizeCalculator;
        this.L$7 = notificationStackScrollLayout2;
        this.L$8 = it2;
        this.Z$0 = z2;
        this.F$0 = f;
        this.I$0 = i3;
        this.label = 2;
        sequenceBuilderIterator.yield(stackHeight2, this);
        return coroutineSingletons;
    }
}
