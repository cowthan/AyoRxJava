# defer

- 在有人注册，即subscribe时，才会创建observable，所以可以保证状态是最新的
- 并且会给每个观察者创建一个Observable对象

```
s = "112334--初始值";
Observable<String> justObservable = Observable.just(s);
s = "3344556--第二次变化值";
Observable<String> deferObservable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
    @Override
    public ObservableSource<? extends String> call() throws Exception {
        Log.i("defer", "创建defer obserable");
        return Observable.just(s);
    }
});
s = "5566778--最新值";

justObservable.subscribe(new Consumer<String>() {
    @Override
    public void accept(String s) throws Exception {
        notifyy("非defer，值是：" + s);  //112334--初始值
    }
});
deferObservable.subscribe(new Consumer<String>() {
    @Override
    public void accept(String s) throws Exception {
        notifyy("defer，值是：" + s); //5566778--最新值
    }
});
s = "77889910--最后的最新值";
deferObservable.subscribe(new Consumer<String>() {
    @Override
    public void accept(String s) throws Exception {
        notifyy("defer，值是：" + s);  //77889910--最后的最新值
    }
});
```