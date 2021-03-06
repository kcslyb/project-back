package cn.kcs.encrypt.anno;

import java.lang.annotation.*;

/**
 * 加密注解
 *
 * <p>加了此注解的接口将进行数据加密操作<p>
 *
 * @author kcs
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {

}
