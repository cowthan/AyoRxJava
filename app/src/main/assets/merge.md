# merge

- 按时间顺序，所以会出现多个Observable交叉
- 内部也是调用了concat
- mergeDelayError，如果出现错误，错误会留到所有item都发射完才发出

```
final String[] list1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
final String[] list2 = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};

Flowable<String> flowable1 = Flowable.interval(0, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
    @Override
    public String apply(Long aLong) throws Exception {
        return list1[aLong.intValue()];
    }
});

Flowable<String> flowable2 = Flowable.interval(1, 1, TimeUnit.SECONDS).map(new Function<Long, String>() {
    @Override
    public String apply(Long aLong) throws Exception {
        return list2[aLong.intValue()];
    }
});

task = Flowable.mergeArray(flowable1, flowable2)
```