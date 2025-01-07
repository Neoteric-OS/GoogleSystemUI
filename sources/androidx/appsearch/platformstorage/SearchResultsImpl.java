package androidx.appsearch.platformstorage;

import android.app.appsearch.AppSearchResult;
import android.app.appsearch.SearchResult;
import androidx.appsearch.app.SearchResults;
import androidx.appsearch.app.SearchSpec;
import androidx.appsearch.exceptions.AppSearchException;
import androidx.appsearch.platformstorage.converter.SearchResultToPlatformConverter;
import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SearchResultsImpl implements SearchResults {
    public final Executor mExecutor;
    public final android.app.appsearch.SearchResults mPlatformResults;

    public SearchResultsImpl(android.app.appsearch.SearchResults searchResults, SearchSpec searchSpec, Executor executor) {
        searchResults.getClass();
        this.mPlatformResults = searchResults;
        executor.getClass();
        this.mExecutor = executor;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.mPlatformResults.close();
    }

    @Override // androidx.appsearch.app.SearchResults
    public final ListenableFuture getNextPageAsync() {
        final ResolvableFuture resolvableFuture = new ResolvableFuture();
        this.mPlatformResults.getNextPage(this.mExecutor, new Consumer() { // from class: androidx.appsearch.platformstorage.SearchResultsImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SearchResultsImpl searchResultsImpl = SearchResultsImpl.this;
                ResolvableFuture resolvableFuture2 = resolvableFuture;
                AppSearchResult appSearchResult = (AppSearchResult) obj;
                searchResultsImpl.getClass();
                if (!appSearchResult.isSuccess()) {
                    resolvableFuture2.setException(new AppSearchException(appSearchResult.getResultCode(), appSearchResult.getErrorMessage(), null));
                    return;
                }
                List list = (List) appSearchResult.getResultValue();
                ArrayList arrayList = new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(SearchResultToPlatformConverter.toJetpackSearchResult((SearchResult) list.get(i)));
                }
                resolvableFuture2.set(arrayList);
            }
        });
        return resolvableFuture;
    }
}
