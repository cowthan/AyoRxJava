# never

- 创建一个什么都不做的Observable

```
Observable observable1=Observable.empty();//直接调用onCompleted。
Observable observable2=Observable.error(new RuntimeException());//直接调用onError。这里可以自定义异常
Observable observable3=Observable.never();//啥都不做
```