# groupBy

- 按照某个条件把Observable的item分组
- 每一组形成一个新的Observable
- 也就是会发射几个GroupedFlowable<Integer, String> flowable
    - getKey就能得到这个item的group信息
    - 而group是怎么算出来的呢，就是groupBy传入的Function呗
- 原始Observable的item顺序还可以得到保证

```
flowable1.groupBy(new Function<String, Integer>() {
        @Override
        public Integer apply(String s) throws Exception {
            return Integer.parseInt(s) % 3;
        }
    })
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Consumer<GroupedFlowable<Integer, String>>() {
        @Override
        public void accept(final GroupedFlowable<Integer, String> flowable) throws Exception {
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            notifyy(flowable.getKey() + "--" + s);
                        }
                    });
        }
    }, Functions.ERROR_CONSUMER,
            new Action() {
                @Override
                public void run() throws Exception {
                    notifyy("onComplete---结束了！@@");
                }
            },
            FlowableInternalHelper.RequestMax.INSTANCE);
```
