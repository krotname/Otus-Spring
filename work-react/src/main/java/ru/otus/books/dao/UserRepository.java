package ru.otus.books.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.books.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByLogin(String login);
}
