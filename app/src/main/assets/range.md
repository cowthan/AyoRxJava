# range

- 创建一个发射指定范围的整数序列的Observable<Integer>

```
task = Flowable.range(10, 8)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                notifyy("item--" + s);
            }
        });
```