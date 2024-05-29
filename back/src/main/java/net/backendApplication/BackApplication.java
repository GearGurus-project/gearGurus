package net.backendApplication;

import net.backendApplication.Entities.Hardware;
import net.backendApplication.Entities.User;
import net.backendApplication.Entities.Student;
import net.backendApplication.Repository.HardwareRepository;
import net.backendApplication.Repository.StudentRepository;
import net.backendApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class BackApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private HardwareRepository hardwareRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
	}
}
