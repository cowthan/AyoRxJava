# window

类似buffer，也是定期收集Observable的item，但window是拆成多个Observable
- 也分为：按item个数，按时间，按监控
- window的结果是一个Observable<Observable<T>>，可以用flat展开，要不就得在Consumer里写嵌套代码


```
.window(3)
.flatMap(new Function<Flowable<String>, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Flowable<String> stringFlowable) throws Exception {
                return stringFlowable;
            }
        })
```