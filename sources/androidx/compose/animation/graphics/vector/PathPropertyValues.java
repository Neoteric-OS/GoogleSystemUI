package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.vector.PathNode;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PathPropertyValues extends PropertyValues {
    @Override // androidx.compose.animation.graphics.vector.PropertyValues
    public final State createState(Transition transition, String str, final int i, Composer composer, int i2) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(119461169);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        Function3 function3 = new Function3() { // from class: androidx.compose.animation.graphics.vector.PathPropertyValues$createState$timeState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                ((Number) obj3).intValue();
                ComposerImpl composerImpl2 = (ComposerImpl) ((Composer) obj2);
                composerImpl2.startReplaceGroup(2115989621);
                OpaqueKey opaqueKey2 = ComposerKt.invocation;
                FiniteAnimationSpec tween$default = AnimationSpecKt.tween$default(i, 0, EasingKt.LinearEasing, 2);
                if (!((Boolean) ((Transition.Segment) obj).getTargetState()).booleanValue()) {
                    tween$default = new ReversedSpec(tween$default, i);
                }
                composerImpl2.end(false);
                return tween$default;
            }
        };
        int i3 = (i2 & 14) | ((i2 << 3) & 896);
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        int i4 = ((i3 << 3) & 7168) | (i3 & 14);
        boolean booleanValue = ((Boolean) transition.getCurrentState()).booleanValue();
        composerImpl.startReplaceGroup(-1210845840);
        float f = booleanValue ? i : 0.0f;
        composerImpl.end(false);
        Float valueOf = Float.valueOf(f);
        boolean booleanValue2 = ((Boolean) ((SnapshotMutableStateImpl) transition.targetState$delegate).getValue()).booleanValue();
        composerImpl.startReplaceGroup(-1210845840);
        float f2 = booleanValue2 ? i : 0.0f;
        composerImpl.end(false);
        final Transition.TransitionAnimationState createTransitionAnimation = TransitionKt.createTransitionAnimation(transition, valueOf, Float.valueOf(f2), (FiniteAnimationSpec) function3.invoke(transition.getSegment(), composerImpl, 0), twoWayConverter, composerImpl, (i4 & 14) | ((i4 << 6) & 458752));
        boolean changed = ((((i2 & 7168) ^ 3072) > 2048 && composerImpl.changed(this)) || (i2 & 3072) == 2048) | composerImpl.changed(createTransitionAnimation);
        Object rememberedValue = composerImpl.rememberedValue();
        if (changed || rememberedValue == Composer.Companion.Empty) {
            rememberedValue = new Function0() { // from class: androidx.compose.animation.graphics.vector.PathPropertyValues$createState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Object obj;
                    PathNode pathNode;
                    PathPropertyValues pathPropertyValues = PathPropertyValues.this;
                    float floatValue = ((Number) createTransitionAnimation.getValue()).floatValue();
                    ArrayList arrayList = (ArrayList) pathPropertyValues.timestamps;
                    ListIterator listIterator = arrayList.listIterator(arrayList.size());
                    while (true) {
                        if (!listIterator.hasPrevious()) {
                            obj = null;
                            break;
                        }
                        obj = listIterator.previous();
                        if (((Timestamp) obj).timeMillis <= floatValue) {
                            break;
                        }
                    }
                    Timestamp timestamp = (Timestamp) obj;
                    if (timestamp == null) {
                        timestamp = (Timestamp) CollectionsKt.first(pathPropertyValues.timestamps);
                    }
                    float f3 = (floatValue - timestamp.timeMillis) / timestamp.durationMillis;
                    if (timestamp.repeatCount != 0) {
                        int i5 = 0;
                        while (f3 > 1.0f) {
                            f3 -= 1.0f;
                            i5++;
                        }
                        if (timestamp.repeatMode == RepeatMode.Reverse && i5 % 2 != 0) {
                            f3 = 1.0f - f3;
                        }
                    }
                    PropertyValuesHolderPath propertyValuesHolderPath = (PropertyValuesHolderPath) timestamp.holder;
                    Iterator it = propertyValuesHolderPath.animatorKeyframes.iterator();
                    int i6 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i6 = -1;
                            break;
                        }
                        if (((Keyframe) it.next()).fraction >= f3) {
                            break;
                        }
                        i6++;
                    }
                    int i7 = i6 - 1;
                    if (i7 < 0) {
                        i7 = 0;
                    }
                    int i8 = i7 + 1;
                    Easing easing = ((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i8)).interpolator;
                    float f4 = (f3 - ((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i7)).fraction) / (((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i8)).fraction - ((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i7)).fraction);
                    if (f4 < 0.0f) {
                        f4 = 0.0f;
                    }
                    float transform = easing.transform(f4 <= 1.0f ? f4 : 1.0f);
                    List list = (List) ((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i7)).value;
                    List list2 = (List) ((Keyframe) ((ArrayList) propertyValuesHolderPath.animatorKeyframes).get(i8)).value;
                    int min = Math.min(list.size(), list2.size());
                    ArrayList arrayList2 = new ArrayList(min);
                    for (int i9 = 0; i9 < min; i9++) {
                        Object obj2 = list.get(i9);
                        PathNode pathNode2 = (PathNode) list2.get(i9);
                        PathNode pathNode3 = (PathNode) obj2;
                        if (pathNode3 instanceof PathNode.RelativeMoveTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeMoveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeMoveTo relativeMoveTo = (PathNode.RelativeMoveTo) pathNode3;
                            PathNode.RelativeMoveTo relativeMoveTo2 = (PathNode.RelativeMoveTo) pathNode2;
                            pathNode = new PathNode.RelativeMoveTo(MathHelpersKt.lerp(relativeMoveTo.dx, relativeMoveTo2.dx, transform), MathHelpersKt.lerp(relativeMoveTo.dy, relativeMoveTo2.dy, transform));
                        } else if (pathNode3 instanceof PathNode.MoveTo) {
                            if (!(pathNode2 instanceof PathNode.MoveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.MoveTo moveTo = (PathNode.MoveTo) pathNode3;
                            PathNode.MoveTo moveTo2 = (PathNode.MoveTo) pathNode2;
                            pathNode = new PathNode.MoveTo(MathHelpersKt.lerp(moveTo.x, moveTo2.x, transform), MathHelpersKt.lerp(moveTo.y, moveTo2.y, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeLineTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeLineTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeLineTo relativeLineTo = (PathNode.RelativeLineTo) pathNode3;
                            PathNode.RelativeLineTo relativeLineTo2 = (PathNode.RelativeLineTo) pathNode2;
                            pathNode = new PathNode.RelativeLineTo(MathHelpersKt.lerp(relativeLineTo.dx, relativeLineTo2.dx, transform), MathHelpersKt.lerp(relativeLineTo.dy, relativeLineTo2.dy, transform));
                        } else if (pathNode3 instanceof PathNode.LineTo) {
                            if (!(pathNode2 instanceof PathNode.LineTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.LineTo lineTo = (PathNode.LineTo) pathNode3;
                            PathNode.LineTo lineTo2 = (PathNode.LineTo) pathNode2;
                            pathNode = new PathNode.LineTo(MathHelpersKt.lerp(lineTo.x, lineTo2.x, transform), MathHelpersKt.lerp(lineTo.y, lineTo2.y, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeHorizontalTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeHorizontalTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            pathNode = new PathNode.RelativeHorizontalTo(MathHelpersKt.lerp(((PathNode.RelativeHorizontalTo) pathNode3).dx, ((PathNode.RelativeHorizontalTo) pathNode2).dx, transform));
                        } else if (pathNode3 instanceof PathNode.HorizontalTo) {
                            if (!(pathNode2 instanceof PathNode.HorizontalTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            pathNode = new PathNode.HorizontalTo(MathHelpersKt.lerp(((PathNode.HorizontalTo) pathNode3).x, ((PathNode.HorizontalTo) pathNode2).x, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeVerticalTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeVerticalTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            pathNode = new PathNode.RelativeVerticalTo(MathHelpersKt.lerp(((PathNode.RelativeVerticalTo) pathNode3).dy, ((PathNode.RelativeVerticalTo) pathNode2).dy, transform));
                        } else if (pathNode3 instanceof PathNode.VerticalTo) {
                            if (!(pathNode2 instanceof PathNode.VerticalTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            pathNode = new PathNode.VerticalTo(MathHelpersKt.lerp(((PathNode.VerticalTo) pathNode3).y, ((PathNode.VerticalTo) pathNode2).y, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeCurveTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeCurveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeCurveTo relativeCurveTo = (PathNode.RelativeCurveTo) pathNode3;
                            PathNode.RelativeCurveTo relativeCurveTo2 = (PathNode.RelativeCurveTo) pathNode2;
                            pathNode = new PathNode.RelativeCurveTo(MathHelpersKt.lerp(relativeCurveTo.dx1, relativeCurveTo2.dx1, transform), MathHelpersKt.lerp(relativeCurveTo.dy1, relativeCurveTo2.dy1, transform), MathHelpersKt.lerp(relativeCurveTo.dx2, relativeCurveTo2.dx2, transform), MathHelpersKt.lerp(relativeCurveTo.dy2, relativeCurveTo2.dy2, transform), MathHelpersKt.lerp(relativeCurveTo.dx3, relativeCurveTo2.dx3, transform), MathHelpersKt.lerp(relativeCurveTo.dy3, relativeCurveTo2.dy3, transform));
                        } else if (pathNode3 instanceof PathNode.CurveTo) {
                            if (!(pathNode2 instanceof PathNode.CurveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.CurveTo curveTo = (PathNode.CurveTo) pathNode3;
                            PathNode.CurveTo curveTo2 = (PathNode.CurveTo) pathNode2;
                            pathNode = new PathNode.CurveTo(MathHelpersKt.lerp(curveTo.x1, curveTo2.x1, transform), MathHelpersKt.lerp(curveTo.y1, curveTo2.y1, transform), MathHelpersKt.lerp(curveTo.x2, curveTo2.x2, transform), MathHelpersKt.lerp(curveTo.y2, curveTo2.y2, transform), MathHelpersKt.lerp(curveTo.x3, curveTo2.x3, transform), MathHelpersKt.lerp(curveTo.y3, curveTo2.y3, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeReflectiveCurveTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeReflectiveCurveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo = (PathNode.RelativeReflectiveCurveTo) pathNode3;
                            PathNode.RelativeReflectiveCurveTo relativeReflectiveCurveTo2 = (PathNode.RelativeReflectiveCurveTo) pathNode2;
                            pathNode = new PathNode.RelativeReflectiveCurveTo(MathHelpersKt.lerp(relativeReflectiveCurveTo.dx1, relativeReflectiveCurveTo2.dx1, transform), MathHelpersKt.lerp(relativeReflectiveCurveTo.dy1, relativeReflectiveCurveTo2.dy1, transform), MathHelpersKt.lerp(relativeReflectiveCurveTo.dx2, relativeReflectiveCurveTo2.dx2, transform), MathHelpersKt.lerp(relativeReflectiveCurveTo.dy2, relativeReflectiveCurveTo2.dy2, transform));
                        } else if (pathNode3 instanceof PathNode.ReflectiveCurveTo) {
                            if (!(pathNode2 instanceof PathNode.ReflectiveCurveTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.ReflectiveCurveTo reflectiveCurveTo = (PathNode.ReflectiveCurveTo) pathNode3;
                            PathNode.ReflectiveCurveTo reflectiveCurveTo2 = (PathNode.ReflectiveCurveTo) pathNode2;
                            pathNode = new PathNode.ReflectiveCurveTo(MathHelpersKt.lerp(reflectiveCurveTo.x1, reflectiveCurveTo2.x1, transform), MathHelpersKt.lerp(reflectiveCurveTo.y1, reflectiveCurveTo2.y1, transform), MathHelpersKt.lerp(reflectiveCurveTo.x2, reflectiveCurveTo2.x2, transform), MathHelpersKt.lerp(reflectiveCurveTo.y2, reflectiveCurveTo2.y2, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeQuadTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeQuadTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeQuadTo relativeQuadTo = (PathNode.RelativeQuadTo) pathNode3;
                            PathNode.RelativeQuadTo relativeQuadTo2 = (PathNode.RelativeQuadTo) pathNode2;
                            pathNode = new PathNode.RelativeQuadTo(MathHelpersKt.lerp(relativeQuadTo.dx1, relativeQuadTo2.dx1, transform), MathHelpersKt.lerp(relativeQuadTo.dy1, relativeQuadTo2.dy1, transform), MathHelpersKt.lerp(relativeQuadTo.dx2, relativeQuadTo2.dx2, transform), MathHelpersKt.lerp(relativeQuadTo.dy2, relativeQuadTo2.dy2, transform));
                        } else if (pathNode3 instanceof PathNode.QuadTo) {
                            if (!(pathNode2 instanceof PathNode.QuadTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.QuadTo quadTo = (PathNode.QuadTo) pathNode3;
                            PathNode.QuadTo quadTo2 = (PathNode.QuadTo) pathNode2;
                            pathNode = new PathNode.QuadTo(MathHelpersKt.lerp(quadTo.x1, quadTo2.x1, transform), MathHelpersKt.lerp(quadTo.y1, quadTo2.y1, transform), MathHelpersKt.lerp(quadTo.x2, quadTo2.x2, transform), MathHelpersKt.lerp(quadTo.y2, quadTo2.y2, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeReflectiveQuadTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeReflectiveQuadTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo = (PathNode.RelativeReflectiveQuadTo) pathNode3;
                            PathNode.RelativeReflectiveQuadTo relativeReflectiveQuadTo2 = (PathNode.RelativeReflectiveQuadTo) pathNode2;
                            pathNode = new PathNode.RelativeReflectiveQuadTo(MathHelpersKt.lerp(relativeReflectiveQuadTo.dx, relativeReflectiveQuadTo2.dx, transform), MathHelpersKt.lerp(relativeReflectiveQuadTo.dy, relativeReflectiveQuadTo2.dy, transform));
                        } else if (pathNode3 instanceof PathNode.ReflectiveQuadTo) {
                            if (!(pathNode2 instanceof PathNode.ReflectiveQuadTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.ReflectiveQuadTo reflectiveQuadTo = (PathNode.ReflectiveQuadTo) pathNode3;
                            PathNode.ReflectiveQuadTo reflectiveQuadTo2 = (PathNode.ReflectiveQuadTo) pathNode2;
                            pathNode = new PathNode.ReflectiveQuadTo(MathHelpersKt.lerp(reflectiveQuadTo.x, reflectiveQuadTo2.x, transform), MathHelpersKt.lerp(reflectiveQuadTo.y, reflectiveQuadTo2.y, transform));
                        } else if (pathNode3 instanceof PathNode.RelativeArcTo) {
                            if (!(pathNode2 instanceof PathNode.RelativeArcTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.RelativeArcTo relativeArcTo = (PathNode.RelativeArcTo) pathNode3;
                            PathNode.RelativeArcTo relativeArcTo2 = (PathNode.RelativeArcTo) pathNode2;
                            pathNode = new PathNode.RelativeArcTo(MathHelpersKt.lerp(relativeArcTo.horizontalEllipseRadius, relativeArcTo2.horizontalEllipseRadius, transform), MathHelpersKt.lerp(relativeArcTo.verticalEllipseRadius, relativeArcTo2.verticalEllipseRadius, transform), MathHelpersKt.lerp(relativeArcTo.theta, relativeArcTo2.theta, transform), relativeArcTo.isMoreThanHalf, relativeArcTo.isPositiveArc, MathHelpersKt.lerp(relativeArcTo.arcStartDx, relativeArcTo2.arcStartDx, transform), MathHelpersKt.lerp(relativeArcTo.arcStartDy, relativeArcTo2.arcStartDy, transform));
                        } else if (!(pathNode3 instanceof PathNode.ArcTo)) {
                            pathNode = PathNode.Close.INSTANCE;
                            if (!Intrinsics.areEqual(pathNode3, pathNode)) {
                                throw new NoWhenBranchMatchedException();
                            }
                        } else {
                            if (!(pathNode2 instanceof PathNode.ArcTo)) {
                                throw new IllegalArgumentException("start and stop path nodes have different types");
                            }
                            PathNode.ArcTo arcTo = (PathNode.ArcTo) pathNode3;
                            PathNode.ArcTo arcTo2 = (PathNode.ArcTo) pathNode2;
                            pathNode = new PathNode.ArcTo(MathHelpersKt.lerp(arcTo.horizontalEllipseRadius, arcTo2.horizontalEllipseRadius, transform), MathHelpersKt.lerp(arcTo.verticalEllipseRadius, arcTo2.verticalEllipseRadius, transform), MathHelpersKt.lerp(arcTo.theta, arcTo2.theta, transform), arcTo.isMoreThanHalf, arcTo.isPositiveArc, MathHelpersKt.lerp(arcTo.arcStartX, arcTo2.arcStartX, transform), MathHelpersKt.lerp(arcTo.arcStartY, arcTo2.arcStartY, transform));
                        }
                        arrayList2.add(pathNode);
                    }
                    return arrayList2;
                }
            };
            composerImpl.updateRememberedValue(rememberedValue);
        }
        State derivedStateOf = SnapshotStateKt.derivedStateOf((Function0) rememberedValue);
        composerImpl.end(false);
        return derivedStateOf;
    }
}
