package cn.kcs.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;

/**
 * @author kcs
 * @date 9/12/19
 **/

@Component
public class MailServiceUtil {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailDto.getTo());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        mailSender.send(message);
        LOGGER.info("[{}]向[{}]发送了邮件; 主题为[{}]; 内容为[{}]", from, mailDto.getTo(), mailDto.getTitle(), mailDto.getContent());
    }

    public void sendAttachmentsMail(MailDto mailDto) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailDto.getTo());
            helper.setSubject(mailDto.getTitle());
            helper.setText(mailDto.getContent());
            for (File file : mailDto.getFileList()) {
                String fileName = MimeUtility.encodeText(file.getName(), "UTF-8", "B");
                helper.addAttachment(fileName, file);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        mailSender.send(message);
        LOGGER.info("[{}]向[{}]发送了邮件; 主题为[{}]; 内容为[{}]; 附件个数[{}]",
                from, mailDto.getTo(), mailDto.getTitle(), mailDto.getContent(), mailDto.getFileList().size());
    }
}
