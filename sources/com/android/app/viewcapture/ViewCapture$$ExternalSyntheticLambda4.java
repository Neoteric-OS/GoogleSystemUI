package com.android.app.viewcapture;

import android.os.Trace;
import android.view.View;
import com.android.app.viewcapture.ViewCapture;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ViewCapture$$ExternalSyntheticLambda4 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ViewCapture$$ExternalSyntheticLambda4(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ViewCapture.ViewPropertyRef viewPropertyRef;
        ViewCapture.ViewPropertyRef viewPropertyRef2;
        ViewCapture.ViewPropertyRef viewPropertyRef3;
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                View view = (View) obj2;
                ViewCapture.WindowListener windowListener = (ViewCapture.WindowListener) obj;
                View view2 = windowListener.mRoot;
                if (view == view2) {
                    view2.getViewTreeObserver().removeOnDrawListener(windowListener);
                    windowListener.mRoot = null;
                    break;
                }
                break;
            default:
                ViewCapture.WindowListener windowListener2 = (ViewCapture.WindowListener) obj2;
                ViewCapture.ViewPropertyRef viewPropertyRef4 = (ViewCapture.ViewPropertyRef) obj;
                windowListener2.getClass();
                Trace.beginSection("vc#copyCleanViewsFromLastFrameBg");
                long j = viewPropertyRef4.elapsedRealtimeNanos;
                int i2 = windowListener2.mFrameIndexBg + 1;
                windowListener2.mFrameIndexBg = i2;
                if (i2 >= ViewCapture.this.mMemorySize) {
                    windowListener2.mFrameIndexBg = 0;
                }
                long[] jArr = windowListener2.mFrameTimesNanosBg;
                int i3 = windowListener2.mFrameIndexBg;
                jArr[i3] = j;
                ViewCapture.ViewPropertyRef viewPropertyRef5 = windowListener2.mNodesBg[i3];
                ViewCapture.ViewPropertyRef viewPropertyRef6 = viewPropertyRef4;
                ViewCapture.ViewPropertyRef viewPropertyRef7 = null;
                ViewCapture.ViewPropertyRef viewPropertyRef8 = null;
                while (true) {
                    viewPropertyRef6.clazz = viewPropertyRef6.view.getClass();
                    viewPropertyRef6.hashCode = viewPropertyRef6.view.hashCode();
                    viewPropertyRef6.id = viewPropertyRef6.view.getId();
                    viewPropertyRef6.view = null;
                    if (viewPropertyRef5 == null) {
                        viewPropertyRef = viewPropertyRef5;
                        viewPropertyRef5 = new ViewCapture.ViewPropertyRef();
                    } else {
                        viewPropertyRef = viewPropertyRef5.next;
                        viewPropertyRef5.next = null;
                    }
                    if (viewPropertyRef6.childCount < 0) {
                        int i4 = viewPropertyRef6.hashCode;
                        int i5 = windowListener2.mFrameIndexBg;
                        if (i5 == 0) {
                            i5 = ViewCapture.this.mMemorySize;
                        }
                        viewPropertyRef2 = windowListener2.mNodesBg[i5 - 1];
                        while (viewPropertyRef2 != null && viewPropertyRef2.hashCode != i4) {
                            viewPropertyRef2 = viewPropertyRef2.next;
                        }
                        if (viewPropertyRef2 != null) {
                            viewPropertyRef2.transferTo(viewPropertyRef6);
                        } else {
                            viewPropertyRef6.childCount = 0;
                        }
                    } else {
                        viewPropertyRef2 = null;
                    }
                    viewPropertyRef6.transferTo(viewPropertyRef5);
                    if (viewPropertyRef7 == null) {
                        viewPropertyRef7 = viewPropertyRef5;
                    } else {
                        viewPropertyRef8.next = viewPropertyRef5;
                    }
                    if (viewPropertyRef2 != null) {
                        int i6 = viewPropertyRef2.childCount;
                        while (i6 > 0) {
                            viewPropertyRef2 = viewPropertyRef2.next;
                            i6 = (i6 - 1) + viewPropertyRef2.childCount;
                            if (viewPropertyRef == null) {
                                viewPropertyRef3 = viewPropertyRef;
                                viewPropertyRef = new ViewCapture.ViewPropertyRef();
                            } else {
                                viewPropertyRef3 = viewPropertyRef.next;
                                viewPropertyRef.next = null;
                            }
                            viewPropertyRef2.transferTo(viewPropertyRef);
                            viewPropertyRef5.next = viewPropertyRef;
                            viewPropertyRef5 = viewPropertyRef;
                            viewPropertyRef = viewPropertyRef3;
                        }
                    }
                    viewPropertyRef8 = viewPropertyRef5;
                    viewPropertyRef5 = viewPropertyRef;
                    ViewCapture.ViewPropertyRef viewPropertyRef9 = viewPropertyRef6.next;
                    if (viewPropertyRef9 == null) {
                        ViewCapture.MAIN_EXECUTOR.execute(new ViewCapture$$ExternalSyntheticLambda7(windowListener2, viewPropertyRef4, viewPropertyRef6, 1));
                        windowListener2.mNodesBg[windowListener2.mFrameIndexBg] = viewPropertyRef7;
                        ViewCapture.this.getClass();
                        Trace.endSection();
                        break;
                    } else {
                        viewPropertyRef6 = viewPropertyRef9;
                    }
                }
        }
    }
}
