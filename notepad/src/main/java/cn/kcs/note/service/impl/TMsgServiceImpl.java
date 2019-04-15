package cn.kcs.note.service.impl;

import cn.kcs.common.daoUtil.ShortUUID;
import cn.kcs.common.util.DataUtil;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.note.dao.TMsgDao;
import cn.kcs.note.entity.ContactDto;
import cn.kcs.note.entity.MsgDto;
import cn.kcs.note.entity.TMsg;
import cn.kcs.note.entity.UserAccount;
import cn.kcs.note.service.TMsgService;
import cn.kcs.note.service.UserAccountService;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.CollectionUtil;

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
        if (CollectionUtil.isEmpty(list)) {
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
        tMsg.setMsgCreattime(DataUtil.stringToDate(DataUtil.currentFormatDate(DataUtil.DATE_TO_STRING_DETAIAL_PATTERN), DataUtil.DATE_TO_STRING_DETAIAL_PATTERN));
        tMsg.setMsgStatus("0");
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
    public String update(String str) {
        TMsg tMsg = new TMsg();
        tMsg.setMsgStatus("1");
        String[] splts = str.split("=");
        if (splts.length > 0) {
            for (String splt : splts) {
                tMsg.setMsgId(splt);
                this.tMsgDao.update(tMsg);
            }
            return "1";
        }

        return "0";
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
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject jsonUser = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String userId = jsonUser.getString("userId");
        for (UserAccount userDto : userDtos) {
            MsgDto msgDto = new MsgDto();
            msgDto.setMsgSender(userId);
            msgDto.setMsgReceiver(userDto.getUserId());
            msgDto.setLimit(20);
            List<TMsg> tMsgs = this.tMsgDao.queryAll(userDto.getUserId(), userId, 0, 20);
            int infoNunber = 0;
            List<String> msgsId = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(tMsgs)) {
                for (TMsg tMsg : tMsgs) {
                    if (tMsg.getMsgReceiver().equals(userId) && "0".equals(tMsg.getMsgStatus())) {
                        infoNunber += 1;
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
            contactDto.setNoticeNumber(infoNunber);
            contactDtos.add(contactDto);
        }
        return contactDtos;
    }

}