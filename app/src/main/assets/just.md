# from

- 将一个或多个对象转换成发射这个或这些对象的一个Observable
- 内部：
    - 如果是单个对象，内部创建的是ScalarSynchronousObservable对象
    - 如果是多个对象，则是调用了from方法创建
