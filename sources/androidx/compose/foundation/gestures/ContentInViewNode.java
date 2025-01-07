package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.BringIntoViewSpec;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.concurrent.CancellationException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ContentInViewNode extends Modifier.Node implements LayoutAwareModifierNode, CompositionLocalConsumerModifierNode {
    public BringIntoViewSpec bringIntoViewSpec;
    public LayoutCoordinates focusedChild;
    public Rect focusedChildBoundsFromPreviousRemeasure;
    public boolean isAnimationRunning;
    public Orientation orientation;
    public boolean reverseDirection;
    public final ScrollingLogic scrollingLogic;
    public boolean trackingFocusedChild;
    public final BringIntoViewRequestPriorityQueue bringIntoViewRequests = new BringIntoViewRequestPriorityQueue();
    public long viewportSize = 0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Request {
        public final CancellableContinuationImpl continuation;
        public final Function0 currentBounds;

        public Request(Function0 function0, CancellableContinuationImpl cancellableContinuationImpl) {
            this.currentBounds = function0;
            this.continuation = cancellableContinuationImpl;
        }

        public final String toString() {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            if (cancellableContinuationImpl.context.get(CoroutineName.Key) != null) {
                throw new ClassCastException();
            }
            StringBuilder sb = new StringBuilder("Request@");
            int hashCode = hashCode();
            CharsKt.checkRadix(16);
            sb.append(Integer.toString(hashCode, 16));
            sb.append("(currentBounds()=");
            sb.append(this.currentBounds.invoke());
            sb.append(", continuation=");
            sb.append(cancellableContinuationImpl);
            sb.append(')');
            return sb.toString();
        }
    }

    public ContentInViewNode(Orientation orientation, ScrollingLogic scrollingLogic, boolean z, BringIntoViewSpec bringIntoViewSpec) {
        this.orientation = orientation;
        this.scrollingLogic = scrollingLogic;
        this.reverseDirection = z;
        this.bringIntoViewSpec = bringIntoViewSpec;
    }

    public static final float access$calculateScrollDelta(ContentInViewNode contentInViewNode, BringIntoViewSpec bringIntoViewSpec) {
        Rect rect;
        float calculateScrollDistance;
        int compare;
        if (!IntSize.m683equalsimpl0(contentInViewNode.viewportSize, 0L)) {
            MutableVector mutableVector = contentInViewNode.bringIntoViewRequests.requests;
            int i = mutableVector.size;
            if (i > 0) {
                int i2 = i - 1;
                Object[] objArr = mutableVector.content;
                rect = null;
                while (true) {
                    Rect rect2 = (Rect) ((Request) objArr[i2]).currentBounds.invoke();
                    if (rect2 != null) {
                        long m321getSizeNHjbRc = rect2.m321getSizeNHjbRc();
                        long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(contentInViewNode.viewportSize);
                        int ordinal = contentInViewNode.orientation.ordinal();
                        if (ordinal == 0) {
                            compare = Float.compare(Float.intBitsToFloat((int) (m321getSizeNHjbRc & 4294967295L)), Float.intBitsToFloat((int) (m685toSizeozmzZPI & 4294967295L)));
                        } else {
                            if (ordinal != 1) {
                                throw new NoWhenBranchMatchedException();
                            }
                            compare = Float.compare(Float.intBitsToFloat((int) (m321getSizeNHjbRc >> 32)), Float.intBitsToFloat((int) (m685toSizeozmzZPI >> 32)));
                        }
                        if (compare <= 0) {
                            rect = rect2;
                        } else if (rect == null) {
                            rect = rect2;
                        }
                    }
                    i2--;
                    if (i2 < 0) {
                        break;
                    }
                }
            } else {
                rect = null;
            }
            if (rect == null) {
                Rect focusedChildBounds = contentInViewNode.trackingFocusedChild ? contentInViewNode.getFocusedChildBounds() : null;
                if (focusedChildBounds != null) {
                    rect = focusedChildBounds;
                }
            }
            long m685toSizeozmzZPI2 = IntSizeKt.m685toSizeozmzZPI(contentInViewNode.viewportSize);
            int ordinal2 = contentInViewNode.orientation.ordinal();
            if (ordinal2 == 0) {
                float f = rect.bottom;
                float f2 = rect.top;
                calculateScrollDistance = bringIntoViewSpec.calculateScrollDistance(f2, f - f2, Float.intBitsToFloat((int) (m685toSizeozmzZPI2 & 4294967295L)));
            } else {
                if (ordinal2 != 1) {
                    throw new NoWhenBranchMatchedException();
                }
                float f3 = rect.right;
                float f4 = rect.left;
                calculateScrollDistance = bringIntoViewSpec.calculateScrollDistance(f4, f3 - f4, Float.intBitsToFloat((int) (m685toSizeozmzZPI2 >> 32)));
            }
            return calculateScrollDistance;
        }
        return 0.0f;
    }

    public final Object bringChildIntoView(Function0 function0, Continuation continuation) {
        Rect rect = (Rect) function0.invoke();
        Unit unit = Unit.INSTANCE;
        if (rect != null && !m55isMaxVisibleO0kMr_c(rect, this.viewportSize)) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
            cancellableContinuationImpl.initCancellability();
            final Request request = new Request(function0, cancellableContinuationImpl);
            final BringIntoViewRequestPriorityQueue bringIntoViewRequestPriorityQueue = this.bringIntoViewRequests;
            bringIntoViewRequestPriorityQueue.getClass();
            Rect rect2 = (Rect) function0.invoke();
            if (rect2 == null) {
                cancellableContinuationImpl.resumeWith(unit);
            } else {
                cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.foundation.gestures.BringIntoViewRequestPriorityQueue$enqueue$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        BringIntoViewRequestPriorityQueue.this.requests.remove(request);
                        return Unit.INSTANCE;
                    }
                });
                MutableVector mutableVector = bringIntoViewRequestPriorityQueue.requests;
                IntRange until = RangesKt.until(0, mutableVector.size);
                int i = until.first;
                int i2 = until.last;
                if (i <= i2) {
                    while (true) {
                        Rect rect3 = (Rect) ((Request) mutableVector.content[i2]).currentBounds.invoke();
                        if (rect3 != null) {
                            Rect intersect = rect2.intersect(rect3);
                            if (intersect.equals(rect2)) {
                                mutableVector.add(i2 + 1, request);
                                break;
                            }
                            if (!intersect.equals(rect3)) {
                                CancellationException cancellationException = new CancellationException("bringIntoView call interrupted by a newer, non-overlapping call");
                                int i3 = mutableVector.size - 1;
                                if (i3 <= i2) {
                                    while (true) {
                                        ((Request) mutableVector.content[i2]).continuation.cancel(cancellationException);
                                        if (i3 == i2) {
                                            break;
                                        }
                                        i3++;
                                    }
                                }
                            }
                        }
                        if (i2 == i) {
                            break;
                        }
                        i2--;
                    }
                }
                mutableVector.add(0, request);
                if (!this.isAnimationRunning) {
                    launchAnimation();
                }
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return result;
            }
        }
        return unit;
    }

    public final Rect getFocusedChildBounds() {
        if (!this.isAttached) {
            return null;
        }
        NodeCoordinator requireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(this);
        LayoutCoordinates layoutCoordinates = this.focusedChild;
        if (layoutCoordinates != null) {
            if (!layoutCoordinates.isAttached()) {
                layoutCoordinates = null;
            }
            if (layoutCoordinates != null) {
                return requireLayoutCoordinates.localBoundingBoxOf(layoutCoordinates, false);
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    /* renamed from: isMaxVisible-O0kMr_c, reason: not valid java name */
    public final boolean m55isMaxVisibleO0kMr_c(Rect rect, long j) {
        long m56relocationOffsetBMxPBkI = m56relocationOffsetBMxPBkI(rect, j);
        return Math.abs(Float.intBitsToFloat((int) (m56relocationOffsetBMxPBkI >> 32))) <= 0.5f && Math.abs(Float.intBitsToFloat((int) (m56relocationOffsetBMxPBkI & 4294967295L))) <= 0.5f;
    }

    public final void launchAnimation() {
        BringIntoViewSpec bringIntoViewSpec = this.bringIntoViewSpec;
        if (bringIntoViewSpec == null) {
            bringIntoViewSpec = (BringIntoViewSpec) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, BringIntoViewSpec_androidKt.LocalBringIntoViewSpec);
        }
        if (this.isAnimationRunning) {
            InlineClassHelperKt.throwIllegalStateException("launchAnimation called when previous animation was running");
        }
        BringIntoViewSpec.Companion.getClass();
        BuildersKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new ContentInViewNode$launchAnimation$2(this, new UpdatableAnimationState(BringIntoViewSpec.Companion.DefaultScrollAnimationSpec), bringIntoViewSpec, null), 1);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* renamed from: onRemeasured-ozmzZPI */
    public final void mo43onRemeasuredozmzZPI(long j) {
        int compare;
        Rect focusedChildBounds;
        long j2 = this.viewportSize;
        this.viewportSize = j;
        int ordinal = this.orientation.ordinal();
        if (ordinal == 0) {
            compare = Intrinsics.compare((int) (j & 4294967295L), (int) (4294967295L & j2));
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            compare = Intrinsics.compare((int) (j >> 32), (int) (j2 >> 32));
        }
        if (compare < 0 && (focusedChildBounds = getFocusedChildBounds()) != null) {
            Rect rect = this.focusedChildBoundsFromPreviousRemeasure;
            if (rect == null) {
                rect = focusedChildBounds;
            }
            if (!this.isAnimationRunning && !this.trackingFocusedChild && m55isMaxVisibleO0kMr_c(rect, j2) && !m55isMaxVisibleO0kMr_c(focusedChildBounds, j)) {
                this.trackingFocusedChild = true;
                launchAnimation();
            }
            this.focusedChildBoundsFromPreviousRemeasure = focusedChildBounds;
        }
    }

    /* renamed from: relocationOffset-BMxPBkI, reason: not valid java name */
    public final long m56relocationOffsetBMxPBkI(Rect rect, long j) {
        long j2;
        long j3;
        long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(j);
        int ordinal = this.orientation.ordinal();
        if (ordinal == 0) {
            BringIntoViewSpec bringIntoViewSpec = this.bringIntoViewSpec;
            if (bringIntoViewSpec == null) {
                bringIntoViewSpec = (BringIntoViewSpec) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, BringIntoViewSpec_androidKt.LocalBringIntoViewSpec);
            }
            float f = rect.bottom;
            float f2 = rect.top;
            float calculateScrollDistance = bringIntoViewSpec.calculateScrollDistance(f2, f - f2, Float.intBitsToFloat((int) (m685toSizeozmzZPI & 4294967295L)));
            long floatToRawIntBits = Float.floatToRawIntBits(0.0f);
            long floatToRawIntBits2 = Float.floatToRawIntBits(calculateScrollDistance);
            j2 = floatToRawIntBits << 32;
            j3 = floatToRawIntBits2 & 4294967295L;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            BringIntoViewSpec bringIntoViewSpec2 = this.bringIntoViewSpec;
            if (bringIntoViewSpec2 == null) {
                bringIntoViewSpec2 = (BringIntoViewSpec) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, BringIntoViewSpec_androidKt.LocalBringIntoViewSpec);
            }
            float f3 = rect.right;
            float f4 = rect.left;
            j2 = Float.floatToRawIntBits(bringIntoViewSpec2.calculateScrollDistance(f4, f3 - f4, Float.intBitsToFloat((int) (m685toSizeozmzZPI >> 32)))) << 32;
            j3 = Float.floatToRawIntBits(0.0f) & 4294967295L;
        }
        return j2 | j3;
    }
}
