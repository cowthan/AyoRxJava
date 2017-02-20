# flatMap

- 将Observable发射的数据变换为Observables集合
- 然后将这些Observable发射的数据平坦化的放进一个单独的Observable
- 内部采用merge合并
- 通俗点解释就是
    - 对于原始Observable的每一个item，flatMap都会将其转为一个Observable
    - 所以就有了很多个Observable
    - 这些个Observable会merge起来

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