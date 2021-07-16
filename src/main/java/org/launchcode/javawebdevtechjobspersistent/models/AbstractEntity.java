package org.launchcode.javawebdevtechjobspersistent.models;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

// Added annotation for superclass and identity field so the subclasses also inherit the
//the properties of the superclass.

@MappedSuperclass

public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int id;

    //Added validation annotations so that user can't leave field blank
    @Size(min = 1, max = 100)

    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}