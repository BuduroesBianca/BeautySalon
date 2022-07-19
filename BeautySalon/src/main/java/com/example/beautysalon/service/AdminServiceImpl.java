package com.example.beautysalon.service;

import com.example.beautysalon.exception.AccountNotFoundException;
import com.example.beautysalon.model.*;
import com.example.beautysalon.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final SalonServiceRepository salonServiceRepository;

    public AdminServiceImpl(UserRepository userRepository, UserService userService, RoleRepository roleRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, SalonServiceRepository salonServiceRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public void deactivateAccount(String role, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if(user.getRole().getName().equalsIgnoreCase(role)) {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @Override
    public void activateAccount(String role, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        if(user.getRole().getName().equalsIgnoreCase(role)) {
            user.setEnabled(true);
            userRepository.save(user);
        }
    }

//    @Override
//    public ResponseEntity<String> changeUserToEditor(Long userId) {
//
//        try {
//            Optional<Role> role = roleRepository.findById(2L);
//
//            if (role.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EDITOR role is not configured");
//            }
//
//            Optional<User> user = userRepository.findById(userId);
//
//            if (user.isPresent()) {
//                if (user.get().getRole().getRoleId().equals(2L)) {
//                    return ResponseEntity.status(HttpStatus.OK).body("This user is already EDITOR");
//                }
//
//                Customer customer = customerRepository.findByUser(user.get());
//
//                user.get().setRole(role.get());
//                Employee employee = new Employee();
//                employee.setUser(user.get());
//                Optional<SalonService> salonService = salonServiceRepository.findById(1L);
//                employee.setSalonService(salonService.get());
//                employeeRepository.save(employee);
//                customerRepository.deleteById(customer.getCustomerId());
//
//            }
//
//            return ResponseEntity.status(HttpStatus.OK).body("Success");
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//
//    }
//
//    @Override
//    public ResponseEntity<String> changeEditorToUser(Long userId) {
//        try {
//            Optional<Role> role = roleRepository.findById(1L);
//
//            if (role.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("REGULAR_USER role is not configured");
//            }
//
//            Optional<User> user = userRepository.findById(userId);
//
//            if (user.isPresent()) {
//                if (user.get().getRole().getRoleId().equals(1L)) {
//                    return ResponseEntity.status(HttpStatus.OK).body("This user is already REGULAR_USER");
//                }
//
//                Employee employee = employeeRepository.findByUser(user.get());
//
//                user.get().setRole(role.get());
//                Customer customer = new Customer();
//                customer.setUser(user.get());
//                customerRepository.save(customer);
//                employeeRepository.deleteById(employee.getId());
//            }
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body("Success");
//    }

//    @Override
//    public ResponseEntity<HttpStatus> changeUserRoles(Long userId, Long roleId) throws UserRoleNotFoundException, AccountNotFoundException {
//
//            Optional<Role> role = roleRepository.findById(roleId);
//
//            if(role.isEmpty()) {
//                throw new UserRoleNotFoundException("Could not find role with ID " + roleId);
//            }
//
//            Optional<User> user = userRepository.findById(userId);
//
//            if(user.isPresent()) {
//                if(user.get().getRole().getRoleId().equals(roleId)) {
//                    return new ResponseEntity<>(HttpStatus.OK);
//                }
//
//                if(role.get().getName().equalsIgnoreCase("EDITOR")) {
//                    Customer customer = customerRepository.findByUser(user.get());
//
//                    user.get().setRole(role.get());
//                    Employee employee = new Employee();
//                    employee.setUser(user.get());
//                    Optional<SalonService> salonService = salonServiceRepository.findById(1L);
//                    employee.setSalonService(salonService.get());
//                    employeeRepository.save(employee);
//                    customerRepository.deleteById(customer.getCustomerId());
//                }
//                else if(role.get().getName().equalsIgnoreCase("REGULAR_USER")) {
//                    Employee employee = employeeRepository.findByUser(user.get());
//
//                    user.get().setRole(role.get());
//                    Customer customer = new Customer();
//                    customer.setUser(user.get());
//                    customerRepository.save(customer);
//                    employeeRepository.deleteById(employee.getId());
//                }
//
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//
//            throw new AccountNotFoundException("Could not find user account with ID " + userId);
//    }

//    @Override
//    public ResponseEntity<HttpStatus> changeUserRoles(Long userId, Long roleId) {
//        try {
//
//            Optional<Role> role = roleRepository.findById(roleId);
//
//            if(!role.isPresent()) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//
//            Optional<User> user = userRepository.findById(userId);
//
//            if(user.isPresent()) {
//                if(user.get().getRole().getRoleId().equals(roleId)) {
//                    return new ResponseEntity<>(HttpStatus.OK);
//                }
//
//                user.get().setRole(role.get());
//                userRepository.save(user.get());
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } catch(Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
