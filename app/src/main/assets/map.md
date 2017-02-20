# map

- 对Observable发射的每一项数据都应用一个函数来变换

```
Flowable.just("111", "222", "333")
    .map(new Function<String, CharSequence>() {
        @Override
        public CharSequence apply(String s) throws Exception {
            return s + "----草泥马";
        }
    }).subscribe(new Consumer<CharSequence>() {
         @Override
         public void accept(CharSequence s) throws Exception {
             notifyy(s + "");    ///111----草泥马, 222----草泥马, 333----草泥马
         }
     });
     ///进的是String，出来的是CharSequence
```