# flatMap

- 将Observable发射的数据变换为Observables集合
- 然后将这些Observable发射的数据平坦化的放进一个单独的Observable
- 内部采用merge合并
- 通俗点解释就是
    - 对于原始Observable的每一个item，flatMap都会将其转为一个Observable
    - 所以就有了很多个Observable
    - 这些个Observable会merge起来

关于其他参数：
- flatMap(Function<? super T, ? extends Publisher<? extends R>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize)
- flatMap(final Function<? super T, ? extends Publisher<? extends U>> mapper,  final BiFunction<? super T, ? super U, ? extends R> combiner, boolean delayErrors, int maxConcurrency, int bufferSize)
- `Function<? super T, ? extends Publisher<? extends R>> mapper`
- `final BiFunction<? super T, ? super U, ? extends R> combiner`：a function that combines one item emitted by each of the source and collection Publishers and returns an item to be emitted by the resulting Publisher
    - 下面有示例代码，这个可以根据原始Item，根据这个Item由flatMap生成的Inner Observable发射的每一个新item，来转成一个新的Item类型
    - 所以Inner Observable有几个Item，这个BiFunction就走了几次
- `boolean delayErrors`：true，则任何error都会等所有item发完再发出，false，则出错直接发出
    - 默认false
- `int maxConcurrency`：最多几个Publishers可以同时被subscribed， the maximum number of Publishers that may be subscribed to concurrently
    - 默认bufferSize()
- `int bufferSize`：可以从inner Publisher里预取的element个数， the number of elements to prefetch from each inner Publisher
    - 默认bufferSize()


还有：
- fromMapIterable
- fromMapMaybe
- fromMapSingle
- fromMapComplatable


```
bufferSize()

static final int BUFFER_SIZE;
static {
    BUFFER_SIZE = Math.max(16, Integer.getInteger("rx2.buffer-size", 128));
}
public static int bufferSize() {
    return BUFFER_SIZE;
}

```

```
Flowable.just(Lists.arrayList("111", "222", "333"), Lists.arrayList("444", "555", "666"))
    .flatMap(new Function<List<String>, Publisher<String>>() {
        @Override
        public Publisher<String> apply(List<String> s) throws Exception {
            return Flowable.fromIterable(s).delay(1000, TimeUnit.MILLISECONDS);  ///--------主要是这句
        }
    }).subscribe(new Consumer<CharSequence>() {
         @Override
         public void accept(CharSequence s) throws Exception {
             notifyy(s + "");
         }
     });
     ///进的是List<String>，出来的是Publisher<String>
```

```
复杂形式：

.flatMap(new Function<List<String>, Publisher<String>>() {
        @Override
        public Publisher<String> apply(List<String> s) throws Exception {
            return Flowable.fromIterable(s).delay(1000, TimeUnit.MILLISECONDS);  ///--------主要是这句
        }
    }, new BiFunction<List<String>, String, Integer>() {      ////这个会对每个元素再做一次变换
        @Override
        public Integer apply(List<String> strings, String s) throws Exception {
            return null;
        }
    }, false, 16, 16)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Consumer<Integer>() {
        @Override
        public void accept(Integer s) throws Exception {
            notifyy(s + "");
        }
    });

```
