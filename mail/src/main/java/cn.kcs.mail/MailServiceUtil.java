//package cn.kcs.mail;
//
//import cn.kcs.common.util.CommonUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeUtility;
//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//
///**
// * @author kcs
// * @date 9/12/19
// **/
//
//@Component
//public class MailServiceUtil {
//    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//    @Value("${spring.mail.username}")
//    private String from;
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public ResponseEntity sendSimpleMail(MailDto mailDto) {
//        ResponseEntity responseEntity = checkedMailDto(mailDto);
//        if (responseEntity != null) {
//            return responseEntity;
//        }
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        message.setTo(mailDto.getTo());
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getContent());
//        mailSender.send(message);
//        LOGGER.info("[{}]向[{}]发送了邮件; 主题为[{}]; 内容为[{}]", from, mailDto.getTo(), mailDto.getTitle(), mailDto.getContent());
//        return new ResponseEntity<>("发送邮件成功", HttpStatus.OK);
//    }
//
//    public ResponseEntity sendAttachmentsMail(MailDto mailDto) {
//        ResponseEntity responseEntity = checkedMailDto(mailDto);
//        if (responseEntity != null) {
//            return responseEntity;
//        }
//        MimeMessage message = mailSender.createMimeMessage();
//        try {
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom(from);
//            helper.setTo(Objects.requireNonNull(mailDto.getTo()));
//            helper.setSubject(Objects.requireNonNull(mailDto.getTitle()));
//            helper.setText(mailDto.getContent());
//            for (File file : mailDto.getFileList()) {
//                String fileName = MimeUtility.encodeText(file.getName(), "UTF-8", "B");
//                helper.addAttachment(fileName, file);
//            }
//        } catch (Exception e) {
//            LOGGER.error("", e);
//        }
//        mailSender.send(message);
//        LOGGER.info("[{}]向[{}]发送了邮件; 主题为[{}]; 内容为[{}]; 附件个数[{}]",
//                from, mailDto.getTo(), mailDto.getTitle(), mailDto.getContent(), mailDto.getFileList().size());
//        return new ResponseEntity<>("发送邮件成功", HttpStatus.OK);
//    }
//
//    private ResponseEntity checkedMailDto(MailDto mailDto) {
//        if (mailDto.getTo() == null) {
//            return new ResponseEntity<>("发送邮件失败，接收数组为空", HttpStatus.BAD_REQUEST);
//        }
//        String[] to = mailDto.getTo();
//        List<String> list = Arrays.asList(to);
//        boolean flag = true;
//        for (String s : list) {
//            if (!CommonUtil.isEmail(s)) {
//                flag = false;
//            }
//        }
//        if (!flag) {
//            return new ResponseEntity<>("发送邮件失败，存在不合法的邮箱", HttpStatus.BAD_REQUEST);
//        }
//        if (mailDto.getTitle() == null) {
//            return new ResponseEntity<>("发送邮件失败，邮件主题为空", HttpStatus.BAD_REQUEST);
//        }
//        return null;
//    }
//}
