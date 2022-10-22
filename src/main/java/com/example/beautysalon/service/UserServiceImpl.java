package com.example.beautysalon.service;

import com.example.beautysalon.exception.RoleNotFoundException;
import com.example.beautysalon.exception.SalonServiceNotFoundException;
import com.example.beautysalon.exception.UsernameAlreadyExistsException;
import com.example.beautysalon.model.*;
import com.example.beautysalon.repository.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
//@Data
//@Value
//@Builder(toBuilder = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SalonServiceRepository salonServiceRepository;

//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository,SalonServiceRepository salonServiceRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.customerRepository = customerRepository;
//        this.employeeRepository = employeeRepository;
//        this.salonServiceRepository = salonServiceRepository;
//    }

//    @Override
//    public ResponseEntity<String> saveUser(User user, Role role) {
//        try{
//            if(usernameAlreadyExists(user.getEmail())) {
//                return ResponseEntity.status(HttpStatus.CONFLICT).body("This user already exists");
//            }
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//            user.setEnabled(true);
//            user.setPassword(encodedPassword);
//            user.setRole(role);
//
//            if(role.getName().equalsIgnoreCase("REGULAR_USER")) {
//                Customer customer = new Customer();
//                customer.setUser(user);
//            }
//
//
//
//            customerRepository.save(customer);
//            userRepository.save(user);
//            return new ResponseEntity<>(HttpStatus.OK);
//
//        }
//        catch(Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

    @Override
    public void saveCustomer(Customer customer) {

        if(usernameAlreadyExists(customer.getEmail())) {
            throw new UsernameAlreadyExistsException(customer.getEmail());
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(customer.getPassword());

        Role role = roleRepository.findById(Long.valueOf(System.getenv("REGULAR_USER_ID")))
                   .orElseThrow(() -> new RoleNotFoundException(Long.valueOf(System.getenv("REGULAR_USER_ID"))));

        User user = new User(customer.getEmail(), encodedPassword, true, role);

        customer.setAppointments(null);
        customerRepository.save(customer);
        userRepository.save(user);
    }


    @Override
    public void saveEmployee(Employee employee) {

        if(usernameAlreadyExists(employee.getEmail())) {
            throw new UsernameAlreadyExistsException(employee.getEmail());
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());

        SalonService salonService = salonServiceRepository.findById(1L)
                            .orElseThrow(() -> new SalonServiceNotFoundException(1L));

        employee.setSalonService(salonService);

        Role role = roleRepository.findById(Long.valueOf(System.getenv("EDITOR_ID")))
                    .orElseThrow(() -> new RoleNotFoundException(Long.valueOf(System.getenv("EDITOR_ID"))));


        User user = new User(employee.getEmail(), encodedPassword, true, role);

        userRepository.save(user);
        employeeRepository.save(employee);
    }

//    @Override
//    public ResponseEntity<User> updateUserInfo(User user) {
//        try{
//            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String username;
//            if(principal instanceof AppUserDetails) {
//                username = ((AppUserDetails)principal).getUsername();
//            }
//            else {
//                username = principal.toString();
//            }
//
//            Optional<User> oldUser = userRepository.getUserByEmail(username);
//
//            if(oldUser.isPresent()) {
//                User updatedUser = oldUser.get();
//                updatedUser.setFirstName(user.getFirstName() == null ? oldUser.get().getFirstName() : user.getFirstName());
//                updatedUser.setLastName(user.getLastName() == null ? oldUser.get().getLastName() : user.getLastName());
//
//                if(user.getEmail() != null) {
//                    if(usernameAlreadyExists(user.getEmail())) {
//                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//                    }
//                    updatedUser.setEmail(user.getEmail());
//                }
//
//                // TO DO: update password (check with BCrypt)
//
//                userRepository.save(updatedUser);
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    private boolean usernameAlreadyExists(String username) {
        try {
            Optional<User> user = userRepository.findUserByEmail(username);
            return user.isPresent();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
