package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanReadProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

    private UserRepository userRepository;

    private final ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;

    private MyBeanReadProperties myBeanReadProperties;

    private UserPojo userPojo;

    private UserService usersService;

    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanReadProperties myBeanReadProperties, UserPojo userPojo, UserRepository userRepository, UserService usersService) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanReadProperties = myBeanReadProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.usersService = usersService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //examples();
        saveUsersInDatabase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void saveWithErrorTransactional() {
        User test1 = new User("Test1Transactional1", "Test1Transactional1@gmail.com", LocalDate.now());
        User test2 = new User("Test2Transactional1", "Test2Transactional1@gmail.com", LocalDate.now());
        User test3 = new User("Test3Transactional1", "Test1Transactional1@gmail.com", LocalDate.now());
        User test4 = new User("Test4Transactional1", "Test4Transactional1@gmail.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);

        try {
            usersService.saveTransactional(users);
        } catch (Exception e){
            LOGGER.error("Esta es una excepción dentro del metodo transactioanal"+ e.getMessage());
        }


        usersService.getAllUsers().stream().forEach(user -> LOGGER.info("Método transactional: " + user));
    }

    private void getInformationJpqlFromUser() {
        LOGGER.info("Usuario con el metodo findByUserEmail" +
                userRepository.findByUserEmail("emduran21@gmail.com").orElseThrow(() -> new RuntimeException("No se encontró el usuario")));

        userRepository.findAndSort("Esmerdi", Sort.by("id").descending())
                .stream()
                .forEach(user -> LOGGER.info("Usuario con método sort " + user));

        userRepository.findByName("Esmerdi")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con query method" + user));

        LOGGER.info("Usuario con query method findByEmailAndName" + userRepository.findByEmailAndName("julia@gmail.com", "Julia")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.findByNameLike("%Es%").stream().forEach(user -> LOGGER.info("Usuario findByNameLike" + user));
        userRepository.findByNameOrEmail(null, "emduran21@gmail.com").stream().forEach(user -> LOGGER.info("Usuario findByNameOrEmail" + user));

        userRepository.findByBirthDateBetween(LocalDate.of(2021, 11, 10), LocalDate.of(2022, 11, 3)).stream()
                .forEach(user -> LOGGER.info("Usuarios con intervalos de fechas: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%Esmerdi%").stream().forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado" + user));

        userRepository.findByNameContainingOrderByIdDesc("Esmerdi").stream().forEach(user -> LOGGER.info("Usuario encontrado con containing y ordenado" + user));

        LOGGER.info("A partir del named parameter" + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 03, 10), "emduran21@gmail.com").orElseThrow(
                () -> new RuntimeException("No se encontró el usuario con el named parameter")
        ));
    }

    private void saveUsersInDatabase() {
        User user1 = new User("Esmerdi", "emduran21@gmail.com", LocalDate.of(2021, 03, 10));
        User user2 = new User("Julia", "julia@gmail.com", LocalDate.of(2022, 03, 10));
        User user3 = new User("Andrea", "andrea@gmail.com", LocalDate.of(2011, 03, 10));
        User user4 = new User("Catalina", "cata@gmail.com", LocalDate.of(1980, 03, 10));
        User user5 = new User("Esmerdi2", "esmerdi@gmail.com", LocalDate.of(1990, 03, 10));
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5);
        list.stream().forEach(userRepository::save);
    }

    private void examples() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanReadProperties.function());
        System.out.println(userPojo.getEmail() + " " + userPojo.getEdad());

        try {
            int a = 10 / 0;
            LOGGER.debug("Mi valor" + a);
        } catch (Exception e) {
            LOGGER.error("esto es un error al dividir por 0" + e.getMessage());
        }
    }
}
