# from

- 将一个Iterable, 一个Future, 或者一个数组，内部通过`代理`的方式转换成一个Observable
- 内部：
    - Future转换为OnSubscribe是通过OnSubscribeToObservableFuture进行
    - Iterable转换通过OnSubscribeFromIterable进行
    - 数组通过OnSubscribeFromArray转换

代码

```
Observable<T> from(Future<? extends T>
Observable<T> from(Future<? extends T>, long timeout, TimeUnit)
Observable<T> from(Future<? extends T>, Scheduler)

Observable<T> fromCallable(Callable<? extends T>)

Observable<T> fromAsync(Action1<AsyncEmitter<T>>, BackpressureMode)

Observable<T> from(Iterable<? extends T>)
Observable<T> from(T[])


例子：
//Future
Future<String> futrue= Executors.newSingleThreadExecutor().submit(new Callable<String>() {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "maplejaw";
    }
});
Observable.from(futrue)
          .subscribe(new Action1<String>() {
    @Override
    public void call(String s) {

    }
});
```

就这些了