package org.hrmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Iterator;


@Getter @Setter

public final class Employee implements Iterable<Employee> {
    private Long id;
    private String firstName;
    private String lastName;

    private String mail;
    private String password;


    @Override
    public Iterator<Employee> iterator() {
        return null;
    }
}
