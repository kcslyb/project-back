package cn.kcs.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author kcs
 * @date 2019-10-30 18:29
 **/
public class CustomCollection {
    /**
     * 提取集合中的对象的某个属性(通过Stream函数)作为key,集合对象作为值，组合成Map.
     *
     * @param collection
     * @param keyMapper
     * @param <E>
     * @param <K>
     * @return
     */
    public static <E, K> Map<K, E> extractIndexToMap(final Collection<E> collection, final Function<E, K> keyMapper) {
        return collection.stream().collect(Collectors.toMap(keyMapper, a -> a, (k1, k2) -> k1));
    }

    /**
     * 提取集合中的对象的一个属性(通过Stream函数), 组合成List.
     * 废除该方法，应该使用Java8模式
     *
     * @param collection
     * @param keyMapper
     * @param <E>
     * @param <K>
     * @return
     */
    public static <E, K> List<K> extractToList(final Collection<E> collection, final Function<E, K> keyMapper) {
        if (collection == null) {
            return new ArrayList<>();
        }
        return collection.stream().map(keyMapper).collect(Collectors.toList());
    }

}
