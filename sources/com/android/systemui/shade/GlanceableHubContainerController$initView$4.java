package com.android.systemui.shade;

import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.log.core.Logger;
import java.util.function.Consumer;
import kotlin.Triple;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$BooleanRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlanceableHubContainerController$initView$4 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ GlanceableHubContainerController$initView$4(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Boolean bool = (Boolean) obj;
                ((GlanceableHubContainerController) this.this$0).anyBouncerShowing = bool.booleanValue();
                GlanceableHubContainerController glanceableHubContainerController = (GlanceableHubContainerController) this.this$0;
                if (glanceableHubContainerController.hubShowing) {
                    Logger logger = glanceableHubContainerController.logger;
                    LogMessage obtain = logger.getBuffer().obtain(logger.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$initView$4.1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("New value for anyBouncerShowing: ", ((LogMessage) obj2).getBool1());
                        }
                    }, null);
                    obtain.setBool1(bool.booleanValue());
                    logger.getBuffer().commit(obtain);
                }
                GlanceableHubContainerController.access$updateTouchHandlingState((GlanceableHubContainerController) this.this$0);
                break;
            case 1:
                Triple triple = (Triple) obj;
                boolean booleanValue = ((Boolean) triple.component1()).booleanValue();
                boolean booleanValue2 = ((Boolean) triple.component2()).booleanValue();
                boolean booleanValue3 = ((Boolean) triple.component3()).booleanValue();
                GlanceableHubContainerController glanceableHubContainerController2 = (GlanceableHubContainerController) this.this$0;
                glanceableHubContainerController2.shadeConsumingTouches = booleanValue2;
                glanceableHubContainerController2.shadeShowing = !booleanValue3;
                boolean z = true;
                boolean z2 = booleanValue && !booleanValue2;
                boolean z3 = !booleanValue3 && (glanceableHubContainerController2.userNotInteractiveAtShadeFullyExpanded || z2);
                glanceableHubContainerController2.userNotInteractiveAtShadeFullyExpanded = z3;
                if (!z3 && !z2) {
                    z = false;
                }
                if (z != glanceableHubContainerController2.shadeShowingAndConsumingTouches && glanceableHubContainerController2.hubShowing) {
                    Logger logger2 = glanceableHubContainerController2.logger;
                    LogMessage obtain2 = logger2.getBuffer().obtain(logger2.getTag(), LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.shade.GlanceableHubContainerController$initView$10$1$1
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("New value for shadeShowingAndConsumingTouches: ", ((LogMessage) obj2).getBool1());
                        }
                    }, null);
                    obtain2.setBool1(z);
                    logger2.getBuffer().commit(obtain2);
                }
                glanceableHubContainerController2.shadeShowingAndConsumingTouches = z;
                GlanceableHubContainerController.access$updateTouchHandlingState((GlanceableHubContainerController) this.this$0);
                break;
            case 2:
                ((GlanceableHubContainerController) this.this$0).isDreaming = ((Boolean) obj).booleanValue();
                break;
            case 3:
                ((GlanceableHubContainerController) this.this$0).onLockscreen = ((Boolean) obj).booleanValue();
                break;
            case 4:
                ((GlanceableHubContainerController) this.this$0).hubShowing = ((Boolean) obj).booleanValue();
                GlanceableHubContainerController.access$updateTouchHandlingState((GlanceableHubContainerController) this.this$0);
                break;
            case 5:
                ((GlanceableHubContainerController) this.this$0).inEditModeTransition = ((Boolean) obj).booleanValue();
                GlanceableHubContainerController.access$updateTouchHandlingState((GlanceableHubContainerController) this.this$0);
                break;
            default:
                if (((Boolean) obj).booleanValue()) {
                    ((Ref$BooleanRef) this.this$0).element = true;
                    break;
                }
                break;
        }
    }
}
