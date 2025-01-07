package com.android.systemui.clipboardoverlay;

import android.net.Uri;
import android.util.Log;
import android.util.Size;
import com.android.wm.shell.R;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ClipboardImageLoader$load$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ ClipboardImageLoader this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.clipboardoverlay.ClipboardImageLoader$load$2$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Uri $uri;
        int label;
        final /* synthetic */ ClipboardImageLoader this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ClipboardImageLoader clipboardImageLoader, Uri uri, Continuation continuation) {
            super(2, continuation);
            this.this$0 = clipboardImageLoader;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                int dimensionPixelSize = this.this$0.context.getResources().getDimensionPixelSize(R.dimen.overlay_x_scale);
                return this.this$0.context.getContentResolver().loadThumbnail(this.$uri, new Size(dimensionPixelSize, dimensionPixelSize * 4), null);
            } catch (IOException e) {
                this.this$0.getClass();
                Log.e("ClipboardImageLoader", "Thumbnail loading failed!", e);
                return null;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClipboardImageLoader$load$2(ClipboardImageLoader clipboardImageLoader, Uri uri, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clipboardImageLoader;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClipboardImageLoader$load$2(this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClipboardImageLoader$load$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ClipboardImageLoader clipboardImageLoader = this.this$0;
            CoroutineDispatcher coroutineDispatcher = clipboardImageLoader.bgDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(clipboardImageLoader, this.$uri, null);
            this.label = 1;
            obj = BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
