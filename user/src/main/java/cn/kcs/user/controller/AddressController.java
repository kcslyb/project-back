package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Address;
import cn.kcs.user.service.AddressService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Address)表控制层
 *
 * @author kcs
 * @since 2019-06-05 14:16:43
 */
@RestController
@Api(value = "address", description = "用户地址 API")
@RequestMapping("address")
public class AddressController {
    /**
     * 服务对象
     */
    @Resource
    private AddressService addressService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("")
    @Encrypt
    @Decrypt
    public ResponseEntity<Address> select(String id) {
        Address address = this.addressService.queryById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    /**
     * 通过userId查询数据
     *
     * @param userId 主键
     * @return 单条数据
     */
    @GetMapping("query")
    @Encrypt
    @Decrypt
    public ResponseEntity<List<Address>> selectByUserId(String userId) {
        Address address = new Address();
        address.setAddressForUser(userId);
        address.setAddressDeleteFlag(0);
        return new ResponseEntity<>(addressService.queryAll(address), HttpStatus.OK);
    }

    /**
     * 通过实体类编辑单条数据
     *
     * @param address 实体类
     * @return 单条数据
     */
    @PutMapping("")
    @Encrypt
    @Decrypt
    public ResponseEntity update(@RequestBody Address address) {
        Address update = this.addressService.update(address);
        if (update != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * 添加单条数据
     *
     * @param address 实体类
     * @return 单条数据
     */
    @PostMapping("")
    @Encrypt
    @Decrypt
    public ResponseEntity add(@RequestBody Address address) {
        Address insert = this.addressService.insert(address);
        if (insert != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /**
     * 添加单条数据
     *
     * @param id 实体类
     * @return 单条数据
     */
    @DeleteMapping("")
    @Encrypt
    @Decrypt
    public ResponseEntity delete(String id) {
        boolean b = this.addressService.deleteById(id);
        if (b) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}