# buffer

攒够几个，或者攒够一段时间，把攒的几个Item作为一个List<T>，然后发射这个List<T>
- 按item个数
    - buffer(3)：攒够3个，形成一个List<String>，然后再当成一条发送
    - buffer(3, 4)：buffer(count, skip)，每当收到skip项数据，就用count项数据来填充缓存
    - buffer(3, 3)相当于buffer(3)
    - 如果连续发射1234567
    - 会有重叠，当skip < count，buffer(3, 2)收到的顺序是123,345,567，因为收到2时（收到2个数据了，就skip一下--存一下），会用123填充，接着发3,4，这时就用3,4,5填充，这不就重了
    - 会有间隙，即消息丢失，当skip > count，buffer(3. 4)收到的顺序是123 567 9..，因为收到4时，会用123填充，接着发5,6,7,8，用567填充，这不4和8就丢了
    - 总之，count就是每一个List<T>的元素个数，skip表示经过几个item之后开始存，并新建一个缓存List
    - 其实可以理解为：每次新建缓存List之后，存储之后的count个数据，并且到第skip个item时，收起缓存，发射，然后新建一个缓存
- 按时间
    - buffer(long timespan, long timeskip, TimeUnit unit)
    - buffer(1000, 2000, TimeUnit.MILLISECONDS)
    - 参数1：隔多久收集一次
    - 参数2：隔多久发射一次，并新建一个缓存
- 按监控
    - buffer(boundary)，传入一个Observable，buffer会监视这个Observable，一旦这个Observable发了一个值，buffer就创建一个新缓存开始收集原始Observable，并发射旧的缓存


```
.buffer(new Callable<Flowable<Long>>() {
        @Override
        public Flowable<Long> call() throws Exception {
            return Flowable.interval(1, 2, TimeUnit.SECONDS);
        }
    })   ///buffer(boundary)，传入一个Observable，buffer会监视这个Observable，一旦这个Observable发了一个值，buffer就创建一个新缓存开始收集原始Observable，并发射旧的缓存
        ///好像只能根据initialDelay的值来？？？ 例如上面是initailDelay是1秒，后面的间隔是2秒，但收到的订阅消息都是1秒一次，总共8次
```