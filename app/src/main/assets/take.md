# take

- 取前N个item，或者取前N秒的item
- interval不是不会停吗，用了take就能调起onComplete了
- 可以
    - take(item_count)
    - take(time, TimeUnit)
    - take(long time, TimeUnit unit, Scheduler scheduler)
- takeLast系列
    - takeLast(long count, long time, TimeUnit unit, Scheduler scheduler)  或者取到count个，或者取够time时间，就停止
    - takeLast(count)
    - takeLast(long time, TimeUnit unit, Scheduler scheduler)
- takeUntil
    - 直到满足条件就停止，但发射了第一条满足条件的
        - 条件：Predicate
        - takeUntil(new Predicate<Long>() {})
        - 注意，如果发射0123456789, 条件是x > 5，其实发射了0123456
        - 如果条件是x >= 5，则会发射012345
    - 直到second publisher发送了item就停止take
        - takeUntil(new Publisher<Long>(){})
- takeWhile
    - 行为和takeUntil一样
    - 条件：Predicate
    - 我一看还以为这个永远不会停止，但其实还是能停止，一旦不符合条件，发射出这一个不符合条件的item，然后走了onComplete
- skip
    - 跳过
    - 可以按count
    - 可以按time
- skipLast
    - 跳过最后N条
    - 跳过最后N秒
- skipUntil
    - 只支持Publisher，不会用啊卧槽！！！
- skipWhile
    - 支持Predicate
    - 只要满足条件，就跳过
    - 一旦不满足条件，就输出
    - 不会导致interval停止，这个才像回事
- firstElement
    - 返回Maybe，因为不一定有第一个item
    - 如果empty，则直接走了onComlete
    - 如果error，则直接走了onError
    - 如果never，则什么也不会调
    - 如果正常，则走onNext一次，但是不走onComplete！！
- first(defaultItem)
    - 返回Single，参数是默认值
    - error了就error
    - empty，never什么的，总会返回默认值
    - never还就是什么都不发啊
    - 所以就是总是有一个Item，或者error，正是Single
- firstOrError
    - 返回Single
    - 要么有，要么错
- 对应的还有
    - last(defaultItem)
    - lastElement()
    - lastOrError
- 还有
    - elementAt(index)
    - elementAt(index, T defaultItem)
    - elementAtOrError(index)

```
.take(3)
.take(3, TimeUnit.SECONDS)

.takeUntil(new Predicate<Long>() {
        @Override
        public boolean test(Long aLong) throws Exception {
            return false;
        }
    })

takeUntil(new Publisher<Long>() {
            @Override
            public void subscribe(Subscriber<? super Long> s) {
                //而且只要不调onNext, onComplete, onError，就不会对原始Observable造成干扰
                s.onNext(1L);  ///这里一发送，似乎就停止take了（原始Observable走了onComplete），但之前的也没有take到？？？
                s.onComplete();
            }
        })
```