package br.com.banco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="conta")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Long id;

    @Column(name = "nome_responsavel", nullable = false)
    private String name;
}
