package com.zo.todoapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "tasks")
@Data
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String user;
  private String task;
  private Date due_date;

}
