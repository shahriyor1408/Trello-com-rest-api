package com.company.trellocomrest.services.mail;

import com.company.trellocomrest.dtos.auth.AuthUserDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {


    private final Configuration configuration;
    private final JavaMailSender javaMailSender;


    @Async
    public void sendEmail(AuthUserDTO user, String activationLink) throws MessagingException, TemplateException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Activation link for Dummies.uz");
        helper.setTo(user.getEmail());
        Map<String, Object> model = Map.of(
                "username", user.getUsername(),
                "activation_link", activationLink
        );
        String emailContent = getEmailContent(model);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);
    }

    private String getEmailContent(Map<String, Object> model) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Template template = configuration.getTemplate("activation.ftlh");
        template.process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}

