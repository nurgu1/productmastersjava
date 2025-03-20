package org.example;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder

public class Company {
    public String name;
    private List<Person> employees;
}
