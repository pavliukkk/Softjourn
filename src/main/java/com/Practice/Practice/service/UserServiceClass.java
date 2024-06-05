package com.Practice.Practice.service;

import com.Practice.Practice.entities.User;
import com.Practice.Practice.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClass {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User updatedUser) {
        // Перевіряємо, чи існує користувач з таким id
        User existingUser = userRepository.findById(updatedUser.getId()).orElse(null);

        // Якщо користувач існує, оновлюємо його дані
        if (existingUser != null) {
            existingUser.setSurname(updatedUser.getSurname());
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            // і т.д. для інших полів

            // Зберігаємо оновленого користувача в базі даних
            return userRepository.save(existingUser);
        } else {
            // Якщо користувач не знайдений, можна викинути виняток або обробити ситуацію інакше
            return null;
        }
    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        User existingUser = (User)session.load(User.class, user.getId());
        existingUser.setSurname(user.getSurname());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        session.update(existingUser);
    }
}
