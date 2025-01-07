package androidx.compose.animation;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EnterTransition {
    public static final EnterTransition None = new EnterTransitionImpl(new TransitionData((Fade) null, (Slide) null, (ChangeSize) null, (Scale) null, (Map) null, 63));

    public final boolean equals(Object obj) {
        return (obj instanceof EnterTransition) && Intrinsics.areEqual(((EnterTransitionImpl) ((EnterTransition) obj)).data, ((EnterTransitionImpl) this).data);
    }

    public final int hashCode() {
        return ((EnterTransitionImpl) this).data.hashCode();
    }

    public final EnterTransition plus(EnterTransition enterTransition) {
        TransitionData transitionData = ((EnterTransitionImpl) enterTransition).data;
        Fade fade = transitionData.fade;
        if (fade == null) {
            fade = ((EnterTransitionImpl) this).data.fade;
        }
        Fade fade2 = fade;
        Slide slide = transitionData.slide;
        if (slide == null) {
            slide = ((EnterTransitionImpl) this).data.slide;
        }
        Slide slide2 = slide;
        ChangeSize changeSize = transitionData.changeSize;
        if (changeSize == null) {
            changeSize = ((EnterTransitionImpl) this).data.changeSize;
        }
        ChangeSize changeSize2 = changeSize;
        Scale scale = transitionData.scale;
        if (scale == null) {
            scale = ((EnterTransitionImpl) this).data.scale;
        }
        Map map = ((EnterTransitionImpl) this).data.effectsMap;
        Map map2 = transitionData.effectsMap;
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return new EnterTransitionImpl(new TransitionData(fade2, slide2, changeSize2, scale, linkedHashMap, 16));
    }

    public final String toString() {
        if (equals(None)) {
            return "EnterTransition.None";
        }
        StringBuilder sb = new StringBuilder("EnterTransition: \nFade - ");
        TransitionData transitionData = ((EnterTransitionImpl) this).data;
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
        return sb.toString();
    }
}
