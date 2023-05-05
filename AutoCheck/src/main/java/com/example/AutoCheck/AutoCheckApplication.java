package com.example.AutoCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import javax.mail.MessagingException;

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
	private static void task() throws MessagingException
	{
		JavaMailUtil.sendMail("20tanu1999@gmail.com ");
	}
}



