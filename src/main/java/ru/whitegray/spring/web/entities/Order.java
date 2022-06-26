package ru.whitegray.spring.web.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "comment")
    private String comment;

    public Order(Long id, String title, String text, String comment) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.comment = comment;
    }

    public Order(String title, String text, String comment) {
        this.title = title;
        this.text = text;
        this.comment = comment;
    }
}
