package com.android.systemui.shade;

import com.android.systemui.common.buffer.RingBuffer;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NPVCDownEventState {
    public static final List TABLE_HEADERS = CollectionsKt__CollectionsKt.listOf("Timestamp", "X", "Y", "QSTouchAboveFalsingThreshold", "Dozing", "Collapsed", "CanCollapseOnQQS", "ListenForHeadsUp", "AllowExpandForSmallExpansion", "TouchSlopExceededBeforeDown", "LastEventSynthesized");
    public long timeStamp = 0;
    public float x = 0.0f;
    public float y = 0.0f;
    public boolean qsTouchAboveFalsingThreshold = false;
    public boolean dozing = false;
    public boolean collapsed = false;
    public boolean canCollapseOnQQS = false;
    public boolean listenForHeadsUp = false;
    public boolean allowExpandForSmallExpansion = false;
    public boolean touchSlopExceededBeforeDown = false;
    public boolean lastEventSynthesized = false;
    public final Lazy asStringList$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.shade.NPVCDownEventState$asStringList$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CollectionsKt__CollectionsKt.listOf(NPVCDownEventStateKt.DATE_FORMAT.format(Long.valueOf(NPVCDownEventState.this.timeStamp)), String.valueOf(NPVCDownEventState.this.x), String.valueOf(NPVCDownEventState.this.y), String.valueOf(NPVCDownEventState.this.qsTouchAboveFalsingThreshold), String.valueOf(NPVCDownEventState.this.dozing), String.valueOf(NPVCDownEventState.this.collapsed), String.valueOf(NPVCDownEventState.this.canCollapseOnQQS), String.valueOf(NPVCDownEventState.this.listenForHeadsUp), String.valueOf(NPVCDownEventState.this.allowExpandForSmallExpansion), String.valueOf(NPVCDownEventState.this.touchSlopExceededBeforeDown), String.valueOf(NPVCDownEventState.this.lastEventSynthesized));
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Buffer {
        public final RingBuffer buffer = new RingBuffer(50, new Function0() { // from class: com.android.systemui.shade.NPVCDownEventState$Buffer$buffer$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new NPVCDownEventState();
            }
        });
    }
}
