package cn.kcs.encrypt.anno;

import java.lang.annotation.*;

/**
 * 解密注解
 *
 * <p>加了此注解的接口将进行数据解密操作<p>
 *
 * @author kcs
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {

}
