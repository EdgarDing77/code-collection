package 散列表;

/**
 * Description:
 *
 * @author:edgarding
 * @date:2021/6/12
 **/
public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    V get(Object key);

    /**
     * put new value into map and return old value form map
     *
     * @param key
     * @param value oldValue
     * @return
     */
    V put(K key, V value);

    V remove(K key);

    void clear();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        /**
         * @return oldValue corresponding to the entry
         */
        V setValue(V value);

        @Override
        boolean equals(Object o);

        @Override
        int hashCode();

    }

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    /**
     * return the value to which the key is mapped
     *
     * @param key
     * @param defaultValue
     * @return
     */
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
                ? v
                : defaultValue;
    }

}
