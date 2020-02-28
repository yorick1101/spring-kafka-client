package me.yorick.poc.kafka.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.yorick.poc.kafka.user.Gender;

@Data
@NoArgsConstructor
public class User {
    private String id;
    private String name;
    private int age;
    private Gender gender;
}
