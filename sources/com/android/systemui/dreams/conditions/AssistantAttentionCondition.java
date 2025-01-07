package com.android.systemui.dreams.conditions;

import com.android.systemui.shared.condition.Condition;
import com.google.android.systemui.assist.AssistManagerGoogle;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AssistantAttentionCondition extends Condition {
    public final AssistManagerGoogle mAssistManager;
    public final AnonymousClass1 mVisualQueryAttentionListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.conditions.AssistantAttentionCondition$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    public AssistantAttentionCondition(AssistManagerGoogle assistManagerGoogle) {
        this.mAssistManager = assistManagerGoogle;
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void start() {
        AssistManagerGoogle assistManagerGoogle = this.mAssistManager;
        List list = assistManagerGoogle.mVisualQueryAttentionListeners;
        AnonymousClass1 anonymousClass1 = this.mVisualQueryAttentionListener;
        if (list.contains(anonymousClass1)) {
            return;
        }
        assistManagerGoogle.mVisualQueryAttentionListeners.add(anonymousClass1);
    }

    @Override // com.android.systemui.shared.condition.Condition
    public final void stop() {
        this.mAssistManager.mVisualQueryAttentionListeners.remove(this.mVisualQueryAttentionListener);
    }
}
