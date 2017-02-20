# scan

- 好像是reduce
- 接受一个方法作为处理器，参数1是之前所有item的计算结果，参数2是最新item，返回值会作为下一次调用的参数1
- 可不就是reduce嘛

```
Flowable.fromArray(list1)
        .scan(new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                return s + s2;
            }
        })
```
