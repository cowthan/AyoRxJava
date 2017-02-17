# repeat

- `repeat(3)`, 重复3次
- 然后调用onComplete
- ObservableOnSubscribe.subscribe(ObservableEmitter)会被重复调用
- 所以取数据的操作也会被多次调用，这点需要注意

代码

```
Observable.just(1, 2, 3).repeat(3)
```

就这些了