package com.android.systemui.statusbar.notification.icon.domain.interactor;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.sequences.SequencesKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconsInteractor$filteredNotifSet$1 extends SuspendLambda implements Function4 {
    final /* synthetic */ boolean $forceShowHeadsUp;
    final /* synthetic */ boolean $showAmbient;
    final /* synthetic */ boolean $showDismissed;
    final /* synthetic */ boolean $showLowPriority;
    final /* synthetic */ boolean $showPulsing;
    final /* synthetic */ boolean $showRepliedMessages;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ NotificationIconsInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationIconsInteractor$filteredNotifSet$1(NotificationIconsInteractor notificationIconsInteractor, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, Continuation continuation) {
        super(4, continuation);
        this.this$0 = notificationIconsInteractor;
        this.$forceShowHeadsUp = z;
        this.$showAmbient = z2;
        this.$showLowPriority = z3;
        this.$showDismissed = z4;
        this.$showRepliedMessages = z5;
        this.$showPulsing = z6;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj3).booleanValue();
        NotificationIconsInteractor$filteredNotifSet$1 notificationIconsInteractor$filteredNotifSet$1 = new NotificationIconsInteractor$filteredNotifSet$1(this.this$0, this.$forceShowHeadsUp, this.$showAmbient, this.$showLowPriority, this.$showDismissed, this.$showRepliedMessages, this.$showPulsing, (Continuation) obj4);
        notificationIconsInteractor$filteredNotifSet$1.L$0 = (List) obj;
        notificationIconsInteractor$filteredNotifSet$1.L$1 = (String) obj2;
        notificationIconsInteractor$filteredNotifSet$1.Z$0 = booleanValue;
        return notificationIconsInteractor$filteredNotifSet$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        final String str = (String) this.L$1;
        final boolean z = this.Z$0;
        CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1 collectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1 = new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list);
        final NotificationIconsInteractor notificationIconsInteractor = this.this$0;
        final boolean z2 = this.$forceShowHeadsUp;
        final boolean z3 = this.$showAmbient;
        final boolean z4 = this.$showLowPriority;
        final boolean z5 = this.$showDismissed;
        final boolean z6 = this.$showRepliedMessages;
        final boolean z7 = this.$showPulsing;
        return SequencesKt.toSet(SequencesKt.filter(collectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1, new Function1() { // from class: com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor$filteredNotifSet$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:40:0x0076, code lost:
            
                if (r10 == true) goto L11;
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invoke(java.lang.Object r10) {
                /*
                    r9 = this;
                    com.android.systemui.statusbar.notification.shared.ActiveNotificationModel r10 = (com.android.systemui.statusbar.notification.shared.ActiveNotificationModel) r10
                    com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor r0 = com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor.this
                    boolean r1 = r2
                    boolean r2 = r3
                    boolean r3 = r4
                    boolean r4 = r5
                    boolean r5 = r6
                    boolean r6 = r7
                    java.lang.String r7 = r8
                    boolean r9 = r9
                    r0.getClass()
                    r8 = 1
                    if (r1 == 0) goto L24
                    java.lang.String r1 = r10.key
                    boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7)
                    if (r1 == 0) goto L24
                    goto L7b
                L24:
                    r1 = 0
                    if (r2 != 0) goto L2d
                    boolean r7 = r10.isAmbient
                    if (r7 == 0) goto L2d
                L2b:
                    r8 = r1
                    goto L7b
                L2d:
                    if (r3 != 0) goto L34
                    boolean r3 = r10.isSilent
                    if (r3 == 0) goto L34
                    goto L2b
                L34:
                    if (r4 != 0) goto L3b
                    boolean r3 = r10.isRowDismissed
                    if (r3 == 0) goto L3b
                    goto L2b
                L3b:
                    if (r5 != 0) goto L42
                    boolean r3 = r10.isLastMessageFromReply
                    if (r3 == 0) goto L42
                    goto L2b
                L42:
                    if (r2 != 0) goto L49
                    boolean r2 = r10.isSuppressedFromStatusBar
                    if (r2 == 0) goto L49
                    goto L2b
                L49:
                    if (r6 != 0) goto L52
                    boolean r2 = r10.isPulsing
                    if (r2 == 0) goto L52
                    if (r9 != 0) goto L52
                    goto L2b
                L52:
                    java.util.Optional r9 = r0.bubbles
                    r0 = 0
                    java.lang.Object r9 = r9.orElse(r0)
                    com.android.wm.shell.bubbles.Bubbles r9 = (com.android.wm.shell.bubbles.Bubbles) r9
                    if (r9 == 0) goto L7b
                    java.lang.String r10 = r10.key
                    com.android.wm.shell.bubbles.BubbleController$BubblesImpl r9 = (com.android.wm.shell.bubbles.BubbleController.BubblesImpl) r9
                    com.android.wm.shell.bubbles.BubbleController$BubblesImpl$CachedState r9 = r9.mCachedState
                    monitor-enter(r9)
                    boolean r0 = r9.mIsStackExpanded     // Catch: java.lang.Throwable -> L72
                    if (r0 == 0) goto L74
                    java.lang.String r0 = r9.mSelectedBubbleKey     // Catch: java.lang.Throwable -> L72
                    boolean r10 = r10.equals(r0)     // Catch: java.lang.Throwable -> L72
                    if (r10 == 0) goto L74
                    r10 = r8
                    goto L75
                L72:
                    r10 = move-exception
                    goto L79
                L74:
                    r10 = r1
                L75:
                    monitor-exit(r9)
                    if (r10 != r8) goto L7b
                    goto L2b
                L79:
                    monitor-exit(r9)     // Catch: java.lang.Throwable -> L72
                    throw r10
                L7b:
                    java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
                    return r9
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor$filteredNotifSet$1.AnonymousClass1.invoke(java.lang.Object):java.lang.Object");
            }
        }));
    }
}
