package androidx.compose.animation;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ExitTransition {
    public static final ExitTransition KeepUntilTransitionsFinished;
    public static final ExitTransition None;

    static {
        Scale scale = null;
        Map map = null;
        Fade fade = null;
        Slide slide = null;
        ChangeSize changeSize = null;
        None = new ExitTransitionImpl(new TransitionData(fade, slide, changeSize, scale, map, 63));
        KeepUntilTransitionsFinished = new ExitTransitionImpl(new TransitionData(fade, slide, changeSize, scale, map, 47));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ExitTransition) && Intrinsics.areEqual(((ExitTransitionImpl) ((ExitTransition) obj)).data, ((ExitTransitionImpl) this).data);
    }

    public final int hashCode() {
        return ((ExitTransitionImpl) this).data.hashCode();
    }

    public final ExitTransition plus(ExitTransition exitTransition) {
        TransitionData transitionData = ((ExitTransitionImpl) exitTransition).data;
        Fade fade = transitionData.fade;
        if (fade == null) {
            fade = ((ExitTransitionImpl) this).data.fade;
        }
        Fade fade2 = fade;
        Slide slide = transitionData.slide;
        if (slide == null) {
            slide = ((ExitTransitionImpl) this).data.slide;
        }
        Slide slide2 = slide;
        ChangeSize changeSize = transitionData.changeSize;
        if (changeSize == null) {
            changeSize = ((ExitTransitionImpl) this).data.changeSize;
        }
        ChangeSize changeSize2 = changeSize;
        Scale scale = transitionData.scale;
        if (scale == null) {
            scale = ((ExitTransitionImpl) this).data.scale;
        }
        Scale scale2 = scale;
        boolean z = transitionData.hold || ((ExitTransitionImpl) this).data.hold;
        Map map = ((ExitTransitionImpl) this).data.effectsMap;
        Map map2 = transitionData.effectsMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return new ExitTransitionImpl(new TransitionData(fade2, slide2, changeSize2, scale2, z, linkedHashMap));
    }

    public final String toString() {
        if (equals(None)) {
            return "ExitTransition.None";
        }
        if (equals(KeepUntilTransitionsFinished)) {
            return "ExitTransition.KeepUntilTransitionsFinished";
        }
        StringBuilder sb = new StringBuilder("ExitTransition: \nFade - ");
        TransitionData transitionData = ((ExitTransitionImpl) this).data;
        Fade fade = transitionData.fade;
        sb.append(fade != null ? fade.toString() : null);
        sb.append(",\nSlide - ");
        Slide slide = transitionData.slide;
        sb.append(slide != null ? slide.toString() : null);
        sb.append(",\nShrink - ");
        ChangeSize changeSize = transitionData.changeSize;
        sb.append(changeSize != null ? changeSize.toString() : null);
        sb.append(",\nScale - ");
        Scale scale = transitionData.scale;
        sb.append(scale != null ? scale.toString() : null);
        sb.append(",\nKeepUntilTransitionsFinished - ");
        sb.append(transitionData.hold);
        return sb.toString();
    }
}
