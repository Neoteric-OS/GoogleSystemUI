package com.android.compose.animation.scene;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import com.android.compose.animation.scene.transformation.AnchoredTranslate;
import com.android.compose.animation.scene.transformation.DrawScale;
import com.android.compose.animation.scene.transformation.EdgeTranslate;
import com.android.compose.animation.scene.transformation.Fade;
import com.android.compose.animation.scene.transformation.OverscrollTranslate;
import com.android.compose.animation.scene.transformation.PropertyTransformation;
import com.android.compose.animation.scene.transformation.RangedPropertyTransformation;
import com.android.compose.animation.scene.transformation.SharedElementTransformation;
import com.android.compose.animation.scene.transformation.Transformation;
import com.android.compose.animation.scene.transformation.Translate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TransformationSpecImpl implements TransformationSpec {
    public final Map cache = new LinkedHashMap();
    public final DefaultSwipeDistance distance;
    public final FiniteAnimationSpec progressSpec;
    public final SpringSpec swipeSpec;
    public final List transformations;

    public TransformationSpecImpl(FiniteAnimationSpec finiteAnimationSpec, SpringSpec springSpec, DefaultSwipeDistance defaultSwipeDistance, List list) {
        this.progressSpec = finiteAnimationSpec;
        this.swipeSpec = springSpec;
        this.distance = defaultSwipeDistance;
        this.transformations = list;
    }

    public static final void computeTransformations$onPropertyTransformation(TransformationSpecImpl transformationSpecImpl, Ref$ObjectRef ref$ObjectRef, ElementKey elementKey, Ref$ObjectRef ref$ObjectRef2, Ref$ObjectRef ref$ObjectRef3, PropertyTransformation propertyTransformation, PropertyTransformation propertyTransformation2) {
        if (propertyTransformation2 instanceof Translate ? true : propertyTransformation2 instanceof OverscrollTranslate ? true : propertyTransformation2 instanceof EdgeTranslate ? true : propertyTransformation2 instanceof AnchoredTranslate) {
            Transformation transformation = (Transformation) ref$ObjectRef.element;
            transformationSpecImpl.getClass();
            throwIfNotNull(transformation, elementKey, "offset");
            ref$ObjectRef.element = propertyTransformation;
            return;
        }
        if (propertyTransformation2 instanceof DrawScale) {
            Transformation transformation2 = (Transformation) ref$ObjectRef2.element;
            transformationSpecImpl.getClass();
            throwIfNotNull(transformation2, elementKey, "drawScale");
            ref$ObjectRef2.element = propertyTransformation;
            return;
        }
        if (!(propertyTransformation2 instanceof Fade)) {
            if (propertyTransformation2 instanceof RangedPropertyTransformation) {
                computeTransformations$onPropertyTransformation(transformationSpecImpl, ref$ObjectRef, elementKey, ref$ObjectRef2, ref$ObjectRef3, propertyTransformation, ((RangedPropertyTransformation) propertyTransformation2).delegate);
            }
        } else {
            Transformation transformation3 = (Transformation) ref$ObjectRef3.element;
            transformationSpecImpl.getClass();
            throwIfNotNull(transformation3, elementKey, "alpha");
            ref$ObjectRef3.element = propertyTransformation;
        }
    }

    public static void throwIfNotNull(Transformation transformation, ElementKey elementKey, String str) {
        if (transformation == null) {
            return;
        }
        throw new IllegalStateException((elementKey + " has multiple " + str + " transformations").toString());
    }

    public final ElementTransformations transformations$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(ElementKey elementKey, ContentKey contentKey) {
        int i;
        Transformation transformation;
        int i2;
        Map map = this.cache;
        Object obj = map.get(elementKey);
        if (obj == null) {
            obj = new LinkedHashMap();
            map.put(elementKey, obj);
        }
        Map map2 = (Map) obj;
        Object obj2 = map2.get(contentKey);
        if (obj2 == null) {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef4 = new Ref$ObjectRef();
            List list = this.transformations;
            int size = list.size();
            Transformation transformation2 = null;
            int i3 = 0;
            while (i3 < size) {
                Transformation transformation3 = (Transformation) list.get(i3);
                if (transformation3.getMatcher().matches(elementKey)) {
                    if (transformation3 instanceof SharedElementTransformation) {
                        throwIfNotNull(transformation2, elementKey, "shared");
                        transformation2 = transformation3;
                    } else {
                        if (transformation3 instanceof PropertyTransformation) {
                            PropertyTransformation propertyTransformation = (PropertyTransformation) transformation3;
                            i = i3;
                            transformation = transformation2;
                            i2 = size;
                            computeTransformations$onPropertyTransformation(this, ref$ObjectRef, elementKey, ref$ObjectRef3, ref$ObjectRef4, propertyTransformation, propertyTransformation);
                        } else {
                            i = i3;
                            transformation = transformation2;
                            i2 = size;
                        }
                        transformation2 = transformation;
                        i3 = i + 1;
                        size = i2;
                    }
                }
                i = i3;
                i2 = size;
                i3 = i + 1;
                size = i2;
            }
            obj2 = new ElementTransformations((SharedElementTransformation) transformation2, (PropertyTransformation) ref$ObjectRef.element, (PropertyTransformation) ref$ObjectRef2.element, (PropertyTransformation) ref$ObjectRef3.element, (PropertyTransformation) ref$ObjectRef4.element);
            map2.put(contentKey, obj2);
        }
        return (ElementTransformations) obj2;
    }
}
