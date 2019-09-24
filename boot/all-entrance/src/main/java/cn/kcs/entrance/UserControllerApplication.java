package cn.kcs.entrance;

import cn.kcs.encrypt.anno.EnableEncrypt;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author kcs
 */
@ComponentScan("cn.kcs.*")
@MapperScan("cn.kcs.*.dao")
@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableEncrypt
public class UserControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserControllerApplication.class, args);
    }

}
