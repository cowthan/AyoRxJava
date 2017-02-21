# sample采样系列

- 采样：sample和throttleLast一样
    - 可以定时采样，就是所谓的采样频率
    - 可以根据一个second Publisher来，second Publisher发射一个item，就采样一下
    - 采样的结果就是发射source Publisher的最新的item
- 注意：采样结果不会重复，如果这一次采样的item还是上回采样的item，这回就不会发射item，直到有新的
- 其他
    - debounce和throtleWithTimeout：这俩一样
        - by time：提供一个时间间隔，如1秒，对于source publisher，如果1秒内没有发射新item，就采样之前最新的item
        - by sampler: 对于每个item-A，都创建一个新的Publisher，在这个新的Publisher发射item之前，旧Publisher发射了item，则item-A会被suppress，否则，就采样item-A
        - debounce(999, TimeUnit.MILLISECONDS) 相当于 throttleWithTimeout(1000, TimeUnit.MILLISECONDS)
        - an onCompleted notification will not trigger a throttle
    - throttleFirst：和throttleLast相反，对于一个时间窗内，throttleFirst采样的是第一条item，而throttleLast采样的是最后一条item

```
.sample(1500, TimeUnit.MILLISECONDS)

.sample(Flowable.interval(...))...

debounce(2000, TimeUnit.MILLISECONDS)  ///2秒之内，没有再发射item，则此前的最后一条item被采样

.debounce(new Function<Long, Publisher<Long>>() {
    @Override
    public Publisher<Long> apply(final Long aLong) throws Exception {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyy(aLong + "");
            }
        });
        return Flowable.timer(2, TimeUnit.SECONDS);   ///2秒之后，新Publisher会发射，在此之前，如果原始Publisher没有发射过，则新Publisher发射之时，也就是采样aLong之时，否则，跳过
    }
})
```
