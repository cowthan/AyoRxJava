# timeput

## 超时：
- timeout(1500, TimeUnit.MILLISECONDS): 1500毫秒不发item，则onError
- timeout(1500, TimeUnit.MILLISECONDS, Publisher): 1500毫秒不发item，则使用备用Publisher发
- timeout(Function<T, Publisher<? extends T>> p2)：Publisher p2根据item来构建，p2发射item时，如果原始Publisher还是没发下一条，就超时
- timeout(Publisher, Function)
- 以上几个形式，都接受最后一个参数为Publisher的情况，如果没这个参数，超时会走onError，有了这个参数，就是备用Publisher，超时激活


```
Flowable.interval(1, 1, TimeUnit.SECONDS)
    .take(10)
    .flatMap(new Function<Long, Publisher<Long>>() {
        @Override
        public Publisher<Long> apply(Long aLong) throws Exception {
            if(aLong <= 5){
                return Flowable.just(aLong);
            }else{
                return Flowable.just(aLong).delay(2000, TimeUnit.MILLISECONDS);
            }
        }
    })
    //.timeout(1500, TimeUnit.MILLISECONDS)    /////----1500毫秒不发射下一条item，就超时
    .timeout(new Function<Long, Publisher<Long>>() {
        @Override
        public Publisher<Long> apply(Long aLong) throws Exception {    /////----等新的Publisher发射了item，原始Publisher还没发射下一条，就超时
            return Flowable.just(aLong).delay(2000, TimeUnit.MILLISECONDS); ////2秒后发射，而原始Flowable会在3秒后发射，所以会超时
        }
    })
    .timeout(new Publisher<Long>() {  /////----这个没他妈整明白
                @Override
                public void subscribe(Subscriber<? super Long> s) {

                }
            }, new Function<Long, Publisher<Long>>() {
                @Override
                public Publisher<Long> apply(Long aLong) throws Exception {
                    return null;
                }
            })
```