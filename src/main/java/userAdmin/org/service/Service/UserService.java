package userAdmin.org.service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import userAdmin.org.service.Model.User;
import userAdmin.org.service.Repository.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public List<User> ViewAllUsers(){
        return iUserRepository.findAll();
    }

    public Optional<User> FindUserById(Integer id){
        return iUserRepository.findById(id);
    }

    public String activateUser(Integer userId) {
        
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " doesn't exist"));
        if(user.isEnabled()){
            
            throw new UsernameNotFoundException(userId + " Already Active!");
        }
        user.setEnable(true);;
        iUserRepository.save(user);

        return "Activation Success";
    }

    public String deactivateUser(Integer userId) {

        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " doesn't exist"));
        if(!user.isEnable()){
            throw new UsernameNotFoundException(userId + " Already Inactive!");
        }
        user.setEnable(false);
        iUserRepository.save(user);

        return "Deactivation Success";
    }
















    // @Autowired
    // private ISignUpRepository _ISignUpRepository;
    // @Autowired
    // private ILoginRepository _ILoginRepository;

    // public SignUp AddUserBySignUp(SignUp _SignUp){
    //     //_SignUp.setPassword(passwordEncoder.encode(_SignUp.getPassword()));
    //     return _ISignUpRepository.save(_SignUp);
    // }

    // public List<SignUp> ViewAllUsers(){
    //     return _ISignUpRepository.findAll();
    // }

    // public SignUp FindUserById(int id){
    //     return _ISignUpRepository.findUserById(id);
    // }

    // public SignUp userLogin(Login _Login){
    //     SignUp existingUser = _ISignUpRepository.findByUserName(_Login.getUserName());
    //     return existingUser;
    // }
    
}