package com.android.systemui.dreams.touch;

import android.graphics.Rect;
import android.graphics.Region;
import android.view.GestureDetector;
import android.view.InputEvent;
import android.view.MotionEvent;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.ambient.touch.TouchHandler;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.dreams.touch.CommunalTouchHandler;
import com.android.systemui.shared.system.InputChannelCompat$InputEventListener;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;
import kotlinx.coroutines.Job;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalTouchHandler implements TouchHandler {
    public final Optional mCentralSurfaces;
    public final ArrayList mFlows;
    public final int mInitiationWidth;
    final Consumer mIsCommunalAvailableCallback;
    public Boolean mIsEnabled = Boolean.FALSE;
    public int mLayoutDirection;
    final Consumer mLayoutDirectionCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.touch.CommunalTouchHandler$1, reason: invalid class name */
    public final class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return true;
        }
    }

    public CommunalTouchHandler(Optional optional, int i, CommunalInteractor communalInteractor, ConfigurationInteractor configurationInteractor, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        this.mFlows = arrayList;
        this.mLayoutDirection = 0;
        final int i2 = 0;
        Consumer consumer = new Consumer(this) { // from class: com.android.systemui.dreams.touch.CommunalTouchHandler$$ExternalSyntheticLambda2
            public final /* synthetic */ CommunalTouchHandler f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i3 = i2;
                CommunalTouchHandler communalTouchHandler = this.f$0;
                switch (i3) {
                    case 0:
                        communalTouchHandler.mIsEnabled = (Boolean) obj;
                        break;
                    default:
                        communalTouchHandler.getClass();
                        communalTouchHandler.mLayoutDirection = ((Integer) obj).intValue();
                        break;
                }
            }
        };
        this.mIsCommunalAvailableCallback = consumer;
        final int i3 = 1;
        Consumer consumer2 = new Consumer(this) { // from class: com.android.systemui.dreams.touch.CommunalTouchHandler$$ExternalSyntheticLambda2
            public final /* synthetic */ CommunalTouchHandler f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i32 = i3;
                CommunalTouchHandler communalTouchHandler = this.f$0;
                switch (i32) {
                    case 0:
                        communalTouchHandler.mIsEnabled = (Boolean) obj;
                        break;
                    default:
                        communalTouchHandler.getClass();
                        communalTouchHandler.mLayoutDirection = ((Integer) obj).intValue();
                        break;
                }
            }
        };
        this.mLayoutDirectionCallback = consumer2;
        this.mInitiationWidth = i;
        this.mCentralSurfaces = optional;
        arrayList.add(JavaAdapterKt.collectFlow(lifecycle, communalInteractor.isCommunalAvailable, consumer));
        arrayList.add(JavaAdapterKt.collectFlow(lifecycle, configurationInteractor.layoutDirection, consumer2));
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void getTouchInitiationRegion(Rect rect, Region region, Rect rect2) {
        Rect rect3 = new Rect(rect);
        int width = rect3.width() - this.mInitiationWidth;
        if (this.mLayoutDirection == 0) {
            rect3.inset(width, 0, 0, 0);
        } else {
            rect3.inset(0, 0, width, 0);
        }
        region.op(rect3, Region.Op.UNION);
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final Boolean isEnabled() {
        return this.mIsEnabled;
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void onDestroy() {
        Iterator it = this.mFlows.iterator();
        while (it.hasNext()) {
            ((Job) it.next()).cancel(new CancellationException());
        }
        this.mFlows.clear();
    }

    @Override // com.android.systemui.ambient.touch.TouchHandler
    public final void onSessionStart(final TouchMonitor.TouchSessionImpl touchSessionImpl) {
        if (this.mIsEnabled.booleanValue()) {
            this.mCentralSurfaces.ifPresent(new Consumer() { // from class: com.android.systemui.dreams.touch.CommunalTouchHandler$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CommunalTouchHandler communalTouchHandler = CommunalTouchHandler.this;
                    final TouchMonitor.TouchSessionImpl touchSessionImpl2 = touchSessionImpl;
                    final CentralSurfaces centralSurfaces = (CentralSurfaces) obj;
                    communalTouchHandler.getClass();
                    touchSessionImpl2.mEventListeners.add(new InputChannelCompat$InputEventListener() { // from class: com.android.systemui.dreams.touch.CommunalTouchHandler$$ExternalSyntheticLambda1
                        @Override // com.android.systemui.shared.system.InputChannelCompat$InputEventListener
                        public final void onInputEvent(InputEvent inputEvent) {
                            ((CentralSurfacesImpl) CentralSurfaces.this).mGlanceableHubContainerController.onTouchEvent((MotionEvent) inputEvent);
                            if (inputEvent == null || ((MotionEvent) inputEvent).getAction() != 1) {
                                return;
                            }
                            touchSessionImpl2.pop();
                        }
                    });
                    touchSessionImpl2.mGestureListeners.add(new CommunalTouchHandler.AnonymousClass1());
                }
            });
        }
    }
}
