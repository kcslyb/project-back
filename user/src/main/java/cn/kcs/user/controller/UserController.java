package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.User;
import cn.kcs.user.entity.dto.UserDto;
import cn.kcs.user.entity.dto.UserGenealogyDto;
import cn.kcs.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author kcs
 * @since 2019-09-30 10:27:48
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping()
    public User queryById(String id) {
        return this.userService.queryById(id);
    }

    /**
     * addGenealogy
     *
     * @param userDto 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @PostMapping()
    public ResponseEntity addGenealogy(@RequestBody UserDto userDto) {
        boolean insert = this.userService.insert(userDto);
        if (insert) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    /**
     * queryAll
     *
     * @param user 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @GetMapping("query")
    public ResponseEntity queryAll(User user) {
        List<User> genealogies = this.userService.queryAll(user);
        return new ResponseEntity<>(genealogies, HttpStatus.OK);
    }

    /**
     * queryUserGenealogyByUserId
     *
     * @param key 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @GetMapping("{key}")
    public ResponseEntity queryUserGenealogyByUserId(@PathVariable String key) {
        UserGenealogyDto userGenealogyDto = this.userService.queryUserGenealogy(key);
        return new ResponseEntity<>(userGenealogyDto, HttpStatus.OK);
    }

}