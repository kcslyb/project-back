package cn.kcs.user.controller;

import cn.kcs.encrypt.anno.Decrypt;
import cn.kcs.encrypt.anno.Encrypt;
import cn.kcs.user.entity.Genealogy;
import cn.kcs.user.service.GenealogyService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Genealogy)表控制层
 *
 * @author kcs
 * @since 2019-09-30 10:28:30
 */
@RestController
@RequestMapping("genealogy")
public class GenealogyController {
    /**
     * 服务对象
     */
    @Resource
    private GenealogyService genealogyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @GetMapping()
    public Genealogy queryById(String id) {
        return this.genealogyService.queryById(id);
    }

    /**
     * addGenealogy
     *
     * @param genealogy 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @PostMapping()
    public ResponseEntity addGenealogy(@RequestBody Genealogy genealogy) {
        boolean insert = this.genealogyService.insert(genealogy);
        if (insert) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    /**
     * queryAll
     *
     * @param genealogy 主键
     * @return 单条数据
     */
    @Encrypt
    @Decrypt
    @RequiresUser
    @GetMapping("query")
    public ResponseEntity queryAll(Genealogy genealogy) {
        List<Genealogy> genealogies = this.genealogyService.queryAll(genealogy);
        return new ResponseEntity<>(genealogies, HttpStatus.OK);
    }

}