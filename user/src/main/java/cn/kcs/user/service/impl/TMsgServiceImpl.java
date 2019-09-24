package cn.kcs.user.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.TMsgDao;
import cn.kcs.user.entity.TMsg;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.dto.ContactDto;
import cn.kcs.user.entity.dto.MsgDto;
import cn.kcs.user.service.TMsgService;
import cn.kcs.user.service.UserAccountService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (TMsg)表服务实现类
 *
 * @author kcs
 * @since 2018-12-30 19:47:03
 */
@Service("tMsgService")
public class TMsgServiceImpl implements TMsgService {
    @Resource
    private TMsgDao tMsgDao;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    @Override
    public TMsg queryById(String id) {
        return this.tMsgDao.queryById(id);
    }

    /**
     * @param msgDto
     * @return
     */
    @Override
    public MsgDto queryAllByLimit(MsgDto msgDto) {
        List<TMsg> tMsgs = new ArrayList<>();
        List<TMsg> list = this.tMsgDao.queryAll(msgDto.getMsgSender(), msgDto.getMsgReceiver(), msgDto.getStart(), msgDto.getLimit());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                tMsgs.add(list.get(i));
                if (list.get(i).getMsgStatus().equals("0") && list.get(i).getMsgReceiver().equals(msgDto.getMsgSender())) {
                    msgDto.setInfoNumber(msgDto.getInfoNumber() + 1);
                }
            }
            msgDto.setMsgs(tMsgs);
            return msgDto;
        }
    }

    /**
     * 新增数据
     *
     * @param tMsg 实例对象
     * @return 实例对象
     */
    @Override
    public TMsg insert(TMsg tMsg) {
        String UUID = ShortUUID.generate();
        tMsg.setMsgId(UUID);
        tMsg.setMsgCreattime(CustomDateUtil.currentFormatDate());
        if (StringUtils.isEmpty(tMsg.getMsgStatus())) {
            tMsg.setMsgStatus("0");
        }
        this.tMsgDao.insert(tMsg);
        return this.queryById(tMsg.getMsgId());
    }

    /**
     * 修改数据
     *
     * @param str 实例对象
     * @return 实例对象
     */
    @Override
    public int update(String str) {
        TMsg tMsg = new TMsg();
        tMsg.setMsgReceiver(LoginInfo.getUserId());
        tMsg.setMsgSender(str);
        tMsg.setMsgStatus("1");
        return tMsgDao.updateStatus(tMsg);
    }

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tMsgDao.deleteById(id) > 0;
    }

    @Override
    public List<ContactDto> findContact() {
        List<UserAccount> userDtos = userAccountService.queryAll(new UserAccount());
        List<ContactDto> contactDtos = new ArrayList<>();
        String userId = LoginInfo.getUserId();
        for (UserAccount userDto : userDtos) {
            List<TMsg> tMsgs = this.tMsgDao.queryAll(userDto.getUserId(), userId, 0, 100);
            int infoNumber = 0;
            List<String> msgsId = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(tMsgs)) {
                for (TMsg tMsg : tMsgs) {
                    if (tMsg.getMsgReceiver().equals(userId) && "0".equals(tMsg.getMsgStatus())) {
                        infoNumber += 1;
                        msgsId.add(tMsg.getMsgId());
                    }
                }
            }
            ContactDto contactDto = new ContactDto();
            contactDto.setMsgsId(msgsId);
            contactDto.setUserId(userDto.getUserId());
            contactDto.setUserName(userDto.getUserName());
            contactDto.setHeadPortrait(userDto.getUserAvatar());
            contactDto.setCurrentUserId(userId);
            contactDto.setNoticeNumber(infoNumber);
            contactDtos.add(contactDto);
        }
        return contactDtos;
    }

}