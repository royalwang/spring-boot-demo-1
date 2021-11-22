package com.bambrow.async.utils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class AsyncUtils {
    public static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futureList) {
        CompletableFuture<Void> allFuturesResult = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        return allFuturesResult.thenApply(x ->
                futureList.stream().map(CompletableFuture::join).collect(Collectors.<T>toList())
        );
    }
}
