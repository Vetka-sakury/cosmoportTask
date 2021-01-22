package com.space.model;



import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)

public class ShipEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    public ShipEntity(Long id) {
        this.id=id;
    }

    public ShipEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                '}';
    }

}
