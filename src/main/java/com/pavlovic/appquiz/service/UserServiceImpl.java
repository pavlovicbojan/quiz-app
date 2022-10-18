package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Role;
import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(int brojStranice) {
        return userRepository.findAll(PageRequest.of(brojStranice,10));
    }

    @Override
    public User save(User user) {
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findbyUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

//    @Override
//    public boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto) {
//        Optional<Korisnik> rezultat = userRepository.findById(id);
//
//        if(!rezultat.isPresent()) {
//            throw new EntityNotFoundException();
//        }
//
//        Korisnik korisnik = rezultat.get();
//
//        if(!korisnik.getKorisnickoIme().equals(korisnikPromenaLozinkeDto.getKorisnickoIme())
//                || !korisnik.getLozinka().equals(korisnikPromenaLozinkeDto.getLozinka())){
//            return false;
//        }
//
//        // dodatak za zadatak 2
//        String password = korisnikPromenaLozinkeDto.getLozinka();
//        if (!korisnikPromenaLozinkeDto.getLozinka().equals("")) {
//            password = passwordEncoder.encode(korisnikPromenaLozinkeDto.getLozinka());
//        }
//
//        korisnik.setLozinka(password);
//
//        userRepository.save(korisnik);
//
//        return true;
//    }
}
