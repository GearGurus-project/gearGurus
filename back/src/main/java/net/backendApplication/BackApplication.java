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
		Student student1 = Student.builder()
				.firstName("Haouari")
				.lastName("Samy")
				.email("haouarisamy@gmail.com")
				.student_number("20205956")
				.group_student("BDSM1")
				.build();

		Student student2 = Student.builder()
				.firstName("Razah")
				.lastName("Adbel")
				.email("abdel@gmail.com")
				.student_number("20215698")
				.group_student("RS1")
				.build();

		Student student3 = Student.builder()
				.firstName("Khazri")
				.lastName("Wahbi")
				.email("wahbi@gmail.com")
				.student_number("20231578")
				.group_student("LSI1")
				.build();

		Hardware hardware1 = Hardware.builder()
				.model("MacBook Air")
				.price("2000")
				.barCode("484845164")
				.category("Computer")
				.datePurchase("20/05/2024")
				.provider("Apple")
				.build();

		User user1 = User.builder()
				.firstName("Jean")
				.lastName("Dupichot")
				.role("admin")
				.password("coucouliko")
				.build();

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);

		hardwareRepository.save(hardware1);

		userRepository.save(user1);
	}
}
