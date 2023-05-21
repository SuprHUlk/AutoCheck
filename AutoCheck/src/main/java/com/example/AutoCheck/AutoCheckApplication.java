package com.example.AutoCheck;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import javax.mail.MessagingException;
import java.net.URISyntaxException;

@SpringBootApplication
public class AutoCheckApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(AutoCheckApplication.class, args);
		try
		{
			task();
		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Scheduled(cron = "0 0 9 * * *")    //Runs every day at 9am
	private static void task() throws MessagingException, URISyntaxException, JsonProcessingException {
		JavaMailUtil.sendMail("20tanu1999@gmail.com ");
	}
}



