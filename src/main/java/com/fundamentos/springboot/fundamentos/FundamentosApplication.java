package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.bean.MyBean;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentos.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.pojo.UserPojo;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import com.fundamentos.springboot.fundamentos.service.UserSercice;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.*;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {


    private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);


    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserSercice userSercice;


    public FundamentosApplication(
            @Qualifier("componentTwoImplement") ComponentDependency componentDependency,
            MyBean myBean,
            MyBeanWithDependency myBeanWithDependency,
            MyBeanWithProperties myBeanWithProperties,
            UserPojo userPojo,
            UserRepository userRepository,
            UserSercice userSercice
    ) {
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userSercice = userSercice;
    }

    public static void main(String[] args) {
        SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        previousClasses();
        saveUsersInDatabase();
        getInfoJPQLFromUser();
        saveWithErrorTransactional();
    }

    private void saveWithErrorTransactional() {
        User test1 = new User("TestTransactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("TestTransactional2", "TestTransactional1@domain.com", LocalDate.now());
//        User test2 = new User("TestTransactional2", "TestTransactional2@domain.com", LocalDate.now());
        User test3 = new User("TestTransactional3", "TestTransactional3@domain.com", LocalDate.now());
        User test4 = new User("TestTransactional4", "TestTransactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);
        try {
            userSercice.saveTransactional(users);
        } catch (Exception e) {
            LOGGER.error("This is an error inside the transactional method " + e);
        }

        userSercice.getAllUsers()
                .stream()
                .forEach(user -> LOGGER.info("This is the user inside the transactional method: " + user));
    }

    private void getInfoJPQLFromUser() {
//        LOGGER.info("User with method findByUserEmail "
//                + userRepository.findByUserEmail("marco@domain.com")
//                .orElseThrow(() -> new RuntimeException("No se encontró el usuario")
//                ));
//
//        userRepository.findAndSort("Mar", Sort.by("id").descending())
//                .stream()
//                .forEach(user -> LOGGER.info("User with method findAndSort: " + user));
//
//        userRepository.findByName("Marisol")
//                .stream()
//                .forEach(user -> LOGGER.info("User with method findByName: " + user));
//
//        LOGGER.info("User with Query Method findByEmailAndName " + userRepository.findByEmailAndName("marco@domain.com", "Marco")
//                .orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
//
//        userRepository.findByNameLike("%u%")
//                .stream()
//                .forEach(user -> LOGGER.info("User with method findByNameLike: " + user));
//
//        userRepository.findByNameOrEmail(null, "luis@domain.com")
//                .stream()
//                .forEach(user -> LOGGER.info("User with method findByNameOrEmail: " + user));

        userRepository.findByBirthDateBetween(
                        LocalDate.of(2021, 1, 1),
                        LocalDate.of(2021, 12, 31))
                .stream()
                .forEach(user ->
                        LOGGER.info("User with method findByBirthDateBetween: " + user));

        userRepository.findByNameLikeOrderByIdDesc("%user%")
                .stream()
                .forEach(user ->
                        LOGGER.info("User with method findByNameLikeOrderByIdDesc: " + user));

        userRepository.findByNameContainingOrderByIdDesc("user")
                .stream()
                .forEach(user ->
                        LOGGER.info("User with method findByNameLikeOrderByIdDescEmailAsc: " + user));

        LOGGER.info("User from parameter is: " + userRepository.getAllByBirthDateAndEmail(
                        LocalDate.of(2021, 1, 1),
                        "daniela@domain.com")
                .orElseThrow(() -> new RuntimeException("No se encontró el usuario")));
    }

    private void saveUsersInDatabase() {
        User user1 = new User("John", "john@domain.com", LocalDate.of(2021, 3, 13));
        User user2 = new User("Marco", "marco@domain.com", LocalDate.of(2021, 12, 8));
        User user3 = new User("Daniela", "daniela@domain.com", LocalDate.of(2021, 9, 8));
        User user4 = new User("Marisol", "marisol@domain.com", LocalDate.of(2021, 6, 18));
        User user5 = new User("Karen", "karen@domain.com", LocalDate.of(2021, 1, 1));
        User user6 = new User("Carlos", "carlos@domain.com", LocalDate.of(2021, 7, 7));
        User user7 = new User("Enrique", "enrique@domain.com", LocalDate.of(2021, 11, 12));
        User user8 = new User("Luis", "luis@domain.com", LocalDate.of(2021, 2, 27));
        User user9 = new User("Paola", "paola@domain.com", LocalDate.of(2021, 4, 10));
        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9);
        list.stream().forEach(userRepository::save);
    }

    private void previousClasses() {
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        out.println(myBeanWithProperties.function());
        out.println(userPojo.getEmail() + " - " + userPojo.getPassword() + " - " + userPojo.getAge());
        try {
            int value = 10 / 0;
            LOGGER.debug("Mi valor " + value);
        } catch (Exception e) {
            LOGGER.error("Esto es un error al dividir por cero", e);
        }
    }
}
