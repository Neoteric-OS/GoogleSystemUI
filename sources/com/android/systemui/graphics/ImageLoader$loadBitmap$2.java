package com.android.systemui.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ImageDecoder;
import android.util.Log;
import com.android.systemui.graphics.ImageLoader;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ImageLoader$loadBitmap$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $allocator;
    final /* synthetic */ int $maxHeight;
    final /* synthetic */ int $maxWidth;
    final /* synthetic */ ImageLoader.Source $source;
    int label;
    final /* synthetic */ ImageLoader this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageLoader$loadBitmap$2(ImageLoader imageLoader, ImageLoader.Source source, int i, int i2, int i3, Continuation continuation) {
        super(2, continuation);
        this.this$0 = imageLoader;
        this.$source = source;
        this.$maxWidth = i;
        this.$maxHeight = i2;
        this.$allocator = i3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ImageLoader$loadBitmap$2(this.this$0, this.$source, this.$maxWidth, this.$maxHeight, this.$allocator, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ImageLoader$loadBitmap$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ImageLoader imageLoader = this.this$0;
        ImageLoader.Source source = this.$source;
        int i = this.$maxWidth;
        int i2 = this.$maxHeight;
        int i3 = this.$allocator;
        imageLoader.getClass();
        try {
            Context context = imageLoader.defaultContext;
            if (!(source instanceof ImageLoader.Uri)) {
                throw new NoWhenBranchMatchedException();
            }
            ImageDecoder.Source createSource = ImageDecoder.createSource(context.getContentResolver(), ((ImageLoader.Uri) source).uri);
            Intrinsics.checkNotNull(createSource);
            return ImageLoader.loadBitmapSync(createSource, i, i2, i3);
        } catch (Resources.NotFoundException e) {
            Log.w("ImageLoader", "Couldn't load resource " + source, e);
            return null;
        }
    }
}
