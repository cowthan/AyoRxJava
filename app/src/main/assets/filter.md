# filter

- 过滤

```
.filter(new Predicate<Long>() {
        @Override
        public boolean test(Long aLong) throws Exception {
            return isPrime(aLong);
        }
    })
```